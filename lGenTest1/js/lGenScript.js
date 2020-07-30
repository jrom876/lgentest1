
    var vlcntr	= 0;    // variable counter
    var vllimit = 0;         // variable counter limit, set by user    
	var arr = [1, 3, 5, 7, 9, 16];
// nullarray
    var mcnNull = []; 
// >8
    var mcnLow	= [1, 3, 4, 7, 11, 13, 16, 17, 20, 21, 22, 23, 26, 27, 33, 34, 35, 38, 39, 41]; 
// >9
    var mcnMed = [1, 3, 4, 7, 13, 20, 21, 26, 27, 33, 34, 38, 39];
// >10
    var mcnHigh	= [1, 4, 20, 21, 26, 27, 34];
function sort(values) {
  var length = values.length;
  for (var i = 1; i < length; ++i) {
    var temp = values[i];
    var j = i - 1;
    for(; j >= 0 && values[j] > temp; --j) {
      values[j+1] = values[j];
    }
    values[j+1] = temp;
  }
  return values; 
}
 
function clearArr()	{
	var n = "";
	document.getElementById("demo0").innerHTML = n;
	document.getElementById("demo1").innerHTML = n;
	document.getElementById("demo2").innerHTML = n;
	document.getElementById("demo3").innerHTML = n;
	document.getElementById("demo4").innerHTML = n;
	document.getElementById("demo5").innerHTML = n;
	document.getElementById("demo6").innerHTML = n;
	document.getElementById("demo7").innerHTML = n;
	document.getElementById("demo8").innerHTML = n;
	document.getElementById("demo9").innerHTML = n;	
}
	
function lGenRand(){
	clearArr();
	var text = "Please enter a valid number";
	var i;
	var inpArr = [];
	var num = document.getElementById("numTicks").value;
	for(i=0; i<num; i++){		
        inpArr[i] = genRand(6);
	}
	if (num<1 || num>10) {document.getElementById("demo0").innerHTML = text;}
	else if (num == 1){document.getElementById("demo0").innerHTML = inpArr[0];}
	else if (num == 2){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];}
	else if (num == 3){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];}
	else if (num == 4){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];}
	else if (num == 5){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];}
	else if (num == 6){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];
		document.getElementById("demo5").innerHTML = inpArr[5];}
	else if (num == 7){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];
		document.getElementById("demo5").innerHTML = inpArr[5];
		document.getElementById("demo6").innerHTML = inpArr[6];}
	else if (num == 8){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];
		document.getElementById("demo5").innerHTML = inpArr[5];
		document.getElementById("demo6").innerHTML = inpArr[6];
		document.getElementById("demo7").innerHTML = inpArr[7];}
	else if (num == 9){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];
		document.getElementById("demo5").innerHTML = inpArr[5];
		document.getElementById("demo6").innerHTML = inpArr[6];
		document.getElementById("demo7").innerHTML = inpArr[7];
		document.getElementById("demo8").innerHTML = inpArr[8];}
	else if (num == 10){
		document.getElementById("demo0").innerHTML = inpArr[0];
		document.getElementById("demo1").innerHTML = inpArr[1];
		document.getElementById("demo2").innerHTML = inpArr[2];
		document.getElementById("demo3").innerHTML = inpArr[3];
		document.getElementById("demo4").innerHTML = inpArr[4];
		document.getElementById("demo5").innerHTML = inpArr[5];
		document.getElementById("demo6").innerHTML = inpArr[6];
		document.getElementById("demo7").innerHTML = inpArr[7];
		document.getElementById("demo8").innerHTML = inpArr[8];
		document.getElementById("demo9").innerHTML = inpArr[9];};
	
    return inputArr;
}
	
function genRand(size){ 	
    var   a = [0];// Holds the lotto numbers we pick 
	var   values = [];// A working register to hold number data
	var flag;
    var   d;
	vlcntr = 0;
    var   i = 0; // counter
	while (i < size)
        {   
        // Make sure there are no duplicate numbers
            flag = false;                
            values[i] = 1 + Math.floor(Math.random() * 42);// Colorado Lotto
            for(d=0; d<i; d++)
            {
                if(a[d] == values[i])
                {
                    flag = true;
                    break;
                }
            }
        // Copy array and increment counter if no duplicates are in the array
            if(flag == false)
            {
                a[i] = values[i];
                i++;
            } 
        // Make sure there are enough MCNs in the array. If not enough MCNs 
        // are in the array, we reset the counter and repeat the while loop.
            if (i == size)
            { 
				compWMCN(a, mcnLow); // returns vlcntr
                if (vlcntr < document.getElementById("numMCNs").value)
                {
                    i = 0;
                    vlcntr = 0;  
                }
            }           
        } 
	sort(values);
	return values+ "     MCN: " + vlcntr;
}

function compWMCN(a,m){
	var c = 0;
	vlcntr = 0;
	var cnt = 0;
	var mcnLen = m.length;
	var aLen = a.length;
	for (cnt = 0; cnt < aLen; cnt++){
		for (c = 0; c < mcnLen; c++){
			if (a[cnt] == m[c])vlcntr++;
		}
	}
	return vlcntr;
}
