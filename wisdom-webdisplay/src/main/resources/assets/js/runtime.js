function runtime(){
	document.getElementById("runtime").innerHTML = "";

    $.ajax({url: "http://localhost:9000/manager/context", type: "GET"}).done(function(resources){
        var numberOfbubbles=resources.length;
		var bubbleRadius=rSize(RaC(numberOfbubbles).length);
		var bubbleSeparation=bubbleRadius*5;
		var coordinates=IcG(PoE(numberOfbubbles,bubbleSeparation),bubbleRadius);
		var b=0, s=0,Bubbles=[],BubbleBig=[],bubbleSmall=[];
		//fill the bubble info except the coordinates, color and radius.
        for(i=0;i<numberOfbubbles;i++){
            BubbleBig[i]={
                id:resources[i]["id"],
                status:resources[i]["status"],
                functionalCore:resources[i]["functional_core"],
                functionalExtensions:resources[i]["functional_extensions"]
            };
        }
        BubbleBig.sort(sortObj);

        for(i=0;i<coordinates[0].length;i++){
            if((i+1)%5==0 ){//multiples of 5 (big bubble)
                BubbleBig[b].x=coordinates[0][i];
                BubbleBig[b].y=coordinates[1][i];
                BubbleBig[b].color=coordinates[3][i];
                BubbleBig[b].radius=coordinates[2][i];
                BubbleBig[b].xTxt=coordinates[0][i]-(bubbleRadius*2);
                BubbleBig[b].yTxt=coordinates[1][i]-(bubbleRadius/5*6);
                Bubbles[i]=BubbleBig[b];
                b+=1;
            }else if((((i+1)%5)-1)==0){//first small bubble
                bubbleSmall[s]={
                    x:coordinates[0][i],
                    y:coordinates[1][i],
                    color:coordinates[3][i],
                    radius:coordinates[2][i],
                    functionalCore:BubbleBig[b].functionalCore};
                Bubbles[i]=bubbleSmall[s];
                s+=1;
            }else if((((i+1)%5)-2)==0){
                bubbleSmall[s]={
                    x:coordinates[0][i],
                    y:coordinates[1][i],
                    color:coordinates[3][i],
                    radius:coordinates[2][i]};
                if (BubbleBig[b].functionalExtensions.length==0){
                    bubbleSmall[s].color="rgb(81,81,81)";
                }else{
                    bubbleSmall[s].functionalExtensions=BubbleBig[b].functionalExtensions
                }

                Bubbles[i]=bubbleSmall[s];
                s+=1;
            }else if((((i+1)%5)-3)==0){
                bubbleSmall[s]={
                    x:coordinates[0][i],
                    y:coordinates[1][i],
                    color:coordinates[3][i],
                    radius:coordinates[2][i]
                };

                Bubbles[i]=bubbleSmall[s];
                s+=1;
            }
            else if((((i+1)%5)-4)==0){
                bubbleSmall[s]={
                    x:coordinates[0][i],
                    y:coordinates[1][i],
                    color:coordinates[3][i],
                    radius:coordinates[2][i]
                };

                Bubbles[i]=bubbleSmall[s];
                s+=1;
            }
        }

		svg1
		.attr("class","contextMap")
		.style('position','absolute');
		var svg = svg1.append("svg");
        tip = d3.tip().attr('class', 'd3-tip').html(function(d) {
            //console.log(d);
            if (d.color=="rgb(190,137,74)"){//brown
                var mystring = d.functionalCore.services.toString();
                var spaced=mystring.replace(/,/g , "<br>");
                return "Functional Core<br>"+"name: <span style='color:red'>" + d.functionalCore.name+"</span>"+
                    "<br>services: <span style='color:red'>"+spaced+"</span>";
            }
            if(d.color=="rgb(255,128,0)"){//orange
                return "Functional Extensions<br>"+"id: <span style='color:red'>" + d.functionalExtensions[0].id+"</span>"+
                    "<br>status: <span style='color:red'>"+d.status+"</span>";
            }
            if(d.color=="rgb(1,160,198)"){//blue
                return "Use<br>"+"id: <span style='color:red'>" + d.id+"</span>"+
                    "<br>status: <span style='color:red'>"+d.status+"</span>";
            }
            if(d.color=="rgb(128,196,28)"){//green
                return "Origin<br>"+"id: <span style='color:red'>" + d.id+"</span>"+
                    "<br>status: <span style='color:red'>"+d.status+"</span>";
            }
            if(d.color=="rgb(255,255,255)"){//white
                return "id: <span style='color:red'>" + d.id+"</span>"+
                    "<br>status: <span style='color:red'>"+d.status+"</span>";
            }//else{functionalExtensions}

        });
        svg.call(tip);

		drawC(Bubbles);

		function drawC(data){
			var wid = PageWidth/2;
			var hei = 5*PageHeight/12;
            var screenElem=svg.selectAll("g bubbleText")
                .data(data,function(d){/*console.log(d);*/ } );
            var elemEnter = screenElem.enter()
                .append("g")
                .attr("transform", function(d){return "translate("+(d.x+wid)+","+(d.y+(hei*3/4))+")";});
            var circle = elemEnter.append("circle")
                .attr("r", function(d){return d.radius;} )
                .attr("stroke","black")
                .attr("fill", function(d){return d.color;})
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide);
                elemEnter.append("text")
                    .style('fill', 'darkOrange')
                    .text(function(d){return d.id;});
            circle.on("click", function(d){
                console.log(d);
                document.getElementById('infop').innerHTML = '<div class="idtitle">id: '+d.id+'</div>';
                d3.select(this)
                    .transition()
                    .duration(500)
                    .style("background","lightblue");
            })
						}
		});
	}
	
runtime();