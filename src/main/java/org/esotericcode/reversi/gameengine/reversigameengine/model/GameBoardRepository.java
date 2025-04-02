package org.esotericcode.reversi.gameengine.reversigameengine.model;

import org.springframework.data.repository.CrudRepository;


public interface GameBoardRepository extends CrudRepository<ReversiBoard, Long> {
}
