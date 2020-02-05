/*
 * 관리자 모드의 회원관리 창에서 관리자가 원하는 사용자를 클릭한 후에
 * 정보보기 버튼을 눌렀을 때 사용자 정보를 출력해주는 클래스
 */

package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import prac.Customer;
import prac.Design;
import prac.Propic;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class MemberChange extends JFrame {

	private JPanel contentPane;
	private static JTextField tf_id;
	private JTextField tf_name;
	private JTextField tf_address;
	private JTextField tf_gender;
	private static JLabel  image_lbl;	
	private JTextField tf_intime;
	private JTextField tf_outtime;
	private JButton btnNewButton;

	private static int profileNum;
	static ImageIcon profileImage = getProfileImage();


	public MemberChange( String name,String id, String gender, String address,int pic, String intime,String outtime) {
		setBackground(new Color(255, 255, 255));
		
		setTitle(name + "님의 회원정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 423, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "사진", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 10, 112, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		image_lbl = new JLabel(profileImage);
		image_lbl.setIcon(new ImageIcon(new ImageIcon(Propic.PROFILEPATH + "/profile"+pic+".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		image_lbl.setBounds(6, 17, 100, 100);
		panel.add(image_lbl);
		image_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Propic profileFrame = new Propic();
				profileFrame.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("이름 : ");
		lblNewLabel.setBounds(16, 161, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("아이디 :");
		lblNewLabel_1.setBounds(16, 195, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("주소 : ");
		lblNewLabel_2.setBounds(16, 231, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("성별 : ");
		lblNewLabel_4.setBounds(211, 161, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tf_id = new JTextField();
		tf_id.setBounds(99, 158, 77, 21);
		tf_id.setText(id);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		tf_name = new JTextField();
		tf_name.setEditable(false);
		tf_name.setBounds(99, 192, 77, 21);
		tf_name.setText(name);
		contentPane.add(tf_name);
		tf_name.setColumns(10);
		
		tf_address = new JTextField();
		tf_address.setBounds(99, 228, 257, 21);
		tf_address.setText(address);
		contentPane.add(tf_address);
		tf_address.setColumns(10);
		
		tf_gender = new JTextField();
		tf_gender.setBounds(304, 158, 83, 21);
		tf_gender.setText(gender);
		contentPane.add(tf_gender);
		tf_gender.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("로그인 시간 : ");
		lblNewLabel_6.setBounds(140, 32, 87, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("로그아웃 시간 : ");
		lblNewLabel_7.setBounds(140, 77, 100, 15);
		contentPane.add(lblNewLabel_7);
		
		tf_intime = new JTextField();
		tf_intime.setEditable(false);
		tf_intime.setBounds(241, 29, 146, 21);
		tf_intime.setText(intime);
		contentPane.add(tf_intime);
		tf_intime.setColumns(10);
		
		tf_outtime = new JTextField();
		tf_outtime.setEditable(false);
		tf_outtime.setBounds(241, 74, 146, 21);
		tf_outtime.setText(outtime);
		contentPane.add(tf_outtime);
		tf_outtime.setColumns(10);
		
		btnNewButton = new JButton("변경하기");
		btnNewButton.setBounds(153, 259, 100, 33);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection conn = Customer.getConnection();
					
					String sql = "UPDATE customer SET name=? WHERE id=?";
					String id = tf_name.getText();
					String name = tf_id.getText();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2, id);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()){
						new MemberChange(id,name,gender,address,pic,intime,outtime/*,is*/);
					}
				}
				catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			
			}
		});
		
		setVisible(true);
	}
	public static void chanProfileImage(int index) {
		profileNum = index;
		profileImage = getProfileImage();
		image_lbl.setIcon(profileImage);
		int pic = profileNum;
		try {
			Connection con = Customer.getConnection();
			String name = tf_id.getText();
			String query = "UPDATE customer SET pic=? WHERE name = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, pic);
			statement.setString(2, name);
			ResultSet set = statement.executeQuery();
			if(set.next()){
			}
			} catch(Exception e) {
				System.out.println(e.getMessage());
				return;
			}
	} 
	private static ImageIcon getProfileImage() {
		return new ImageIcon(new ImageIcon(Propic.PROFILEPATH + "/profile"+profileNum+".png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	}
}
