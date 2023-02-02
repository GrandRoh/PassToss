package passtoss.Business_status.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Business_status_DAO {

	private DataSource ds;
	
	
	public Business_status_DAO() {
		try {
			Context init =new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
		
	}


	public int insert(Business_status_Bean bsb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		int result = 0; 
		try {
			
			conn = ds.getConnection();
			
			String sql = "insert into business_status (MEMO_SEQ , MEMO_ID,MEMO_CONTENT  "
					+ ",board_date,limit_DATE, STATUS , PRIORITY) values "
					+ "( (SELECT NVL(MAX(MEMO_SEQ), 0) + 1 FROM business_status),?,?,sysdate,?,?,?)";
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, bsb.getMemo_id());
			pstmt.setString(2, bsb.getMemo_content());
			pstmt.setString(3, bsb.getLimit_date());
			pstmt.setInt(4, bsb.getStatus());
			pstmt.setInt(5, bsb.getPriority());
	

				
			result = pstmt.executeUpdate();
			if(result ==1) {
				System.out.println("데이터 모두삽입완료");
			}
			
			
		}catch(Exception se){
			System.out.println("boardInsert 에러:" + se);
			
		}finally {
		
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return result;
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;

		int x = 0; 
		try {
			
			conn = ds.getConnection();
			
			String sql = "select count(*) from business_status";
			pstmt = conn.prepareStatement(sql.toString());
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				x = rs.getInt(1);
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}

		return x;
	}

	public List<Business_status_Bean> getMemoList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;
			
			String sql = "select * from business_status";
                   


			List<Business_status_Bean> list = new ArrayList<Business_status_Bean>();

			
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setInt(1, id); 추후 아이디추가
	
			rs= pstmt.executeQuery();
			
				
			while (rs.next()) {
				Business_status_Bean bsb = new Business_status_Bean();
				bsb.setMemo_seq(rs.getInt("memo_seq"));
				bsb.setMemo_content(rs.getString("memo_content"));
				bsb.setLimit_date(rs.getString("limit_date"));
				bsb.setStatus(rs.getInt("status"));
				bsb.setPriority(rs.getInt("Priority"));
				list.add(bsb);
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}

		return list;
	}


	public int delete(int memo_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		int result = 0; 
		try {
			
			conn = ds.getConnection();
			
			String sql = "delete from business_status where memo_seq = ?";
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, memo_seq);
	

				
			result = pstmt.executeUpdate();
			if(result ==1) {
				System.out.println("삭제 완료");
			}
			System.out.println(result);
			
		}catch(Exception se){
			System.out.println("delete 에러:" + se);
			
		}finally {
		
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return result;
	}
}
