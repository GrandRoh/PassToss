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
	post		number,								-- 우편번호
	profileImg	varchar2(50)						-- 프로필이미지
	joindate	sysdate								-- 가입날짜
	authority	number(1) check(authority in (0,1,2)) -- 권한	
)

alter table member add profileImg varchar2(50);		-- 프로필사진 경로

alter table  member modify authority default 0; -- 회원가입 시 기본값 0(준회원)

alter table member add joindate date default sysdate; -- 가입날짜

alter table member add POST number; -- 우편번호

select * from member;

update member
set authority = 0
where authority = 1

delete member

insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('admin','1234','Y','560555-1234567',0,'admin@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',2,'',sysdate);



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
