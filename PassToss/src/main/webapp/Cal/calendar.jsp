<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>calendar</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- bootstrap 4 -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- fullcalendar -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.css">
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.js"></script>
<style>
*{margin:0;padding:0}
	
 	.container{padding:10px; background:#fff;
 				margin:10px; display:inline-block; vertical-align:top;
 				 width:80%}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
 	
 	

</style>

    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                timeZone: 'UTC',
                initialView: 'dayGridMonth', 
                events:[ 
                    {
                        title:'일정',
                        start:'2023-02-26 00:00:00',
                        end:'2023-02-27 24:00:00' 
               
                    }
                ], headerToolbar: {
                    center: 'addEventButton' // headerToolbar에 버튼을 추가
                }, customButtons: {
                    addEventButton: { // 추가한 버튼 설정
                        text : "일정 추가",  // 버튼 내용
                        click : function(){ // 버튼 클릭 시 이벤트 추가
                            $("#calendarModal").modal("show"); // modal 나타내기

                            $("#addCalendar").on("click",function(){  // modal의 추가 버튼 클릭 시
                                var content = $("#calendar_content").val();
                                var start_date = $("#calendar_start_date").val();
                                var end_date = $("#calendar_end_date").val();
                                
                                //내용 입력 여부 확인
                                if(content == null || content == ""){
                                    alert("내용을 입력하세요.");
                                }else if(start_date == "" || end_date ==""){
                                    alert("날짜를 입력하세요.");
                                }else if(new Date(end_date)- new Date(start_date) < 0){ // date 타입으로 변경 후 확인
                                    alert("종료일이 시작일보다 먼저입니다.");
                                }else{ // 정상적인 입력 시
                                    var obj = {
                                        "title" : content,
                                        "start" : start_date,
                                        "end" : end_date
                                    }//전송할 객체 생성

                                    console.log(obj); //서버로 해당 객체를 전달해서 DB 연동 가능
                                }
                            });
                        }
                    }
                },
                googleCalendarApiKey : 'AIzaSyA8JVAhT01G1Vb2txKh2-LglzZsBWqysYM',
				events : {
					googleCalendarId : '56c8c3c8bccfa4795cb41ba5a290c820017621355e466bdfa87002c3d5ccd0bf@group.calendar.google.com'

				},
                editable: true, // false로 변경 시 draggable 작동 x 
                displayEventTime: false // 시간 표시 x
            });
            calendar.render();
        });
    </script>
    <style>
        #calendarBox{
            width: 80%;
            float:left;
     
        }
        
        #container_memo{
         width: 65%;
            margin:auto;
            text-align:center
        }

    </style>
</head>

<body>
<div class='container box_radius15'>
    <div id="calendarBox">
        <div id="calendar"></div>
    </div>
    
    <br>

</div>
<div class="container_memo" id="container_memo">


	<form id="insert_form">
	 <fieldset >
	 	<legend> 일정 메모 </legend>
	 		<table class="table">
	 			<tr>
	 				<th><label>작성자</label></th>
	 				<td><input type="text" name="name" class="form-control" id="memoWriter" required></td>
	 			</tr>
	 			<tr>
	 				<th><label>부서명</label></th>
	 				<td><input type="text" name="price" class="form-control" id="memoDname" required></td>
	 			</tr>
	 			<tr>
	 				<th><label>내용</label></th>
	 				<td><input type="text" name="maker" class="form-control" id="memoContent" required></td>
	 			</tr>
	 			<tr>
				<td colspan=2>
					<button type="submit" class="btn btn-primary btn-block" >추가</button>
	 			</td>
	 			</tr>
	 		</table>
	 </fieldset>
	
	</form>

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
                        <form action="/Calendar" name="eventAdd" type="post">
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
                         </form>
                </div>
    
            </div>
        </div>
    </div>
   
   
   </div>
   
</body>

</html>