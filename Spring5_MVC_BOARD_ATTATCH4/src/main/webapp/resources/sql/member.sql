select * from member;

select * from board;

drop table comments;
drop table board;

create table board(
	board_num		number,			-- 글번호
	board_name		varchar2(30),	-- 작성자
	board_pass		varchar2(30),	-- 비밀번호
	board_subject	varchar2(300),	-- 제목
	board_content	varchar2(4000),	-- 내용
	board_file		varchar2(50),	-- 첨부될 파일 명(가공)
	board_original	varchar2(50),	-- 첨부될 파일명
	board_re_ref	number,			--답변 글 작성시 참조되는 글의 번호
	board_re_lev	number,			--답변 글 깊이
	board_re_seq	number,			--답변 글 순서
	board_readcount	number,			--조회수
	board_date		date,			--작성날짜
	primary key(board_num)
);

create table member(
	id varchar2(15) primary key,
	password varchar2(10),
	name varchar2(15),
	age Number,
	gender varchar2(5),
	email varchar2(30)
);
create table comments(
	num			number		primary key,
	id			varchar2(30) references member(id),
	content		varchar2(200),
	reg_date	date,
	BOARD_RE_REF number references board(BOARD_NUM) on delete cascade
-- 참조하는 댓글도 삭제함
);

create table delete_File(
	BOARD_FILE varchar2(50) primary key
)
select * from delete_File;
delete member where id='deco1'
delete board;
delete comments;
delete delete_file;
select count(*) 
 		from board;
 		
select * from comments;