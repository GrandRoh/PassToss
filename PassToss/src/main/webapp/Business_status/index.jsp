<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../AdminPage/leftMenu.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>



<head>

<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>


<title>업무 현황</title>
<style>
*{margin:0;padding:0}
	
	body{background:#e2e2e2;min-width:1400px}
	.container{padding:10px;background:#fff;margin:10px;display:inline-block;vertical-align:top}
	.sidenav{display:inline-block}
	
	.container >div {vertical-align:top}
	.box_radius15 , .memo_inner_box li{border-radius:5px}
	
	.status_left_box{width:63%;padding:1%;display:inline-block}
	.status_right_box{width:33%;padding:1%;display:inline-block}
	
	.memo_wrap{background:#2a2d5b;padding: 20px 10px;text-align:center;min-height:400px}
	
	.memo_inner_box{width:30%;display:inline-block;margin-right:5px;padding:5px;vertical-align:top}
	.memo_inner_box:nth-child(1){background:#587def}
	.memo_inner_box:nth-child(2){background:orange}
	.memo_inner_box:nth-child(3){background:#04a85f}
	.memo_inner_box p{color:#fff}
	.memo_inner_box ul{padding:0;list-style:none}
	.memo_inner_box ul li {background:#fff;padding:3px 5px;font-weight:bold;margin-bottom:5px}
	
	.schedule_wrap{margin-top:15px}
	.schedule_wrap img{width:10px}
	.schedule_wrap tr{margin-top:3px}
	.schedule_wrap td:nth-child(1){border-left:3px solid #6bb0fd}
	.schedule_wrap td{background:#eee;padding:3px}
	.center{text-align:center}
	
	.color_fff{color:#fff}
	.status_color1{background:#587def !important}
	.status_color2{background:orange !important}
	.status_color3{background:#04a85f !important}
	
	.PRIORITY_color1{background:green !important}
	.PRIORITY_color2{background:orange !important}
	.PRIORITY_color3{background:red !important}
	
	.status_top_graph_wrap{}
	.status_top_graph_info{}
	.status_top_graph_info p {display:inline-block}
	.graph_info_div p span{width:10px;height:10px;display:inline-block;
								border-radius:10px;margin-right:10px}
	.graph_info_div p:nth-child(1) span{background:green}
	.graph_info_div p:nth-child(2) span{background:orange}
	.graph_info_div p:nth-child(3) span{background:red}
	
	.status_bottom_graph_info div{display:inline-block;vertical-align:top}
	
	#all_status_graph_wrap{width: 349px;
   						 height: 204px;
					    padding: 30px 57px 31px 30px;
					    background: url(./image/empty_battery.png) no-repeat;}
	#all_status_graph>div{float:left;height:145px}
	
</style>
</head>
<body>

	<div class='container box_radius15'>
	
		
		<div class='status_left_box'>
			
			<div class='memo_wrap box_radius15'>
				<div class='memo_inner_box box_radius15'>
					<p>할 것</p>
					<ul>
						<c:forEach var="m" items="${memolist }">
							<c:if  test = "${m.status == 1}">
								<li>${m.memo_content }</li>
							 </c:if>
						</c:forEach>
					</ul>
				</div>
				
				<div class='memo_inner_box box_radius15'>
					<p>작업중</p>
					<ul>
						<c:forEach var="m" items="${memolist }">
							<c:if  test = "${m.status == 2}">
								<li>${m.memo_content }</li>
							 </c:if>
						</c:forEach>
					</ul>
				</div>
				
				<div class='memo_inner_box box_radius15'>
					<p>완료</p>
					<ul>
						<c:forEach var="m" items="${memolist }">
							<c:if  test = "${m.status == 3}">
								<li>${m.memo_content }</li>
							 </c:if>
						</c:forEach>
					</ul>
				</div>
			
			</div>
			
			<div class='schedule_wrap'>
				<table style='border-collapse: initial;border-spacing: 3px 5px;'>
					<colgroup>
						<col width="50%">
						<col width="8%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="5%">
					</colgroup>
				
					<tr>
						<th>업무내용</th>
						<th>사람</th>
						<th>우선순위</th>
						<th>상태</th>
						<th>기한</th>
						<th><a href ='#1' id='business_add_btn'  data-toggle="modal"
                           data-target="#business_add"><img src="image/plus_btn.png" alt="추가" style='width:70%'></a></th>
					</tr>
					
					<c:if test="${listcount > 0 }">
						<c:forEach var="m" items="${memolist }">
							<tr>
								<td>${m.memo_content }</td>
								
								<td class='center'><img src="image/remove.png" alt="인물사진"></td>
									
							     
							
							       <c:if  test = "${m.priority == 1}">
							           <td class='PRIORITY_color1 color_fff'>낮음</td>
							       </c:if>
							       <c:if  test = "${m.priority == 2}">
							           <td class='PRIORITY_color2 color_fff'>중간</td>
							       </c:if>
							       <c:if  test = "${m.priority == 3}">
							           <td class='PRIORITY_color3 color_fff'>높음</td>
							       </c:if>
							     
							     	  <c:if  test = "${m.status == 1}">
							           <td class='status_color1 color_fff'>대기</td>
							       </c:if>
							       <c:if  test = "${m.status == 2}">
							           <td class='status_color2 color_fff'>진행</td>
							       </c:if>
							       <c:if  test = "${m.status == 3}">
							           <td class='status_color3 color_fff'>완료</td>
							       </c:if>
				
							    <%--<c:set var="datevalue" value="${m.limit_date }"/>
								 <td>${fn:substring(datevalue,0,10)}</td>--%>
								 
								<td>${m.diffMin }분</td>
								
								<td><a href="javascript:status_delete(${m.memo_seq })"><img src="image/remove.png" alt="추가" style='width:70%'></a></td>
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${listcount == 0 }">
						
						<tr>
							<td>등록된 일정이 없습니다</td>
						</tr>
						
					</c:if>
				</table>
				
			</div>
		
		
			
		
		</div>
		
		<div class='modal' id="business_add" >
      		<div class="modal-dialog">
	      		<div class="modal-content">
	      			<div class="modal-body">
	      				<form name="addForm" action="BusinessAddAction.bs" method="post">
		      				<div class="form-group">
		      					
		      						<input type=hidden 
		      								name="MEMO_ID" id="MEMO_ID" value="${id}">
		      								
		      						<label for ="MEMO_CONTENT">내용</label>
		      						<input type="text" class="form-control" placeholder="Enter CONTENT"
		      								name="MEMO_CONTENT" id="MEMO_CONTENT">
		      							
		      						<div>
		      							<label for ="LIMIT_DATE">기한</label>
		      							<select name="yy" id="year">
		      								<option>2023</option>
		      							</select>년
										<select name="mm" id="month">
											<option>02</option>
										</select>월
										<select name="dd" id="day">
											<option>02</option>
										</select>일
		      						</div>
		      						
		      						
		      						<label for ="Priority">우선순위</label>
		      						<select name='Priority'>
		      							<option value="1">낮음</option>
		      							<option value="2">중간</option>
		      							<option value="3">높음</option>
		      						</select>
		      						
		      		
		      								
		      						<label for ="STATUS">진행상태</label>
		      						<select name='STATUS'>
		      							<option value="1">대기</option>
		      							<option value="2">진행중</option>
		      							<option value="3">완료</option>
		      						</select>
		      
		      								
		      					</div>
	      					   <button type="submit" class="btn btn-primary">전송</button>
	      					   <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
	      				</form>
	      			</div>
	      		</div>
	      	</div>
      	</div>
	
		<div class='status_right_box'>
			<div class='status_top_graph_wrap center'>
				<h2>전체 업무 현황</h2>
				
				<div id="all_status_graph_wrap">
					<div id='all_status_graph'>
					
					</div>
				</div>
				<div class='status_top_graph_info graph_info_div'>
					<p><span></span>완료</p>
					<p><span></span>진행</p>
					<p><span></span>대기</p>
				</div>
				
			</div>
			
			
			<div class='status_bottom_graph_wrap center'>
				<h1 style='border-bottom:1px solid #eee;padding-bottom:3px'>진행정도</h1>
				<div class="status_bottom_graph_info graph_info_div">
					<div>
						<div id="donutchart" style="width: 400px; height: 400px;"></div>
					</div>
				</div>
			</div>
			
			
			
		</div>
	</div>
	
</body>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
   
  
	   let cnt_a0 = 0;
	   let cnt_a1 = 0;
	   let cnt_a2 = 0;
	   <c:forEach var="s" items="${statuscount}" varStatus="cnt">
				 cnt_a<c:out value="${cnt.index}"/> = <c:out value="${s.count}"/>;
		</c:forEach>
   
		if(cnt_a0 == 0 && cnt_a1 == 0 && cnt_a2 == 0 )	{
			$("#donutchart").text("등록된 업무가 없습니다");
			console.log("없음");
		}else{
		      google.charts.load("current", {packages:["corechart"]});
		      google.charts.setOnLoadCallback(drawChartCircle);
		}
     

    	
		
		
      function drawChartCircle() {
    	 
        var data = google.visualization.arrayToDataTable([
        	
        	
          ['Task', 'Working_status'],
          ['완료',     cnt_a2],
          ['진행중',      cnt_a1],
          ['대기',  cnt_a0]

        ]);

        var circle_options = {
      	  chartArea:{left:30,top:0,width:'100%',height:'75%'},
          pieHole: 0.4,
          colors: ['rgb(51, 102, 204)', 'rgb(255, 153, 0)', 'rgb(220, 57, 18)', ]
        };

        var circle = new google.visualization.PieChart(document.getElementById('donutchart'));
        circle.draw(data, circle_options);
      }
      
      function status_delete(memo_seq){
    	  
    	  const i =  confirm("정말 삭제하시겠습니까?");
    	  
    	  if (i == true){
    		  location.href=" BusinessDeleteAction.bs?memo_seq=" + memo_seq;
    	  }
    	  
      }
      
      function draw_stackedbar(a,b,c){
    	  if(a == 0 && b == 0 && c == 0 ){
    		  $("#all_status_graph").html("등록된 업무가 없습니다")
    	  }else{
    		  let sum = a + b + c;
    		  a = a/sum * 100;
    		  b = b/sum * 100;
    		  c = c/sum * 100;
    		  console.log(a +' '+ b +' '+ c)
    		  
    		  $("#all_status_graph").append("<div style='background:red;width:"+ a +"%'></div>");
    		  $("#all_status_graph").append("<div style='background:orange;width:"+ b +"%'></div>");
    		  $("#all_status_graph").append("<div style='background:green;width:"+ c +"%'></div>");
    		  
    	  }
      }
      
      
      
      
      $(function(){
      
    	  $("#business_add_btn").click(function(){
    		  $("#year,#month,#day").find('option').remove();
	    	  var now = new Date();
	    	    var year = now.getFullYear();
	    	    var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
	    	    var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());           
	    	    //년도 selectbox만들기               
	    	    for(var i = 2022 ; i <= year ; i++) {
	    	        $('#year').append('<option value="' + i + '">' + i + '</option>');    
	    	    }
	
	    	    // 월별 selectbox 만들기            
	    	    for(var i=1; i <= 12; i++) {
	    	        var mm = i > 9 ? i : "0"+i ;            
	    	        $('#month').append('<option value="' + mm + '">' + mm + '</option>');    
	    	    }
	    	    
	    	    // 일별 selectbox 만들기
	    	    for(var i=1; i <= 31; i++) {
	    	        var dd = i > 9 ? i : "0"+i ;            
	    	        $('#day').append('<option value="' + dd + '">' + dd+ '</option>');    
	    	    }
	    	    $("#year  > option[value="+year+"]").attr("selected", "true");        
	    	    $("#month  > option[value="+mon+"]").attr("selected", "true");    
	    	    $("#day  > option[value="+day+"]").attr("selected", "true"); 
	    	    
    	  })
    	  
    
    	  
    	  
    		
    	  	draw_stackedbar(cnt_a0,cnt_a1,cnt_a2);
    	  
    	  
      })
     
    </script>
