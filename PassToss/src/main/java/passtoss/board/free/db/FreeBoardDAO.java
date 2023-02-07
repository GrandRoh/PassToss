package passtoss.board.free.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FreeBoardDAO {
	
	private DataSource ds;
	
	public FreeBoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int getListCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from board_free");
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

	//일반 게시물 
	public List<FreeBoard> getBoardList(int page, int limit) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_FREE "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	      ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 1"
				+ "     and rownum <= ?) "
				+ " where rnum >= ? and rnum <= ?";
		
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
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
	
	//공지사항 
	public List<FreeBoard> getBoardList() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_FREE "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	      ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 0"
				+ "     and rownum <= 3) "
				+ " where rnum >= 1 and rnum <= 3";
		
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
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

	public int getListCount(String field, String value) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int x = 0;
		
		try{
			
			con = ds.getConnection();
			
			if(field.equals("all")) {
				sql = " select count(*) "
					+ " from board_free "
					+ " where(board_subject like ? or board_name like ?)";	
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
				pstmt.setString(2, "%"+value+"%");
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				sql = "select count(*) from board_free "
					+ "where " + field + " like ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
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
	
	//공지사항 
	public List<FreeBoard> getBoardList(String field, String value) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "		and " + field + " like ?"
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
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

	//일반게시물 
	public List<FreeBoard> getBoardList(String field, String value, int page, int limit) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, endrow);
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, endrow);
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ "		and " + field + " like ?"
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setInt(2, endrow);
				pstmt.setInt(3, startrow);
				pstmt.setInt(4, endrow);
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
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

	public int boardInsert(FreeBoard board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			con = ds.getConnection();
			
			String sql = "insert into board_free "
					   + "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
					   + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
					   + " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
					   + " BOARD_DATE, BOARD_NOTICE) "
					   + " values(fboard_seq.nextval, ?, ?, "
					   + " 		  ?, ?, fboard_seq.nextval, "
					   + "		  ?, ?, ?, sysdate, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getBoard_notice());
			
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

	public FreeBoard getDetail(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoard board = null; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "select * from board_free where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new FreeBoard();
				
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
}
