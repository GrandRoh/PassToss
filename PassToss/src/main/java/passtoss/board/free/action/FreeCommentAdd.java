package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.Commentfree;
import passtoss.board.free.db.CommentfreeDAO;

public class FreeCommentAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentfreeDAO fdao = new CommentfreeDAO();
		Commentfree co = new Commentfree();
		
		co.setId(request.getParameter("id"));
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());
		
		co.setComment_re_lev(Integer.parseInt("comment_re_lev"));
		co.setComment_re_seq(Integer.parseInt("comment_re_seq"));
		co.setComment_board_num(Integer.parseInt("Comment_board_num"));
		
		int ok = fdao.commentsInsert(co);
		response.getWriter().print(ok);
		
		return null;
	}

}
