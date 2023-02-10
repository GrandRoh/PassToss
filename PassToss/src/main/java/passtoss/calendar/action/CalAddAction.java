package passtoss.calendar.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import passtoss.calendar.db.CalDAO;
import passtoss.calendar.db.CalVO;





public class CalAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
 		
 		String data = request.getParameter("alldata");
 		System.out.println(data);
 		
JSONParser jp = new JSONParser();
JSONObject jo = null;
try {
	jo = (JSONObject)jp.parse(data);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


CalDAO dao=new CalDAO();
	CalVO vo = new CalVO();
	
String title = (String)jo.get("title");
 		 
String start= jo.get("start").toString().substring(0,10);
 		
String end= jo.get("end").toString().substring(0,10);
vo.setTitle(title);
vo.setStart1(start);
vo.setEnd1(end);
dao.calAdd(vo);

return null;
		
		
		
}
}
