package org.game.service;

import org.game.dao.AntidoteDao;
import org.game.entity.ActionType;
import org.game.entity.Antidote;
import org.game.entity.AntidoteAction;
import org.game.entity.PlantAntidote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AntidoteServiceImpl implements AntidoteService {

    @Autowired
    private AntidoteDao antidoteDao;


    public List<Antidote> getAllAntidotes() {

        return antidoteDao.findAll();
    }

    public Antidote getAntidoteById(int id) {

        return antidoteDao.findById(id).orElse(null);
    }

    public Antidote getAntidoteByName(String name) {
        return antidoteDao.findAntidoteByName(name).orElse(null);
    }

    public List<PlantAntidote> getPlantAntidoteByAntidoteId(int antidoteId) {
        return antidoteDao.findPlantAntidoteByAntidoteId(antidoteId).orElse(null);
    }

    // Uses the junction table to return list of actions for that antidote
    public List<ActionType> getActionTypeByAntidoteId(int antidoteId) {
        List<AntidoteAction> actionIds = antidoteDao.findAntidoteActionsByAntidoteId(antidoteId);
        List<ActionType> actionTypes = new ArrayList<ActionType>();

        if(actionIds != null) {
            for (AntidoteAction actionId : actionIds) {
                ActionType actionType = antidoteDao.findActionTypeByActionId(actionId.getAntidoteActionKey().getActionId()).orElse(null);
                actionTypes.add(actionType);
            }
            return actionTypes;
        }
        else{
            return null;
        }

    }
}
