package htwsaar.ariadne.learcard.security.service.implementation;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;
import htwsaar.ariadne.learcard.repositorys.LearnCardGroupRepository;
import htwsaar.ariadne.learcard.security.service.interfaces.LearnCardGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnCardGroupServiceImpl implements LearnCardGroupService {

    private LearnCardGroupRepository learnCardGroupRepository;

    @Autowired
    public LearnCardGroupServiceImpl(LearnCardGroupRepository learnCardGroupRepository){this.learnCardGroupRepository = learnCardGroupRepository;}

    public LearnCardGroup findByIdAndName(long id, String name) {
        return learnCardGroupRepository.findByIdAndUserName(id,name);
    }


    public void deleteCardGroup(long id, String name) {
        learnCardGroupRepository.deleteByIdAndUserName(id,name);
    }

    @Override
    public LearnCardGroup findByIdAndUserName(long id, String name) {
        return learnCardGroupRepository.findByIdAndUserName(id,name);
    }

    @Override
    public void deleteByIdAndUserName(long id, String name) {
    learnCardGroupRepository.deleteByIdAndUserName(id,name);
    }

    @Override
    public List<LearnCardGroup> findByUserName(String username) {
        return learnCardGroupRepository.findByUserName(username);
    }

    @Override
    public Optional<LearnCardGroup> findById(long id) {
        return learnCardGroupRepository.findById(id);
    }

    @Override
    public LearnCardGroup save(LearnCardGroup group) {
        return learnCardGroupRepository.save(group);
    }
}
