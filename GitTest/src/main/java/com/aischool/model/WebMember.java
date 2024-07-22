package com.aischool.model;

//WebMember 클래스는 데이터베이스의 정보를 저장할 데이터 타입
//테이블 : WEB_MEMBER
//컬렴명 : EMAIL, PW, TEL, ADDRESS
//데이터 타입: VARCHAR2

// 위 정보를 Java에서 표현할 수 있게 클래스를 생성
//WEB_MEMBER -> WebMember 클래스 
//EMAIL, PW, TEL, ADDRESS - > 필드변수
//VARCHAR2 -> String


// ++ 생성자(기본&사용자정의), getter&setter 메소드 생성
public class WebMember {
    private String empid;
    private String pw;
    private String name;
    private String email;
    private String birthday;
    private String hiredate;
    private String phone;
    private String inlinenum;
    private String deptidx;
    private String position;
    private String joindate;
    private String lastlogin;
    private String role;

    public WebMember() {
    }

    public WebMember(String empid, String pw, String name, String email, String birthday, String hiredate, 
                     String phone, String inlinenum, String deptidx, String position, String joindate, 
                     String lastlogin, String role) {
        super();
        this.empid = empid;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.hiredate = hiredate;
        this.phone = phone;
        this.inlinenum = inlinenum;
        this.deptidx = deptidx;
        this.position = position;
        this.joindate = joindate;
        this.lastlogin = lastlogin;
        this.role = role;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInlinenum() {
        return inlinenum;
    }

    public void setInlinenum(String inlinenum) {
        this.inlinenum = inlinenum;
    }

    public String getDeptidx() {
        return deptidx;
    }

    public void setDeptidx(String deptidx) {
        this.deptidx = deptidx;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
