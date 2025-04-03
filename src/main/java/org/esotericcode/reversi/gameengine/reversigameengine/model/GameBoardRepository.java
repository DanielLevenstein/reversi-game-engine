package org.esotericcode.reversi.gameengine.reversigameengine.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameBoardRepository extends CrudRepository<ReversiBoard, Long> {

}
