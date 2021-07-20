package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnSession;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import htwsaar.ariadne.learcard.repositorys.LearnSessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



public class LearnSessionController {
    @Autowired
    private LearnSessionRepository learnSessionRepository;

}
