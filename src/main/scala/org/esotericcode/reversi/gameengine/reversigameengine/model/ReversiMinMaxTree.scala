package org.esotericcode.reversi.gameengine.reversigameengine.model

import org.esotericcode.reversi.gameengine.reversigameengine.model

import java.util
import scala.collection.mutable


case class Node(
        board: ImmutableReversiBoard,             // Game board state
        player: Char,                             // The player we should be optimizing for
        move: Option[String],                     // The player we should be optimizing for
     ) {
  def calculate(depth: Int): ScoredNode = {
    ReversiMinMaxTree.minimax(this, depth, player)

  }
}

case class MoveKey(immutableReversiBoard: ImmutableReversiBoard, player: Char)
case class ScoredNode(node: Node, score: Double, depth: Int)

object ReversiMinMaxTree {
  val scoredNodeMap: mutable.Map[MoveKey, ScoredNode] = new mutable.HashMap[MoveKey, ScoredNode]
  def minimax(
               node: Node,
               depth: Int,
               player: Char, // The player we should be optimizing for
  ): ScoredNode  = {
    val moveKey = MoveKey(node.board, node.player)
    val currentNode = scoredNodeMap.get(moveKey)

    if(currentNode.nonEmpty && currentNode.get.depth >= depth){
      currentNode.get
    } else 
    {
      val moves = node.board.getValidMoves(player)

      if (depth == 0 || moves.isEmpty) {
        val score = node.board.heuristicCountMoves(player)
        return ScoredNode(node, score, depth): ScoredNode
      }
      val childScores = moves.map { move =>
        val newBoard = node.board.makeMove(move, player)
        val newPlayer =
          if (player.equals('X')) 'O'
          else 'X'
        val childNode = Node(newBoard, newPlayer, Some(move))
        val score = minimax(childNode, depth - 1, player).score
        (childNode, score, depth - 1)
      }

      val (bestChild, bestScore, _) =
        childScores.maxBy(_._2)

      System.out.println(bestChild.move, bestScore, depth)
      val finalScoredNode = ScoredNode(bestChild, bestScore, depth)
      scoredNodeMap.put(moveKey, finalScoredNode)
      finalScoredNode
    }
  }
}