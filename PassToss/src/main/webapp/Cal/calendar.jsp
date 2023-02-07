<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src='css/main.js'></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script>
	var calendar = null;

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {
						var Calendar = FullCalendar.Calendar;
						var Draggable = FullCalendar.Draggable;

						var containerEl = document
								.getElementById('external-events');
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

						calendar = new Calendar(
								calendarEl,
								{ //캘린더 객체를 생성할 때 헤더툴바 옵션
									headerToolbar : {
										left : 'prev,next today',
										center : 'addEventButton',
										right : 'dayGridMonth,timeGridWeek,timeGridDay'
									},
									customButtons : {
										addEventButton : {
											text : '일정 등록',
											click : function() {
												var dateStr = prompt('(YYYY-MM-DD) 형식으로 날짜를 입력하세요');
												var date = new Date(dateStr
														+ 'T00:00:00'); 

												if (!isNaN(date.valueOf())) { 
													calendar
															.addEvent({
																title : 'dynamic event',
																start : date,
																allDay : true
															});
													alert('일정이 등록되었습니다.');
												} else {
													alert('Invalid date.');
												}

											}
										}

									},
									editable : true, //수정가능여부 false 드래그드롭후 수정 불가
									droppable : true, // 
									drop : function(info) {
										if (checkbox.checked) {
											info.draggedEl.parentNode
													.removeChild(info.draggedEl);
										}
									},
									googleCalendarApiKey : 'AIzaSyA8JVAhT01G1Vb2txKh2-LglzZsBWqysYM',
									events : {
										googleCalendarId : '56c8c3c8bccfa4795cb41ba5a290c820017621355e466bdfa87002c3d5ccd0bf@group.calendar.google.com'

									}
								//	locale: "ko" //한글 출력 옵션 추가시 주석 제거

								})

						calendar.render();
					});
	
	
</script>

</head>
<body>



	<div id='external-events'
		style="float: left; width: 20%; padding-right: 30px; padding-left: 20px; margin-top: 100px">
		<p>
			<strong>Draggable Events</strong>
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

    <div id="calendarBox">
        <div id="calendar"></div>
    </div>

    <!-- modal 추가 -->
    <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="taskId" class="col-form-label">일정 내용</label>
                        <input type="text" class="form-control" id="calendar_content" name="calendar_content">
                        <label for="taskId" class="col-form-label">시작 날짜</label>
                        <input type="date" class="form-control" id="calendar_start_date" name="calendar_start_date">
                        <label for="taskId" class="col-form-label">종료 날짜</label>
                        <input type="date" class="form-control" id="calendar_end_date" name="calendar_end_date">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" id="addCalendar">추가</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        id="sprintSettingModalClose">취소</button>
                </div>
    
            </div>
        </div>
    </div>
</body>


</html>