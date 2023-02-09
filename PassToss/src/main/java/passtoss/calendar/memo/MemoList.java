package passtoss.calendar.memo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MemoList implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemoDAO dao = new MemoDAO();

		List<MemoVO> memolist = new ArrayList<MemoVO>();

		System.out.println("dg");
		int page = 1;
		int limit = 5;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		System.out.println("넘어온 페이지 =" + page);

		// 추가
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit =" + limit);

		int listcount = dao.getListCount();

		memolist = dao.getMemoList(page, limit);
		
		System.out.println("++" + memolist);

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지 수 =" + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 =" + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startpage);

		if (endpage > maxpage)
			endpage = maxpage;

		request.setAttribute("page", page); // 현재 페이지수
		request.setAttribute("maxpage", maxpage); // 최대 페이지수

		// 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("startpage", startpage);

		// 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("endpage", endpage);

		// 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("listcount", listcount);

		// 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("memolist", memolist);

		request.setAttribute("limit", limit);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("memo.jsp");
		return forward; // BoardFrontController.java로 리턴됩니다.

	}// execute end
}
