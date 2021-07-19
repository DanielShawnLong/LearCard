package htwsaar.ariadne.learcard.entity;

import javax.persistence.*;

@Entity
@Table(name = "learngroups")
public class LearnGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
