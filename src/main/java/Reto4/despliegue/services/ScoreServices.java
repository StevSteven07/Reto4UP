package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Score;
import Reto4.despliegue.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServices {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score save (Score score) {
        if (validarCampos(score)) {
            if (score.getId() == null) {
                return scoreRepository.save(score);
            } else {
                Optional<Score> e = scoreRepository.getScore(score.getId());
                if (e.isPresent()) {
                    return score;
                } else {
                    return scoreRepository.save(score);
                }
            }
        } return score;
    }

    public Score update(Score score){
        if(validarCampos(score)) {
            if (score.getId() != null) {
                Optional<Score> s = scoreRepository.getScore(score.getId());
                if (s.isPresent()) {
                    if (score.getStarts() != null) {
                        s.get().setStarts(score.getStarts());
                    }
                    if (score.getMessageText() != null) {
                        s.get().setMessageText(score.getMessageText());
                    }/*
                    if(score.getReservation()!=null){
                        s.get().setReservation(score.getReservation());
                    }*/
                    scoreRepository.save(s.get());
                    return s.get();
                } else {
                    return score;
                }
            } else {
                return score;
            }
        } return score;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Score> score = scoreRepository.getScore(id);
        if (score.isPresent()){
            scoreRepository.delete(score.get());
            flag=true;
        }
        return flag;
    }

    public boolean validarCampos (Score score){
        return ((score.getStarts()>0 && score.getStarts()<=5) && score.getMessageText().length()<=250);
    }
}
