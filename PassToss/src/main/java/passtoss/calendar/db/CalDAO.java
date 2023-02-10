package passtoss.calendar.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



public class CalDAO {

	private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	
	public CalDAO() {
		
		try {
			Context init = new InitialContext();
			ds=(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 :" +ex);
		}
	}
	
	public JsonArray calList(){

		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		JsonArray array = new JsonArray();
		try {
			
		con=ds.getConnection();
		
		String sql= "select * from calendar";
			
		pstmt = con.prepareStatement(sql);
		
		rs=pstmt.executeQuery();
		
	
		while(rs.next()) {//더이상 읽을 데이터가 없을 때까지 반복
			
			JsonObject json=new JsonObject();
			json.addProperty("title", rs.getString(1));
			json.addProperty("start", rs.getString(2));
			json.addProperty("end", rs.getString(3));
			
			
			array.add(json);
		}
		}catch(Exception se) {
			se.printStackTrace();
		}finally {
			try {
				if(rs !=null)
					rs.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt!=null)
					pstmt.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(con !=null)
				con.close();//4단계 : DB연결 끊는다
			
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		}
		return array;
	}

	

	public int calAdd(CalVO v) {
		Connection con = null;
        PreparedStatement pstmt = null;
        int result=0;
        
        try {
 
            con =  ds.getConnection();
            
            String sql = "insert into calendar (title, start1, end1) " 
            			+ "values(?, ?, ?)";
            pstmt = con.prepareStatement(sql);
 
            pstmt.setString(1, v.getTitle());
            pstmt.setString(2, v.getStart1());
            pstmt.setString(3, v.getEnd1());
            
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

}