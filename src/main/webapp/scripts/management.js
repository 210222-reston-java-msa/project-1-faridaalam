

	let dropDown = document.getElementById('dropdown07');
	
	let userString = sessionStorage.getItem('currentUser');
	
	
	if (userString === null) {
		window.location = "index.html";
	} else {
		
		let currentUser = JSON.parse(userString);
		
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


function loadTable(){

	let req = new XMLHttpRequest();
	req.onreadystatechange = stateChng;
	req.open('Get','readAllExp',true);
	req.send(null);

  
	function stateChng(){
	  if (this.readyState === 4 && this.status === 200) {
		
		document.getElementById('tableHolder').innerHTML = this.responseText;
		addEventToViewBtn();		
	  }
	  else if (this.status === 401)
	  {	window.location= "index.html";
	  } else if (this.status ===403){
		  window.location = "home.html";
	  }
  }
}
function addEventToViewBtn(){

document.querySelectorAll(".tblC").forEach( input => input.addEventListener('click', changeView) );


}		

function changeView(){
	
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


 
}


document.addEventListener('DOMContentLoaded', function () {
	document.querySelector('#logOut').addEventListener('click',logout);
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


function approve(id){
	
	var exp ={
		expId : id
	}
	document.addEventListener("DOMContentLoaded", function(event) {
		var b = document.getElementById(`${id}`);
		b[0].disabled = true;
		b[1].disabled = true;
	
	  });	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = stateChng;
	xhr.open('POST','approve',true);
	
	xhr.setRequestHeader('Content-Type', 'Application/json');
    
    xhr.send(JSON.stringify(exp));

  
	function stateChng(){
	  if (this.readyState === 4 && this.status === 200) {
		
			
	  }
	  else if (this.status === 401)
	  {	window.location= "index.html";
	  } else if (this.status ===403){
		  window.location = "home.html";
	  }
  }
}


function deny(id){
	
	var exp ={
		expId : id
	}
	//document.querySelectorAll('#' +id).forEach(input => input.disabled =true);
	document.addEventListener("DOMContentLoaded", function(event) {
		var b = document.getElementById(`${id}`);
		b[0].disabled = true;
		b[1].disabled = true;
	
	  });
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = stateChng;
	xhr.open('POST','deny',true);
	
	xhr.setRequestHeader('Content-Type', 'Application/json');
    
    xhr.send(JSON.stringify(exp));

  
	function stateChng(){
	  if (this.readyState === 4 && this.status === 200) {
		
			
	  }
	  else if (this.status === 401)
	  {	window.location= "index.html";
	  } else if (this.status ===403){
		  window.location = "home.html";
	  }
  }
}