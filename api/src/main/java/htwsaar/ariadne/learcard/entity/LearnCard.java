package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;

import javax.persistence.*;

/**
 * Entity CARD
 * @author Pamela Filipinski, Daniel-Shawn Long
 */
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

    @Column(columnDefinition = "BOOLEAN")
    private Boolean rightAnswer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    private LearnCardGroup group;

    public LearnCard() {
        this.isSolved = false;
        this.frontText = "";
        this.backText = "";
        this.userName = "";
        this.group = null;
        this.rightAnswer = false;
    }

    public LearnCard(Boolean isSolved, String frontText, String backText, String userName, LearnCardGroup group, Boolean rightAnswer) {
        this.isSolved = isSolved;
        this.frontText = frontText;
        this.backText = backText;
        this.userName = userName;
        this.group = group;
        this.rightAnswer = rightAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LearnCardGroup getGroup() {
        return group;
    }

    public void setGroup(LearnCardGroup group) {
        this.group = group;
    }

    public Boolean getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


}
