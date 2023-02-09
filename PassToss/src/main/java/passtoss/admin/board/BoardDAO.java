package passtoss.admin.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}

	public int getListCount(String table) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;// DB에 해당 id가 없습니다.
		try {
			conn = ds.getConnection();

			String sql = "select count(*) from " + table;

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				x += rs.getInt(i);
				System.out.println("x = " + x);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getListCount() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return x;
	}

	public List<Board> getfreeBoardList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select *"
				+ "				 from(select rownum rnum, j.*"
				+ "     			  from(SELECT board_free.*, NVL(CNT,0)CNT"
				+ "     	  			   FROM board_free LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       					FROM comment_free"
				+ "				 	          		       					GROUP BY COMMENT_BOARD_NUM)"
				+ "	      				   ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      			   ORDER BY BOARD_RE_REF DESC,"
				+ "	      				   BOARD_RE_SEQ ASC) j"
				+ "	 				 where board_notice = 1"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getBoardlist() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}

}
