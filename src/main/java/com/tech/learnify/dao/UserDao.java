package com.tech.learnify.dao;
import java.sql.*;

import com.tech.learnify.entities.User;


public class UserDao {
	private Connection con;
	
	
	
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	
//	Method to insert the user to database
	public boolean saveUser(User user)
	{
		boolean f = false;
		try 
		{
//			user--database
		
			String query = "insert into user(name,email,password,gender,about) values (?,?,?,?,?)";
			PreparedStatement pstmt =  this.con.prepareStatement(query);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3,user.getPassword());
			pstmt.setString(4,user.getGender());
			pstmt.setString(5, user.getAbout());
			
			
			
			pstmt.executeUpdate();
			f = true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return f;
	}
	
	//Get user by useremail and user Password
	public User getUserByEmailAndPassword(String email, String password)
	{
		User user = null;
		
		try 
		{
			String query = "select * from user where email = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2,password);
			
			ResultSet set = pstmt.executeQuery();
			
			
			if(set.next())
			{
				user = new User();
				String name = set.getString("name");
//				Set to user object
				user.setName(name);
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setDatetime(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
				
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	
	public boolean updateUser(User user)
	{
		
		boolean f = false;
		try 
		{
			String query = "update user set name = ?, email = ?,password = ?, gender=  ? , about = ?, profile = ? where id = ? ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getPassword());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getAbout());
			ps.setString(6,user.getProfile());
			ps.setInt(7, user.getId());
			
			ps.executeUpdate();
			f = true;
			
			
			

	}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}

}
