package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "learnsessions")

public class LearnSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
