package prac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class zipControl {
	Customer customer = new Customer();
         Connection conc;
         //Statement pstmt;
         PreparedStatement pstmt;
         ResultSet rs;

         private String seq;
         private String zipcode;
         private String sido;
         private String gugun;
         private String dong;
         private String ri;
         private String St_bunji;
         private String Ed_bunji;
         
         public String getSeq() {
         	return seq;
         }
         public void setSeq(String seq) {
         	this.seq = seq;
         }
         public String getZipcode() {
         	return zipcode;
         }
         
         public void setZipcode(String zipcode) {
         	this.zipcode = zipcode;
         }
       //각각의 주소 단위를 String형태로 지정해주어 문자열로 받아올수 있도록 하였다.
       //또한 get()과 set()을 이용하여 데이터 값을 반환하거나 셋팅시켜주는 메소드를 추가시켜주었다.
         
         
         // 데이터베이스 연결
         public void connection() {
        	 try {
        		 conc = customer.getConnection();
     		}
     		catch(Exception e) {
     		}
         }
         
         // 데이터베이스 연결종료
         public void disconnection() {
                  try {
                           if(pstmt != null) pstmt.close();
                           if(rs != null) rs.close();
                           if(conc != null) conc.close();
                  } catch (SQLException e) {
                  }
         }
         
         // 시도데이터=============================================
         public ArrayList<zipDAO> searchSido() {
                  ArrayList<zipDAO> sidoList = new ArrayList<>();
                  try {
                	  String quer = "SELECT DISTINCT sido FROM post ORDER BY sido";
                	  pstmt = conc.prepareStatement(quer);
                	  rs = pstmt.executeQuery();
                           //pstmt = conc.createStatement();
                           //rs = pstmt.executeQuery("SELECT DISTINCT(sido) FROM post ORDER BY sido");
                           while(rs.next()){
                                   zipDAO zipcode = new zipDAO();
                                   zipcode.setSido(rs.getString("sido"));
                                   sidoList.add(zipcode);
                           }
                  } catch (SQLException e) {
                  }
                  return sidoList;
                  
         }

         // 구군데이터=============================================
         public ArrayList<zipDAO> searchGugun(String sido) {
                  ArrayList<zipDAO> gugunList = new ArrayList<>();
                  try {
                	  //pstmt = conc.createStatement();
                      //rs = pstmt.executeQuery("select distinct (gugun) from post where sido = \'" + sido + "\' order by gugun");
                           String quer = "select distinct (gugun) from post where sido = \'" + sido + "\' order by gugun";
                           pstmt = conc.prepareStatement(quer);
                           rs = pstmt.executeQuery();
                           while(rs.next()){
                                   zipDAO zipcode = new zipDAO();
                                   zipcode.setGugun(rs.getString("GUGUN"));
                                   gugunList.add(zipcode);
                           }
                  } catch (SQLException e) {
                  }
                  return gugunList;         
         }

         // 동데이터=============================================
         public ArrayList<zipDAO> searchDong(String sido, String gugun) {
                  ArrayList<zipDAO> dongList = new ArrayList<>();
                  try {
                	  //pstmt = conc.createStatement();
                      //rs = pstmt.executeQuery("select distinct (dong) from post where sido = \'" + sido + "\'  and gugun = \'" + gugun + "\' order by dong");
                          String quer = "select distinct (dong) from post where sido = \'" + sido + "\'  and gugun = \'" + gugun + "\' order by dong";
                         pstmt = conc.prepareStatement(quer);
                         rs = pstmt.executeQuery();
                           while(rs.next()){
                                   zipDAO zipcode = new zipDAO();
                                   zipcode.setDong(rs.getString("DONG"));
                                   dongList.add(zipcode);
                           }
                  } catch (SQLException e) {
                  }
                  return dongList;          
         }

         // 전부주소 데이터 =============================================
         public ArrayList<zipDAO> searchAddress(String sido, String gugun, String dong) {
                  ArrayList<zipDAO> addressList = new ArrayList<>();
                  try {
                	 // pstmt = conc.createStatement();
                     // rs = pstmt.executeQuery("select * from post where sido = \'" + sido + "\'  and gugun = \'" + gugun + "\' and dong = \'" + dong +"\'");
                        
                          String quer = "select * from post where sido = \'" + sido + "\'  and gugun = \'" + gugun + "\' and dong = \'" + dong +"\'";
                         pstmt = conc.prepareStatement(quer);
                         rs = pstmt.executeQuery();
                           while(rs.next()){
                                   zipDAO zipcode = new zipDAO();
                                   zipcode.setSeq(rs.getString("seq"));
                                   zipcode.setZipcode(rs.getString("zipcode"));
                                   zipcode.setSido(rs.getString("sido"));
                                   zipcode.setGugun(rs.getString("gugun"));
                                   zipcode.setDong(rs.getString("dong"));
                                   zipcode.setRi(rs.getString("ri"));
                                   zipcode.setSt_bunji(rs.getString("st_bunji"));
                                   zipcode.setEd_bunji(rs.getString("ed_bunji"));
                                   addressList.add(zipcode);
                           }
                  } catch (SQLException e) {
                  }
                  return addressList;
         }
         public ArrayList<zipDAO> searchKeyDong(String dongName) {
     		
     		ArrayList<zipDAO> addressList = new ArrayList<>();

         	try {
         		//pstmt = conc.createStatement();
               // rs = pstmt.executeQuery("select * from zipcode where dong like \'%\' \'" + dongName + "\' || \'%\'");
                  
         		String quer = "select * from post where dong like \'%\' \'" + dongName + "\' || \'%\'";
         		pstmt = conc.prepareStatement(quer);
         		rs = pstmt.executeQuery();

         		while(rs.next()){

         			zipDAO zipcode = new zipDAO();
         			
         			zipcode.setSeq(rs.getString("seq"));
                     zipcode.setZipcode(rs.getString("zipcode"));
                     zipcode.setSido(rs.getString("sido"));
     	            zipcode.setGugun(rs.getString("gugun"));
     	            zipcode.setDong(rs.getString("dong"));
     	            zipcode.setRi(rs.getString("ri"));
     	            zipcode.setSt_bunji(rs.getString("st_bunji"));
     	            zipcode.setEd_bunji(rs.getString("ed_bunji"));
     	            
     	            addressList.add(zipcode);
         		}
         	} catch (SQLException e) {
         		e.printStackTrace();
         	}
                  
         	return addressList;  
     	}
     }

