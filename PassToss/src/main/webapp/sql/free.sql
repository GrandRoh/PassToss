drop table board_free;

create table board_free(
BOARD_NUM		NUMBER(5) 		primary key,
BOARD_NAME		VARCHAR2(30),
BOARD_SUBJECT	VARCHAR2(300),
BOARD_CONTENT	VARCHAR2(4000),
BOARD_FILE		VARCHAR2(50),
BOARD_RE_REF	NUMBER(5),
BOARD_RE_LEV	NUMBER(5) check(BOARD_RE_LEV in(0,1,2)),
BOARD_RE_SEQ	NUMBER(5),
BOARD_READCOUNT	NUMBER(5),
BOARD_DATE		DATE default sysdate,
BOARD_NOTICE	NUMBER(5) check(BOARD_NOTICE in(0,1))
);

select * from board_free;

create table comment_free(
	num					number			primary key,
	id					varchar2(30) 	references member(id),
	content				varchar2(200),
	comment_date		DATE default sysdate,
	comment_board_num	number		references board_free(board_re_ref)on delete cascade,	--comm 테이블이 참조하는 보드 글 번호
	comment_re_lev		number(1)	check(comment_re_lev in (0,1,2)), --원문이면 0 답글이면 1 답글 2개까지
	comment_re_seq		number, -- 원문이면 0, 1레벨이면 시퀀스 +1
	comment_re_ref 		number 	--원문은 자신 글번호, 답글이면 원문 글번호
	);