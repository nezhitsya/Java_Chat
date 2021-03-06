package prac;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
//채팅을 실행시키는 내부 프레임
public class AppFrame extends JInternalFrame {

	private JPanel contentPane;
	private LoginPanel loginPane;
	private ChatPanel chatPane;

	public AppFrame() {
		setTitle("DaY Talk");
		loginPane = new LoginPanel(this);
		chatPane = new ChatPanel();
		changeToLogin();
	}
	
	public void changeToLogin() {		
		contentPane = loginPane;
		paintPane();
	}

	public void changeToChat() {
		contentPane = chatPane;
		paintPane();
	}
	
	public LoginPanel getLoginPane() {
		return loginPane;
	}
	
	public ChatPanel getChatPane() {
		return chatPane;
	}
	
	public void changeToError(String errorMessage) {
		contentPane = new ErrorPanel(this, errorMessage);
		paintPane();
	}	
	
	public void paintPane() {
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		revalidate();
		repaint();
	}
	
}
