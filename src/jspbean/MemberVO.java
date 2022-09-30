package jspbean;

public class MemberVO {
    private String name;
    private String phoneNum;
    private String id;
    private String passwd;

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getPhoneNum() {return this.phoneNum;}
    public void setPhoneNum(String phoneNumber) {this.phoneNum = phoneNumber;}

    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}

    public String getPasswd() {return this.passwd;}
    public void setPasswd(String passwd) {this.passwd = passwd;}
}
