package passtoss.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.admin.board.Board;
import passtoss.admin.board.BoardDAO;

public class AdminBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		List<Board> boardlist = null;

		int page = 1;
		int limit = 10;
		if (request.getParameter("page") == null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limmit = " + limit);

		int listcount = dao.getListCount();
		boardlist = dao.getBoardList(page, limit);

		return null;
	}

}
