<%@page import="com.project.mytechblog.entities.User"%>
<%@page import="com.project.mytechblog.dao.LikeDao"%>
<%@page import="com.project.mytechblog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.project.mytechblog.helper.ConnectionProvider"%>
<%@page import="com.project.mytechblog.dao.PostDao"%>

  <div class = "row">

<% 
Thread.sleep(1000);
PostDao dao = new PostDao (ConnectionProvider.getConnection());
int cid = Integer.parseInt(request.getParameter("cid"));
List<Post> posts = null;
if(cid==0){
	 posts = dao.getAllPosts();
}else{
	posts = dao.getPostByCatId(cid);
}

if(posts.size()==0)
{
	out.println("<h4 class = 'text-center'>Sorry. No posts in this category...</h4>");
	return;
}
for(Post p: posts){
	String arrOfStr [] = p.getPostContent().split(",");
	%>
	
 <div class = "col-md-6 mt-2">
 <div class = "card">
 <img class="card-img-top" style = "max-height:250px" src="blog_pics/<%= p.getPostPic() %>" alt="Card image cap">
 <div class = "card-body">
 <h5><%= p.getPostTitle() %></h5>
 <p><%=arrOfStr[0]+ ","+ arrOfStr[1] %></p>
 <code><%= p.getPostCode() %></code>
 </div>
 <div class = "card-footer">
 <%
LikeDao likeDao = new LikeDao (ConnectionProvider.getConnection());
User user = (User)session.getAttribute("currentUser");
%>
 <a href = "show_blog_page.jsp?post_id=<%=p.getPostId() %>" class = "btn btn-outline-success btn-sm">Read More...</a>
 <a href = "#!" onclick = "insertLike (<%= p.getPostId()%>, <%= user.getUserId()%>)" class = "btn btn-outline-success btn-sm"><i class = "fa fa-thumbs-o-up"></i> <span class = "like-counter"><%= likeDao.countLikeOnPost(p.getPostId()) %></span></a>
 <a href = "#!" class = "btn btn-outline-success btn-sm"><i class = "fa fa-commenting-o"></i> <span>20</span></a>
 </div>
 </div>
 
 </div>
<% 
}
%>
</div>