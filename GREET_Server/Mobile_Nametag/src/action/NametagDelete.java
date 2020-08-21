package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NametagDelete {
private static NametagDelete nametag_delete = new NametagDelete();
	
	private NametagDelete() {
		
	}
	
	public static NametagDelete getDeleter() {
		return nametag_delete;
	}
	
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	String returns = "";
		
	//DB에 데이터 추가
	public String delete( String key ) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle_test");
			conn = ds.getConnection();
			
			String sql = "delete from NAMETAG where key=?";
			
			//쿼리문 수행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			int res = pstmt.executeUpdate();
			if(res == 1) {
				returns = String.format("{res:[{'result':'%s'}]}", "success" );
			}
			else {
				returns = String.format("{res:[{'result':'%s'}]}", "fail" );
			}
						
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
