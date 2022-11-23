

function login(){
	document.getElementById("error").innerHTML="";
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	if(email.length==0 || email.length == ""){
        document.getElementById("error").innerHTML="Please enter your Email Id";
        return 0;
    }
     if(password.length==0 || password.length == ""){
        document.getElementById("error").innerHTML="Please enter your password";
        return 0;
    }
    
    var uJson= 
        {
            emailId:email,
            password:password

        }    

        console.log(uJson);
        
       $.ajax({      
        url: "/validateUser",//uat      
        type: "POST",
        data: JSON.stringify(uJson),
        contentType: "application/json; charset=utf-8",
        processData: true,
        success: function(result){
            console.log(result);
             if(result.status==="SUCCESS"){
				localStorage.setItem("emailId",email);
				localStorage.setItem("userId",result.id);
				if(result.userType==0){
                 window.location.href="viewissue.html";
                 }
                 else{
				window.location.href="viewTicketsAdmin.html";
				}
            }
             else{
                document.getElementById("error").innerHTML="Incorrect email or password provided";
             }
        },
          cache: false
    });
}

function registerUser(){
    document.getElementById("error").innerHTML="";
    var name = document.getElementById("signupUsername").value;
    var email = document.getElementById("email").value;
    var mobileNumber = document.getElementById("phno").value;
    var password = document.getElementById("password").value;
    var confPassword = document.getElementById("confirmPassword").value;
    
    if(email.length==0 || email.length == ""){
        document.getElementById("error").innerHTML="Please enter your Email Id";
        return 0;
    }
    if(name.length==0 || name.length == ""){
        document.getElementById("error").innerHTML="Please enter your User Name";
        return 0;
    }

    if(mobileNumber.length==0 || mobileNumber.length==" "){
        document.getElementById("error").innerHTML="Please enter your mobile number";
        return 0;
      }
    if(mobileNumber.length!=10 || mobileNumber.length==" "){
        document.getElementById("error").innerHTML="Please enter a valid mobile number";
        return 0;
      }
    if(password.length==0 || password.length == ""){
        document.getElementById("error").innerHTML="Please enter your password";
        return 0;
    }
    if(confPassword.length==0 || confPassword.length == ""){
        document.getElementById("error").innerHTML="Please re-enter your password for confirmation";
        return 0;
    }
    if(password!==confPassword){
        document.getElementById("error").innerHTML="Your passwords do not match";
        return 0;
    }

    var uJson= 
        {
            emailId:email,
            name:name,
            phoneNumber:mobileNumber,
            password:password,
            userType:1
        }    

       // console.log(uJson);
        
       $.ajax({      
        url: "/registerUser",//uat      
        type: "POST",
        data: JSON.stringify(uJson),
        contentType: "application/json; charset=utf-8",
        processData: true,
        success: function(result){
            console.log(result);
             if(result.status==="SUCCESS"){
                alert("The user registration complete");
                 window.location.href="index.html";
            }
             else{
                document.getElementById("error").innerHTML="User is already Registered";
             }
        },
          cache: false
    });
}


