<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nagarro.assignment.model.User"%>
<%@page import="com.nagarro.assignment.model.Images"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>

	<div>
		<div>
			<a href="#">Image Management</a>
		</div>
		<%
			if (session.getAttribute("user") == null) {
			response.sendRedirect("Index.jsp");

		}
		String fullName = "";

		User user = (User) session.getAttribute("user");
		try {
			fullName = user.getFullname();
		} catch (Exception e) {

			response.sendRedirect("Index.jsp");

		}
		%>
		<h6>
			Hello
			<%=fullName%>
		</h6>
		<a href="Logout.jsp"><span class="glyphicon glyphicon-share"></span>
			Logout</a>
	</div>
	<div class="container">
		<form action="ImageUpload" method="post" enctype="multipart/form-data">
			<input type="text" name="imagename" required> <br /> 
			<input type="file" name="image" accept="image/*" required> <br />
			<input type="submit" value="Upload">
			<hr />
		</form>
		<%
			if (session.getAttribute("message") != null) {
			String message = session.getAttribute("message").toString();
		%>
		<p>
			<%=message%></p>
		<%
			}
		%>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4>Images Available</h4>
				<div class="table-responsive">
					<table id="mytable" class="table table-bordred table-striped">
						<thead>
							<th>Image Id</th>

							<th>Image</th>
							<th>Image Name</th>
							<th>Image Size</th>
							<th>Edit</th>
							<th>Delete</th>
						</thead>
						<!-- code for loop the list of images -->
						<%
							Collection<Images> images = user.getImageList();
						int i = 0;
						if (!images.isEmpty()) {
							for (Images image : images) {
								i++;
						%>
						<!-- row of table -->
						<tr>
							<!-- row structure -->
							<td>
								<!-- id of the image --> <%=i%>
							</td>
							<td>
								<!-- image preview --> 
								<img
								src="ImageRetriever?imageId=<%=image.getImageId()%>" height="99"
								width="99"></img>


							</td>

							<td>
								<!-- name of the image --> <%=image.getImageName()%>
							</td>
							<td>
								<!-- size of the image --> <%=image.getImageSize()%>
							</td>
							<td>
								<!-- preview of the image --> <!-- button for edit -->
								<button type="button"
									class="glyphicon glyphicon-edit btn btn-primary a-btn-slide-text"
									data-toggle="modal" data-target="#editModal<%=image.getImageId() %>">Edit</button>
									 <!-- Modal -->
								<!-- Model for edit image -->
								<div class="modal fade" id="editModal<%=image.getImageId() %>" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Edit Image</h4>
											</div>
											<div class="modal-body">
												<form action="ImageEdit?imageId=<%=image.getImageId()%>"
													method="post" enctype="multipart/form-data">
													<input type="text" name="imagenamenew"
														placeholder="Name of image"
														value=<%=image.getImageName()%> required> <br />
													<input type="file" name="imagenew" accept="image/*"
														required> <br /> <input type="submit"
														value="Upload">
													<hr />
												</form>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>


							</td>
							<td>
								<!-- edit upload new image -->

							</td>
							<td>
								<!-- edit name of the image -->
							</td>
							<td><a href="ImageDelete?imageid=<%=image.getImageId()%>"
								class="btn btn-primary a-btn-slide-text"> <span
									class="glyphicon glyphicon-remove" aria-hidden="true"></span> <span><strong>Delete</strong></span>
							</a></td>



						</tr>
						<%
							}
						}
						%>
						<!-- end of the loop -->
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
</body>
</html>