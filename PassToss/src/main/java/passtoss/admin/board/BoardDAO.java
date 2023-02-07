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

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;// DB에 해당 id가 없습니다.
		try {
			conn = ds.getConnection();

			String sql = "select count(*) from board_free " + "union all " + "select count(*) from board_dept";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1) + rs.getInt(2);
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

	public List<Board> getBoardList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select * "
				+ " from (select rownum rnum, j.* "
				+ "		  from (select board.*, nvl(cnt, 0) as cnt "
				+ "				from board left outer join (select comment_board_num, count(*) as cnt "
				+ "				 							from comm "
				+ "				 							group by comment_board_num) "
				+ "				on board_num = comment_board_num "
				+ "				order by board_re_ref desc, "
				+ "				board_re_seq asc) j "
				+ "		 where rownum <= ? "
				+ "		)"
				+ " where rnum >= ? and rownum <= ?";

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

			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); // 값을 담은 객체를 리스트에 저장합니다.
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
