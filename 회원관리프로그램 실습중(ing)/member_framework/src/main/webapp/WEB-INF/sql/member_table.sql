CREATE TABLE member (
  username VARCHAR(10),
  userid VARCHAR(10),
  pwd VARCHAR(10),
  email VARCHAR(20),
  phone CHAR(13),
  admin TINYINT DEFAULT 0, -- 0: 사용자, 1: 관리자
  PRIMARY KEY (userid)
);

-- 데이터 삽입

INSERT INTO member (username, userid, pwd, email, phone, admin)
VALUES ('은우', 'Ouu', '1234', 'Ouu@naver.com', '010-1111-2222', 0);
INSERT INTO member (username, userid, pwd, email, phone, admin)
VALUES ('태형', 'Tea', '1234', 'Tea@naver.com', '010-2222-8888', 0);
INSERT INTO member (username, userid, pwd, email, phone, admin)
VALUES ('송강', 'Gang', '1234', 'Gang@naver.com', '010-3333-6666', 1);
INSERT INTO member (username, userid, pwd, email, phone, admin)
VALUES ('수희', 'Suhi', '1234', 'Suhi@naver.com', '010-7777-8888', 0);


-- 조회
SELECT * FROM member;

-- 커밋
commit;


insert into myfile (idx, title, cate, ofile, sfile) values(112,'서부에서','사진','서유점','2023');


-- 테이블 삭제
DROP TABLE member;


create table member_table(
    id bigint primary key auto_increment,
    memberEmail varchar(20) unique,
    memberPassword varchar(20),
    memberName varchar(20),
    memberAge int,
    memberMobile varchar(30)
);







