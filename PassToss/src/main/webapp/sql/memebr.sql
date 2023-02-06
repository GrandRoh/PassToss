drop table member cascade constraints purge

create table dept(
	deptno	number(5) primary key,
	dname	varchar2(20)
)

create table member(
	id			varchar2(12) primary key,			-- 아이디
	password	varchar2(10),						-- 비밀번호
	name		varchar2(15),						-- 이름
	jumin		varchar2(14),						-- 주민번호
	deptno		number(2) references dept(deptno),	-- 부서번호
	email		varchar2(30),						-- 이메일
	phone		varchar2(15),						-- 연락처
	address		varchar2(150),						-- 주소
	authority	number(1) check(authority in (0,1,2)) -- 권한	
)

alter table member add profileImg varchar2(50)		-- 프로필사진 경로

alter table  member modify authority default 0; -- 회원가입 시 기본값 0(준회원)

alter table member add joindate date default sysdate; -- 가입날짜

select * from member;

update member
set authority = 0
where authority = 1

delete member

insert into member
values('admin','1234','염재영','960103-1234567',0,'duawodud66@naver.com','010-6233-0272','경기도 고양시 일산서구',2,'',sysdate);
insert into member
values('a1234','1234','a','960101-1234567',10,'a1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('b1234','1234','b','960102-1489212',20,'b1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('c1234','1234','c','960103-1234567',30,'c1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('d1234','1234','d','960104-1234567',40,'d1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('e1234','1234','e','960105-1234567',10,'e1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('f1234','1234','f','960106-1234567',20,'f1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('g1234','1234','g','960107-1234567',30,'g1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('h1234','1234','h','960108-1234567',40,'h1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('i1234','1234','i','960109-1234567',10,'i1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('j1234','1234','j','960110-1234567',20,'j1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('k1234','1234','k','960111-1234567',30,'k1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);
insert into member
values('l1234','1234','l','960112-1234567',40,'l1234@naver.com','010-6233-0272','경기도 고양시 일산서구',0,'',sysdate);


insert into member


delete dept

insert into dept
values(0,'관리자');

insert into dept
values(10, '1팀');

insert into dept
values(20, '2팀');

insert into dept
values(30, '3팀');

insert into dept
values(40, '4팀');


select * from dept
