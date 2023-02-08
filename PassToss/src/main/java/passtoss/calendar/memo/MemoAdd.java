package passtoss.calendar.memo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemoAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemoDAO dao=new MemoDAO();
		
		MemoVO vo = new MemoVO();
		vo.setId(request.getParameter("id"));
		vo.setMemo(request.getParameter("memo"));
		System.out.println("memo=" + vo.getMemo());
		vo.setMemo_num(Integer.parseInt(request.getParameter("memo_num")));

		int ok = dao.memoInsert(vo);

		response.getWriter().print(ok);
		return null;
		
		
		
}
}
