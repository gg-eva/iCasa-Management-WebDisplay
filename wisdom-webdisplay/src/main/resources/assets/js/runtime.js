function runtime(){
	document.getElementById("runtime").innerHTML = "";

    $.ajax({url: "http://localhost:9000/manager/context", type: "GET"}).done(function(resources){
        NuberOfCtxtElements=resources.length;
        var numberOfbubbles=resources.length;
		console.log()
		var bubbleRadius=rSize(RaC(numberOfbubbles).length);
		var bubbleSeparation=bubbleRadius*5;
		var coordinates=IcG(PoE(numberOfbubbles,bubbleSeparation),bubbleRadius);

		var bubbleSize=0;
		/* creation of empty variables to store the position of every bubble, Big and Small,
		position: X and Y Color and Size*/
		var BdataX = [], BdataY = [], TdataX = [],TdataY = [],BdataC = [], BdataS = [],
		SdataX = [], SdataY = [], SdataC = [], SdataS = [], Btext = [];

		//gets the text for each bubble
			for(var AA =0; AA<numberOfbubbles; AA++){
			   Btext[AA]= resources[AA]["id"];
			}
			console.log(Btext);

		var b=0, s=0;
		//clean data for double layer draw
		for(i=0;i<coordinates[0].length;i++){
			if((i+1)%5==0 ){
				//console.log(i+": "+dataS[i]);
				BdataX[b] = coordinates[0][i];
				BdataY[b] = coordinates[1][i];
                TdataX[b] = coordinates[0][i]-(bubbleRadius*2);
				TdataY[b] = coordinates[1][i]+(bubbleRadius/5*6);
				BdataC[b] = coordinates[3][i];
				BdataS[b] = coordinates[2][i];
				b+=1;
			}else{
				SdataX[s] = coordinates[0][i];
				SdataY[s] = coordinates[1][i];
				SdataC[s] = coordinates[3][i];
				SdataS[s] = coordinates[2][i];
				s+=1;
			}
		}
	
		svg1
		.attr("class","contextMap")
		.style('position','absolute');
		var svg = svg1.append("svg");

		drawC(SdataX,SdataY,SdataC,SdataS,TdataX,TdataY,"0");
		drawC(BdataX,BdataY,BdataC,BdataS,TdataX,TdataY,Btext);

		function drawC(x,y,color,size,tx,ty,txt){
			var ordOmain = ["A","ZZZ"];
			var scaleX = d3.scaleOrdinal()
			  .domain(ordOmain) //data
			  .range(x);	//screen range (pixels?)
			var scaleY = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(y);

            var TscaleX = d3.scaleOrdinal()
                .domain(ordOmain)
                .range(tx);

            var TscaleY = d3.scaleOrdinal()
                .domain(ordOmain)
                .range(ty);

			var colors = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(color);

			var sizes = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(size);
			console.log(sizes);
var wv= d3.scaleOrdinal()
	.domain(txt)
	.range(txt);

console.log(wv);
			var tnp=bubbleRadius*numberOfbubbles;


			var wid = PageWidth/2;
			var hei = 5*PageHeight/12;

			var g = svg.append("g")
			  .attr("transform", "translate("+wid+","+hei+")");
				console.log(g);
				console.log("break");
				if (bubbleSize=="0"){
					g.attr("class","small");
					bubbleSize+=1;
				}else
				{
					g.attr("class","big");
				}
			g.selectAll("circle")
			  .data(x)
			  .enter().append("circle")
						//.attr("x", function (d){return scale(d);})
			  .attr("cx",scaleX)
			  .attr("cy", scaleY)
			  .attr("r", sizes)
			  .attr("height", 20)
			  .attr("fill", colors)
			  .attr("stroke", "black")
			  .attr("stroke-width", 1);
			if (txt!="0"){
                    g.selectAll("text")
						.data(x)
						.enter().append("text")
                        .attr("x",TscaleX)
                        .attr("y",TscaleY)
                        .attr("dy",".35em")
                        .text(wv);
			}

						}
		});
	}
	
runtime();
