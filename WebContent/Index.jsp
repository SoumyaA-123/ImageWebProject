<%@ page language="java"%>
<!--
user name : sapna123
password : 12345
 -->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<script>
	var check = function() {
		if (document.getElementById('passwordnew').value == document
				.getElementById('passwordnewconfirm').value) {
			document.getElementById('message').style.color = 'white';
			document.getElementById('message').innerHTML = 'matching';
			return true;
		} else {
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML = 'Password does not match';
			return false;
		}
	}
</script>

<body>
	<div class="container" align="center">

		<h2>Image Utility Management</h2>

		<form action="Login" method="post">
			<div>
				<label for="username">Enter Username:</label> <input type="text"
					id="username" placeholder="Enter username" name="username">
			</div>
			<div>
				<label for="pwd">Enter Password:</label> <input type="password"
					id="password" placeholder="Enter password" name="password">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>

		<%
			if (session.getAttribute("authorized") != null && session.getAttribute("authorized").equals("false")) {
		%>
		<p
			style="color: crimson; text-align: center; font-weight: bold; font-family: Arial, Helvetica, sans-serif;">Invalid
			credentials</p>
		<%
			session.setAttribute("authorized", null);
		}
		%>

		<button style="float: right" type="button" class="btn btn-info a-btn-slide-text" data-toggle="modal" data-target="#editModal">
		Reset Password</button>
		<!-- Modal -->
		<div class="modal fade" id="editModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Reset Password</h4>
					</div>
					<div>
						<form action="ResetPassword" method="post" onsubmit="return check()">
							<input type="text" name="username" placeholder="Confirm Username" required>
							 <br /> 
							 <input type="text" name="fullname" placeholder="Confirm Full Name" required> 
							 <br /> 
							 <input type="password" name="passwordnew" id="passwordnew" placeholder="New Password" onkeyup='check();' required>
							 <br /> 
							 <input type="password" name="passwordnewconfirm" id="passwordnewconfirm" placeholder="Confirm new password" onkeyup='check();' required> 
							 <br /> 
							 <input type="submit" value="Change Password">
							 <hr />
							 <span id='message'><%=session.getAttribute("message")%></span>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		if (session.getAttribute("passwordmessage") != null) {
	%>
	<p
		style="color: blue; text-align: center; font-weight: bold; font-family: Arial, Helvetica, sans-serif;">
		<%=session.getAttribute("passwordmessage")%></p>
	<%
		session.setAttribute("passwordmessage", null);
	}
	%>
<!-- 
	<div>
		<a href="Register.jsp">Register</a>
	</div>
 -->
</body>

</html>
