/**
 * @author pierc
 */


//determine the max number of elements per radius. [1 4 8] means 1 element in the inner circle, 4 in the second, etc.
			function RaC(q){//returns an array with the number of elements to put in a determined radius
				var sum=[1];
				var totalSum=1;
				var i=2;
				while(totalSum<q){
					if(q==1){
						return sum;
					}
					else{
							sum[i-1]=Math.pow(2,i);
							totalSum+=sum[i-1];
							i+=1;
					}
				}return sum;
			}

			
//determine the position of each element
			function PoE(nElements, offset){
				var position=[];
				var elements=RaC(nElements);//  [ 1, 4, 8, 16 ]

				var Alpha;
				var px;
				var py;
				var sum;
				var Multiplier=0; //in index of what element is being process during the current circle
				var index=0;	//a index of which circle we are currently at
				for(i=0;i<nElements;i++){ 
					Alpha=2*Math.PI/elements[index];
					sum=0;
					for(j=0;j<=index;j++){ //sums the possible elements in all teh current circles
						sum+=elements[j];
					}
					if (i+1>sum){//next circle?
						index+=1;
						Multiplier=0;
					}
					px=offset*index*Math.cos(Multiplier*Alpha);
					py=offset*index*Math.sin(Multiplier*Alpha);
					position[i]=[px, py];
					Multiplier+=1;
				}
				return position;
			}
			
//[Individual Coordinates Graph] calculates the coordinates for each group of bubbles
			function IcG(coor,radio){
				longIndex=0;
				valuesX=[];
				valuesY=[];
				valuesR=[];
				valuesC=[];
				for(i=0;i<coor.length;i++){
					//first small bubble
					valuesX[longIndex]=coor[i][0]-radio;
					valuesY[longIndex]=coor[i][1]+(coor[i][1]/100);
					valuesR[longIndex]=radio/3;
					valuesC[longIndex]="rgb(190,137,74)";
					longIndex+=1;
					//second small bubble
					valuesX[longIndex]=coor[i][0]-(0.7*radio);
					valuesY[longIndex]=coor[i][1]-(0.7*radio);
					valuesR[longIndex]=radio/3;
					valuesC[longIndex]="rgb(255,128,0)";
					longIndex+=1;
					//third small bubble
					valuesX[longIndex]=coor[i][0]+(coor[i][0]/100);
					valuesY[longIndex]=coor[i][1]-radio;
					valuesR[longIndex]=radio/3;
					valuesC[longIndex]="rgb(1,160,198)";
					longIndex+=1;
					//fourth small bubble
					valuesX[longIndex]=coor[i][0]+(0.7*radio);
					valuesY[longIndex]=coor[i][1]-(0.7*radio);
					valuesR[longIndex]=radio/3;
					valuesC[longIndex]="rgb(128,196,28)";
					longIndex+=1;
					//large bubble
					valuesX[longIndex]=coor[i][0];
					valuesY[longIndex]=coor[i][1];
					valuesR[longIndex]=radio;
					valuesC[longIndex]="rgb(255,255,255)";
					longIndex+=1;
				}
				return [valuesX, valuesY, valuesR, valuesC];
			}
			function rSize(levels){//returns the maximum radio of a bubble for an specific number of levels.
				var wid = window.innerWidth;
    			var hei = window.innerHeight;
    			var nRadios=levels*8+2;
    			if (hei<wid){//horizontal
    				return hei/nRadios;
    			}else{
    				return wid/nRadios;
    			}
    			
    			
    			
			}
			
