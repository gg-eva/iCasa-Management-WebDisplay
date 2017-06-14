function runtime(){
	document.getElementById("runtime").innerHTML = "";
	//variable to be change for an input variable
	var numberOfbubbles=50;			
	
	var bubbleRadius=rSize(RaC(numberOfbubbles).length);
	var bubbleSeparation=bubbleRadius*4;
	var coordinates=IcG(PoE(numberOfbubbles,bubbleSeparation),bubbleRadius);
	
	var bubbleSize=0;
	/* creation of empty variables to store the position of every bubble, Big and Small,
	position: X and Y Color and Size*/
	var BdataX = [], BdataY = [], BdataC = [], BdataS = [], 
	SdataX = [], SdataY = [], SdataC = [], SdataS = []; 
	var b=0, s=0;
	//clean data for double layer draw
	for(i=0;i<coordinates[0].length;i++){
		if((i+1)%5==0 ){
			//console.log(i+": "+dataS[i]);
			BdataX[b] = coordinates[0][i];
			BdataY[b] = coordinates[1][i];
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
			
	drawC(SdataX,SdataY,SdataC,SdataS);
	drawC(BdataX,BdataY,BdataC,BdataS);
	
	function drawC(x,y,color,size){
		var ordOmain = ["A","ZZZ"];
		var scaleX = d3.scaleOrdinal()
		  .domain(ordOmain) //data
		  .range(x);	//screen range (pixels?)
		var scaleY = d3.scaleOrdinal()
		  .domain(ordOmain)
		  .range(y);
			
		var colors = d3.scaleOrdinal()
		  .domain(ordOmain)
		  .range(color);
			  
		var sizes = d3.scaleOrdinal()
		  .domain(ordOmain)
		  .range(size);
			
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
			//svg.selectAll("path")
			//		.attr()
					}
					
					
		
/* data TEST start */
/*			var dataX = [41, 68, 131.131, 194, 131];
			var dataY = [135.135, 72, 45, 72, 135];
			var dataC = ["rgb(190,137,74)", "rgb(255,128,0)", "rgb(1,160,198)", "rgb(128,196,28)", "rgb(255,255,255)"];
			var dataS = [30,30,30,30,90];
			ordOmain = ["A","B","C","D","E"];
			/* data TEST end */
			
			/*draw TEST start*/
	/*		var scaleX = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(dataX);
			var scaleY = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(dataY);
			
			var colors = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(dataC);
			  
			var sizes = d3.scaleOrdinal()
			  .domain(ordOmain)
			  .range(dataS);
			/*draw TEST end*/
	/*		
var rect2 = svg.append("svg").selectAll("circle")
				.data(dataX)
				.enter().append("circle")
				.attr("cx",scaleX)
					.attr("cy", scaleY)
					.attr("r", sizes)
					.attr("height", 20)
					.attr("fill", colors)
					.attr("stroke", "black")
					.attr("stroke-width", 1);
			svg.selectAll("path");*/
	}
	
runtime();
