package prac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
// 사용자 개인정보 mysql db에 연동하여 저장
public class Customer {
	
	public static void createCustomer(String name, String id, String pass, String gender, String address, int pic) {
		try {
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement(""
					+ "INSERT INTO customer"
					+ "(name, id, pass, gender, address, pic) "
					+ "VALUE "
					+ "('"+name+"', '"+id+"', '"+pass+"', '"+gender+"', '"+address+"', '"+pic+"')");
			insert.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ArrayList<String> getCustomers() {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select id, pass FROM customer");
			ResultSet results = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while(results.next()) {
				list.add(" ID : "+results.getString("id")+
						" Password : "+results.getString("pass"));
			}
			return list;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/customer";
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"customer_manager","1120");
			System.out.println("The Connection Successful");
			return con;
		}
		catch(Exception e) {
			return null;
		}
	}
}
