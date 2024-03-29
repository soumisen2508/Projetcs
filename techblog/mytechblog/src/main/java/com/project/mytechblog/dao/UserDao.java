package com.project.mytechblog.dao;
import java.sql.*;
import com.project.mytechblog.entities.User;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	//method used to insert user details into database
	
	public boolean saveUser(User user) {
		
		boolean f = false;
		
		try {
			
			//user-->database
			
			String query = "insert into user(userName, userEmail, userPassword, userGender, userAbout) values(?, ?, ?, ?, ?)";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserGender());
			ps.setString(5, user.getUserAbout());
			
			ps.executeUpdate();
			f = true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	//get user by userEmail and userPassword
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		try {
			String query = "select * from user where userEmail = ? and userPassword = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet set = ps.executeQuery();
			
			if(set.next()) {
				user = new User();
				
				//data from DB
				String userName = set.getString("userName");
				
				//set to user object
				user.setUserName(userName);
				
				user.setUserId(set.getInt("userId"));
				user.setUserEmail(set.getString("userEmail"));
				user.setUserGender(set.getString("userGender"));
				user.setUserAbout(set.getString("userAbout"));
				user.setRegDate(set.getDate("regDate"));
				user.setUserProfile(set.getString("userProfile"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
		
		
	}

	public boolean updateUser(User user) {
		boolean f = false;
		try {
			String query = "update user set userName = ?, userEmail = ?, userPassword = ?, userAbout = ?, userProfile = ? where userId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserAbout());
			ps.setString(5, user.getUserProfile());
			ps.setInt(6, user.getUserId());
			
			ps.executeUpdate();
			f = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public User getUserByUserId(int userId) {
		User user = null;
		try {
		String query = "select * from user where userId = ?";
		PreparedStatement ps = this.con.prepareStatement(query);
		ps.setInt(1, userId);
		ResultSet set = ps.executeQuery();
		if(set.next()) {
			user = new User();
			
			//data from DB
			String userName = set.getString("userName");
			
			//set to user object
			user.setUserName(userName);
			
			user.setUserId(set.getInt("userId"));
			user.setUserEmail(set.getString("userEmail"));
			user.setUserGender(set.getString("userGender"));
			user.setUserAbout(set.getString("userAbout"));
			user.setRegDate(set.getDate("regDate"));
			user.setUserProfile(set.getString("userProfile"));
			
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
