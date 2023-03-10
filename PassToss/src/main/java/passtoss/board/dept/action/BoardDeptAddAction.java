package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class BoardDeptAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoard board = new DeptBoard();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int deptno = (Integer)session.getAttribute("deptno");
		System.out.println("deptno = " + deptno);
		
		String realFolder="";
		
		String saveFolder="boardupload";
		
		int fileSize = 5*1024*1024; 
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		
		int result= 0;
		try {
			
			MultipartRequest multi = new MultipartRequest(
					request,
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			board.setBoard_notice(Integer.parseInt(multi.getParameter("board_notice")));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_name(id);
			
			if(!id.equals("admin")) {
			 board.setBoard_deptno(deptno);
			} else if(id.equals("admin")) {
				board.setBoard_deptno(Integer.parseInt(multi.getParameter("board_deptno")));
			}
			
			String filename = multi.getFilesystemName("board_file");
			board.setBoard_file(filename);
			
			result = dao.boardInsert(board);
			
			if(result == 0) {
				System.out.println("????????? ?????? ??????");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "????????? ??? ????????? ?????????????????????.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("????????? ?????? ??????");
			
			forward.setRedirect(true);
			forward.setPath("DeptList.bod"); 
			return forward;
		} catch(IOException ex) {
			ex.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "????????? ????????? ???????????????.");
			forward.setRedirect(false);
			return forward;
		} 
	}

}
