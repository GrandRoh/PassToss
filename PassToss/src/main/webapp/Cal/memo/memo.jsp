<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>



<style>
   table caption {
   caption-side: top;
   text-align: center
}

h1 {
   text-align: center
}

li .gray {
   color: gray;
}

body>div>table>tbody>tr>td:last-child>a {
   color: red
}

form {
   margin: 0 auto;
   width: 80%;
   text-align: center
}

select {
   color: #495057;
   background-color: #fff;
   background-clip: padding-box;
   border: 1px solid #ced4da;
   border-radius: .25rem;
   transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
   outline: none;
}

.container {
   width: 60%
}

td:nth-child(1) {
   width: 33%
}

.input-group {
   margin-bottom: 3em
}
</style>
</head>
<body>
   <div class="container">
  
      
      <c:if test="${listcount > 0}">
   
        <table class="table table-striped">
           
           <thead>
             
              <tr>
                 <td>아이디</td>
                 <td>메모</td>
                 <td>삭제</td>
              </tr>
          </thead>
          <tbody>
             <c:forEach var="m" items="${totallist}">
                <tr>
                   <td><a href="MemoList.me?id=${m.id}">${m.id}</a></td>
                   <td>${m.memo}</td>
                   <td><a href="MemoDelete.me?id=${m.id}">삭제</a></td>
                </tr>
             </c:forEach>
          </tbody>
        </table>
       
       
     
        
      <div>
         <ul class="pagination justify-content-center">
            <c:if test="${page <= 1}">
               <li class="page-item">
                 <a class="page-link gray">이전 &nbsp;</a>
               </li>
            </c:if>
            <c:if test="${page > 1}">
               <li class="page-item">
               <%-- 이렇게 적어도 되는데 너무 길어서 forEach문 사용함 아래에서 사용중 --%>
     <a href="MemoList.me?page=${page-1}"
                    class="page-link">이전 &nbsp;</a>
               </li>
            </c:if>
            
            <c:forEach var="a" begin="${startpage}" end="${endpage}">
              <c:if test="${a == page}">
                 <li class="page-item active">
                    <a class="page-link">${a}</a>
                 </li>
              </c:if>
              <c:if test="${a != page}"> <%-- 풀어쓰면 코드가 너무 길어서 forEach문 사용 --%>
                 <c:url var="go" value="MemoList.me">
                  
                    <c:param name="page"    value="${a}" />
                 </c:url>
                 <li class="page-item">
                    <a href="${go}"
                       class="page-link">${a}</a>
                 </li>
              </c:if>
            </c:forEach>
            
            <c:if test="${page >= maxpage}">
               <li class="page-item">
                 <a class="page-link gray">&nbsp;다음</a>
               </li>
            </c:if>
            <c:if test="${page < maxpage}">
               <c:url var="next" value="MemoList.me">
                
                    <c:param name="page"    value="${page+1}" />
                 </c:url>
               <li class="page-item">
                 <a href="${next}"
                    class="page-link">&nbsp;다음</a>
               </li>
            </c:if>
         </ul>
     </div>
     
  </c:if> 

  </div>


  
</body>
</html>