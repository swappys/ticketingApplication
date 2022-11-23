$(document).ready(function() {
	var emailId = localStorage.getItem("emailId");
	document.getElementById("emailId").value = emailId;

});
function makeid(length) {
	var result = '';
	var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var charactersLength = characters.length;
	for (var i = 0; i < length; i++) {
		result += characters.charAt(Math.floor(Math.random() * charactersLength));
	}
	return result;
}


function raiseTicket() {
	document.getElementById("error").innerHTML = "";
	var user_Id = localStorage.getItem("userId");
	var email = document.getElementById("emailId").value;
	var ticket_type = document.getElementById("ticketType").value;
	var comments = document.getElementById("uComments").value;

	if (comments.length == 0 || comments.length == "") {
		document.getElementById("error").innerHTML = "Please enter the comments";
		return 0;
	}
	var ticketNo = makeid(5)
	var uJson =
	{
		userId: user_Id,
		emailId: email,
		ticketType: ticket_type,	
		status: 1,
		u_comments: comments,
		ticketNumber:ticketNo

	}

	console.log(uJson);

	$.ajax({
		url: "/createTicket",//uat      
		type: "POST",
		data: JSON.stringify(uJson),
		contentType: "application/json; charset=utf-8",
		processData: true,
		success: function(result) {
			console.log(result);
			if (result.status1 === "SUCCESS") {
				
				window.location.href = "viewissue.html";
			}
			else {
				document.getElementById("error").innerHTML = "There was an error creating ticket";
			}
		},
		cache: false
	});
}