package model;

import java.sql.*;

public class Complain {
	
	public String insertComplains(String category, String subject, String message, String date, String contactNumber) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into complains values (?, ?, ?, ?, ?, ? )";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,category);
			preparedStmt.setString(3,subject);
			preparedStmt.setString(4,message);
			preparedStmt.setString(5,date);
			preparedStmt.setString(6,contactNumber);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readComplains() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>Complain Category</th><th>Complain Subject</th><th>Complain Message</th><th>Complain Date</th><th>Contact Number</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from complains";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String complainID = Integer.toString(rs.getInt("complainID"));
				String complainCategory	 = rs.getString("complainCategory");
				String complainSubject = rs.getString("complainSubject");
				String complainMessage = rs.getString("complainMessage");
				String complainDate = rs.getString("complainDate");
				String contactNumber = rs.getString("contactNumber");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +complainCategory+ "</td><td>" +complainSubject+ "</td><td>" +complainMessage+ "</td><td>" +complainDate+ "</td><td>" +contactNumber+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='complainID' type='hidden' value='"+complainID+"'><input name='complainCategory' type='hidden' value='"+complainCategory+"'><input name='complainSubject' type='hidden' value='"+complainSubject+"'><input name='complainMessage' type='hidden' value='"+complainMessage+"'><input name='complainDate' type='hidden' value='"+complainDate+"'><input name='contactNumber' type='hidden' value='"+contactNumber+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='complainID' type='hidden' value='"+complainID+"'><input name='complainCategory' type='hidden' value='"+complainCategory+"'></form></td>"
						+ "		</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the complains";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateComplain(String id, String category, String subject, String message, String date, String contactNumber) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update complains set complainCategory=?, complainSubject=?, complainMessage=?, complainDate=?, contactNumber=? where complainID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,category);
			preparedStmt.setString(2,subject);
			preparedStmt.setString(3,message);
			preparedStmt.setString(4,date);
			preparedStmt.setString(5,contactNumber);
			preparedStmt.setInt(6, Integer.parseInt(id));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Updated Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while updating";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deleteComplain(String complainID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from complains where complainID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(complainID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}
