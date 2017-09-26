var svg1 = d3.select("#runtime");
var svg3 = d3.select("#services");
var svg2 = d3.select("#context");
/*			.append("svg")
			.attr("width", 250)
			.attr("height", 200);*/
var PageWidth = window.innerWidth;
var PageHeight = window.innerHeight;

var myVar = setInterval(reDraw, 3000);

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

function urlResponse(bar){
    //test1=bar;
    console.log(bar);
}



function ajaxGET(url){
    $.ajax({url: url, type: "GET"}).done(function(resource){
        urlResponse(resource);
    });
}

function ajaxGETshow(url){
    $.ajax({url: url, type: "GET"}).done(function(resource){
        var mesOut="";
        for(var t=0;t<resource.length;t++){
           // alert(mesOut);
            mesOut = mesOut + resource[t].app_name +": "+resource[t].registered+"; ";
            console.log(resource[t].app_name +resource[t].registered);
        }
        alert(mesOut);
        urlResponse(resource);
    });
}