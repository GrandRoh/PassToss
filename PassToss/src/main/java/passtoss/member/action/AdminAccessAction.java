package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.MemberDAO;

public class AdminAccessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String[] id = request.getParameterValues("select");

		int result = dao.authorize(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == id.length) {
			out.println("alert('" + result + "명의 가입이 승인되었습니다.');");
			out.println("location.href='AdminJoinList.net'");
		} else {
			out.println("alert('가입승인에 실패했습니다.');");
			out.println("history.back();");
		}

		out.println("</script>");
		out.close();
		return null;
	}

}
