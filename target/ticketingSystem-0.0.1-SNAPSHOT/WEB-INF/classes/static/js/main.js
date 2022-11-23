function registerUser(){
    document.getElementById("error").innerHTML="";
    var name = document.getElementById("signupUsername").value;
    var email = document.getElementById("email").value;
    var mobileNumber = document.getElementById("phno").value;
    var password = document.getElementById("password").value;
    var confPassword = document.getElementById("confirmPassword").value;
    if(name.length==0 || name.length == ""){
        document.getElementById("error").innerHTML="Please enter your User Name";
        return 0;
    }
    if(email.length==0 || email.length == ""){
        document.getElementById("error").innerHTML="Please enter your Email Id";
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
        url: "http://localhost:8080/registerUser",//uat      
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


