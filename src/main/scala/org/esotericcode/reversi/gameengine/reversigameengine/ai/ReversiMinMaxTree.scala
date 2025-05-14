package org.esotericcode.reversi.gameengine.reversigameengine.ai

import org.esotericcode.reversi.gameengine.reversigameengine.ai.ReversiAIHeuristic.combinedHeuristic
import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard

import scala.collection.mutable

case class Node(
        board: ImmutableReversiBoard,             // Game board state
        player: Char,                             // The player we should be optimizing for
        move: Option[String],                     // The player we should be optimizing for
     ) {

  def calculate(depth: Int): ScoredNode = {
    ReversiMinMaxTree.minimax(this, depth, player, combinedHeuristic)
  }
}


case class MoveKey(immutableReversiBoard: ImmutableReversiBoard, player: Char)
case class ScoredNode(node: Node, score: Double, depth: Int)

object ReversiMinMaxTree {
  val scoredNodeMap: mutable.Map[MoveKey, ScoredNode] = new mutable.HashMap[MoveKey, ScoredNode]

  def minimax(
               node: Node, // Board state and player for current node
               depth: Int, // Depth we are searching to
               originalPlayer: Char, // Original player
               heuristic: (ImmutableReversiBoard, Char) => Double,
               alpha: Double = Double.NegativeInfinity,
               beta: Double = Double.PositiveInfinity
             ): ScoredNode = {
    val moveKey = MoveKey(node.board, node.player)
    val cached = scoredNodeMap.get(moveKey)

    if (cached.nonEmpty && cached.get.depth >= depth) {
      return cached.get
    }

    val moves = node.board.getValidMoves(node.player)

    if (depth == 0 || moves.isEmpty) {
      val score = heuristic(node.board, originalPlayer)
      return ScoredNode(node, score, depth)
    }

    var bestScore = if (node.player == originalPlayer) Double.NegativeInfinity else Double.PositiveInfinity
    var bestChild: Option[Node] = None
    var alphaVar = alpha
    var betaVar = beta
    var i = 0
    var shouldPrune = false

    while (i < moves.length && !shouldPrune) {
      val move = moves(i)
      val newBoard = node.board.makeMove(move, node.player)
      val nextPlayer = if (node.player == 'X') 'O' else 'X'
      val childNode = Node(newBoard, nextPlayer, Some(move))
      val score = minimax(childNode, depth - 1, originalPlayer, heuristic, alphaVar, betaVar).score

      if (node.player == originalPlayer) {
        if (score > bestScore) {
          bestScore = score
          bestChild = Some(childNode)
        }
        alphaVar = math.max(alphaVar, bestScore)
      } else {
        if (score < bestScore) {
          bestScore = score
          bestChild = Some(childNode)
        }
        betaVar = math.min(betaVar, bestScore)
      }

      if (betaVar <= alphaVar) {
        shouldPrune = true
      }

      i += 1
    }

    val finalNode = ScoredNode(bestChild.getOrElse(node), bestScore, depth)
    scoredNodeMap.put(moveKey, finalNode)
    finalNode
  }
}