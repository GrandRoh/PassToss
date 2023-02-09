package passtoss.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonObject;

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

	public int getListCount(int authority) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listcount = 0;

		try {
			conn = ds.getConnection();

			String sql = "select count(*) from member "
						+ "where authority = ? "
						+ "order by joindate desc";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listcount = rs.getInt(1);
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
		return listcount;
	}

	public List<Member> getMemberList(int page, int limit, int authority) {// 회원가입 정보 조회
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();

			String sql = "select * "
						+ "from (select m.*, rownum r "
						+ "		 from (select * from member "
						+ "		 	   where authority = ? "
						+ "			   order by joindate desc) m "	//가장 최근에 가입한 순으로 정렬
						+ "		 where rownum <= ? "
						+ "		) "
						+ "where r between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(1, authority);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);			
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
				m.setProfileImg(rs.getString("profileImg"));
				m.setJoindate(rs.getString("joindate"));
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

	public int getListCount(String field, String value, int authority) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from member "
					   + "where authority = ? "
					   + "and " + field + " like ? "
					   + "order by joindate desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			pstmt.setString(2, "%" + value + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listcount = rs.getInt(1);
			}
		} catch (Exception se) {
			se.printStackTrace();
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
		return listcount;
	}

	public List<Member> getMemberList(String field, String value, int page, int limit, int authority) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();

			String sql = "select * "
						+ "from (select m.*, rownum r "
						+ "		 from (select * from member "
						+ "		 	   where authority = ?"
						+ "			   and " + field + " like ? "
						+ "			   order by joindate desc) m "
						+ "		 where rownum <= ? "
						+ "		) "
						+ "where r between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			pstmt.setString(2, "%" + value + "%");
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(3, endrow);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
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
				m.setProfileImg(rs.getString("profileImg"));
				m.setJoindate(rs.getString("joindate"));
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

	public int authorize(String[] id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int count = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			System.out.println("id.length="+id.length);
			for (int i = 0; i < id.length; i++) {
				String sql = "update member " 
							+ "set authority = 1 "
							+ "where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id[i]);
				result = pstmt.executeUpdate();
				pstmt.close();
				
				if (result == 1)
					count++;
				if (i == id.length-1) {
					if (count != id.length) {
						conn.rollback();
						System.out.println("가입승인 중 문제가 발생했습니다.");
					} else {
						conn.commit();
						System.out.println("commit 됨");
					}
				}
				
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("authorize() 에러: " + se);
		} finally {
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
		return count;
	}

	public JsonObject member_info(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JsonObject obj=null;
		try {
			conn = ds.getConnection();

			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				obj=new JsonObject();
				obj.addProperty("id", id);
				obj.addProperty("password", rs.getString("password"));
				obj.addProperty("name", rs.getString("name"));
				obj.addProperty("jumin", rs.getString("jumin"));
				obj.addProperty("deptno", rs.getString("deptno"));
				obj.addProperty("email", rs.getString("email"));
				obj.addProperty("phone", rs.getString("phone"));
				obj.addProperty("address", rs.getString("address"));
				obj.addProperty("authority", rs.getString("authority"));
				obj.addProperty("profileImg", rs.getString("profileImg"));
				obj.addProperty("joindate", rs.getString("joindate").substring(0, 10));
			}

		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("member_info() 에러: " + se);
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
		return obj;
	}

	public int isId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;

		int result = -1; // DB에 해당 id가 없습니다
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			while (rs.next()) {
				result = 0 ; // DB에 해당 id가 있습니다
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

		return result;
	}

	public int insert(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			
			conn = ds.getConnection();
			
			String sql = "insert into member (id,password,name,jumin,deptno,email,phone,address,AUTHORITY,post,PROFILEIMG)"
					+ " values ( ? , ?, ?, ? ,? ,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getJumin());
			pstmt.setInt(5, m.getDeptno());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setInt(9, 0);
			pstmt.setInt(10, m.getPost());
			pstmt.setString(11, m.getProfileImg());
	
			result = pstmt.executeUpdate();
			
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("아이디 중복 에러입니다" + e);
		
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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

	public int isId(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = -1; 
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				if(rs.getString(2).equals(pass)) {
					result = 1 ; 
				}else {
					result = 0 ; 
				}

				
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

		return result;

	}

	public String getprofileimg(String id) {
		String img_src = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn = ds.getConnection();
			
			String sql = "select PROFILEIMG from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				img_src = rs.getString(1);
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

		return img_src;
	}

	public List<Member> memberinfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;
		List<Member> list =  new ArrayList<Member>();
 		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setDeptno(rs.getInt("DEPTNO"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("ADDRESS"));
				m.setPost(rs.getInt("post"));
				m.setProfileImg(rs.getString("PROFILEIMG"));
				
				list.add(m);
				
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

}
