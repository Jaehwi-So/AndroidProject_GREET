package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NametagOutput {
	
	private static NametagOutput nametag_output = new NametagOutput();
	
	private NametagOutput() {
		
	}
	
	public static NametagOutput getReader() {
		return nametag_output;
	}
	
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	String returns = "";
		
	//DB에서 데이터 가져옴
	public String read( String key ) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle_test");
			conn = ds.getConnection();
			
			String sql = "select * from NAMETAG where key=?";
			
			//쿼리문 수행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			
			rs = pstmt.executeQuery();
			if(rs == null) {
				System.out.println("null");
			}
			int cnt = 0; 
			String res_key = "";
			String res_photo = "";
			while( rs.next() ) {
				res_key = rs.getString("key");
				res_photo = rs.getString("image");
				cnt++;
			}
			System.out.println("여기까지 됨");
			if( cnt == 1 ) { //정보검색 성공한 경우
				returns = String.format("{'result':'%s', 'key':'%s', 'image':'%s'}", "success", res_key, res_photo );
			}else {
				returns = String.format("{'result':'%s', 'key':'%s', 'image':'%s'}", "fail", res_key, res_photo );
			}			
						
		} catch (Exception e) {
			
		}finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return returns;
		
	} //write
	
	
}
