
	let dropDown = document.getElementById('dropdown07');
	
	let userString = sessionStorage.getItem('currentUser');


	if (userString === null) {
		window.location = "index.html";
	} else {
		
		let currentUser = JSON.parse(userString); 
		
		console.log(currentUser);
		
		if (currentUser != null) {
			
			dropDown.innerHTML = currentUser.firstName+ " " + currentUser.lastName;
			document.getElementById('firstname').value = currentUser.firstName;
			document.getElementById('lastname').value = currentUser.lastName;
			document.getElementById('email').value = currentUser.email;
		}
		
	}

	
	
	function logout() {
		
		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "logout");
		xhr.send();
		
		sessionStorage.removeItem('currentUser');
		window.location = "index.html";
		
	}


	function update() {
	

		let first_name = document.getElementById('firstname').value;
		let last_name = document.getElementById('lastname').value;
		let e_mail = document.getElementById('email').value;
		let pWord = document.getElementById('pWord').value;
		let newpassWord1 = document.getElementById('newpWord1').value;	
		let newpassWord2 = document.getElementById('newpWord2').value;	
		
		let user = {
			
			firstName: first_name,
			lastName: last_name,
			email: e_mail,
			password: pWord,
			newPword1 : newpassWord1,
			newPword2 : newpassWord2
		}
	
	
	   
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			
			if (this.readyState === 4 && this.status === 200) {
	
				sessionStorage.setItem('currentUser', this.responseText)
	
				window.location = "home.html";
			}
	
			if (this.readyState === 4 && this.status === 204) { 
	
				let childDiv = document.getElementById('warningText');
				childDiv.textContent = "Wrong Password!";
				
			}
		}
		
		
		xhr.open("POST", "editprofile", true);
		xhr.setRequestHeader('Content-Type', 'Application/json');
		
		xhr.send(JSON.stringify(user));
	
		}
		
	function back(){
	   window.location = "home.html";
	} 
		
		
		document.addEventListener('DOMContentLoaded', function () {
	  document.querySelector('#clickMe').addEventListener('click',update);
	  });
		
			document.addEventListener('DOMContentLoaded', function () {
	  document.querySelector('#clickMe2').addEventListener('click',back);
	  });
		
		