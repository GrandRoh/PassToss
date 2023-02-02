package passtoss.member.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import passtoss.member.db.Member;

public class MemberDAO {

	private DataSource ds;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}

	public List<Member> getMemberList() {// 회원가입 정보 조회
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();

			String sql = "select * from member " 
					   + "where authority = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setJumin("jumin");
				m.setDeptno(rs.getInt("deptno"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setAuthority(rs.getInt("authority"));
				m.setProfileImg(rs.getString("profifleImg"));
				list.add(m);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getMemberList() 에러: " + se);
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
