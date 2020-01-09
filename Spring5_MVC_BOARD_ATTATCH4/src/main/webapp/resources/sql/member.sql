select * from member;

select * from board;

drop table comments;
drop table board;

create table board(
	board_num		number,			-- �۹�ȣ
	board_name		varchar2(30),	-- �ۼ���
	board_pass		varchar2(30),	-- ��й�ȣ
	board_subject	varchar2(300),	-- ����
	board_content	varchar2(4000),	-- ����
	board_file		varchar2(50),	-- ÷�ε� ���� ��(����)
	board_original	varchar2(50),	-- ÷�ε� ���ϸ�
	board_re_ref	number,			--�亯 �� �ۼ��� �����Ǵ� ���� ��ȣ
	board_re_lev	number,			--�亯 �� ����
	board_re_seq	number,			--�亯 �� ����
	board_readcount	number,			--��ȸ��
	board_date		date,			--�ۼ���¥
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
-- �����ϴ� ��۵� ������
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