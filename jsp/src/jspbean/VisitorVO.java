package jspbean;

public class VisitorVO {
    private String name, writeDate, content;

    @Override
    public String toString() {
        return "VisitorVO [name=" + name + ", writeDate=" + writeDate + ", content=" + content + "]";
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public String getWriteDate() {return this.writeDate;}
    public void setWriteDate(String writeDate) {this.writeDate = writeDate;}
    public String getContent() {return this.content;}
    public void setContent(String content) {this.content = content;}
}
