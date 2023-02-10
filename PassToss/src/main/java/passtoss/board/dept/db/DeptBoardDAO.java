package passtoss.board.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DeptBoardDAO {
	
	private DataSource ds;
	
	public DeptBoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int getListCount(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from board_dept "
									   + "where board_notice = ? and board_deptno = ?");
			pstmt.setInt(1, 1); // 게시글만 count함 
			pstmt.setInt(2, deptno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getListCount() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return x;
	}

	public List<DeptBoard> getBoardList(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_DEPT "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 0"
				+ "		and board_deptno = ? "
				+ "     and rownum <= 3) "
				+ " where rnum >= 1 and rnum <= 3";
		
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				board.setBoard_deptno(rs.getInt("board_deptno"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return list;
	}

	public List<DeptBoard> getBoardList(int page, int limit, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_DEPT "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	          ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 1 "
				+ "     and board_deptno = ? "
				+ "     and rownum <= ?) "
				+ " where rnum >= ? and rnum <= ?";
		
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				board.setBoard_deptno(rs.getInt("board_deptno"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return list;
	}

	public int getListCount(String field, String value, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int x = 0;
		
		try{
			
			con = ds.getConnection();
			
			if(field.equals("all")) {
				sql = " select count(*) "
					+ " from board_dept "
					+ " where(board_subject like ? or board_name like ?) "
					+ " and board_notice = ? "
					+ " and board_deptno = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, 1); // 게시글만 count함
				pstmt.setInt(4, deptno);
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				sql = "select count(*) from board_dept "
					+ "where " + field + " like ? "
					+ "and board_deptno = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
				pstmt.setInt(2, deptno);
				rs = pstmt.executeQuery();
			}
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception e)
		{
			System.out.println("getListCount() 에러: " + e);
		}
		finally
		{
			try
			{
				if(rs != null)
				rs.close();
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				if(pstmt != null)
				pstmt.close();
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				if(con != null)
				con.close();		
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return x;
	}

	public List<DeptBoard> getBoardList(String field, String value, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "     and board_deptno = ? "
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, deptno);
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "		and " + field + " like ? "
						+ "		and board_deptno = ? "
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setInt(2, deptno);
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				board.setBoard_deptno(rs.getInt("board_deptno"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return list;
	}

	public List<DeptBoard> getBoardList(String field, String value, int page, int limit, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "	    and board_deptno = ? "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, deptno);
				pstmt.setInt(4, endrow);
				pstmt.setInt(5, startrow);
				pstmt.setInt(6, endrow);
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ "		and " + field + " like ? "
						+ "		and board_deptno = ? "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setInt(2, deptno);
				pstmt.setInt(3, endrow);
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, endrow);
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				board.setBoard_deptno(rs.getInt("board_deptno"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return list;
	}

	public int boardInsert(DeptBoard board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			con = ds.getConnection();
			
			String sql = "insert into board_dept "
					   + "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
					   + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
					   + " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
					   + " BOARD_DATE, BOARD_NOTICE, BOARD_DEPTNO) "
					   + " values(fboard_seq.nextval, ?, ?, "
					   + " 		  ?, ?, fboard_seq.nextval, " 
					   + "		  ?, ?, ?, sysdate, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getBoard_notice());
			pstmt.setInt(9, board.getBoard_deptno());
			
			result = pstmt.executeUpdate();
			System.out.println("name : " + board.getBoard_name());
			if(result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
			
		}catch (Exception ex) {
			System.out.println("boardInsert() 에러: " + ex);
		}finally{
			
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

	public String getdname(int deptno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dname = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "select dname from dept where deptno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dname = rs.getString(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getdname() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return dname;
	}

	public void setReadCountUpdate(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update board_dept "
				   + "set BOARD_READCOUNT=BOARD_READCOUNT+1 "
				   + "where BOARD_NUM = ?";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println("setReadCountUpdate() 에러: " + ex);
		}finally{
			
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
		
	}

	public DeptBoard getDetail(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DeptBoard board = null; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "select * from board_dept where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new DeptBoard();
				
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
				board.setBoard_notice(rs.getInt("board_notice"));
				board.setBoard_notice(rs.getInt("board_deptno"));
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getDetail() 에러: " + ex);
		}finally{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
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
		return board;
	}

	public int boardModify(DeptBoard board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		
		try {
			
			con= ds.getConnection();
			
			String sql = "update board_dept "
					   + "set board_subject=?, board_content=?, board_file=?, board_notice =? "
					   + "where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_subject());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setString(3, board.getBoard_file());
			pstmt.setInt(4, board.getBoard_notice());
			pstmt.setInt(5, board.getBoard_num());
			
			result = pstmt.executeUpdate();
			System.out.println("dao 수정 result : " + result);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("boardModify() 에러: " + ex);
		}finally{
			
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
