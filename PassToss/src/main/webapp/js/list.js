//freeView, beptView 에서 사용함 
//검색 관련 script코드 내용, 줄보기 관련 ajax 코드 내용 들어가야함 
function go(page){
	const limit = $('#lineviewcount').val();
	//const data = `limit=${limit}&state=ajax&page=${page}`;
	const data = {limit:limit, state:"ajax", page:page}
	ajax(data); // ajax라는 이름으로 만든 함수 
}

$(function(){
	
	let selectedValue = '${search_field}'
		if(selectedValue != '-1')
			$("#viewcount").val(selectedValue);
		else
			selectedValue = 0;
			
	const message = ["검색어", "제목", "작성자"]
	$("input").attr("placeholder", message[selectedValue] + " 입력하세요.")
	
	$("button").click(function(){
		
		if($("input").val() == ''){
			alert("검색어를 입력하세요");
			$("input").focus();
			return false;
		}
	}); //button click end
	
	$("#viewcount").change(function(){
		selectedValue = $(this).val();
		$("input").val('');
		$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
		
	}); //viewcount change end 
		
	$("#lineviewcount").change(function(){
		go(1);
	});
})