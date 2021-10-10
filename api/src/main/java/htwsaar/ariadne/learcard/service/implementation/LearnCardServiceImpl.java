package htwsaar.ariadne.learcard.service.implementation;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import htwsaar.ariadne.learcard.service.interfaces.LearnCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnCardServiceImpl implements LearnCardService {

    private final LearnCardRepository learnCardRepository;

    @Autowired
    public LearnCardServiceImpl(LearnCardRepository learnCardRepository) { this.learnCardRepository = learnCardRepository;}

    @Override
    public List<LearnCard> getAll() {
        return learnCardRepository.findAll();
    }

    @Override
    public LearnCard insertCard(LearnCard card) {
        learnCardRepository.save(card);
        return card;
    }

    @Override
    public LearnCard findCardByIdandName(long id, String name) {
        return learnCardRepository.findByIdAndUserName(id,name);
    }

    @Override
    public Optional<LearnCard> findCardById(long id) {
        return learnCardRepository.findById(id);
    }

    @Override
    public List<LearnCard> findAllByName(String name) {
        return learnCardRepository.findByUserName(name);
    }

    @Override
    public void removeCard(long id, String name) { learnCardRepository.deleteByIdAndUserName(id,name);}

    @Override
    public List<LearnCard> findByGroupIdAndUserName(Long group, String username) {
        return learnCardRepository.findByGroupIdAndUserName(group,username);
    }

    @Override
    public List<LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group, String username, Boolean answer) {
        return learnCardRepository.findByGroupIdAndUserNameAndRightAnswer(group,username,answer);
    }

    @Override
    public List<LearnCard> findByGroupIdAndUserNameAndRightAnswerAndIsSolved(Long group, String username, Boolean rightAnswer, Boolean isSolved) {
        return learnCardRepository.findByGroupIdAndUserNameAndRightAnswerAndIsSolved(group,username,rightAnswer,isSolved);
    }


}
