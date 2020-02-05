package prac;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class zipSearch extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> comboBox;
    private JComboBox comboBox_1;
    private JComboBox comboBox_2;
   
    private Connection conn = null;
    private PreparedStatement pstmt = null;      
    private ResultSet rs = null;         
    private JScrollPane scrollPane;
    private JPanel panel;
    private JTextField tfDong;
    private JButton btnPostCheck = new JButton("확인");
    public Object post;

    private void start() 
    {
       btnPostCheck.setBackground(new Color(255, 255, 255));
       btnPostCheck.addActionListener(this);
    }
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {    
           EventQueue.invokeLater(new Runnable() {
                   public void run() {
                          try {
                                  zipSearch frame = new zipSearch();
                                  frame.setVisible(true);
                          } catch (Exception e) {
                                  e.printStackTrace();
                          }
                   }
           });
    }

    /**
     * Create the frame.
     */
    public zipSearch() {
    	setUndecorated(true);
       start();
 		setLocationRelativeTo(null);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setBounds(100, 100, 606, 583);
           contentPane = new JPanel();
           contentPane.setBackground(Color.PINK);
           contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
           setContentPane(contentPane);
           contentPane.setLayout(null);
           
           JLabel lblExit = new JLabel("X");
   		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
   		lblExit.setBounds(565, 10, 35, 35);
   		lblExit.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
   		contentPane.add(lblExit);
   		lblExit.addMouseListener(new MouseAdapter() {
   			@Override
   			public void mouseClicked(MouseEvent arg0) {
   				setVisible(false);
   			}
   		});
           panel = new JPanel();
           panel.setBackground(new Color(255, 250, 250));
           panel.setBorder(new TitledBorder(null, "우편번호 검색", TitledBorder.LEADING, TitledBorder.TOP, null, null));
           panel.setBounds(6, 61, 594, 70);
           contentPane.add(panel);
           panel.setLayout(null);
          
           scrollPane = new JScrollPane();
           scrollPane.setBackground(new Color(255, 250, 250));
           scrollPane.setBounds(10, 208, 588, 313);
           contentPane.add(scrollPane);
          
           table = new JTable();
           table.setModel(new DefaultTableModel(
                   new Object[][] {
                          {" ", " ", " ", " ", " ", " ", " ", " "},
                   },
                   new String[] {
                          "일련번호", "우편번호", "시도", "구.군", "동", "리", "첫번째번지", "마지막번지"
                   }
           ) {
                   boolean[] columnEditables = new boolean[] {
                          false, false, false, false, false, false, false, false
                   };
                   public boolean isCellEditable(int row, int column) {
                          return columnEditables[column];
                   }
           });
          
           scrollPane.setViewportView(table);
          
          
           //첫번째 combobox 생성
           comboBox = new JComboBox();  
           comboBox.setBounds(146, 40, 100, 20);
           panel.add(comboBox);
           comboBox.addItem("시.도 선택");
          
           displaySido();
           //시.도 콤보박스=============================================
           comboBox.addItemListener(new ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
           if(e.getStateChange()==ItemEvent.SELECTED)
                   selectSido(comboBox.getSelectedItem().toString());
                         
                   }
           });
           comboBox.setToolTipText("");
          
          
           JLabel label = new JLabel("시.도");
           label.setBounds(146, 14, 100, 20);
           panel.add(label);
           label.setHorizontalAlignment(SwingConstants.CENTER);
          
           //구.군 ComboBox=============================================
           comboBox_1 = new JComboBox();
           comboBox_1.setBounds(258, 40, 100, 20);
           panel.add(comboBox_1);
          
           JLabel label_1 = new JLabel("구.군");
           label_1.setBounds(258, 14, 100, 20);
           panel.add(label_1);
           label_1.setHorizontalAlignment(SwingConstants.CENTER);
          
           comboBox_1.addItemListener(new ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                          if(e.getStateChange()==ItemEvent.SELECTED)
                                  selectGugun(comboBox.getSelectedItem().toString() ,comboBox_1.getSelectedItem().toString());
                   }
           });
          
           //동 ComboBox=============================================
           comboBox_2 = new JComboBox();
           comboBox_2.setBounds(370, 40, 100, 20);
           panel.add(comboBox_2);
          
           JLabel label_2 = new JLabel("동");
           label_2.setBounds(370, 14, 100, 20);
           panel.add(label_2);
           label_2.setHorizontalAlignment(SwingConstants.CENTER);
           
           comboBox_2.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                      if(e.getStateChange()==ItemEvent.SELECTED)
                     
                      //table에 집어넣기 실행=====================================
                      selectDong(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString());
               }             
       });
           
//           동이름 검색 패널
           JPanel panel_1 = new JPanel();
           panel_1.setBackground(new Color(255, 250, 250));
           panel_1.setBorder(new TitledBorder(null, "동이름 우편번호 검색", TitledBorder.LEADING, TitledBorder.TOP, null, null));
           panel_1.setBounds(6, 141, 594, 57);
           contentPane.add(panel_1);
           
           JLabel lblNewLabel = new JLabel("동이름");
           panel_1.add(lblNewLabel);
           
           tfDong = new JTextField(20);
           panel_1.add(tfDong);
           tfDong.setColumns(10);
           
           JButton btnDongSearce = new JButton("검색");
           panel_1.add(btnDongSearce);
           
          
           btnPostCheck.setBounds(204, 531, 212, 42);
           contentPane.add(btnPostCheck);
           
           btnDongSearce.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            searchDong(tfDong.getText());
         }
      });
           
    }
    
//    동이름으로 검색하는 메소드 
    public void searchDong(String dongName){
       //선언
        zipControl controller = new zipControl();
        //DB연결
        controller.connection();   
        
        //
        ArrayList<zipDAO> addressList = controller.searchKeyDong(dongName);
        Object[][] arrAdd = new Object[addressList.size()][8];
        for(int i = 0 ; i < addressList.size() ; i++){
                zipDAO zipcode = addressList.get(i);
                
                arrAdd[i][0] = zipcode.getSeq();
                arrAdd[i][1] = zipcode.getZipcode();
                arrAdd[i][2] = zipcode.getSido();
                arrAdd[i][3] = zipcode.getGugun();
                arrAdd[i][4] = zipcode.getDong();
                arrAdd[i][5] = zipcode.getRi();
                arrAdd[i][6] = zipcode.getSt_bunji();
                arrAdd[i][7] = zipcode.getEd_bunji();
               
                table.setModel(new zipTable(arrAdd));
                
        }             
        //DB연결 해제
        controller.disconnection();
    }
    
    //프로그램 시작시 시.도 보여주기====================================================================
    public void displaySido(){
           //선언
           zipControl controller = new zipControl();
           //DB연결
           controller.connection();             
           ArrayList<zipDAO> sidoList = controller.searchSido();
           for(int i = 0 ; i < sidoList.size() ; i++){
                   zipDAO zipcode = sidoList.get(i);
                   comboBox.addItem(zipcode.getSido());
           }             
           //DB연결 해제
           controller.disconnection();
    }
    //sido 선택(gugun 출력)====================================================================
    public void selectSido(String sido){
           System.out.println(sido);
           zipControl controller = new zipControl();
           //DB연결
           controller.connection();             
           //
           ArrayList<zipDAO> gugunList = controller.searchGugun(sido);
           comboBox_1.removeAllItems();
           comboBox_2.removeAllItems();
           comboBox_1.addItem("구.군 선택");
           for(int i = 0 ; i < gugunList.size() ; i++){
                   zipDAO zipcode = gugunList.get(i);
                   comboBox_1.insertItemAt(zipcode.getGugun(), i);
           }
           table.setModel(new zipTable());
           //DB연결 해제
           controller.disconnection();
    }      
    //gugun 선택(dong 출력)====================================================================
    public void selectGugun(String sido, String gugun){
           System.out.println(gugun);
           zipControl controller = new zipControl();
           //DB연결
           controller.connection();             
           //
           ArrayList<zipDAO> dongList = controller.searchDong(sido, gugun);
           comboBox_2.removeAllItems();
           comboBox_2.addItem("동 선택");
           for(int i = 0 ; i < dongList.size() ; i++){
                   zipDAO zipcode = dongList.get(i);
                   comboBox_2.insertItemAt(zipcode.getDong(),i);
           }
           table.setModel(new zipTable());
           //DB연결 해제
           controller.disconnection();                 
    }
   
    //마지막 Dong 선택(테이블에 출력)====================================================================
    public void selectDong(String sido, String gugun, String dong){
          System.out.println("Selected Dong : " + dong);
           zipControl controller = new zipControl();
           //DB연결
           controller.connection();             
           //
           ArrayList<zipDAO> addressList = controller.searchAddress(sido, gugun, dong);
          
           Object[][] arrAdd = new Object[addressList.size()][8];
          
           for(int i = 0 ; i < addressList.size() ; i++){
                   zipDAO zipcode = addressList.get(i);
                   //출력!
                   System.out.println(zipcode.getSeq() + " " + zipcode.getZipcode()+ " " +zipcode.getSido()+ " " +zipcode.getGugun()+ " " +zipcode.getDong() + " " + zipcode.getRi() + " " + zipcode.getSt_bunji() + " " + zipcode.getEd_bunji());                      
                   //테이블에 넣기!
                   arrAdd[i][0] = zipcode.getSeq();
                   arrAdd[i][1] = zipcode.getZipcode();
                   arrAdd[i][2] = zipcode.getSido();
                   arrAdd[i][3] = zipcode.getGugun();
                   arrAdd[i][4] = zipcode.getDong();
                   arrAdd[i][5] = zipcode.getRi();
                   arrAdd[i][6] = zipcode.getSt_bunji();
                   arrAdd[i][7] = zipcode.getEd_bunji();
                  
                   table.setModel(new zipTable(arrAdd));
                   System.out.println("table Setting ");
           }
           //DB연결 해제
           controller.disconnection();
          
    }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == btnPostCheck)
         {
      // TODO Auto-generated method stub
         
         int row = table.getSelectedRow();
         Object post = table.getValueAt(row, 1);
         Object sido = table.getValueAt(row, 2);
         Object gugun = table.getValueAt(row, 3);
         Object dong = table.getValueAt(row, 4);
         Object ri = table.getValueAt(row, 5);
         
         String allPost = ((String)gugun + ' ' + (String)dong + ' ' + (String)ri);
         
         this.setVisible(false);
         Design.txtpost.setText((String) post);
         Design.textArea.setText((String) allPost);
         Design.textField.setText((String) sido);
         
         }
      
   }
}