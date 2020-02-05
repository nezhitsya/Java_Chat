package prac;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import server.ManagerLogin;
import prac.Listener;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// 메인 프레임
public class Design {

	private JFrame frame;
	private static JTextField IDtxt;
	private JTextField PWtxt;
	private static JTextField txtName;
	private JTextField txtidc;
	private JTextField txtpsw;
	static JLabel lblpic;
	static JLabel lblupic;
			
	private JInternalFrame Profile;
	private JFrame zipSearch = new zipSearch();
	private JFrame Manager = new ManagerLogin();
	private JTextField txtFname;
	public static JTextField txtpost;
	public static JTextArea textArea;
	public static JTextField textField;
	private JPasswordField chpsw; 
	private static String uname;
	private static int profileNum = 0;
	static ImageIcon profileImage = getProfileImage();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design window = new Design();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Design() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Customer customer = new Customer();
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Image\\App.png"));
		frame.setResizable(false); //크기 조정 불가
		frame.setBounds(100, 100, 850, 550);
		frame.setLocationRelativeTo(null); //항상 가운데 등장
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel User = new JPanel();
		
		JLabel u_name = new JLabel();
		u_name.setFont(new Font("휴먼명조", Font.ITALIC, 20));
		u_name.setBounds(168, 264, 85, 39);
		User.add(u_name);
				
				User.setBackground(new Color(153, 153, 204));
				User.setBounds(12, 145, 392, 395);
				frame.getContentPane().add(User);
				User.setLayout(null);
				User.setVisible(false);
				
				lblupic = new JLabel();
				lblupic.setBounds(118, 82, 160, 160);
				User.add(lblupic);
				lblupic.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Propic profileFrame = new Propic();
						profileFrame.setVisible(true);
					}
				});
				
				JLabel bntLogout = new JLabel("로그아웃");
				bntLogout.setFont(new Font("휴먼명조", Font.ITALIC, 18));
				bntLogout.setHorizontalAlignment(SwingConstants.CENTER);
				bntLogout.setBackground(Color.WHITE);
				bntLogout.setBounds(295, 362, 85, 33);
				User.add(bntLogout);
				
				JLabel bntChpass = new JLabel("비밀번호 변경");
				bntChpass.setHorizontalAlignment(SwingConstants.CENTER);
				bntChpass.setFont(new Font("휴먼명조", Font.ITALIC, 18));
				bntChpass.setBackground(Color.WHITE);
				bntChpass.setBounds(250, 322, 130, 33);
				User.add(bntChpass);
				bntChpass.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
						Connection con = customer.getConnection();
						String nam = getName();
						String password = JOptionPane.showInputDialog("변경할 비밀번호를 입력해 주세요.");
						String regExp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20}$";
						String query = "UPDATE customer SET pass=? WHERE name=?";
						PreparedStatement statement = con.prepareStatement(query);
						statement.setString(1, password);
						statement.setString(2, nam);
						if (password.matches(regExp)) {
							ResultSet set=statement.executeQuery();
							JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null, "숫자,대문자,특수문자를 하나 이상 추가해 주세요.");
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					}
				});
		
		JPanel Main = new JPanel();
		Main.setBounds(0, 0, 416, 550);
		Main.setBackground(new Color(153, 153, 204));
		frame.getContentPane().add(Main);
		Main.setLayout(null);
		
		JLabel lblDayTalk = new JLabel("DaY Talk");
		lblDayTalk.setBounds(91, 94, 191, 61);
		Main.add(lblDayTalk);
		lblDayTalk.setHorizontalAlignment(SwingConstants.CENTER);
		lblDayTalk.setFont(new Font("휴먼명조", Font.BOLD | Font.ITALIC, 35));
		
		Profile = new AppFrame();
		Profile.setBounds(414, 41, 436, 509);
		frame.getContentPane().add(Profile);
		Profile.setBackground(new Color(248, 248, 255));
		Profile.setVisible(false);
		Profile.getContentPane().setLayout(null);
		
		JPanel Bar = new JPanel();
		Bar.setBounds(0, 0, 850, 42);
		Bar.setBackground(new Color(153, 153, 204));
		frame.getContentPane().add(Bar);
		Bar.setLayout(null);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(803, 10, 35, 35);
		lblExit.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		Bar.add(lblExit);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				out_time(getID());
				System.exit(0);
			}
		});
		
		JLabel lblDown = new JLabel("ㅡ");
		lblDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblDown.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		lblDown.setBounds(768, 10, 35, 35);
		Bar.add(lblDown);
		lblDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setState(JFrame.ICONIFIED);
			}
		});

		JPanel LogIn = new JPanel();
		LogIn.setBounds(414, 41, 436, 509);
		frame.getContentPane().add(LogIn);
		LogIn.setBackground(new Color(153, 153, 204));
		LogIn.setLayout(null);
		
		JButton btnLogIn = new JButton("로그인");
		btnLogIn.setBackground(new Color(204, 204, 255));
		btnLogIn.setBounds(119, 382, 240, 45);
		btnLogIn.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		LogIn.add(btnLogIn);
		
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				try {
					Connection con = customer.getConnection();
					String name = IDtxt.getText();
					String pasw = PWtxt.getText();
					String ppname = "Day";
					
					if(name.equals(ppname)) {
						Manager.setVisible(true);
					}
					in_time(name);
					String query = "SELECT * FROM customer WHERE id = ? and pass = ?";
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, name);
					statement.setString(2, pasw);
				
					ResultSet set = statement.executeQuery();
					if(set.next()) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						LogIn.setVisible(false);
						Profile.setVisible(true);
						User.setVisible(true);
						u_name.setText(getName());
						lblupic.setIcon(new ImageIcon(new ImageIcon(Propic.PROFILEPATH + "/profile"+getProfileNum()+".png").getImage().getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH)));
					}
					else {
						JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 다시 확인해주세요.");
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
					return;
				}
			}
		});
		
		bntLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					out_time(getID());
						LogIn.setVisible(true);
						Profile.setVisible(false);
						User.setVisible(false);
			}
		});
		
		IDtxt = new JTextField();
		IDtxt.setBounds(119, 274, 240, 40);
		IDtxt.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		IDtxt.setText("아이디");
		LogIn.add(IDtxt);
		IDtxt.setColumns(10);
		
		PWtxt = new JPasswordField(30);
		PWtxt.setText("비밀번호");
		PWtxt.setBounds(119, 324, 240, 40);
		PWtxt.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		LogIn.add(PWtxt);
		PWtxt.setColumns(10);
		JButton Signbtn = new JButton("회원가입");
		Signbtn.setBackground(new Color(204, 204, 255));
		Signbtn.setBounds(119, 437, 114, 36);
		LogIn.add(Signbtn);
		Signbtn.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		
		JButton bntFind = new JButton("계정찾기");
		bntFind.setBackground(new Color(204, 204, 255));
		bntFind.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		bntFind.setBounds(245, 437, 114, 36);
		LogIn.add(bntFind);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("맑은 고딕", Font.ITALIC, 18));
		lblId.setBounds(55, 279, 52, 28);
		LogIn.add(lblId);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setFont(new Font("맑은 고딕", Font.ITALIC, 18));
		lblPw.setBounds(55, 329, 52, 28);
		LogIn.add(lblPw);
		
		JPanel Setting = new JPanel();
		Setting.setBounds(414, 41, 436, 509);
		frame.getContentPane().add(Setting);
		Setting.setBackground(new Color(230, 230, 250));
		Setting.setLayout(null);
		
		Setting.setVisible(false);
		
		JLabel Name = new JLabel("이 름");
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		Name.setBounds(41, 105, 69, 39);
		Setting.add(Name);
		
		txtName = new JTextField();
		txtName.setBounds(134, 112, 119, 32);
		Setting.add(txtName);
		txtName.setColumns(10);
		
		JLabel label = new JLabel("아 이 디");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label.setBounds(41, 203, 69, 39);
		Setting.add(label);
		
		txtidc = new JTextField();
		txtidc.setColumns(10);
		txtidc.setBounds(134, 210, 164, 32);
		Setting.add(txtidc);
		
		JLabel label_1 = new JLabel("비밀번호");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_1.setBounds(41, 252, 69, 39);
		Setting.add(label_1);
		
		txtpsw = new JPasswordField(30);
		txtpsw.setColumns(10);
		txtpsw.setBounds(134, 259, 190, 32);
		Setting.add(txtpsw);
		
		JComboBox gendertxt = new JComboBox(new String[] {"Female","Male"});
		gendertxt.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		gendertxt.setBackground(new Color(255, 255, 255));
		gendertxt.setBounds(134, 154, 99, 39);
		Setting.add(gendertxt);
		
		JLabel label_2 = new JLabel("성 별");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_2.setBounds(41, 154, 69, 39);
		Setting.add(label_2);
		
		JButton btnSign = new JButton("확 인");
		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String gender = gendertxt.getSelectedItem().toString();
				String id = txtidc.getText();
				String pass = txtpsw.getText();
				String sido = textField.getText();
				String gudong = textArea.getText();
				String post = txtpost.getText();
				String address = sido + gudong + post;
				int pic = profileNum;
				customer.createCustomer(name,id,pass,gender,address,pic);
				JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.");
				
				Setting.setVisible(false);
				LogIn.setVisible(true);
			}
		});
		btnSign.setBackground(new Color(248, 248, 255));
		btnSign.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnSign.setBounds(156, 460, 121, 39);
		Setting.add(btnSign);
		
		JButton chpswbtn = new JButton("확 인");
		chpswbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String regExp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20}$";
				String chpass = txtpsw.getText();
				if (chpass.matches(regExp)) {
					JOptionPane.showMessageDialog(null, "유효한 비밀번호 입니다.");
				} else {
					JOptionPane.showMessageDialog(null, "숫자,대문자,특수문자를 하나 이상 추가해 주세요.");
				}
			}
		});
		chpswbtn.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		chpswbtn.setBackground(new Color(248, 248, 255));
		chpswbtn.setBounds(336, 252, 79, 39);
		Setting.add(chpswbtn);
		
		JLabel label_3 = new JLabel("\uD68C \uC6D0 \uAC00 \uC785");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		label_3.setBounds(41, 30, 168, 39);
		Setting.add(label_3);
		
		JButton Check = new JButton("중복확인");
		Check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = customer.getConnection();
					String IdId = txtidc.getText();
							
					String query = "SELECT * FROM customer WHERE id = ?";
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, IdId);
					ResultSet set = statement.executeQuery();
						if(set.next()) {
							JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
						}
						else {
							JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
						return;
					}
				}
			});
		
		Check.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		Check.setBackground(new Color(248, 248, 255));
		Check.setBounds(310, 203, 105, 38);
		Setting.add(Check);
		
		JLabel label_6 = new JLabel("주 소");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(41, 345, 69, 39);
		Setting.add(label_6);
		
		txtpost = new JTextField();
		txtpost.setColumns(10);
		txtpost.setBounds(209, 351, 99, 32);
		Setting.add(txtpost);
		
		JButton postbnt = new JButton("우편번호");
		postbnt.setFont(new Font("Dialog", Font.PLAIN, 16));
		postbnt.setBackground(new Color(248, 248, 255));
		postbnt.setBounds(310, 345, 105, 38);
		Setting.add(postbnt);
		postbnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zipSearch.setVisible(true);
			}
		});
		
		textArea = new JTextArea();
		textArea.setBounds(134, 393, 271, 57);
		Setting.add(textArea);
		
		chpsw = new JPasswordField(30);
		chpsw.setColumns(10);
		chpsw.setBounds(134, 301, 190, 32);
		Setting.add(chpsw);
		
		JLabel label_7 = new JLabel("비밀번호 확인");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_7.setBounds(12, 296, 110, 39);
		Setting.add(label_7);
		
		JLabel lblBack = new JLabel("<");
		lblBack.setFont(new Font("굴림", Font.BOLD, 20));
		lblBack.setBounds(400, 10, 24, 22);
		Setting.add(lblBack);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(134, 351, 75, 32);
		Setting.add(textField);
		
		lblpic = new JLabel(profileImage);
		lblpic.setBounds(262, 35, 160, 160);
		Setting.add(lblpic);
		lblpic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Propic profileFrame = new Propic();
				profileFrame.setVisible(true);
			}
		});
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Setting.setVisible(false);
				LogIn.setVisible(true);
			}
		});
		
		JPanel Find = new JPanel();
		Find.setBounds(414, 41, 436, 509);
		frame.getContentPane().add(Find);
		Find.setBackground(new Color(230, 230, 250));
		Find.setLayout(null);
		Find.setVisible(false);
		
		Signbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				Setting.setVisible(true);
				LogIn.setVisible(false);
			}
		});
		
		bntFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				LogIn.setVisible(false);
				Find.setVisible(true);
			}
		});
		
		JLabel label_4 = new JLabel("계 정 찾 기");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		label_4.setBounds(132, 55, 168, 61);
		Find.add(label_4);
		
		JComboBox boxF = new JComboBox(new String[] {"Female","Male"});
		boxF.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		boxF.setBackground(Color.WHITE);
		boxF.setBounds(146, 205, 99, 39);
		Find.add(boxF);
		
		JButton Findbnt = new JButton("확 인");
		Findbnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Find.setVisible(false);
				LogIn.setVisible(true);
			}
		});
		Findbnt.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Findbnt.setBackground(new Color(248, 248, 255));
		Findbnt.setBounds(155, 437, 121, 39);
		Find.add(Findbnt);
		
		Findbnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				try {
					Connection con = customer.getConnection();
					String nam = txtFname.getText();
					String gend = boxF.getSelectedItem().toString();
					String query = "SELECT id,pass FROM customer WHERE name=? and gender=?";
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, nam);
					statement.setString(2, gend);
					ResultSet results = statement.executeQuery();
					ArrayList<String> list = new ArrayList<String>();
					while(results.next()) {
						list.add(" ID : "+results.getString("id")+
								" Password : "+results.getString("pass"));
					}
				
					ResultSet set=statement.executeQuery();
					if(set.next()) {
						JOptionPane.showMessageDialog(null,  list + " 입니다.");
						LogIn.setVisible(true);
						Find.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "개인 정보를 다시 확인해주세요.");
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
					return;
				}
			}
		});
		
		JLabel label_5 = new JLabel("이 름");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_5.setBounds(41, 151, 69, 39);
		Find.add(label_5);
		
		txtFname = new JTextField();
		txtFname.setColumns(10);
		txtFname.setBounds(136, 158, 190, 32);
		Find.add(txtFname);
		
		JLabel label_8 = new JLabel("성 별");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_8.setBounds(41, 200, 69, 39);
		Find.add(label_8);
	}
	public static void changeProfileImage(int index) {
		profileNum = index;
		profileImage = getProfileImage();
		lblpic.setIcon(profileImage);
	} 
	public static void chProfileImage(int index) {
		profileNum = index;
		profileImage = getProfileImage();
		lblupic.setIcon(profileImage);
		int pic = profileNum;
		try {
			Connection con = Customer.getConnection();
			String name = getName();
					
			String query = "UPDATE customer SET pic=? WHERE name = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, pic);
			statement.setString(2, name);
			ResultSet set = statement.executeQuery();
			} catch(Exception e) {
				System.out.println(e.getMessage());
				return;
			}
	} 
	private static ImageIcon getProfileImage() {
		return new ImageIcon(new ImageIcon(Propic.PROFILEPATH + "/profile"+profileNum+".png").getImage().getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH));
	}
	public static String getID() {
		return IDtxt.getText();
	}
	public static String getName() {
		try {
			Connection con = Customer.getConnection();
			String idc = getID();
			
			String query = "SELECT * FROM customer WHERE id = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, idc);
		
			ResultSet results = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while(results.next()) {
				uname = results.getString("name");
			}
		} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		return uname;
	}
	public static int getProfileNum() {
		try {
			Connection con = Customer.getConnection();
			String idc = getID();
			
			String query = "SELECT * FROM customer WHERE id = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, idc);
		
			ResultSet results = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while(results.next()) {
				profileNum = results.getInt("pic");
			}
		} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		return profileNum;
	}
	public void in_time(String id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn=Customer.getConnection();
			// 데이터베이스의 데이터를 읽어온다.
			stmt=conn.prepareStatement("update Customer set in_time = now() where id = '" + id + "'");
			
			// update문을 써서 현재 로그인 한 시간을 저장한다. 그 전에는 null값을 넣어주었음.
			stmt.executeUpdate();
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
	public void out_time(String id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn=Customer.getConnection();
			// 데이터베이스의 데이터를 읽어온다.
			stmt=conn.prepareStatement("update customer set out_time = now() where id = '" + id + "'");
			// update문을 써서 현재 로그아웃 한 시간을 저장한다. 그 전에는 null값을 넣어주었음.
			stmt.executeUpdate();
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

