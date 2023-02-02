package passtoss.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.Member;

public class JoinListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		List<Member> list = dao.getMemberList();

		request.setAttribute("joinlist", list);
		
		forward.setPath("AdminPage/joinList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
