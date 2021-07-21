package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;

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

    public LearnCard () {
        this.isSolved = false;
        this.frontText = "";
        this.backText = "";
        this.userName = "";
    }

    public LearnCard (String frontText, String backText, String userName) {
        this.isSolved = false;
        this.frontText = frontText;
        this.backText = backText;
        this.userName = userName;
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


}
