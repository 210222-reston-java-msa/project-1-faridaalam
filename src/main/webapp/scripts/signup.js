function signUp() {
	

    let first_name = document.getElementById('firstname').value;
    let last_name = document.getElementById('lastname').value;
    let e_mail = document.getElementById('email').value;
    let pWord = document.getElementById('pWord').value;

    let user = {
        
        firstName: first_name,
        lastName: last_name,
        email: e_mail,
        password: pWord
    }


   
    var xhr = new XMLHttpRequest();
    // 2. xhr.onreadystatechange
    xhr.onreadystatechange = function() {
        
        if (this.readyState === 4 && this.status === 200) {

            sessionStorage.setItem('currentUser', this.responseText)

            window.location = "home.html";
        }

        if (this.readyState === 4 && this.status === 204) { 

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = document.getElementById('email').value + " already exist in the database.";
            
        }
    }
    
    
    xhr.open("POST", "signup", true);
    xhr.setRequestHeader('Content-Type', 'Application/json');
    
    xhr.send(JSON.stringify(user));

    }
    
function back(){
   window.location = "index.html";
} 
    
    
    document.addEventListener('DOMContentLoaded', function () {
  document.querySelector('#clickMe').addEventListener('click',signUp);
  });
    
        document.addEventListener('DOMContentLoaded', function () {
  document.querySelector('#clickMe2').addEventListener('click',back);
  });
    
    