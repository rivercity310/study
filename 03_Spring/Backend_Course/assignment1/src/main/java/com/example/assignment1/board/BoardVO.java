package com.example.assignment1.board;

public class BoardVO {
    private int seq;
    private int id;
    private String name;
    private String nation;
    private String location;
    private int reward;
    private String position;
    private String content;
    private String tech;
    private String searchKeyword;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "seq=" + seq +
                ", id=" + id +
                ", reward=" + reward +
                ", position='" + position + '\'' +
                ", content='" + content + '\'' +
                ", tech='" + tech + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
