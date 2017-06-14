//from JSON file to data
//this uses the library d3 to import the JSON data

//*************************************************************
//*****************START OF TEMPORAL PERT**********************
var margin = {top: 20, right: 300, bottom: 230, left: 90},
	  width = PageWidth - margin.left - margin.right,
	  height = PageHeight - margin.top - margin.bottom;

var treemap = d3.tree()
	  .size([height, width]);

//*************************************************************
//********************END OF TEMPORAL PART*********************



var HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() { 
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open( "GET", aUrl, true );            
        anHttpRequest.send( null );
    }
}


var client = new HttpClient();
client.get('http://localhost:9000/manager/context', function(response) {
    // do something with response
    console.log(response);
});


var NumberOfNodes;
//first part is to import the data
var fruits = ["Banana", ["Orange", "Apple"], "Mango"];

console.log(fruits[1].length);
 d3.json("../data/runtime.json", function(error, treeData) {
	  if (error) throw error;
	
	  //  assigns the data to a hierarchy using parent-child relationships
	  var nodes = d3.hierarchy(treeData, function(d) {
	    return d.children;
	    });
	    NumberOfNodes=nodes.children.length;
	    console.log("**# of nodes: "+NumberOfNodes);
	    
	    for(i=0; i<NumberOfNodes; i++){
	    	console.log(nodes.children[i].data.id);
	    	console.log(nodes.children[i].data.name);
	    	console.log(nodes.children[i].data.source);
	    	console.log(nodes.children[i].data.interfaces);
	    	console.log(nodes.children[i].data.connectionsIDs.length);
	    }
	    //console.log	(nodes);
	    //console.log	(nodes.descendants().slice(1));
	  // maps the node data to the tree layout
	  nodes = treemap(nodes);
	  
	  /*svg3.append("svg")
	      .attr("width", width + margin.left + margin.right)
	      .attr("height", height + margin.top + margin.bottom),*/
	    g = svg3.append("svg").append("g")
	      .attr("transform",
	        "translate(" + margin.left + "," + margin.top + ")");
	        
	  // adds the links between the nodes
	  var link = g.selectAll(".link")
	    .data( nodes.descendants().slice(1))
	    .enter().append("path")
	    .attr("class", "link")
	    .attr("d", function(d) {
	       return "M" + d.y + "," + d.x
	       + "C" + (d.y + d.parent.y) / 2 + "," + d.x
	       + " " + (d.y + d.parent.y) / 2 + "," + d.parent.x
	       + " " + d.parent.y + "," + d.parent.x;
	       });
	          // adds each node as a group
	  var node = g.selectAll(".node")
	    .data(nodes.descendants())
	    .enter().append("g")
	    .attr("class", function(d) { 
	      return "node" + 
	      (d.children ? " node--internal" : " node--leaf"); })
	    .attr("transform", function(d) { 
	      return "translate(" + d.y + "," + d.x + ")"; });
	
	  // adds the circle to the node
	  node.append("circle")
	    .attr("r", 10);
	
	  // adds the text to the node
	  node.append("text")
	    .attr("dy", ".35em")
	    .attr("x", function(d) { return d.children ? -13 : 13; })
	    .style("text-anchor", function(d) { 
	    return d.children ? "end" : "start"; })
	    .text(function(d) { return d.data.name; });
	});