package org.esotericcode.reversi.gameengine.ai

import org.esotericcode.reversi.gameengine.model.ImmutableReversiBoard
import org.esotericcode.reversi.gameengine.model.ImmutableReversiBoard.getOpponent

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
          if(i == 0 || i == 7){ // Piece is on edge
            if(j == 0 || j == 7) { // Take corner if possible
              score += 10
            }
            else if(i == 1 || i == 6){ // Don't give away corner
              score -=5
            } else {
              score += 5 // Other edge pieces are good
            }
          }
          else if(j == 0 || j == 7){ // Piece is on edge
            if(i == 1 || i == 6){ // Don't give away corner
              score -=5
            } else {
              score += 5 // Other edge pieces are good
            }
          }
          else if(i == 1 || i == 6){ // piece is adjacent to edge
            score -=1
            if(j == 1 || j == 6){ // Piece is diagonal from corner
              score -=10
            }
          }
          else if(j == 1 || j == 6){ // piece is adjacent to edge
            score -=1
          }
        }
      }
    }
    score
  }

}
