package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "learnsessions")

public class LearnSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private Integer level;

    @ManyToOne (cascade = CascadeType.ALL)
    private LearnCardGroup group;

    public LearnSession() {
        this.userName = "";
        this.level = null;
        this.group = null;
    }

    public LearnSession (String userName, Integer level, LearnCardGroup group){
        this.userName = userName;
        this.level = level;
        this.group = group;
    }

    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName){ this.userName = userName; }

    public Integer getLevel(){return level;}
    public void setLevel(Integer level){this.level = level;}

    public LearnCardGroup getGroup() {
        return group;
    }
    public void setGroup(LearnCardGroup group) {
        this.group = group;
    }



}
