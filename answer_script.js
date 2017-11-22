var stackArray=[]; //global array for stack wala question
var i=0; //global counter also the stack pointer
var bubbleArray=[]; //array for bubble Sort

function validate(){
	var form1=document.getElementById('f1');
	for(i=0;i<5;i++){ //iterate through all form1 elements
		if(form1.elements[i].value==""){ 
			alert("One or more fields are empty");
			break;
		}
	}
}
function check_roll(roll){
	var len=roll.toString().length //convert to string and find length
	var pat=/\./;
	if(len!=7){
		alert("Roll number must be 7 digits.")
	}
	else if(pat.test(roll)) //must not contain decimal point
		alert("Cannot Conatin dot "); 
}
function check_name(name){
	if(name.replace(/\s+/g, '').length<4) //remove white spaces and check length
		alert("Minimum 4 characters");
	pat=/[^a-zA-Z]/; //find anything except a-zA-Z
	if (result=name.match(pat))
		 alert("Found unacceptable character: "+result);
}
function staticPasswordProtection(p){
	window.pass=p;//made it global to access in next function
	if(pass.length<6)
		alert("Atleast 6 characters required");
 	var pat=/((^[0-9]+[a-z]+)|(^[a-z]+[0-9]+))+[0-9a-z]+$/i; //allow only alphanumeric
	if(!pass.match(pat))
		alert("Must be Alphanumeric")
}
function confirmPassword(cpass){
	if(cpass!=pass)
		alert("Passwords do not match");
}
function printDOB(mydob){
	var days= new Array(7);
	days[0] =  "Sunday";days[1] = "Monday";days[2] = "Tuesday";days[3] = "Wednesday";
	days[4] = "Thursday";days[5] = "Friday";days[6] = "Saturday";
	var months=new Array(12);
	months[0]="January";months[1]="February";months[2]="March";months[3]="April";months[4]="May";months[5]="June";
	months[6]="July";months[7]="August";months[8]="September";months[9]="October";months[10]="November";months[11]="December";
	var parsedDate = Date.parse(mydob);
	var d = new Date(parsedDate);
	var dayInWords=days[d.getDay()];
	var monthInWords=months[d.getMonth()];
	var birthday=dayInWords+", "+monthInWords+" "+d.getDate()+", "+d.getFullYear();
	if(birthday=="Thursday, July 3, 1997") alert("Amazing! It's Kumar Prateek's Birthday"); //easter-egg
	document.getElementById('birthdayBash').innerHTML="So, Your Birthday is on "+birthday;
}
function storeIt(element){
 bubbleArray[i]=element;
	 var unsortedList=document.getElementById('unsorted');
	 unsortedList.innerHTML+=bubbleArray[i++]+", ";//append data to unsorted span
}
function bubbleSort(){ 
	for(i=0;i<bubbleArray.length;i++){
		for(j=0; j<bubbleArray.length;j++){
			if(parseInt(bubbleArray[i])<parseInt(bubbleArray[j])){//is parseInt() really necessary? -IDTS.
				var temp=bubbleArray[i];
				bubbleArray[i]=bubbleArray[j];
				bubbleArray[j]=temp;
			}
		}
	}
	for(i=0;i<bubbleArray.length;i++)
		document.getElementById('sorted').innerHTML+=bubbleArray[i]+", ";
}
function pushIt(toPush){
	if (toPush=="") 
		alert("Nothing to push");
	else{
		stackArray[i]=toPush;
		window.stack=document.getElementById('stackTable');
		var row=stack.insertRow(0);
		row.innerHTML=stackArray[i++]; //append to stackTable
	}
}
function popIt(){
  if(i<1) 
  	alert("Stack is already empty");
  else{
  	alert("Element popped: "+stackArray[--i]);	
  	stack.deleteRow(0);
  }
}

function changeColor(){
	var sample=document.getElementById('sample');
	sample.style.color="red";
	sample.innerHTML+="Yay!! Code-Red";
}

function startMoving(){
	var elem=document.getElementById('myimage');
	var pos = 40;
  var id = setInterval(moveRight, 10);
  function moveRight() {
    if (pos ==450 ) {
		pos=0;
	 	moveRight();
    }
    else{
    	pos++; 
      	elem.style.left = pos + 'px'; 
    } 
  }
}

function validation(){
	var form=document.getElementById('f6');
	var radio=document.getElementsByName('person');
	var person;
	var flag=0;
	if(radio[0].checked == true)
		person="Teacher";
		else if(radio[1].checked == true)
		person="Student";
		else alert("Teacher/Student? What are you?");
	var lang=document.getElementsByName('lang');
	for(i=0;i<4;i++){
		if(lang[i].checked==true){
			flag=1;
			break;
		}	
	}if(flag==0) alert("You must select one language!");
	var feedback = document.getElementsByName('feedback');
	var values = feedback.value; 
		if(values == null)
		alert("Must give feedback!");
	else alert("Submitted!")
}
