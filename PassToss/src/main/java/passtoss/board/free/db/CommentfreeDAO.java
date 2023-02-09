package passtoss.board.free.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentfreeDAO {
	
	private DataSource ds;
	
	public CommentfreeDAO() {
		
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int commentsInsert(Commentfree co) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "insert into comment_free "
					   + "values(fcom_seq.nextval, ?, ?, sysdate, ?, ?, ?, fcom_seq.nextval)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getId());
			pstmt.setString(2, co.getContent());
			pstmt.setInt(3, co.getComment_board_num());
			pstmt.setInt(4, co.getComment_re_lev());
			pstmt.setInt(5, co.getComment_re_seq());
			
			result = pstmt.executeUpdate();
			
			if(result ==1 ) {
				System.out.println("데이터 삽입 완료되었습니다.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("commentsInsert() 에러 : " + e);
		}
		finally
		{
			
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return result;
	}
	
	

}
