$(document).ready(function() {
	document.getElementById("emailId").value = localStorage.getItem("emailId");
	document.getElementById("ticketType").value = localStorage.getItem("ticketType");
	document.getElementById("uComments").value = localStorage.getItem("u_comments");
});

function updateTicket(){
	var ticketId = localStorage.getItem("tid");
	var emailId = document.getElementById("emailId").value ;
	var ticketType = document.getElementById("ticketType").value;
	var comments = document.getElementById("uComments").value;
	var uJson=
	{	
		userId:localStorage.getItem("userId"),		
		emailId: emailId,
		ticketType:ticketType,
		status:1,
		u_comments:comments,
		ticketNumber:localStorage.getItem("ticketNumber")
		 
	}

		$.ajax({
		url: "/updateTicket/"+ticketId,//uat      
		type: "PUT",
		data: JSON.stringify(uJson),
		contentType: "application/json; charset=utf-8",
		processData: true,
		success: function(result) {
		
			if (result === "Success") {	
			window.location.href="viewIssue.html";		
			}
		},
		cache: false
	});	
}

