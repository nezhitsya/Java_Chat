package prac;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import prac.FileSaveLoadUtil;
import prac.Message;
import prac.TypeOfMessage;
// 메시지를 송신하는 클래스
public class Sender {

	String name;
	ObjectOutputStream objectOutputStream;
	private static Sender sender;
	
	public Sender(ObjectOutputStream objectOutputStream, String name) {
		this.name = name;
		this.objectOutputStream = objectOutputStream;
		sender = this;
	}
	
	public void sendMessage(String userMessage) {
		Message message = getMessage(userMessage);
		message.setType(TypeOfMessage.MESSAGE);
		send(message);
	}
	
	public void sendWhisper(String userMessage, String whisperTarget) {
		Message message = getMessage(userMessage);
		message.setType(TypeOfMessage.WHISPER);
		message.setWhisperTarget(whisperTarget);
		send(message);
	}
	
	public void sendImage(String imagePath) {
		Message message = getMessage(null);
		message.setType(TypeOfMessage.IMAGE);
		message.setImageExtention(imagePath.substring(imagePath.length()-4, imagePath.length()));
		message.setImage(FileSaveLoadUtil.fileLoad(imagePath));
		send(message);
	}
	
	public void sendSearch(String keyword) {
		Message message = getMessage(keyword);
		message.setType(TypeOfMessage.SEARCH);
		send(message);
	}
	
	public Message getMessage(String userMessage) {
		Message message = new Message();
		message.setName(name);
		message.setMessage(userMessage);		
		return message;
	}
	
	public void send(Message message) {
		try {
			objectOutputStream.writeObject(message);
			objectOutputStream.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Sender getSender() {
		return sender;
	}
	
	public static void insert_DB(String name, String userMessage) {
		Connection conn = null;
		Statement stmt = null;
		try{
			conn=Customer.getConnection();
			// 데이터베이스의 데이터를 읽어온다.
			stmt=conn.createStatement();
			// insert into 쿼리문을 실행하여 java의 데이터를 MySQL에 저장
			String message = userMessage;
			String username = name;
			
			stmt.executeUpdate("insert into room." + LoginPanel.getRoom() + "(name,userMessage,time) values('" + username + "','" + message + "', now())");
		}
        catch(SQLException se) {
            System.out.println(se.getMessage());
        }
        finally {
            try {
            	stmt.close();
            }
            catch(Exception ignored) {
            }
            try {
            	conn.close();
            }
            catch (Exception ignored ) {
            }
         }
	}
}
