function setActiveLink(fragmentId){
			  var navbarDiv = document.getElementById("mainNav"),
			      links = navbarDiv.children,
			      i, link, pageName;
			      console.log(links);
			  for(i = 0; i < links.length; i++){
			    link = links[i];
			    pageName = link.getAttribute("href").substr(1);
			    if(pageName === fragmentId) {
			      link.setAttribute("class", "active");
			     // link.setAttribute("style", "display:block");
			    } else {
			      link.removeAttribute("class");
			     // link.setAttribute("style", "display:none");
			    }
			  }
			  switch(fragmentId){
					case "runtime":
						svg1.style("display", "block");
						svg2.style("display", "none");
						svg3.style("display","none");
					break;
					
					case "context":
						svg1.style("display", "none");
						svg2.style("display", "block");
						svg3.style("display","none");
					break;
					
					case "services":
						svg1.style("display","none");
						svg2.style("display","none");
						svg3.style("display","block");
					break;
					
					default:
					
				}
			}
			
			if(!location.hash){
				location.hash="runtime";
			}
			navigation();
			function navigation(){
				fragmentId = location.hash.substr(1);
				
				setActiveLink(fragmentId);
				
				if(fragmentId=="runtime"){
					//divShow();
				}
			}
			
			window.addEventListener("hashchange",navigation);
