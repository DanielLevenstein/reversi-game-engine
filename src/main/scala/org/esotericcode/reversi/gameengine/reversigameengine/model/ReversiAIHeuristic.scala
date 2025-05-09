package org.esotericcode.reversi.gameengine.reversigameengine.model

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard.getOpponent

object ReversiAIHeuristic {

  def combinedHeuristic(board: ImmutableReversiBoard, player: Char): Double = {
    heuristicCornerControl(board, player) + heuristicCountMoves(board, player)
  }

  // Simple heuristic that trys to maximize the number of moves in the future.
  def heuristicCountMoves(board: ImmutableReversiBoard, player: Char): Double = {
    board.getValidMoves(player).size - board.getValidMoves(getOpponent(player)).size
  }

  // Simple implementation of corner control heuristic
  def heuristicCornerControl(board: ImmutableReversiBoard, player: Char): Double = {
    var score = 0
    for(i <- 0 until 8){
      for(j <- 0 until 8){
        if(board.getPiece(i,j) == player){

          // +10 for all and corners and edges -5 for all pieces adjacent to corners and +5 for all edges.
          if(i == 0 || i == 7 || j == 0 || j == 7){
            if(j == 1 || j == 6 || i == 1 || i == 6){
              score -= 5
            } else {
              if((i == 0 && j == 0) || (i == 7 && j == 7) || (i == 7 && j == 0) || (i == 0 && j == 7)) {
                score += 5
              } else {
                score += 10
              }
            }
          }

          if((i == 1 && j == 1) || (i == 6 && j == 6) || (i == 6 && j == 1) || (i == 1 && j == 6)){
            score -= 5
          }
        }
      }
    }
    score
  }

}
