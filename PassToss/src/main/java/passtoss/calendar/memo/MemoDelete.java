package passtoss.calendar.memo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MemoDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memo_num=Integer.parseInt(request.getParameter("memo_num"));
		
		MemoDAO dao = new MemoDAO();
		
		int result=dao.memoDelete(memo_num);
		PrintWriter out = response.getWriter();
		
		out.print(result);
		return null;
	}

}
