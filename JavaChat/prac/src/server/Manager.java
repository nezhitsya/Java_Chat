/*
 * 관리자모드 GUI 클래스
 * 관리자 비밀번호를 입력받아 맞으면 관리자모드를 실행시킨다.
 */
package server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import prac.Design;
import prac.zipSearch;

import java.awt.SystemColor;

public class Manager extends JFrame{
	// ctrl + shift + o 자동으로 import됨
	
	private JPanel contentPane;	
	
	public Manager(){
		
		//if(pw.equals("1120")){														// 	1120를 입력받으면 관리자 모드를 실행시킨다.
			//setTitle("관리자모드");
			init();			// 화면 생성 메소드			
		//}
	}
	
	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgram = new JLabel("Manager");
		lblProgram.setFont(new Font("휴먼명조", Font.BOLD | Font.ITALIC, 32));
		lblProgram.setBounds(105, 50, 172, 65);
		contentPane.add(lblProgram);
		
		
		contentPane.setBackground(new Color(224, 255, 255));
	
		
		JButton btnNewButton = new JButton("DB 삭제");			// DB삭제 버튼
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {	// DB 삭제 버튼 클릭 시
			public void actionPerformed(ActionEvent arg0) {
				new DBDelete();
			}
		});
		btnNewButton.setFont(new Font("휴먼명조", Font.PLAIN, 14));
		btnNewButton.setBounds(68, 172, 220, 38);
		contentPane.add(btnNewButton);
		//---------------------------------------------------------------------
		JButton infoSave_btn = new JButton("회원정보저장");		// txt저장 버튼 클릭 시
		infoSave_btn.setBackground(SystemColor.activeCaption);
		infoSave_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DBSave();
				JOptionPane.showMessageDialog(null, "정상적으로 DB데이터가 txt로 저장되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		infoSave_btn.setFont(new Font("휴먼명조", Font.PLAIN, 14));
		infoSave_btn.setBounds(68, 278, 220, 38);
		contentPane.add(infoSave_btn);
		//-------------------------------------------------------------------
		JButton member_btn = new JButton("회원관리");			// 회원관리 버튼 클릭 시
		member_btn.setBackground(SystemColor.activeCaption);
		member_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MemberLoad(); 
			}
		});
		member_btn.setFont(new Font("휴먼명조", Font.PLAIN, 14));
		member_btn.setBounds(68, 225, 220, 38);
		contentPane.add(member_btn);
		//-----------------------------------------------------------------
		JButton chatSave_btn = new JButton("채팅내용저장");
		chatSave_btn.setBackground(SystemColor.activeCaption);
		chatSave_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new chatSave(); 
				JOptionPane.showMessageDialog(null, "정상적으로 DB데이터가 txt로 저장되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		chatSave_btn.setFont(new Font("휴먼명조", Font.PLAIN, 14));
		chatSave_btn.setBounds(68, 331, 220, 38);
		contentPane.add(chatSave_btn);
		
		this.setVisible(true);	// true = 화면에 보이게 false = 화면에 보이지 않게
	}
	/*public static void main(String args[]){
		new ManagerLogin();
	}*/
}