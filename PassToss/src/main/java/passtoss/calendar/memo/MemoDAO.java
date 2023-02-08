package passtoss.calendar.memo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




 
public class MemoDAO {
	private DataSource ds;

	public MemoDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
    // 삽입
    public int memoInsert(MemoVO v) {
 
        Connection con = null;
        PreparedStatement pstmt = null;
        int result=0;
        
        try {
 
            con =  ds.getConnection();
            
            String sql = "insert into memo (memo_num, id, memo, reg_date) " 
            		+ "values(memo_seq.nextval, ?, ?, sysdate)";
            pstmt = con.prepareStatement(sql);
 
            pstmt.setString(1, v.getId());
            pstmt.setString(2, v.getMemo());
            result= pstmt.executeUpdate();
            if(result == 1)
	            System.out.println("데이터 삽입 완료되었습니다.");
	         
	      }catch(Exception e)
	      {
	         e.printStackTrace();
	         System.out.println("memoInsert() 에러 : " + e);
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
    
 
    // 리스트
    public List<MemoVO> memoList() {
 
        List<MemoVO> items = new ArrayList<MemoVO>();
 
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
 
        try {
 
            // DB가 static이라서 바로 접근 가능하다.
 
            conn =ds.getConnection(); // db연결자 리턴
 
            String sql = "select * from memo order by memo_num";
 
            pstmt = conn.prepareStatement(sql);
 
            // 결과값 리턴
            rs = pstmt.executeQuery();
 
            // 다 읽어 와라 결과셋.next() 다음 레코드가 있으면 true
            while (rs.next()) {
 
                // 데이터 그릇을 만들어서 oracle에서 불러온 데이터들을 담아준다.
                MemoVO vo = new MemoVO();
 
                // 결과셋.get자료형("칼럼이름")
                vo.setMemo_num(rs.getInt("memo_num"));
                vo.setId(rs.getString("id"));
 
                // 부등호 문자 &lt ; Less Than <
                // &gt; Greater Than >
 
                String memo = rs.getString("memo");
                // 태그 문자 처리
 
                memo = memo.replace("<", "&lt;");
                memo = memo.replace(">", "&gt;");
                // 공백문자 처리
                memo = memo.replace("  ", "&nbsp;&nbsp;");
 
                // 줄바꿈 문자처리
                memo = memo.replace("\n", "<br>");
 
                vo.setMemo(memo);
 
                vo.setReg_date(rs.getString("reg_date"));
 
                // list에 쌓인다.
                items.add(vo);
            }
 
        } catch (Exception e) {
 
            e.printStackTrace();
        } finally {// 예외 발생여부와 상관없이 항상 실행
 
            // resultset= > statement=> connection
 
            try {
                if (rs != null) {
                    rs.close();
                }
 
            } catch (Exception e2) {
                e2.printStackTrace();
            }
 
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
 
            } catch (Exception e2) {
                e2.printStackTrace();
            }
 
            try {
                if (conn != null) {
                    conn.close();
                }
 
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return items;
 
    }
	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from memo");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러" + ex);
		} finally {
			if (rs != null) // null이 아니면 클로즈해라
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			if (con != null)
				try {
					con.close();// 4단계 : DB연결 끊는다

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
		return x;

	}
	
	public List<MemoVO> getMemoList(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// page: 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_sq asc에 의해 정렬한 것ㅇ르
		// 조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문입니다.

		String sql = "select * from memo ";

		System.out.println(sql);
		List<MemoVO> list = new ArrayList<MemoVO>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row번호(1 11 21 31 ...
		int endrow = startrow + limit - 1; // 읽을 마지막 row번호(10 20 30 40...
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			// db에서 가져온 데이터를 VO객체에 담습니다.
			while (rs.next()) {
				MemoVO vo = new MemoVO();
				vo.setMemo_num(rs.getInt("memo_num"));
				vo.setId(rs.getString("id"));
				vo.setMemo(rs.getString("memo"));
				vo.setReg_date(rs.getString("reg_date"));
				
				list.add(vo); // 값을 담은 객체를 리스트에 저장합니다.

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getMemo() 에러" + ex);
		} finally {
			if (rs != null) // null이 아니면 클로즈해라
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			if (con != null)
				try {
					con.close();// 4단계 : DB연결 끊는다

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
		return list;
	}
	public int memoDelete(int memo_num) {
		Connection con = null;
	      PreparedStatement pstmt = null;
	      int result = 0;
	      
	      try {
	         con = ds.getConnection();
	         
	         String sql = "delete memo where memo_num = ? ";
	        		 	
	         
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, memo_num);
	         result= pstmt.executeUpdate();
	         
	         if(result == 1)
	         System.out.println("데이터 삭제되었습니다.");
	         
	      }catch(Exception e)
	      {
	         e.printStackTrace();
	         
	      }finally  {
	         
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
	public int getListCount(String string, String search_word) {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<MemoVO> getMemoList(String string, String search_word, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
}
 