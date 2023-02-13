package passtoss.calendar.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/

import passtoss.calendar.db.CalDAO;
import passtoss.calendar.db.CalVO;





public class CalAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
 		
 		String data = request.getParameter("alldata");
 		System.out.println(data);
 		
 		
 		JsonElement element = JsonParser.parseString(data);
 		 JsonObject jo = element.getAsJsonObject();


CalDAO dao=new CalDAO();
	CalVO vo = new CalVO();
	
String title = jo.get("title").toString().replaceAll("\"","");
 		 
String start= jo.get("start").toString().replaceAll("\"","").substring(0,10);
 		
String end= jo.get("end").toString().replaceAll("\"","").substring(0,10);

System.out.println(title);
System.out.println(start);
System.out.println(end);


vo.setTitle(title);
vo.setStart1(start);
vo.setEnd1(end);

dao.calAdd(vo);

return null;


		
	
		
}
}
