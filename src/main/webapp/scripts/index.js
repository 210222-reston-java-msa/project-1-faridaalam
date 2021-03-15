function signIn() {
	
    
    let email = document.getElementById('email').value;
    let pWord = document.getElementById('pWord').value;

    let loginTemplate = {
        email: email,
        password: pWord
    };


   
    var xhr = new XMLHttpRequest();
    // 2. xhr.onreadystatechange
    xhr.onreadystatechange = function() {
        
        if (this.readyState === 4 && this.status === 200) {
            
            sessionStorage.setItem('currentUser', this.responseText)
            let direct = JSON.parse(this.responseText);
            
            if(direct.role === 1){
                 window.location = "home.html";
            }
            else if (direct.role === 2){
                  window.location = "management.html";
            }
         

            
        }

        if (this.readyState === 4 && this.status === 204) { 



            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username or Password is incorrect"
        }
    }
    
    
    xhr.open("POST", "login", true);
    xhr.setRequestHeader('Content-Type', 'Application/json');
    
    xhr.send(JSON.stringify(loginTemplate));

    }
    
function signUp(){
   window.location = "signup.html";
} 
    
    
    document.addEventListener('DOMContentLoaded', function () {
  document.querySelector('#clickMe').addEventListener('click',signIn);
  });
    
        document.addEventListener('DOMContentLoaded', function () {
  document.querySelector('#clickMe2').addEventListener('click',signUp);
  });
    
    