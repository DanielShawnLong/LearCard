package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;

import javax.persistence.*;

@Entity
@Table(name = "learncards")
public class LearnCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean isSolved;

    @NotNull
    private String frontText;

    @NotNull
    private String backText;

    private String userName;

    @ManyToOne (cascade = CascadeType.ALL)//(targetEntity = htwsaar.ariadne.learcard.entity.LearnCardGroup.class)
    @NotNull
    private LearnCardGroup group;

    public LearnCard () {
        this.isSolved = false;
        this.frontText = "";
        this.backText = "";
        this.userName = "";
       this.group = null;
    }

    public LearnCard (String frontText, String backText, String userName, LearnCardGroup group) {
        this.isSolved = false;
        this.frontText = frontText;
        this.backText = backText;
        this.userName = userName;
       this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public void setUserName(String userName){ this.userName = userName; }

    public String getUserName() {
        return userName;
    }

    public LearnCardGroup getGroup() {
        return group;
    }

    public void setGroup(LearnCardGroup group) {
        this.group = group;
    }


}
