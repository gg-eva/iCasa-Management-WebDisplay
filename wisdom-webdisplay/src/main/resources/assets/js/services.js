
function servs(){
	//document.getElementById("services").innerHTML = "";
	var margin = {top: 20, right: 300, bottom: 230, left: 90},
	  width = PageWidth - margin.left - margin.right,
	  height = PageHeight - margin.top - margin.bottom;
	// declares a tree layout and assigns the size
	var treemap = d3.tree()
	  .size([height, width]);

    //console.log("-------------------------services bgn V1--------------------------------------------------");

var apps=[];

    function urlResponse(bar){
        apps=bar;
        //console.log(apps);
    }

    ajaxGET("http://localhost:9000/apps");

    function ajaxGET(url){
        $.ajax({url: url, type: "GET"}).done(function(resource){
            document.getElementById('footer').innerHTML = "<button style=\"background-color:#2a2a2a border: none; margin: 0px 2px;\" class=\"button\" type=\"button\" onclick=\"ajaxGETshow('http://localhost:9000/apps');\">apps</button>";
            for (var i =0;i<resource.length;i++){
                if(resource[i].registered==true){
                    document.getElementById('footer').innerHTML += "<button style=\"background-color:olive; color: white; border: none; margin: 0px 2px;\" class=\"button\" type=\"button\" onclick=\"ajaxGETshow('http://localhost:9000/apps/toggle/"+resource[i].app_name+"');\">"+resource[i].app_name+"</button>";
                }else{
                    document.getElementById('footer').innerHTML += "<button style=\"background-color:brown ; color: white; border: none; margin: 0px 2px;\" class=\"button\" type=\"button\" onclick=\"ajaxGETshow('http://localhost:9000/apps/toggle/"+resource[i].app_name+"');\">"+resource[i].app_name+"</button>";
                }

            }
            urlResponse(resource);
        });
    }
}


servs();


$("#btnid").on('keyup', function (e) {
    if (e.keyCode == 13) {
        var jobValue = "http://localhost:9000/env/buttons/add/"+e.target.value;

        ajaxGET(jobValue);
    }
});

$("#lightid").on('keyup', function (e) {
    if (e.keyCode == 13) {
        var jobValue = "http://localhost:9000/env/lights/add/"+e.target.value;

        ajaxGET(jobValue);
    }
});

$("#zone").on('keyup', function (e) {
    if (e.keyCode == 13) {
        var jobValue = "http://localhost:9000/env/zones/add/"+e.target.value;

        ajaxGET(jobValue);
    }
});

$("#loczone").on('keyup', function (e) {
    if (e.keyCode == 13) {
        var jobValue = "http://localhost:9000/env/locations/add/"+e.target.value;

        ajaxGET(jobValue);
    }
});


