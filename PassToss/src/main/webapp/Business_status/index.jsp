<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<head>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">



<meta charset="UTF-8">
<title>업무 현황</title>
<style>
*{margin:0;padding:0}
	
	body{background:#e2e2e2}

	.container >div {vertical-align:top}
	.box_radius15 , .memo_inner_box li{border-radius:5px}
	.container{padding:10px;background:#fff;margin:10px auto;min-width:1000px}
	.status_left_box{width:63%;padding:1%;display:inline-block}
	.status_right_box{width:33%;padding:1%;display:inline-block}
	
	.memo_wrap{background:#2a2d5b;padding: 20px 10px;text-align:center}
	
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
	.status_color1{background:green !important}
	.status_color2{background:orange !important}
	.status_color3{background:red !important}
	
	
	.status_top_graph_wrap{}
	.status_top_graph_info{}
	.status_top_graph_info p {display:inline-block}
	.graph_info_div p span{width:10px;height:10px;display:inline-block;
								border-radius:10px;margin-right:10px}
	.graph_info_div p:nth-child(1) span{background:green}
	.graph_info_div p:nth-child(2) span{background:orange}
	.graph_info_div p:nth-child(3) span{background:red}
	
	.status_bottom_graph_info div{display:inline-block;vertical-align:top}
	
</style>
</head>
<body>
	<div class='container box_radius15'>
		<div class='status_left_box'>
			
			<div class='memo_wrap box_radius15'>
				<div class='memo_inner_box box_radius15'>
					<p>할것</p>
					<ul>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
					</ul>
				</div>
				
				<div class='memo_inner_box box_radius15'>
					<p>할것</p>
					<ul>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
					</ul>
				</div>
				
				<div class='memo_inner_box box_radius15'>
					<p>할것</p>
					<ul>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
						<li>클라이언트 미팅</li>
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
						<th>오늘</th>
						<th>사람</th>
						<th>상태</th>
						<th>우선순위</th>
						<th>시간</th>
						<th><img src="image/plus_btn.png" alt="추가" style='width:70%'></th>
					</tr>
					
					
					<tr>
						<td>클라이언트 미팅</td>
						<td class='center'><img src="image/remove.png" alt="인물사진"></td>
						<td class='status_color1 color_fff'>작업 중</td>
						<td class='status_color1 color_fff'>낮음</td>
						<td>5시간</td>
						<td></td>
					</tr>
					
					<tr>
						<td>클라이언트 미팅</td>
						<td class='center'><img src="image/remove.png" alt="인물사진"></td>
						<td class='status_color2 color_fff'>작업 중</td>
						<td class='status_color2 color_fff'>중간</td>
						<td>5시간</td>
						<td></td>
					</tr>
					
					<tr>
						<td>클라이언트 미팅</td>
						<td class='center'><img src="image/remove.png" alt="인물사진"></td>
						<td class='status_color3 color_fff'>작업 중</td>
						<td class='status_color3 color_fff'>높음</td>
						<td>5시간</td>
						<td></td>
					</tr>
				</table>
				
			</div>
		
		</div>
		
	
		<div class='status_right_box'>
			<div class='status_top_graph_wrap center'>
				<h2>전체 업무 현황</h2>
				
				<div>
					<div id="barchart_values" style="width: 500px; height: 100px;"></div>
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
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChartCircle);
      function drawChartCircle() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['완료',     15],
          ['진행중',      2],
          ['정체',  4]

        ]);

        var circle_options = {
      	  chartArea:{left:30,top:0,width:'100%',height:'75%'},
          pieHole: 0.4,
        };

        var circle = new google.visualization.PieChart(document.getElementById('donutchart'));
        circle.draw(data, circle_options);
      }
      
     
    </script>
