$("#exampleModal").ready(function() {
	console.log($("#idInfo").text())
	$.ajax({
		url: 'memberInfo.net',
		data: {
			id: $("#idInfo").text()
		},
		type: 'post',
		datatype: 'json',
		success: function(rdata) {
			let output = "";
			output += "<table class='table table-bordered'>";
			+"<tr><th>아이디</th><td>" + rdata.id + "</td></tr>";
			$(".modal-body").html(output);
		}
	})
})
let selectedValue = '${search_field}';
if (selectedValue != '-1')
	$("#viewcount").val(selectedValue);
else
	selectedValue = 0;

const message = ["아이디", "이름", "부서번호"];
$("input").attr("placeholder", message[selectedValue] + "을(를) 입력하세요");

$("button[name=searchbutton]").click(function() {
	//검색어 공백 유효성 검사합니다.
	if ($("input").val() == '') {
		alert("검색어를 입력하세요");
		$("input").focus();
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

$("#viewcount").change(function() {
	selectedValue = $(this).val();
	$("input").val('');
	$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
}) //$("#viewcount").change

$("#selectAll").click(function() {
	if ($(this).is(":checked"))
		$("input[name=select]").prop("checked", true);
	else
		$("input[name=select]").prop("checked", false);
})

$("#authorize").submit(function() {
	if (!$("input[name=select]").is(":checked")) {
		alert('가입승인할 회원을 선택하세요.');
		return false;
	} else {
		const authorize = confirm('가입 승인하시겠습니까?');
		if (!authorize) {
			alert('취소하였습니다.');
			return false
		}
	}
})