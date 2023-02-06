package passtoss.Business_status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.Business_status.db.Business_status_Bean;
import passtoss.Business_status.db.Business_status_DAO;

public class Business_Status_list implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		List<Business_status_Bean> memolist = new ArrayList<Business_status_Bean>();
		Business_status_DAO dao = new Business_status_DAO();
		
	
		List<Business_status_Bean> statuscount = new ArrayList<Business_status_Bean>();
		List<Business_status_Bean> prioritycount = new ArrayList<Business_status_Bean>();
		
		//속성별 group by
		statuscount = dao.getCountAttribute("status");
	
		prioritycount = dao.getCountAttribute("priority");
	
		request.setAttribute("statuscount", statuscount);
		request.setAttribute("prioritycount", prioritycount);


		
		memolist = dao.getMemoList();
		int listcount = dao.getListCount();
		
		request.setAttribute("listcount", listcount);
		request.setAttribute("memolist", memolist);
		
		forward.setRedirect(false);
		forward.setPath("Business_status/index.jsp");
		return forward;
	}
}
