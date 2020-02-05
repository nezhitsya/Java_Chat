package prac;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import prac.Design;
import prac.ChatPanel;
import prac.FileSaveLoadUtil;
import prac.UserList;
import prac.FilePath;
import prac.Message;
// 최초 서버 접속 이후 서버에서 전송되는 메시지 수신
// login() 서버 접속
// islogined() 접속 확인
// receive() 서버 전송되는 메시지 수신
public class Listener implements Runnable {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 2367;

	Socket socket;
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	AppFrame frame;
	ChatPanel chatPanel;
	String name;
	Message message;

	public Listener(AppFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket(HOST, PORT);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			//name = frame.getLoginPane().getTxtName();
			name = Design.getName();
			chatPanel = frame.getChatPane();
			login();
			if (isLogined()) {
				receive();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			networkDisconnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	private void login() throws IOException {
		message = new Message();
		message.setName(name);
		message.setMessage(""+Design.getProfileNum());
		objectOutputStream.writeObject(message);
		objectOutputStream.reset();
	}
	
	private boolean isLogined() throws ClassNotFoundException, IOException {
		message = (Message) objectInputStream.readObject();
		switch (message.getType()) {
		case DUPLICATE:
			duplicateName();
			return false;
		case WELCOME:
			UserList.setList(message.getUserList());
		default:
			frame.changeToChat();
			new Sender(objectOutputStream,name);
			printMessage(message.getMessage());
			return true;
		}
	}

	private void receive() throws IOException, ClassNotFoundException {
		while (socket.isConnected()) {
			message = (Message) objectInputStream.readObject();
			switch (message.getType()) {
			case DUPLICATE:
				duplicateName();
				return;
			case IMAGE:
				message.setMessage((saveImage()));
				printMessage();
				break;
			case WELCOME:
			case EXIT:
				UserList.setList(message.getUserList());
				printMessage(message.getMessage());
				break;
			default:
				printMessage();
				break;
			}
		}
	}
	
	private void duplicateName() {
		new ErrorPanel(frame, "이미 존재하는 닉네임 입니다. 다른 이름을 선택해 주세요");
	}
	
	private void networkDisconnection() {
		new ErrorPanel(frame, "네트워크에 문제가 발생했습니다.");
	}
	
	private void printMessage(String adminMsg) {
		chatPanel.addMessage(adminMsg);
	}

	private void printMessage() {
		chatPanel.addMessage(isMine(),message);
	}
	
	private boolean isMine() {
		return name.equals(message.getName());
	}
	
	private String saveImage() {
		return FileSaveLoadUtil.fileSave(message.getImageExtention(), FilePath.DOWNLOADFILEPATH.toString(), message.getImage());
	}

	private void closeConnection() {
		if (objectInputStream != null) {
			try {
				objectInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (objectOutputStream != null) {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
