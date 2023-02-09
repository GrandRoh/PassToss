$(function() {	
	$("#viewcount").change(function(){
      go(1); //보여줄 페이지를 1페이지로 설정
      
   })

	let selectedValue = $("#search_field").val();

	if (selectedValue != '-1')
		$("#select_value").val(selectedValue);
	else
		selectedValue = 0;

	console.log(selectedValue)

	const message = ["아이디", "이름", "부서번호"];
	$("input").attr("placeholder", message[selectedValue] + " 입력하세요");

	$("button[name=searchbutton]").click(function() {
		//검색어 공백 유효성 검사합니다.
		if ($("input[name=search_word]").val() == '') {
			alert("검색어를 입력하세요");
			$("input[name=search_word]").focus();
			return false;
		}

		const word = $(".input-group input").val();
		console.log(word);
		if (selectedValue == 2) {
			const pattern = /^[1-4]{1}0$/;
			if (!pattern.test(word)) {
				alert("알맞는 부서번호를 입력하세요(10~40)");
				$(".input-group input").val('');
				return false;
			}
		}
	})//button click

	$("#select_value").change(function() {
		selectedValue = $(this).val();
		$("input[name=search_word]").val('');
		$("input[name=search_word]").attr("placeholder", message[selectedValue] + " 입력하세요");
	}) //$("#viewcount").change

	$("#selectAll").click(function() {//전체 체크
		if ($(this).is(":checked"))
			$("input[name=select]").prop("checked", true);
		else
			$("input[name=select]").prop("checked", false);
	})

	$(".authorize").on("click", function() {//액션-권한수정 유효성검사
		if (!$("input[name=select]").is(":checked")) {
			alert('가입승인할 회원을 선택하세요.');
		} else {
			$("#modal2").modal("show");
		}
	})

	$(".delete").on("click", function() {//액션-삭제 유효성검사
		if (!$("input[name=select]").is(":checked")) {
			alert('삭제할 회원을 선택하세요.');
			return false;
		} else {
			const authorize = confirm('선택한 회원정보를 삭제하시겠습니까?');
			if (!authorize) {
				alert('취소하였습니다.');
				return false;
			}
		}
	})

	$("#modal1").on('show.bs.modal', function(e) {
		let id = $(e.relatedTarget).data('id');
		$.ajax({
			url: 'AdminMemberInfo.net',
			data: {
				id: id
			},
			type: 'post',
			datatype: 'json',
			success: function(rdata) {
				$("#idInfoLabel").text(rdata.id + ' 회원정보');

				let output = "";
				output += "<table class='table table-bordered'>"
					+ "<tr><th>아이디</th><td>" + rdata.id + "</td></tr>"
					+ "<tr><th>비밀번호</th><td>" + rdata.password + "</td></tr>"
					+ "<tr><th>이름</th><td>" + rdata.name + "</td></tr>"
					+ "<tr><th>주민번호</th><td>" + rdata.jumin + "</td></tr>"
					+ "<tr><th>부서번호</th><td>" + rdata.deptno + "</td></tr>"
					+ "<tr><th>이메일</th><td>" + rdata.email + "</td></tr>"
					+ "<tr><th>연락처</th><td>" + rdata.phone + "</td></tr>"
					+ "<tr><th>주소</th><td>" + rdata.address + "</td></tr>"
					+ "<tr><th>회원등급</th><td> 준회원 </td></tr>"
					+ "<tr><th>가입일자</th><td>" + rdata.joindate + "</td></tr>";
				$(".modal-body").html(output);
			}
		})
	})

	$("#modal2").on('show.bs.modal', function() {
		output = "";
		output += "<form action='AdminAccess.net' method='post' id='authorize'>"
			+ "<label><input type='radio' name='authority' value='준회원'>준회원</label>"
			+ "<label><input type='radio' name='authority' value='정회원'>정회원</label><br>"
			+ "</form>"
		$(".modal-body").html(output);
	})
	
	$("button[type=submit]").on("click", function(){
		if(!$("input:radio[name=authority]").is(":checked")){
			alert("설정할 권한을 선택하세요");
			return false;
		}
	})

})



