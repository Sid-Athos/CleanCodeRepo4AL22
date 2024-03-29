package com.cleancode.persistence.adapters;

import com.cleancode.domain.pojo.BattleHistory;
import com.cleancode.domain.pojo.Opponent;
import com.cleancode.domain.ports.out.BattleHistory.BattleHistoryPersistencePort;
import com.cleancode.persistence.entities.BattleHistoryEntity;
import com.cleancode.persistence.repositories.BattleHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattleHistoriesSpi implements BattleHistoryPersistencePort {

    private final BattleHistoryRepository battleHistoryRepository;

    public BattleHistoriesSpi(BattleHistoryRepository battleHistoryRepository) {
        this.battleHistoryRepository = battleHistoryRepository;
    }

    @Override
    public List<BattleHistory> findAllUserBattleHistoryByUserName(String userName) {
        return battleHistoryRepository.findAllByAttackedUsernameOrAttackerUsername(userName, userName)
                .stream()
                .map(entity -> BattleHistory.createOne(new Opponent(entity.getAttackerUsername(), entity.getAttackerCard()), new Opponent(entity.getAttackedUsername(), entity.getAttackedCard()),new Opponent(entity.getWinner(), null)))
                .collect(Collectors.toList());
    }

    @Override
    public Long addUserBattleHistory(BattleHistory battleHistory) {
        BattleHistoryEntity entity = new BattleHistoryEntity();
        entity.setAttackerUsername(battleHistory.getUser().getUserName());
        entity.setAttackerCard(battleHistory.getUser().getCardReference());
        entity.setAttackedUsername(battleHistory.getEnemy().getUserName());
        entity.setAttackedCard(battleHistory.getEnemy().getCardReference());
        entity.setWinner(battleHistory.getWinner().getUserName());
        return battleHistoryRepository.save(entity).getId();
    }
}
