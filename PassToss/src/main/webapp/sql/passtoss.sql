create table member(
	id			varchar2(12) primary key,
	password	varchar2(10),
	name		varcha2(15),
	jumin		varchar2(14),
	deptno		number(2) references dept(detno),
	email		varchar2(30),
	phone		varchar2(15),
	address		varchar2(150),
	authority	number(1) check(authoriity in (0,1,2))
)

create table dept(
	deptno	number(5) primary key,
	dname	varchar2(20)
)