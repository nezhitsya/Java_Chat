package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.swing.JFrame;

import prac.Customer;
/*
 * DB에 저장되어 있는 chat_info테이블의 데이터를 txt파일로 저장해주는 클래스
 * (txt 저장 버튼 클릭시 작동)
 */
public class DBSave extends JFrame {
	DBSave(){
		Connection conn = null;
		Statement stmt = null;
		PrintWriter writer = null;
		
		try{
			conn = Customer.getConnection();
			stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from customer;");
			writer = new PrintWriter("chatting.txt");

			while(rs.next()){
				String name = rs.getString("name");
				String id = rs.getString("id");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				
				// String in_time = rs.getString("in_time");
				// String out_time = rs.getString("out_time");

				writer.printf("아이디 : %s %n이름 : %s %n성별 : %s %n주소 : %s %n"
						+ "---------------------------------------------------------------%n%n",
						id,name,gender,address);
			}
			
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException ioe){
			System.out.println("출력 불가");
		}
		finally{
			try{
				writer.close();
			}
			catch(Exception e){	}
			try{
				stmt.close();
			}
			catch(Exception ignored){
			}
			try{
				conn.close();
			}
			catch(Exception ignored){
			}
		}
	}
}
