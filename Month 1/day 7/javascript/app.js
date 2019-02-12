(function() {
  'use strict';

  // Do not write anything above this line and do not delete it


  // This is our note pad for testing JS
  // Write your code here 
  
  window.alert('Welcome interns');
  console.log('We will use console api for logging purposes');

  // Do not write anything below this line and do not delete it

}());
function myFunction() {
 var amount = prompt("Please enter amount");
 var rate = prompt("Please enter rate");
 var years = prompt("Please enter year");
 if (amount != null && rate!=null && years!=null) {
	var simple_intrest =(amount*rate*years)/100;
   document.getElementById("demo").innerHTML =
   "simple interest " + simple_intrest;
 }

}
function IsPalindrome() {
  var return_var;
  var str = prompt("Please enter string");
if(str!=null){
  var len = Math.floor(str.length / 2);
  for (var i = 0; i < len; i++)
    if (str[i] !== str[str.length - i - 1])
      return_var= false;
else
  return_var= true;
  document.getElementById("demo").innerHTML = return_var;
	}
}
function Area() {
  var rad = prompt("Please enter Radius");
if(rad!=null){
  var area = 3.14*(rad*rad);
  document.getElementById("demo").innerHTML = "Area of circle  "+ area;
	}
}
function show() {
var object1 = {
  a: 1,
  b: 2,
  c: 3
};
var object2 = Object.assign({c: 4, d: 5}, object1);

console.log(object2.c, object2.d);

}
function employee() {
  'use strict';
	var Employee=[{name:"manav",age:20,salary:6000,dob:"6 may 1998"},
			 {name:"aadi",age:19,salary:400,dob:"16 feb 1998"},
			 {name:"anubhav",age:19,salary:3000,dob:"21 june 1999"},
			 {name:"akash",age:10,salary:2000,dob:"26 may 1988"}];
	for(var key in Employee)
		document.write("Name: "+Employee[key].name+" Age: "+Employee[key].age+" Salary: "+Employee[key].salary+" Date of Birth: "+Employee[key].dob+"<br><br>");
	document.write("<hr>");
	document.write("salary more then 5000 <br>");
	for(var key in Employee)
	{if((Employee[key].salary)>5000)
		document.write(Employee[key].name);
	}
	document.write("");
	for(var key in Employee)
	{if((Employee[key].age)>18)
		document.write(Employee[key].name+" is older then 18 <br>");
	}
	document.write("<hr>");
	for(var key in Employee)
	{if((Employee[key].age)<=18)
		document.write(Employee[key].name+" is younger then 18 <br>");
	}
  
  document.write("<hr>");
	for(var key in Employee)
	{if((Employee[key].age)<20 && (Employee[key].salary)<1000 )
  {document.write(Employee[key].name+" is younger then 20 and also salary is less then 1000<br>");
    Employee[key].salary=Employee[key].salary*4;
   document.write("after increment salary will be :"+Employee[key].salary);
  }
	}
}

