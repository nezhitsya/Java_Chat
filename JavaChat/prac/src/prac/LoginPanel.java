package prac;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import prac.Listener;
// 스레드 시작
public class LoginPanel extends JPanel {
	public static String Room;
	transient private static ArrayList list = new ArrayList();
	
	public LoginPanel(final AppFrame frame) {
		
		setBackground(new Color(248, 248, 255));
		setLayout(null);
		
		JButton btnLogin = new JButton("Enter");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(153, 153, 204));
		btnLogin.setFont(new Font("휴먼명조", Font.BOLD | Font.ITALIC, 35));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room = JOptionPane.showInputDialog("채팅방 이름을 입력해주세요.");
				RoomDB roomdb = new RoomDB(Room);
				new Thread(new Listener(frame)).start();
			}
		});
		btnLogin.setBounds(86, 129, 257, 251);
		add(btnLogin);
	}
	public static String getRoom() {
		return Room;
	}
}
