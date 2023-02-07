package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.Member;
import passtoss.member.db.MemberDAO;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int post = Integer.parseInt(request.getParameter("post1")) ;
		int jumin1 = Integer.parseInt(request.getParameter("jumin1")) ;
		int jumin2 = Integer.parseInt(request.getParameter("jumin2")) ;
		int deptno = Integer.parseInt(request.getParameter("deptno")) ;
		String email = request.getParameter("email");
		String address = request.getParameter("address");		
		String phone = request.getParameter("phone");		
		
		String jumin =  jumin1 +"-"+jumin2;
		
		Member m = new Member();
		MemberDAO dao = new MemberDAO();
		
		m.setId(id);m.setPassword(pass);m.setName(name);m.setPost(post);m.setJumin(jumin);
		m.setDeptno(deptno);m.setEmail(email);m.setAddress(address);m.setPhone(phone);
		
		int result = dao.insert(m);
		System.out.println(result);
		if(result ==0) {
			System.out.println("회원가입 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원 가입 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(result ==1) {
			out.println("alert('회원 가입이 되었습니다.');");
			out.println("location.href='LoginAction.net';");
		}else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
			out.println("history.back();");
		}
		out.println("</script>");
		out.close();
		
		
		
		
		return null;
	}

}
