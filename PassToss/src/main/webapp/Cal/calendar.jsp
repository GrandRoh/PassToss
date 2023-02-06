<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='utf-8' />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<script>
	document
			.addEventListener(
					'DOMContentLoaded',
					function() {
						var calendarEl = document.getElementById('calendar');
						var calendar = new FullCalendar.Calendar(
								calendarEl,
								{
									
									initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
									headerToolbar : { // 헤더에 표시할 툴 바
										start : 'prev next today',
										center : 'title',
										end : 'dayGridMonth,dayGridWeek,dayGridDay'
									},
									titleFormat : function(date) {
										return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
									},
									//initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
									selectable : true, // 달력 일자 드래그 설정가능
									droppable : true,
									editable : true,
									nowIndicator: true, // 현재 시간 마크
									locale: 'ko', // 한국어 설정
									googleCalendarApiKey : 'AIzaSyA8JVAhT01G1Vb2txKh2-LglzZsBWqysYM',
									events : {
										googleCalendarId : '56c8c3c8bccfa4795cb41ba5a290c820017621355e466bdfa87002c3d5ccd0bf@group.calendar.google.com'

									}
								});
						calendar.render();
					});
</script>
</head>
<body>
	<div id='calendar'></div>
</body>
</html>