package prac;

import java.io.*;
import java.sql.*;

public class Insert {
	public static void main(String[] args) throws Exception {
			Connection conn = Customer.getConnection();
			PreparedStatement stmt = null;
		try {
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement("" +
					"INSERT INTO post" +
					"(seq, zipcode, sido, gugun, dong, ri, st_bunji, ed_bunji) " +
					"VALUES" +
					"(?,?,?,?,?,?,?,?)");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data/post1.txt"), "euc-kr"));
			try {
				int totCnt = 0;
				int cnt = 0;
				String s;
				long start = System.nanoTime();
				while ((s = reader.readLine()) != null) {
					totCnt++;
					int charCnt = 0;
					int seq = totCnt;
					String zipcode = s.substring(0,7);
					charCnt = zipcode.length() +1;
					String sido = s.substring(charCnt, s.indexOf('|', charCnt));
					charCnt += sido.length() +1;
					String gugun = s.substring(charCnt, s.indexOf('|', charCnt));
					charCnt += gugun.length() +1;
					String dong = s.substring(charCnt, s.indexOf('|', charCnt));
					charCnt += dong.length() +1;
					String ri = s.substring(charCnt, s.indexOf('|', charCnt));
					charCnt += ri.length() +1;
					String st_bunji = s.substring(charCnt, s.indexOf('|', charCnt));
					charCnt += st_bunji.length() +1;
					String ed_bunji = s.substring(charCnt, s.indexOf('|', charCnt));
					stmt.setInt(1, seq);
					stmt.setString(2, zipcode.trim());
					stmt.setString(3, sido.trim());
					stmt.setString(4, gugun.trim());
					stmt.setString(5, dong.trim());
					stmt.setString(6, ri.trim());
					stmt.setString(7, st_bunji.trim());
					stmt.setString(8, ed_bunji.trim());
					stmt.addBatch();
					cnt++;
					
					if (cnt == 1000) {
						cnt = 0;
						stmt.executeBatch();
					}	
				}
				if (cnt > 0)
					stmt.executeBatch();
				long end = System.nanoTime();
				System.out.println((end - start) + "(ns)");
			} finally {
				reader.close();
			}
			conn.commit();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			conn.close();
		}
	}
}
