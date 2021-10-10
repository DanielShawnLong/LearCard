package htwsaar.ariadne.learcard.service;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import htwsaar.ariadne.learcard.repositorys.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final LearnCardRepository cardRepository;

    public CardService(LearnCardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public LearnCard saveCard(LearnCard card){
        return cardRepository.save(card);
    }

}
