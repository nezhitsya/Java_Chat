package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.StringTokenizer;
import java.util.Vector;

import prac.Handler;
// 서버
public class ServerLauncher {

	private static final int PORT = 2367; //지정 포트번호
	
	private Vector user_vc = new Vector();					// 사용자 벡터
	private Vector room_vc= new Vector();					// 채팅방 벡터
	private StringTokenizer st;								// 프로토콜 구분을 위한 변수
	
	public static void main(String[] args) {
		
		try (ServerSocket listener = new ServerSocket(PORT)) {
			// 소켓에 포트번호 입력
			while (true) {
				new Thread(new Handler(listener.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
