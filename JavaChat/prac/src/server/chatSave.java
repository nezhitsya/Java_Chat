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
import javax.swing.JOptionPane;

import prac.Customer;
/*
 * DB에 저장되어 있는 room테이블의 데이터를 txt파일로 저장해주는 클래스
 * (txt 저장 버튼 클릭시 작동)
 */
public class chatSave extends JFrame {
	chatSave(){

		String roomname = JOptionPane.showInputDialog("저장할 채팅방 이름을 입력해 주세요.");
		
		Connection conn = null;
		Statement stmt = null;
		PrintWriter writer = null;
		
		try{
			conn = Customer.getConnection();
			stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from room."+roomname+";");
			writer = new PrintWriter(roomname+".txt");

			while(rs.next()){
				String id = rs.getString("name");
				String message = rs.getString("userMessage");
				String time = rs.getString("time");
				
				writer.printf("< %s >  %s  -  %s  %n%n",
						id, message, time);
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
