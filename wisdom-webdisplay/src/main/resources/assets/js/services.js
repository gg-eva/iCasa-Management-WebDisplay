servs();
function servs(){
	//document.getElementById("services").innerHTML = "";
	var margin = {top: 20, right: 300, bottom: 230, left: 90},
	  width = PageWidth - margin.left - margin.right,
	  height = PageHeight - margin.top - margin.bottom;
	// declares a tree layout and assigns the size
	var treemap = d3.tree()
	  .size([height, width]);
	/*  d3.json("/manager/contex", function(error, treeData) {
	  if (error) throw error;

	  //  assigns the data to a hierarchy using parent-child relationships
	  var nodes = d3.hierarchy(treeData, function(d) {
	    return d.children;
	    });
	    console.log("nodes_____________________________________");
	    console.log	(nodes);
	    console.log("descendands_______________________________");
	    console.log	(nodes.descendants().slice(1));
	  // maps the node data to the tree layout
	  nodes = treemap(nodes);

	  /*svg3.append("svg")
	      .attr("width", width + margin.left + margin.right)
	      .attr("height", height + margin.top + margin.bottom),*/
	 /*   g = svg3.append("svg").append("g")
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
	});*/
}