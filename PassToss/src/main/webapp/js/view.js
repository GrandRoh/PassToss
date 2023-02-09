// 댓글 관련, 
let option = 1; //선택한 등록순과 최신순을 수정, 삭제, 추가 후에도 유지되도록 하기위한 변수로 사용됩니다.

function getList(state){ //현재 선택한 댓글 정렬방식을 저장합니다. 1=>등록순, 2=>최신순
	
}

$(function(){
	getList(option); // 처음 로드 될때는 등록순 정렬 
	
	$('.comment-area').on('keyup','.comment-write-area-text',function(){
		const length = $(this).val().length;
		$(this).prev().text(length+'/200');
	});// keyup','.comment-write-area-text', function() {
		
	//댓글 등록을 클릭하면 데이터베이스에 저장 -> 저장 성공 후에 리스트 불러옵니다.
	$('ul+.comment-write .btn-register').click(function(){
		const content=$('.comment-write-area-text').val();
		if(!content){
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			url : "freeCommentAdd.bof",
			type : "post",
			data : {
				id : $("#loginid").val(),// boardView에서 hidden으로 값을 넘겨서 사용
				content : content,
				comment_board_num : $("#comment_board_num").val(),
				comment_re_lev : 0,
				comment_re_seq : 0
			},
			success : function(rdata){
				if(rdata == 1){
					getList(option);
				}
			}
		}) // ajax
		
		$('.comment-write-area-text').val(''); //textarea 초기화
		$('.comment-write-area-count').val(''); //입력한 글 카운트 초기화 
	})// $('.btn-register').click(function(){
})