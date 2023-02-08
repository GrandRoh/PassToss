package passtoss.calendar.memo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






public class MemoList implements Action {
	
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		MemoDAO dao=new MemoDAO();
		
		int page = 1;
		int limit = 10;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
			
		}
		System.out.println("넘어온 페이지 =" + page);
		
		List<MemoVO> list = null;
		int listcount = 0;
		int index=-1; //search_field에 존재하지 않는 값으로 초기화
		 
	
			listcount=dao.getListCount();
			list=dao.getMemoList(page, limit);
		
		
		int maxpage=(listcount + limit -1 )/limit;
		System.out.println("총 페이지 수 =" + maxpage);
		
		int startpage = ((page-1)/10)*10+1;
		int endpage = startpage+10-1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 ="+endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 ="+ startpage);
		
		if(endpage > maxpage) endpage=maxpage;
		
		request.setAttribute("page", page); //현재페이지수
		request.setAttribute("maxpage", maxpage); //최대페이지수
		
		//현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		//현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		
		
		request.setAttribute("listcount", listcount);//총 글의 수
		request.setAttribute("totallist", list);
	
	
		
		forward.setPath("calendar.jsp");
		forward.setRedirect(false);
		return forward;
		}
	}
	