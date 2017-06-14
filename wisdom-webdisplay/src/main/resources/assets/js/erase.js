/**
 * Created by pierc on 14-Jun-17.
 */
document.write("Test fro external script.");
var numberOfbubbles=18;
//console.log();
var bubbleRadius=rSize(RaC(numberOfbubbles).length);

var bubbleSeparation=bubbleRadius*4;
var coordinates=IcG(PoE(numberOfbubbles,bubbleSeparation),bubbleRadius);
console.log(coordinates);

//console.log(bubbleRadius);
var coordinates=IcG(PoE(numberOfbubbles,bubbleSeparation),bubbleRadius);
console.log(coordinates);

var BdataX = [], BdataY = [], BdataC = [], BdataS = [],
    SdataX = [], SdataY = [], SdataC = [], SdataS = [];
var b=0, s=0;

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
var div = d3.select("body").append("div")
    .attr("class","contextMap")
    .style('position','absolute');
var svg = div.append("svg")
    .attr("width", 250)
    .attr("height", 200);

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


    var wid = window.innerWidth/2;
    var hei = window.innerHeight/2;

    var g = svg.append("g")
        .attr("transform", "translate("+wid+","+hei+")");

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