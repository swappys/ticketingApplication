$(document).ready(function() {
	var uId = localStorage.getItem("userId");
	var uJson =
	{
		userId: uId,
	}

	console.log(uJson);

	$.ajax({
		url: "/getTicketsForUser",//uat      
		type: "POST",
		data: JSON.stringify(uJson),
		contentType: "application/json; charset=utf-8",
		processData: true,
		success: function(result) {
			console.log(result.tickets[0].status);
			if (result.status1 === "SUCCESS") {
				if (result.tickets.length > 0) {
					var table = "";
					var statusFinal="Open";
					var adminComments = "<p style='color:red'>No comments from admin</p>";
					var updateButton = "";
					
					for (var i in result.tickets) {
						var ticketId = result.tickets[i].ticketId;
						var button="<td><button type='button' class='btn btn-primary' onClick='updateTicket("+ticketId+")'>Update</button> </td>";
						if(result.tickets[i].status==2){
							statusFinal="Closed"
							 button = "<td><button type='button' class='btn btn-primary' onClick='alert(\"Cannot update as this ticket is closed\")'>Update</button> </td>";
						}
						if(result.tickets[i].a_comments !=null){
							adminComments = result.tickets[i].a_comments 
						}
						table += "<tr style=\'background-color:white\'>";
						table += "<td>" + i + "</td>"
							+ "<td>"+result.tickets[i].ticketNumber + "</td>"
							+ "<td>" + result.tickets[i].emailId + "</td>"
							+ "<td>" + result.tickets[i].ticketType + "</td>"
							+ "<td>" + statusFinal + "</td>"
							+ "<td>" + result.tickets[i].u_comments + "</td>"
							+ "<td>" + adminComments+ "</td>"
							+ button
						table += "</tr>";
						statusFinal="Open";
					 adminComments = "<p style='color:red'>No comments from admin</p>";
					 button="<td><button type='button' class='btn btn-primary' onClick='updateTicket("+ticketId+")'>Update</button> </td>";
					}

					document.getElementById("result").innerHTML = table;

				}
			}
			else {
				document.getElementById("error").innerHTML = "Incorrect email or password provided";
			}
		},
		cache: false
	});
});

function updateTicket(ticketId){
	var tid=ticketId
	var uJson=
	{
		ticketId: tid
	}	
		$.ajax({
		url: "/getTicket",//uat      
		type: "POST",
		data: JSON.stringify(uJson),
		contentType: "application/json; charset=utf-8",
		processData: true,
		success: function(result) {
			if (result.status1 === "SUCCESS") 
			{
			console.log(result)
			localStorage.setItem("emailId",result.tickets[0].emailId);
			localStorage.setItem("ticketType",result.tickets[0].ticketType);			
			localStorage.setItem("u_comments",result.tickets[0].u_comments);
			localStorage.setItem("tid",result.tickets[0].ticketId);
		    localStorage.setItem("ticketNumber",result.tickets[0].ticketNumber);		
			window.location.href="updateIssue.html";		
			}
		},
		cache: false
	});
	
	
}