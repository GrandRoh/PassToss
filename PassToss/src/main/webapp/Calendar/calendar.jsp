<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<style>
html, body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

/*
#external-events {
	position: fixed;
	z-index: 2;
	top: 20px;
	left: 20px;
	width: 150px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
}
*/
.demo-topbar+#external-events { /* will get stripped out */
	top: 60px;
}

#external-events .fc-event {
	cursor: move;
	margin: 3px 0;
}

#calendar-container {
	position: relative;
	z-index: 1;
	margin-left: 200px;
}

#calendar {
	max-width: 1100px;
	margin: 20px auto;
}
</style>

<link href='css/main.css' rel='stylesheet' />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.1/index.global.min.js'></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script>
	var list = [];
		getlist();
	function getlist() {
		$.ajax({
			type : 'post',
			url : 'CalList.cal',
			dataType : 'json',
			async : false

		}).done(function(result) {
			list = result;
			//
			console.log(list)
		}) //성공시
		.fail(function(request, status, error) {
			alert("에러발생:" + error);
		}) //실패시
	}

	var calendar = null;

	document.addEventListener('DOMContentLoaded', function() {
		var Calendar = FullCalendar.Calendar;
		var Draggable = FullCalendar.Draggable;

		var containerEl = document.getElementById('external-events');
		var calendarEl = document.getElementById('calendar');
		var checkbox = document.getElementById('drop-remove');

		new Draggable(containerEl, {
			itemSelector : '.fc-event',
			eventData : function(eventEl) {
				return {
					title : eventEl.innerText
				};
			}
		});

		calendar = new Calendar(calendarEl, {
			headerToolbar : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay'
			},

			editable : true,
			droppable : true,

			drop : function(info) {
				if (checkbox.checked) {
					info.draggedEl.parentNode.removeChild(info.draggedEl);
				}

			},

			events : list

		})

		calendar.render();
	});

	//1. 전체 이벤트 데이터를 추출해야 한다. 
	//2. 추출한 데이터를 ajax로 서버에 전송하여 DB에 저장해야 한다
	function allSave() {
		var allEvent = calendar.getEvents();
		console.log(allEvent);
		//콘솔 통해 아래 데이터 확인 가능 console.log(allEvent); =>api 메소드 반환형태 array임을 확인 가능
		//title: 'Meeting' allDay:true 
		//end: 
		//Fri Feb 10 2023 09:00:00 GMT+0900 (Korean Standard Time) {}
		//start: 
		//Thu Feb 09 2023 09:00:00 GMT+0900 (Korean Standard Time) {}

		//var events = new Object();
		for (var i = 0; i < allEvent.length; i++) {

			var obj = new Object();
			obj.title = allEvent[i]._def.title;//이벤트 명칭
			//obj.allday=allEvent[i]._def.allDay; //하루종일의 이벤트인지 알려주는 boolean값
			obj.start = allEvent[i]._instance.range.start; //시작날짜및 시간
			obj.end = allEvent[i]._instance.range.end; //종료날짜및 시간

			//events.push(obj); //전체 이벤트들이 배열 형태로 json객체 형태로 events 변수에 담긴다

			//해당 events를 서버에 전송할때 string 형태로 넘길 것이기 때문이 이 내용을 json.stringfy 함수로

		}

		var jsondata = JSON.stringify(obj);
		console.log(jsondata);
		savedata(jsondata);

	}

	function savedata(jsondata) {
		$.ajax({
			type : 'post',
			url : 'CalAdd.cal', //데이터베이스
			data : {
				'alldata' : jsondata
			},
			dataType : 'text',
			async : false
		//동기식

		}).done(function(result) {
			location.reload();
		}) //성공시
		.fail(function(request, status, error) {
			alert("에러발생:" + error);
		}) //실패시
	}
</script>

</head>

<body>

<div class='container box_radius15 board_container'>

	<div id='external-events'
		style="float: left; width: 10%; padding-right: 30px; padding-left: 20px; margin-top: 100px">
		<p>
			<strong>부서명</strong>
		</p>

		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: red">Meeting</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: blue">Conference</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: green">Marketing</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: black">Call</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Zoom</div>
		</div>

		<p>
			<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove
				after drop</label>
		</p>
	</div>


	<div style="float: left; width: 60%">
		<div
			style="height: 30px; text-align: center; font-size: 35px; color: gray; margin-bottom: 30px; font-weight: bold">
			<div style="width: 60%; float: left; text-align: right">
				<button class="btn btn-primary" onclick="javascript:allSave();">일정등록</button>
			</div>


		</div>

		<div id='calendar'></div>

	</div>

 </div>

</body>


</html>