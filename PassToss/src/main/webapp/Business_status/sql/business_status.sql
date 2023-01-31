drop table business_status purge;
drop sequence business_status_SEQ;


create table business_status(
	MEMO_SEQ         NUMBER	, 
	MEMO_ID	         VARCHAR2(30),
	MEMO_CONTENT     VARCHAR2(50) ,
	BOARD_DATE     DATE   default sysdate ,
	LIMIT_DATE     DATE    ,
	STATUS           NUMBER,
	PRIORITY         NUMBER,
	PRIMARY KEY(MEMO_SEQ)
);


create sequence business_status_SEQ;



