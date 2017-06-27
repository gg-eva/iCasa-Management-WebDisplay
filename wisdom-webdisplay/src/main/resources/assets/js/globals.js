var svg1 = d3.select("#runtime");
var svg3 = d3.select("#services");
var svg2 = d3.select("#context");
/*			.append("svg")
			.attr("width", 250)
			.attr("height", 200);*/
var PageWidth = window.innerWidth;
var PageHeight = window.innerHeight;
			
//var myVar = setInterval(reDraw, 3000);

function reDraw(){
	PageWidth = window.innerWidth;
	PageHeight = window.innerHeight;
	servs();
	runtime();
};

window.onresize = function (event) {
	reDraw();
};

function sortObj(a,b) {
    if (a.id < b.id)
        return -1;
    if (a.id > b.id)
        return 1;
    return 0;
}