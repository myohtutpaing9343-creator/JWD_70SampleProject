package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.modelview.UserBean;

public class UserRespository {

	public  int createUser(UserBean obj) {
		int i = 0;
		String sql = "insert into users(user_name,userid,gender)values(?,?,?);";

		try (Connection con = DBConnection.getcConnection(); PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, obj.getUser_name());
			ps.setString(2, obj.getUserid());
			ps.setString(3, obj.getGender());
			i = ps.executeUpdate();
if(i>=0) {
	try(ResultSet rs=ps.getGeneratedKeys()){
		if(rs.next()) {
			i=rs.getInt(1);
	}
    }
    }
	
		} catch (SQLException e) {
			System.out.println("User Error: " + e.getMessage());
		}
		return i;
	}

	private PreparedStatement ps;
	
		public int getUserRowCount() {
			int i=0;
			String sql="select count(*)as users_count from users;";

			try (Connection con=DBConnection.getcConnection();
			PreparedStatement ps=con.prepareStatement(sql)){
				
				ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			  i=rs.getInt("users_count");
				  
			  }
				
				
			}catch (SQLException e) {
				System.out.println("select error :"+e.getMessage());
			}
			return i;

}

		public String getUserid(int id) {
			String userid=null;
			String sql = "select userid from users where id=?;";

			 
				
				
				try (Connection con=DBConnection.getcConnection();
				PreparedStatement ps=con.prepareStatement(sql)){
					 ps.setInt(1,id);
					ResultSet rs=ps.executeQuery();
				if(rs.next()) {
				  userid=rs.getString("userid");
					  
				  }
					
					
				}catch (SQLException e) {
					System.out.println("select error :"+e.getMessage());
				}
				return userid;
	    }
		
		public UserBean getbyUserId(String userid) {
			UserBean obj=null;
			String sql = "select*from users where userid=?;";

			 
				
				
				try (Connection con=DBConnection.getcConnection();
				PreparedStatement ps=con.prepareStatement(sql)){
					 ps.setString(1,userid);
					 
					ResultSet rs=ps.executeQuery();
					
				if(rs.next()) {
				  obj=new UserBean();
					 obj.setId(rs.getInt("id"));
					 obj.setUser_name(rs.getString("user_name"));
					 obj.setGender(rs.getString("gender"));
					 obj.setUserid(rs.getString("userid"));
				  }
					
					
				}catch (SQLException e) {
					System.out.println("select error :"+e.getMessage());
				}
				return obj;
	    }
		public  int CheckInUser(int userid ) {
			int i = 0;
			String sql = "insert into attendence (user_id) values(?)";

			try (Connection con = DBConnection.getcConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, userid);
				
				i = ps.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println("Check in User: " + e.getMessage());
			}
			return i;
		}
		public  int checkoutUser(int userid ) {
			int i = 0;
			String sql = "update attendence set check_out=? where userid=?";

			try (Connection con = DBConnection.getcConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setTime(1,Time.valueOf(LocalTime.now()));
				ps.setInt(2, userid);
				
				i = ps.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println("Check Out User : " + e.getMessage());
			}
			return i;
		}
		
}


