* [DB멘토 김광진] pilot6@naver.com 

>> 팀명 : Peeka 
   팀원 : 신현준, 류훈민, 오상훈, 정의진, 정희원
   주제 : 얼굴인식 기술을 이용한 보안 강화 업무 프로세스 시스템

* 오라클 서버 구축 정보-신현준팀 (Oracle)
   HOSTNAME : project-db-stu3.smhrd.com
   PORT : 1524
   USER : Insa5_SpringA_hacksim_1
   PW : aischool1

* DB모델링 주소 
  URL : https://aquerytool.com/aquerymain/index/?rurl=ed0058de-0673-4644-adea-fd7a41c73b45&
  Password : 6wy8eo

* 데이터베이스 설계순서 

요구사항분석 > 개념설계 > 논리설계 > 물리설계 > 데이터베이스구현 (개.눈.물)


* 컬럼, 변수 네이밍 룰(naming rule)

  Member : PASKAL CASE 
  memberId : CAMEL CASE 
  member_id : snake lower case 
  MEMBER_ID  : SNAKE UPPER CASE 


* 얼굴 사진 데이터의 처리 

  file_folder = 'd:/uploads/';
  * file_image = 'face0001.jpg';
  * file_rname = file_basename + 23023032(시간.밀리초) + '사번' + '.' + file_ext;
  file_basename = 'face0001';
  * file_ext = 'jpg';
  * file_size = 2340023;
  file_path = file_folder + file_rname;

* 엔터티 관계 차수 

  회원(member_tb)   --------------- 얼굴 사진(face_image_tb)

       1                   :        0, 1, N (Numerous, 여러개의)


* ERD 
  ER-Diagram
  Entity Relationship Diagram        

  Relation      : 일반적인 관계('날씨와 우산판매량의 관계')
  Relations     : 국가적인 관계(relations) between the two countries.
  Relationship  : 개인적인 관계/ 사회적인 관계/ 비지니스관계

* 회원권한(user_role)  : 일반사원('E', Employee), 관리자('M', Manager), 임원('C')

* 말풍선(Verbose, 말이 많은) : 상세모드


* VO(Value Object) : DTO(Data Transfer Object) : Java Model 

  MemberVO.java, MemberDTO.java 


  // 사원정보 
public class MemberDTO {

    // 회원 사번 
    private String user_empid;

    // 회원 비밀번호 
    private String user_pw;

    // 회원 이름 
    private String user_name;

    // 회원 이메일 
    private String user_email;

    // 회원 생년월일 
    private Date user_birthdate;

    // 회원 입사일 
    private Date hired_at;

    // 회원 연락처 
    private String user_phone;

    // 회원 내선번호 
    private String user_inline;

    // 부서 식별자 
    private Double dept_idx;

    // 회원 직책 
    private String user_position;

    // 회원 가입일자 
    private Date joined_at;

    // 마지막 로그인 
    private Date last_login;

    // 회원 권한 
    private String user_role;

    public String getUserEmpid() {
        return user_empid;
    }

    public void setUserEmpid(String userEmpid) {
        this.user_empid = user_empid;
    }

    public String getUserPw() {
        return user_pw;
    }

    public void setUserPw(String userPw) {
        this.user_pw = user_pw;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = user_name;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String userEmail) {
        this.user_email = user_email;
    }

    public Date getUserBirthdate() {
        return user_birthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.user_birthdate = user_birthdate;
    }

    public Date getHiredAt() {
        return hired_at;
    }

    public void setHiredAt(Date hiredAt) {
        this.hired_at = hired_at;
    }

    public String getUserPhone() {
        return user_phone;
    }

    public void setUserPhone(String userPhone) {
        this.user_phone = user_phone;
    }

    public String getUserInline() {
        return user_inline;
    }

    public void setUserInline(String userInline) {
        this.user_inline = user_inline;
    }

    public Double getDeptIdx() {
        return dept_idx;
    }

    public void setDeptIdx(Double deptIdx) {
        this.dept_idx = dept_idx;
    }

    public String getUserPosition() {
        return user_position;
    }

    public void setUserPosition(String userPosition) {
        this.user_position = user_position;
    }

    public Date getJoinedAt() {
        return joined_at;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joined_at = joined_at;
    }

    public Date getLastLogin() {
        return last_login;
    }

    public void setLastLogin(Date lastLogin) {
        this.last_login = last_login;
    }

    public String getUserRole() {
        return user_role;
    }

    public void setUserRole(String userRole) {
        this.user_role = user_role;
    }

    // member_tb 모델 복사
    public void CopyData(member_tb param)
    {
        this.user_empid = param.getUserEmpid();
        this.user_pw = param.getUserPw();
        this.user_name = param.getUserName();
        this.user_email = param.getUserEmail();
        this.user_birthdate = param.getUserBirthdate();
        this.hired_at = param.getHiredAt();
        this.user_phone = param.getUserPhone();
        this.user_inline = param.getUserInline();
        this.dept_idx = param.getDeptIdx();
        this.user_position = param.getUserPosition();
        this.joined_at = param.getJoinedAt();
        this.last_login = param.getLastLogin();
        this.user_role = param.getUserRole();
    }
}
-------------------------------------------------------------------
-- 오라클 전체 테이블 삭제하기(TIP)
: TABLE / SEQUENCE / TRIGGER / INDEX / VIEW 등에 사용 가능함.
DROP TABLE COUNTRIES CASCADE CONSTRAINTS;

-- 문자열 합치기 
-- 자바 : 'A' + 'B'
-- 오라클 : 'A'||'B'

SELECT 'DROP TABLE ' || TABLE_NAME || ' CASCADE CONSTRAINTS;' FROM USER_TABLES;
SELECT 'DROP SEQUENCE ' || SEQUENCE_NAME || ' ;' FROM USER_SEQUENCES;

DROP SEQUENCE DEPARTMENTS_SEQ ;
DROP SEQUENCE EMPLOYEES_SEQ ;
DROP SEQUENCE LOCATIONS_SEQ ;


DROP TABLE REGIONS CASCADE CONSTRAINTS;
DROP TABLE LOCATIONS CASCADE CONSTRAINTS;
DROP TABLE DEPARTMENTS CASCADE CONSTRAINTS;
DROP TABLE JOBS CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEES CASCADE CONSTRAINTS;
DROP TABLE JOB_HISTORY CASCADE CONSTRAINTS;
DROP TABLE 수강생정보 CASCADE CONSTRAINTS;
DROP TABLE 성적표 CASCADE CONSTRAINTS;
DROP TABLE 직원 CASCADE CONSTRAINTS;
DROP TABLE 부서 CASCADE CONSTRAINTS;
DROP TABLE 네이버회원 CASCADE CONSTRAINTS;
DROP TABLE 네이버블로그 CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE WEB_MEMBER CASCADE CONSTRAINTS;
DROP TABLE COUNTRIES CASCADE CONSTRAINTS;

--------------------------------------------------------------------
(핵심프로젝트 오라클 SQL문)

-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- department_tb Table Create SQL
-- 테이블 생성 SQL - department_tb
CREATE TABLE department_tb
(
    dept_idx        NUMBER(18, 0)    NOT NULL, 
    dept_name       VARCHAR2(50)     NOT NULL, 
    dept_loc        VARCHAR2(500)    NOT NULL, 
    dept_space      VARCHAR2(500)    NOT NULL, 
    dept_manager    VARCHAR2(50)     NOT NULL, 
     PRIMARY KEY (dept_idx)
);

-- Auto Increment를 위한 Sequence 추가 SQL - department_tb.dept_idx
CREATE SEQUENCE department_tb_SEQ
START WITH 1
INCREMENT BY 1;

-- Auto Increment를 위한 Trigger 추가 SQL - department_tb.dept_idx
CREATE OR REPLACE TRIGGER department_tb_AI_TRG
BEFORE INSERT ON department_tb 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT department_tb_SEQ.NEXTVAL
    INTO :NEW.dept_idx
    FROM DUAL;
END;

-- DROP TRIGGER department_tb_AI_TRG; 

-- DROP SEQUENCE department_tb_SEQ; 

-- 테이블 Comment 설정 SQL - department_tb
COMMENT ON TABLE department_tb IS '부서';

-- 컬럼 Comment 설정 SQL - department_tb.dept_idx
COMMENT ON COLUMN department_tb.dept_idx IS '부서 식별자';

-- 컬럼 Comment 설정 SQL - department_tb.dept_name
COMMENT ON COLUMN department_tb.dept_name IS '부서 명';

-- 컬럼 Comment 설정 SQL - department_tb.dept_loc
COMMENT ON COLUMN department_tb.dept_loc IS '부서 위치';

-- 컬럼 Comment 설정 SQL - department_tb.dept_space
COMMENT ON COLUMN department_tb.dept_space IS '근무지';

-- 컬럼 Comment 설정 SQL - department_tb.dept_manager
COMMENT ON COLUMN department_tb.dept_manager IS '부서 관리자';


-- member_tb Table Create SQL
-- 테이블 생성 SQL - member_tb
CREATE TABLE member_tb
(
    user_empid        VARCHAR2(30)     NOT NULL, 
    user_pw           VARCHAR2(30)     NOT NULL, 
    user_name         VARCHAR2(50)     NOT NULL, 
    user_email        VARCHAR2(50)     NOT NULL, 
    user_birthdate    DATE             NOT NULL, 
    hired_at          DATE             NOT NULL, 
    user_phone        VARCHAR2(20)     NOT NULL, 
    user_inline       VARCHAR2(4)      NOT NULL, 
    dept_idx          NUMBER(18, 0)    NOT NULL, 
    user_position     VARCHAR2(20)     NOT NULL, 
    joined_at         DATE             DEFAULT SYSDATE NOT NULL, 
    last_login        DATE             NOT NULL, 
    user_role         CHAR(1)          DEFAULT 'E' NOT NULL, 
     PRIMARY KEY (user_empid)
);

-- 테이블 Comment 설정 SQL - member_tb
COMMENT ON TABLE member_tb IS '사원정보';

-- 컬럼 Comment 설정 SQL - member_tb.user_empid
COMMENT ON COLUMN member_tb.user_empid IS '회원 사번';

-- 컬럼 Comment 설정 SQL - member_tb.user_pw
COMMENT ON COLUMN member_tb.user_pw IS '회원 비밀번호';

-- 컬럼 Comment 설정 SQL - member_tb.user_name
COMMENT ON COLUMN member_tb.user_name IS '회원 이름';

-- 컬럼 Comment 설정 SQL - member_tb.user_email
COMMENT ON COLUMN member_tb.user_email IS '회원 이메일';

-- 컬럼 Comment 설정 SQL - member_tb.user_birthdate
COMMENT ON COLUMN member_tb.user_birthdate IS '회원 생년월일';

-- 컬럼 Comment 설정 SQL - member_tb.hired_at
COMMENT ON COLUMN member_tb.hired_at IS '회원 입사일';

-- 컬럼 Comment 설정 SQL - member_tb.user_phone
COMMENT ON COLUMN member_tb.user_phone IS '회원 연락처';

-- 컬럼 Comment 설정 SQL - member_tb.user_inline
COMMENT ON COLUMN member_tb.user_inline IS '회원 내선번호';

-- 컬럼 Comment 설정 SQL - member_tb.dept_idx
COMMENT ON COLUMN member_tb.dept_idx IS '부서 식별자';

-- 컬럼 Comment 설정 SQL - member_tb.user_position
COMMENT ON COLUMN member_tb.user_position IS '회원 직책';

-- 컬럼 Comment 설정 SQL - member_tb.joined_at
COMMENT ON COLUMN member_tb.joined_at IS '회원 가입일자';

-- 컬럼 Comment 설정 SQL - member_tb.last_login
COMMENT ON COLUMN member_tb.last_login IS '마지막 로그인';

-- 컬럼 Comment 설정 SQL - member_tb.user_role
COMMENT ON COLUMN member_tb.user_role IS '회원 권한';

-- Index 설정 SQL - member_tb(user_name)
CREATE INDEX IX_member_tb_1
    ON member_tb(user_name);

-- Unique Index 설정 SQL - member_tb(user_email, user_phone)
CREATE UNIQUE INDEX UQ_member_tb_1
    ON member_tb(user_email, user_phone);

-- Foreign Key 설정 SQL - member_tb(dept_idx) -> department_tb(dept_idx)
ALTER TABLE member_tb
    ADD CONSTRAINT FK_member_tb_dept_idx_departme FOREIGN KEY (dept_idx)
        REFERENCES department_tb (dept_idx) ;

-- Foreign Key 삭제 SQL - member_tb(dept_idx)
-- ALTER TABLE member_tb
-- DROP CONSTRAINT FK_member_tb_dept_idx_departme;


-- vacation_tb Table Create SQL
-- 테이블 생성 SQL - vacation_tb
CREATE TABLE vacation_tb
(
    vac_idx       NUMBER(18, 0)    NOT NULL, 
    user_empid    VARCHAR2(30)     NOT NULL, 
    vac_year      VARCHAR2(4)      NOT NULL, 
    vy_cnt        NUMBER(18, 1)    NOT NULL, 
    vm_cnt        NUMBER(18, 1)    NOT NULL, 
     PRIMARY KEY (vac_idx)
);

-- Auto Increment를 위한 Sequence 추가 SQL - vacation_tb.vac_idx
CREATE SEQUENCE vacation_tb_SEQ
START WITH 1
INCREMENT BY 1;

-- Auto Increment를 위한 Trigger 추가 SQL - vacation_tb.vac_idx
CREATE OR REPLACE TRIGGER vacation_tb_AI_TRG
BEFORE INSERT ON vacation_tb 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT vacation_tb_SEQ.NEXTVAL
    INTO :NEW.vac_idx
    FROM DUAL;
END;

-- DROP TRIGGER vacation_tb_AI_TRG; 

-- DROP SEQUENCE vacation_tb_SEQ; 

-- 테이블 Comment 설정 SQL - vacation_tb
COMMENT ON TABLE vacation_tb IS '년차/월차 휴가 일자';

-- 컬럼 Comment 설정 SQL - vacation_tb.vac_idx
COMMENT ON COLUMN vacation_tb.vac_idx IS '휴가 식별자';

-- 컬럼 Comment 설정 SQL - vacation_tb.user_empid
COMMENT ON COLUMN vacation_tb.user_empid IS '회원 사번';

-- 컬럼 Comment 설정 SQL - vacation_tb.vac_year
COMMENT ON COLUMN vacation_tb.vac_year IS '년도';

-- 컬럼 Comment 설정 SQL - vacation_tb.vy_cnt
COMMENT ON COLUMN vacation_tb.vy_cnt IS '연차 일수';

-- 컬럼 Comment 설정 SQL - vacation_tb.vm_cnt
COMMENT ON COLUMN vacation_tb.vm_cnt IS '월차 일수';

-- Foreign Key 설정 SQL - vacation_tb(user_empid) -> member_tb(user_empid)
ALTER TABLE vacation_tb
    ADD CONSTRAINT FK_vacation_tb_user_empid_memb FOREIGN KEY (user_empid)
        REFERENCES member_tb (user_empid) ;

-- Foreign Key 삭제 SQL - vacation_tb(user_empid)
-- ALTER TABLE vacation_tb
-- DROP CONSTRAINT FK_vacation_tb_user_empid_memb;


-- calendar_tb Table Create SQL
-- 테이블 생성 SQL - calendar_tb
CREATE TABLE calendar_tb
(
    cal_idx        NUMBER(18, 0)     NOT NULL, 
    user_empid     VARCHAR2(30)      NOT NULL, 
    cal_content    VARCHAR2(2000)    NOT NULL, 
    cal_date       DATE              NOT NULL, 
    cal_color      VARCHAR2(10)      NOT NULL, 
    cal_status     VARCHAR2(20)      NOT NULL, 
     PRIMARY KEY (cal_idx)
);

-- Auto Increment를 위한 Sequence 추가 SQL - calendar_tb.cal_idx
CREATE SEQUENCE calendar_tb_SEQ
START WITH 1
INCREMENT BY 1;

-- Auto Increment를 위한 Trigger 추가 SQL - calendar_tb.cal_idx
CREATE OR REPLACE TRIGGER calendar_tb_AI_TRG
BEFORE INSERT ON calendar_tb 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT calendar_tb_SEQ.NEXTVAL
    INTO :NEW.cal_idx
    FROM DUAL;
END;

-- DROP TRIGGER calendar_tb_AI_TRG; 

-- DROP SEQUENCE calendar_tb_SEQ; 

-- 테이블 Comment 설정 SQL - calendar_tb
COMMENT ON TABLE calendar_tb IS '캘린더';

-- 컬럼 Comment 설정 SQL - calendar_tb.cal_idx
COMMENT ON COLUMN calendar_tb.cal_idx IS '일정 식별자';

-- 컬럼 Comment 설정 SQL - calendar_tb.user_empid
COMMENT ON COLUMN calendar_tb.user_empid IS '회원 사번';

-- 컬럼 Comment 설정 SQL - calendar_tb.cal_content
COMMENT ON COLUMN calendar_tb.cal_content IS '일정 내용';

-- 컬럼 Comment 설정 SQL - calendar_tb.cal_date
COMMENT ON COLUMN calendar_tb.cal_date IS '일정 날짜';

-- 컬럼 Comment 설정 SQL - calendar_tb.cal_color
COMMENT ON COLUMN calendar_tb.cal_color IS '일정 칼라';

-- 컬럼 Comment 설정 SQL - calendar_tb.cal_status
COMMENT ON COLUMN calendar_tb.cal_status IS '일정 상태';

-- Foreign Key 설정 SQL - calendar_tb(user_empid) -> member_tb(user_empid)
ALTER TABLE calendar_tb
    ADD CONSTRAINT FK_calendar_tb_user_empid_memb FOREIGN KEY (user_empid)
        REFERENCES member_tb (user_empid) ;

-- Foreign Key 삭제 SQL - calendar_tb(user_empid)
-- ALTER TABLE calendar_tb
-- DROP CONSTRAINT FK_calendar_tb_user_empid_memb;


-- face_image_tb Table Create SQL
-- 테이블 생성 SQL - face_image_tb
CREATE TABLE face_image_tb
(
    file_idx       NUMBER(18, 0)     NOT NULL, 
    file_name      VARCHAR2(300)     NOT NULL, 
    file_ext       VARCHAR2(10)      NOT NULL, 
    file_size      INT               NOT NULL, 
    file_rname     VARCHAR2(1500)    NOT NULL, 
    uploaded_at    DATE              NOT NULL, 
    user_empid     VARCHAR2(30)      NOT NULL, 
     PRIMARY KEY (file_idx)
);

-- Auto Increment를 위한 Sequence 추가 SQL - face_image_tb.file_idx
CREATE SEQUENCE face_image_tb_SEQ
START WITH 1
INCREMENT BY 1;

-- Auto Increment를 위한 Trigger 추가 SQL - face_image_tb.file_idx
CREATE OR REPLACE TRIGGER face_image_tb_AI_TRG
BEFORE INSERT ON face_image_tb 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT face_image_tb_SEQ.NEXTVAL
    INTO :NEW.file_idx
    FROM DUAL;
END;

-- DROP TRIGGER face_image_tb_AI_TRG; 

-- DROP SEQUENCE face_image_tb_SEQ; 

-- 테이블 Comment 설정 SQL - face_image_tb
COMMENT ON TABLE face_image_tb IS '얼굴 인식 사진파일';

-- 컬럼 Comment 설정 SQL - face_image_tb.file_idx
COMMENT ON COLUMN face_image_tb.file_idx IS '파일 식별자';

-- 컬럼 Comment 설정 SQL - face_image_tb.file_name
COMMENT ON COLUMN face_image_tb.file_name IS '파일 이름';

-- 컬럼 Comment 설정 SQL - face_image_tb.file_ext
COMMENT ON COLUMN face_image_tb.file_ext IS '파일 확장자';

-- 컬럼 Comment 설정 SQL - face_image_tb.file_size
COMMENT ON COLUMN face_image_tb.file_size IS '파일 사이즈';

-- 컬럼 Comment 설정 SQL - face_image_tb.file_rname
COMMENT ON COLUMN face_image_tb.file_rname IS '파일 실제이름';

-- 컬럼 Comment 설정 SQL - face_image_tb.uploaded_at
COMMENT ON COLUMN face_image_tb.uploaded_at IS '업로드 날짜';

-- 컬럼 Comment 설정 SQL - face_image_tb.user_empid
COMMENT ON COLUMN face_image_tb.user_empid IS '회원 사번';

-- Foreign Key 설정 SQL - face_image_tb(user_empid) -> member_tb(user_empid)
ALTER TABLE face_image_tb
    ADD CONSTRAINT FK_face_image_tb_user_empid_me FOREIGN KEY (user_empid)
        REFERENCES member_tb (user_empid) ;

-- Foreign Key 삭제 SQL - face_image_tb(user_empid)
-- ALTER TABLE face_image_tb
-- DROP CONSTRAINT FK_face_image_tb_user_empid_me;


