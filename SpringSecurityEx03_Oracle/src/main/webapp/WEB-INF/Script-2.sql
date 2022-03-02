-- 사용자 테이블 : 나중에 회원관리할때 다른 정보도 추가로 넣어서 사용하면됩니다.
CREATE SEQUENCE users_idx_seq;
CREATE TABLE users(
	idx NUMBER PRIMARY KEY,
	userid varchar2(100) NOT NULL,
	password varchar2(100) NOT NULL,
	enabled NUMBER DEFAULT 1
);

INSERT INTO users VALUES (users_idx_seq.nextval, 'user1','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval, 'admin','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval, 'dba','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval, 'root','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval, 'dba2','1234', 1);

SELECT * FROM users;

-- 권한 등록 테이블
CREATE SEQUENCE user_roles_idx_seq;
CREATE TABLE user_roles(
	idx NUMBER PRIMARY KEY,
	username varchar2(100) NOT NULL,
	role varchar2(30) NOT NULL
);
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'user1','ROLE_USER');
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'admin','ROLE_ADMIN');
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'admin','ROLE_DBA');
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'dba','ROLE_DBA');
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'root','ROLE_ADMIN');
INSERT INTO user_roles VALUES (user_roles_idx_seq.nextval, 'dba2','ROLE_DBA');

COMMIT;

SELECT * FROM user_roles;
DELETE FROM user_roles WHERE idx=7;
COMMIT;


