
function strip(s){
	s = s.substring(0, s.length-1);
	s = s.substring(1, s.length);
	return s;
}

function is24(v){
	if (Math.abs(24-v)<0.001) return true;
	return false;
}

function valuesFor4numbers(a,b,c,d){
	var ret = [];
	var abc = valuesFor3numbers(a,b,c);
	for(i=0; i < abc.length; i++){
	     var abci = valuesFor2numbers(abc[i], d);
	     for(var j=0; j< abci.length; j++){
	    	 if(!ret.includes(abci[j])){
	    	 	ret.push(abci[j]);
	    	 }
	     }
	  }
	
	var abd = valuesFor3numbers(a,b,d);
	for(i=0; i < abd.length; i++){
	     var abdi = valuesFor2numbers(abd[i], c);
	     for(var j=0; j< abdi.length; j++){
	    	 if(!ret.includes(abdi[j])){
	    	 	ret.push(abdi[j]);
	    	 }
	     }
	  }
	
	var acd = valuesFor3numbers(a,d,c);
	for(i=0; i < acd.length; i++){
	     var acdi = valuesFor2numbers(acd[i], b);
	     for(var j=0; j< acdi.length; j++){
	    	 if(!ret.includes(acdi[j])){
	    	 	ret.push(acdi[j]);
	    	 }
	     }
	  }
	
	
	var bcd = valuesFor3numbers(b,c,d);
	for(i=0; i < bcd.length; i++){
	     var bcdi = valuesFor2numbers(bcd[i], a);
	     for(var j=0; j< bcdi.length; j++){
	    	 if(!ret.includes(bcdi[j])){
	    	 	ret.push(bcdi[j]);
	    	 }
	     }
	  }
	//two in a group case
	
	var ab = valuesFor2numbers(a,b);
	var cd = valuesFor2numbers(c,d);
	
	pushValues(ab,cd, ret);
	ab = valuesFor2numbers(a,c);
	cd = valuesFor2numbers(b,d);

	pushValues(ab,cd, ret);
	ab = valuesFor2numbers(a,d);
	cd = valuesFor2numbers(b,c);

	pushValues(ab,cd, ret);
	return ret;
	
}

function pushValues(ab, cd, ret){
	for(var i=0; i<ab.length; i++){
		for(var j=0; j<cd.length; j++){
			var abcd= valuesFor2numbers(ab[i], cd[j]);
			for(var k=0; k < abcd.length; k++){
				if(!ret.includes(abcd[k])){
					ret.push(abcd[k]);
				}
			}
		}
	}
}
function valuesFor3numbers(a, b, c){
   var ret = [];
   var abValues = valuesFor2numbers(a,b);
   for(i=0; i < abValues.length; i++){
     var abi = valuesFor2numbers(abValues[i], c);
     for(var j=0; j< abi.length; j++){
    	 if(!ret.includes(abi[j])){
    		 ret.push(abi[j]);
    	 }
     }
   }
   
   var acValues = valuesFor2numbers(a,c);
   for(i=0; i < acValues.length; i++){
     var aci = valuesFor2numbers(acValues[i], b);
     for(var j=0; j< aci.length; j++){
    	 if(!ret.includes(aci[j])){
    		 ret.push(aci[j]);
    	 }
     }
   }
   
   var bcValues = valuesFor2numbers(b,c);
   for(i=0; i < bcValues.length; i++){
     var bci = valuesFor2numbers(bcValues[i], a);
     for(var j=0; j< bci.length; j++){
    	 if(!ret.includes(bci[j])){
    		 ret.push(bci[j]);
    	 }
     }
   }
   return ret;
}

function valuesFor2numbers(a, b){
   var ret = [];

   ret.push("("+a+"+"+b+")");
   ret.push("("+a+"-"+b+")");
   if(a!=b){
	   ret.push("("+b+"-"+a+")");
   }
   ret.push("("+a+"*"+b+")");
   if(b!=0){
     ret.push("("+a+"/"+b+")");
   }
   if(a!=0 && a!=b){
	   ret.push("("+b+"/"+a+")");
   }
   return ret;
}
