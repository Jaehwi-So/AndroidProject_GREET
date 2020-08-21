package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NametagInput {
	
	private static NametagInput nametag_input = new NametagInput();
	
	private NametagInput() {
		
	}
	
	public static NametagInput getWriter() {
		return nametag_input;
	}
	
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	String returns = "";
		
	//DB에 데이터 추가
	public String write( String key, String image ) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle_test");
			conn = ds.getConnection();
			
			String sql = "insert into NAMETAG values(?, ?)";
			
			//쿼리문 수행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, image);
			pstmt.executeUpdate();
			
			returns = String.format("{res:[{'result':'%s'}]}", "success" );
						
		} catch (Exception e) {
			returns = String.format("{res:[{'result':'%s'}]}", "fail" );
		}finally {
			
			try {
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
