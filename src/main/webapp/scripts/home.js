// capture the welcome element and modofy it so that it says welcome + username

	let dropDown = document.getElementById('dropdown07');
	
	// capture the userString by accessing the session.....
	let userString = sessionStorage.getItem('currentUser');
	
	// set up some logic....
	// IF the user is null....redirect them to the index.html page
	if (userString === null) {
		window.location = "index.html";
	} else {
		
		let currentUser = JSON.parse(userString); // parse the data that we se == to that attribute
		
		console.log(currentUser);
		
		if (currentUser != null) {
			
			dropDown.innerHTML = currentUser.firstName+ " " + currentUser.lastName;
		}
		
	}

	
	function logout() {
		
		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "logout");
		xhr.send();
		
		sessionStorage.removeItem('currentUser');
		window.location = "index.html";
		
	}


// add-remove form1 to the page
var menuHidden = true;
	function add(){
		if (menuHidden){
			//document.querySelector('#expense').style.visibility = 'visible';
			document.querySelector('#form1').innerHTML = f1;
			showImgName();
			menuHidden=false;
			document.querySelector('#submitF1').addEventListener('click',sendF1);
			document.querySelector('#cancelF1').addEventListener('click',add);
		}else{
			document.querySelector('#form1').innerHTML = "";
			menuHidden = true;
			}

		}
var img;
function sendF1(){

	var amnt= document.getElementById('amount').value;
	var nte = document.getElementById('note').value;

	const rbs = document.querySelectorAll('input[name="ExpenseType"]');
	let selectedValue;
	for (const rb of rbs) {
		if (rb.checked) {
			selectedValue = rb.value;
			break;
		}
	}
  
	
  var expenseTemplate = {
	  amount: amnt,
	  note: nte,
	  type: selectedValue,
	  image: img
  };
	
  
  let req = new XMLHttpRequest();
  req.onreadystatechange = stateChng;
  req.open('POST','addExpense',true);
  req.setRequestHeader('content', 'application/JSON');
  req.send(JSON.stringify(expenseTemplate));
  img="";

  function stateChng(){
	if (this.readyState === 4 && this.status === 200) {
		console.log("stateCHG");
	add();
	document.getElementById('expenseAdded').style.visibility='visible';
	setTimeout(() => {  
		document.getElementById('expenseAdded').style.visibility='hidden';
	 }, 3000);
	 loadTable();
	}
	else if (this.status === 401)
	{	window.location= "index.html";
	} else if (this.status ===403){
		window.location = "management.html";
	}
}
}
  
document.getElementById('expenseAdded').style.visibility='hidden';

function loadTable(){

	let req = new XMLHttpRequest();
	req.onreadystatechange = stateChng;
	req.open('Get','readExpEmp',true);
	req.send(null);

  
	function stateChng(){
	  if (this.readyState === 4 && this.status === 200) {
		
		document.getElementById('tableHolder').innerHTML = this.responseText;
		addEventToViewBtn();		
	  }
	  else if (this.status === 401)
	  {	window.location= "index.html";
	  } else if (this.status ===403){
		  window.location = "management.html";
	  }
  }
}
function addEventToViewBtn(){

document.querySelectorAll(".tblC").forEach( input => input.addEventListener('click', changeView) );


}		

function changeView(){
	//console.log("event click");
	//console.log( document.querySelector('.tblC:checked').value );

 var selected = document.querySelector('.tblC:checked').value;
var trPending = document.querySelectorAll(".rowPending");
var trApproved = document.querySelectorAll(".rowApproved");
var trDenied = document.querySelectorAll(".rowDenied");

	if (selected ==1 ){
		console.log("all trpending");
		console.log(trPending[0]);
		trPending.forEach(input => input.style.display= 'table-row');
		trApproved.forEach(input => input.style.display= 'table-row');
		trDenied.forEach(input => input.style.display= 'table-row');

	} else if (selected ==2){
		console.log("pending");
		trPending.forEach(input => input.style.display= 'table-row');
		trApproved.forEach(input => input.style.display= 'none');
		trDenied.forEach(input => input.style.display= 'none');

	}else if (selected ==3){
		trPending.forEach(input => input.style.display= 'none');
		trApproved.forEach(input => input.style.display= 'table-row');
		trDenied.forEach(input => input.style.display= 'none');
		
	}else if (selected ==4){
		trPending.forEach(input => input.style.display= 'none');
		trApproved.forEach(input => input.style.display= 'none');
		trDenied.forEach(input => input.style.display= 'table-row');
		
	}

/* 	var allRows = document.querySelectorAll("#rowID[style.display='table-row']");

	for(var i = 0 ; i <allRows.length ; i++){
		if ((i%2)==0) {
			allRows[i].style.backgroundColor = '#ffe0cc';
		}else
		{
			allRows[i].style.backgroundColor = '#ffb380';
		} 
	} */
 
}


document.addEventListener('DOMContentLoaded', function () {
	document.querySelector('#logOut').addEventListener('click',logout);
		});

document.addEventListener('DOMContentLoaded', function () {
	document.querySelector('#add').addEventListener('click',add);
		});

// get form1
var f1 = '';
		function makeHttpObject() {
			try {return new XMLHttpRequest();}
			catch (error) {}
			try {return new ActiveXObject("Msxml2.XMLHTTP");}
			catch (error) {}
			try {return new ActiveXObject("Microsoft.XMLHTTP");}
			catch (error) {}
		  
			throw new Error("Could not create HTTP request object.");
		  }
		  
		  var request = makeHttpObject();
		  request.open("GET", "form1.html", true);
		  request.send(null);
		  request.onreadystatechange = function() {
			if (request.readyState == 4)
			  f1= request.responseText;
		  };

/// form1 ^

/*  ==========================================
    SHOW UPLOADED IMAGE
* ========================================== */
function readURL(input) {
	encodeImageFileAsURL(input);
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imageResult')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* $(function () {
    $('#upload').on('change', function () {
        readURL(input);
    });
}); */

function encodeImageFileAsURL(element) {
	var file = element.files[0];
	var reader = new FileReader();
	reader.onloadend = function() {
	  img= (reader.result);
	}
	reader.readAsDataURL(file);
  }
/*  ==========================================
    SHOW UPLOADED IMAGE NAME
* ========================================== */
function showImgName(){
var input = document.getElementById( 'upload' );


input.addEventListener( 'change', showFileName );
}
function showFileName( event ) {
  var infoArea = document.getElementById( 'upload-label' );
  var input = event.srcElement;
  var fileName = input.files[0].name;
  infoArea.textContent = 'File name: ' + fileName;
}

loadTable();