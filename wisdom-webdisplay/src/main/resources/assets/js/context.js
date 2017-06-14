function context(){
	
			
			
/* data TEST start */
			var infoTxt = ["a","b","c","d","e","f"];
			
			var dataX = [41, 68, 131.131, 194, 131];
			var dataY = [135.135, 72, 45, 72, 135];
			var dataC = ["rgb(190,137,74)", "rgb(255,128,0)", "rgb(1,160,198)", "rgb(128,196,28)", "rgb(255,255,255)"];
			var dataS = [30,30,30,30,90];
			ordOmain = ["A","B","C","D","E"];
			/* data TEST end */
			
			/*draw TEST start*/
			var scaleX = d3.scaleOrdinal()
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
			
var rect2 = svg2.append("svg").selectAll("g circleText")
				.data(dataX)
				.enter().append("circle")
				.attr("cx",scaleX)
					.attr("cy", scaleY)
					.attr("r", sizes)
					.attr("height", 20)
					.attr("fill", colors)
					.attr("stroke", "black")
					.attr("stroke-width", 1);
			svg2.selectAll("path");
			svg2.append("text")
			.attr("dx", function(d){return -20})
	    	.text(function(d){return "d.label"});
			//.text(infoTxt);
	}
	
context();
