package com.project.mytechblog.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.project.mytechblog.entities.Category;
import com.project.mytechblog.entities.Post;

public class PostDao {

	Connection con;

	public PostDao(Connection con) {
		super();
		this.con = con;
	}
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> list = new ArrayList<>();
		
		try {
			String query = "select * from categories";
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				int catId = set.getInt("categoryId");
				String categoryName = set.getString("categoryName");
				String categoryDesc = set.getString("categoryDesc");
				Category c = new Category(catId, categoryName, categoryDesc);
				list.add(c);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean savePost(Post p) {
		boolean f = false;
		try {
			String query = "insert into posts (postTitle, postContent, postCode, catId, postPic, userId) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, p.getPostTitle());
			ps.setString(2, p.getPostContent());
			ps.setString(3, p.getPostCode());
			ps.setInt(4, p.getCatId());
			ps.setString(5, p.getPostPic());
			ps.setInt(6, p.getUserId());
			ps.executeUpdate();
			f = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	//fetching all posts from database
	
	public List<Post> getAllPosts(){
		
		List<Post> list = new ArrayList<>();
		
		//fetch all posts
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from posts order by postId desc");
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				int postId = set.getInt("postId");
				String postTitle = set.getString("postTitle");
				String postContent = set.getString("postContent");
				String postCode = set.getString("postCode");
				String postPic = set.getString("postPic");
				Date postDate = set.getDate("postDate");
				int catId = set.getInt("catId");
				int userId = set.getInt("userId");
				
				Post post = new Post(postId, postTitle, postContent, postCode, postDate, catId, postPic, userId);
				
				list.add(post);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Post> getPostByCatId(int catId){
		
      List<Post> list = new ArrayList<>();
		
		//fetch all posts filtered by categoryId
      
      try {
			PreparedStatement ps = con.prepareStatement("select * from posts where catId = ?");
			ps.setInt(1, catId);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				int postId = set.getInt("postId");
				String postTitle = set.getString("postTitle");
				String postContent = set.getString("postContent");
				String postCode = set.getString("postCode");
				String postPic = set.getString("postPic");
				Date postDate = set.getDate("postDate");
				int userId = set.getInt("userId");
				
				Post post = new Post(postId, postTitle, postContent, postCode, postDate, catId, postPic, userId);
				
				list.add(post);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public Post getPostByPostId(int postId) {
		
		Post post = null;
		
		try {
			String query = "select * from posts where postId = ?";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setInt(1, postId);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				String postTitle = set.getString("postTitle");
				String postContent = set.getString("postContent");
				String postCode = set.getString("postCode");
				String postPic = set.getString("postPic");
				Date postDate = set.getDate("postDate");
				int userId = set.getInt("userId");
				int catId = set.getInt("catId");
				post = new Post(postId, postTitle, postContent, postCode, postDate, catId, postPic, userId);
				
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return post;
		
	}
}
