package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Entity GROUP
 * @author Pamela Filipinski, Felix Werner
 */

@Entity
@Table(name = "learncardgroups")
public class LearnCardGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String groupName;
    private String userName;
    private Integer level;


    public LearnCardGroup() {

        this.groupName = "";
        this.userName = "";
        this.level = 0;

    }

    public LearnCardGroup(String groupName,
                          String userName,
                          Integer level) {

        this.groupName = groupName;
        this.userName = userName;
        this.level = level;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }


}