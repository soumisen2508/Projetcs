<%@page import="com.project.mytechblog.dao.LikeDao"%>
<%@page import="com.project.mytechblog.dao.UserDao"%>
<%@page import="com.project.mytechblog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.project.mytechblog.entities.Post"%>
<%@page import="com.project.mytechblog.helper.ConnectionProvider"%>
<%@page import="com.project.mytechblog.dao.PostDao"%>
<%@page import="com.project.mytechblog.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page errorPage = "error_page.jsp" %>
<%
    User user = (User)session.getAttribute("currentUser");
    if(user==null){
    	response.sendRedirect("login_page.jsp");
    }
    %>
<%
int postId = Integer.parseInt(request.getParameter("post_id"));
PostDao dao = new PostDao(ConnectionProvider.getConnection());
Post p = dao.getPostByPostId(postId);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= p.getPostTitle() %></title>
<!--css-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.banner-background {
clip-path: polygon(38% 0, 70% 0%, 100% 0, 100% 87%, 79% 98%, 52% 89%, 0 100%, 0 0);
}
.post-title{
font-weight: 100;
}
.post-content{
font-weight: 100;
font-size:20px;
}
</style>
</head>
<body>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v18.0" nonce="r2Qzys2G"></script>
<!-- navbar -->

<nav class="navbar navbar-expand-lg navbar-dark primary-background">
  <a class="navbar-brand" href="index.jsp"><span class = "fa fa-dashboard"></span>  Tech Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="profile.jsp"><span class = "fa fa-universal-access"></span>  LearnCode With Soumi <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <span class = "fa fa-check-square-o"></span>   Categories
        </a>
         <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <%
       PostDao postd = new PostDao(ConnectionProvider.getConnection());
       ArrayList<Category>list = postd.getAllCategories();
       for(Category c:list)
       {
       %>
       <a class="dropdown-item" href="#"><%= c.getCategoryName() %></a>
       <div class="dropdown-divider"></div>
       
       <%
       }
       %>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><span class = "fa fa-address-card-o"></span>  Contact</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" data-toggle = "modal" data-target = "#add-post-modal"><span class = "fa fa-caret-square-o-right"></span>  Post</a>
      </li>
    </ul>
    <ul class="navbar-nav mr-right">
    <li class="nav-item">
        <a class="nav-link"  href = "#!"data-toggle = "modal" data-target = "#profile-modal"><span class = "fa fa-user-circle"></span>  <%= user.getUserName() %></a>
      </li>
    <li class="nav-item">
        <a class="nav-link" href="LogoutServlet"><span class = "fa fa-sign-out"></span>  Logout</a>
      </li>
    </ul>
  </div>
</nav>

<!-- end of navbar -->

<!-- main content of the body -->

<div class = "container">
<div class = "row my-4">
<div class = "col-md-8 offset-md-2">
<div class = "card">
<div class = "card-header primary-background text-white">
<h4 class = "post-title"><%= p.getPostTitle() %></h4>
</div>
<div class = "card-body">
<p class = "post-content"><%= p.getPostContent() %></p>
<br>
<br>
<div class = "post-code">
<code><%= p.getPostCode() %></code>
</div>
<div class= "row my-3">
<div class = "col-md-6">
<% UserDao ud = new UserDao(ConnectionProvider.getConnection()); %>
<p style = "font-style:italic; font-weight:bold;font-size:20px">Author: <%= ud.getUserByUserId(p.getUserId()).getUserName() %></p>
</div>
<div class = "col-md-6">
<p style = "font-style:italic;font-weight:bold;font-size:20px">Posted On: <%= p.getPostDate().toLocaleString()%></p>
</div>
</div>
</div>
<div class = "card-footer">
<%
LikeDao likeDao = new LikeDao (ConnectionProvider.getConnection());

%>
<a href = "#!" onclick = "insertLike (<%= p.getPostId()%>, <%= user.getUserId()%>)" class= "btn btn-outline-success btn-sm"><i class = "fa fa-thumbs-o-up"></i> <span class = "like-counter"><%= likeDao.countLikeOnPost(p.getPostId()) %></span></a>
<a href = "#!" class = "btn btn-outline-success btn-sm"><i class = "fa fa-commenting-o"></i> <span>20</span></a>
<div class="fb-page" data-href="http://localhost:8080/mytechblog/show_blog_page.jsp?post_id=6" data-tabs="timeline" data-width="" data-height="" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"></div>
</div>
</div>
</div>
</div>
</div>

<!-- end of main content of the body -->

<!-- add post modal -->

<!-- Modal -->
<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog" aria-labelledby="addPostModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header primary-background text-white">
        <h5 class="modal-title" id="exampleModalLabel">Provide Post Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form id = "add-post-form" action = "AddPostServlet" method = "post">
       <div class = "form-group">
       <select name = "cId"class = "form-control">   
       <option selected disabled>---Select Post Category---</option>
       
       <%
       PostDao postDao = new PostDao(ConnectionProvider.getConnection());
       ArrayList<Category> list2 = postDao.getAllCategories();
       for(Category c:list2)
       {
       %>
       
       <option value = "<%= c.getCategoryId() %>"><%= c.getCategoryName() %></option>
       
       <%
       }
       %>
       </select>
       </div>
       <div class = "form-group">
       <input name = "postTitle" type = "text" placeholder = "Enter Post Title" class = "form-control">
       </div>
       <div class = "form-group">
       <textarea name = "postContent" class = "form-control"rows="3" placeholder = "Enter Post Content"></textarea>
       </div>
       <div class = "form-group">
       <textarea name = "postCode" class = "form-control"rows="3" placeholder = "Enter Your Program Code (if any)"></textarea>
       </div>
       <div class = "form-group">
       <label>Select Your Photo (if any)</label>
       <br>
       <input name = "postPic" type = "file">
       </div>
       <div class="container text-center">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn primary-background text-white">Post</button>
        </div>
       </form>
      </div>
      </div>
    </div>
  </div>
</div>

<!-- end of add post modal -->
<!-- profile modal -->

<!-- Modal -->
<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header primary-background text-white text-center">
        <h5 class="modal-title" id="exampleModalLabel">Tech Blog</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <div class = "container text-center">
      <img src ="profile pics/<%= user.getUserProfile()%>" class = "img-fluid" style="border-radius:50%; max-width:150px;">
      <br>
        <h5 class="modal-title " id="exampleModalLabel"><%= user.getUserName() %></h5>
        <!-- details -->
        <div id="profile-details">
        <br>
        <table class="table">
  <tbody>
    <tr>
      <th scope="row">ID</th>
      <td><%= user.getUserId() %></td>
    </tr>
    <tr>
      <th scope="row">Email Address</th>
      <td><%= user.getUserEmail() %></td>
    </tr>
    <tr>
      <th scope="row">Status</th>
      <td><%= user.getUserAbout() %></td>
    </tr>
    <tr>
      <th scope="row">Registered on: </th>
      <td><%= user.getRegDate().toString()%></td>
    </tr>
  </tbody>
</table>
</div>

<!-- edit profile -->

<div id = "profile-edit" style = "display:none">
<h4 class = "mt-2">Please Edit Carefully</h3>
<form action = "EditServlet" method = "post" enctype = "multipart/form-data">
<table class = "table">
<tr>
<td>ID : </td>
<td><%= user.getUserId() %></td>
</tr>
<tr>
<td>Email : </td>
<td><input type = "email" class = "form-control" name = "user_email" value = "<%= user.getUserEmail() %>"></td>
</tr>
<tr>
<td>Name : </td>
<td><input type = "text" class = "form-control" name = "user_name" value = "<%= user.getUserName()%>"></td>
</tr>
<tr>
<td>Password : </td>
<td><input type = "password" class = "form-control" name = "user_password" value = "<%= user.getUserPassword()%>"></td>
</tr>
<tr>
<td>Status : </td>
<td><textarea rows = "4"class = "form-control" name = "user_about"><%= user.getUserAbout()%></textarea></td>
</tr>
<tr>
<td>New Profile Photo :</td>
<td><input type = "file" class = "form-control" name = "user_image"></td>
</tr>
</table>
<div class = "container">
<button type = "submit" class = "btn primary-background text-white">Save</button>
</div>
</form>
</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button id = "edit-profile-button" type="button" class="btn primary-background text-white">Edit</button>
      </div>
    </div>
  </div>
</div>

<!-- end of profile modal -->



<!-- javascripts -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="js/myjs.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>

$(document).ready(function(){
	let editStatus = false;
	
	$('#edit-profile-button').click(function(){
		if(editStatus == false)
			{
			$("#profile-details").hide()
			$("#profile-edit").show()
			editStatus = true;
			$(this).text("Back")
			
			}else{
				$("#profile-details").show()
				$("#profile-edit").hide()
				editStatus = false;
				$(this).text("Edit")
			}
		
	})
	
})

</script>


<!-- add post js -->

<script>
$(document).ready(function(e){
	$("#add-post-form").on("submit", function(event){
		
		//this code gets called when form is submitted
		
		event.preventDefault();
		console.log("you have clicked on submit")
		let form = new FormData(this);
		
		//now requesting to server
		
		$.ajax({
			url: "AddPostServlet",
			type: 'POST',
			data: form,
			success: function (data, textStatus, jqXHR){
				//success
				console.log(data);
					swal("Good job!", "Shared Your Post Successfully.", "success");
				
			},
			error: function (jXHR, textStatus, errorThrown){
				//error
				swal("Error!", "Something Went Wrong. Try Again ...", "error");
			},
			processData: false,
			contentType: false
		})
	})
})
</script>

</body>
</html>