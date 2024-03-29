package com.project.mytechblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDao {
	Connection con;
	
	
	public LikeDao(Connection con) {
		super();
		this.con = con;
	}


	public boolean insertLikeOnPost(int postId, int likeUserId) {
		boolean f = false;
		try {
		String query = "insert into liked(postId, likeUserId) values(?, ?)";
		PreparedStatement ps = this.con.prepareStatement(query);
		ps.setInt(1, postId);
		ps.setInt(2, likeUserId);
		ps.executeUpdate();
		f = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public int countLikeOnPost(int postId) {
		int count = 0;
		
		try {
			String query = "select count(*) from liked where postId = ?";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setInt(1, postId);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				count = set.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;	
	}
	
	public boolean isLikedByUser(int postId, int userId) {
		boolean f = false;
		
		try {
			String query = "select * from liked where where postId = ? and likeUserId = ?";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, userId);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean deleteLike (int postId, int likeUserId) {
		boolean f = false;
		try {
			PreparedStatement ps = this.con.prepareStatement("delete from liked where postId = ? and likeUserId = ?");
			ps.setInt(1, postId);
			ps.setInt(2, likeUserId);
			ps.executeUpdate();
			f = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
