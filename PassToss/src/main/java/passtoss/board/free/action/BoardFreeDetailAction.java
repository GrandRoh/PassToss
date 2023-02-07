package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.FreeBoard;
import passtoss.board.free.db.FreeBoardDAO;

public class BoardFreeDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FreeBoardDAO fdao = new FreeBoardDAO();
		ActionForward forward = new ActionForward();
		FreeBoard board = new FreeBoard();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		board.setBoard_readcount(num);
		
		board = fdao.getDetail(num);
		
		//int maxnum = fdao.getListCount();
		
		if(board == null) {
			System.out.println("게시글 불러오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("board", board);
		//request.setAttribute("maxnum", maxnum);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("free_board/freeContentView.jsp"); 
		return forward;
	}

}
