$(document).ready(function() {
	document.getElementById("emailId").value = localStorage.getItem("emailId");
	document.getElementById("ticketType").value = localStorage.getItem("ticketType");
	document.getElementById("uComments").value = localStorage.getItem("u_comments");
	if(localStorage.getItem("a_comments")==="null"){
		document.getElementById("aComments").value = "";
	}
	else{
		document.getElementById("aComments").value = localStorage.getItem("a_comments")
	}
	if(localStorage.getItem("status")==="1"){
		document.getElementById("status").value = "Open";
	}
	else{
		document.getElementById("status").value = "Closed";
	}
});

function updateTicket(){
	var ticketId = localStorage.getItem("tid");
	var emailId = document.getElementById("emailId").value ;
	var ticketType = document.getElementById("ticketType").value;
	var comments = document.getElementById("uComments").value;
	var acomments = document.getElementById("aComments").value;
	var status=1;
	if(document.getElementById("status").value==="Open")
	{
		status=1
	}
	else{
		status=2
	}
	var uJson=
	{	
		userId:localStorage.getItem("userId"),		
		emailId: emailId,
		ticketType:ticketType,
		status:status,
		u_comments:comments,
		a_comments:acomments,
		ticketNumber:localStorage.getItem("ticketNumber")
		 
	}
	var url= "http://localhost:8080/updateTicket/"+ticketId;//uat
	console.log(url);
	console.log(uJson);	
		$.ajax({
		url: "/updateTicket/"+ticketId,//uat      
		type: "PUT",
		data: JSON.stringify(uJson),
		contentType: "application/json; charset=utf-8",
		processData: true,
		success: function(result) {
		
			if (result === "Success") {	
			window.location.href="viewTicketsAdmin.html";		
			}
		},
		cache: false
	});	
}

