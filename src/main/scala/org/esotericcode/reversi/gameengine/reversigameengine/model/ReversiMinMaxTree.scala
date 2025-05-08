package org.esotericcode.reversi.gameengine.reversigameengine.model



case class Node(
        board: ImmutableReversiBoard,             // Game board state
        player: Char,                     // The player we should be optimizing for
        move: Option[String],                     // The player we should be optimizing for
        isMax: Boolean,
        children: Seq[Node] = Nil
     ) {
  def calculate(depth: Int): ScoredNode = {
    ReversiMinMaxTree.minimax(this, depth, player)

  }
}

case class ScoredNode(Node: Node, score: Double)


object ReversiMinMaxTree {

  def minimax(
               node: Node,
               depth: Int,
               player: Char, // The player we should be optimizing for

  ): ScoredNode  = {

    val moves = node.board.getValidMoves(player)
    if (depth == 0 || moves.isEmpty) {
      val score = node.board.heuristicCountMoves(player)
      return ScoredNode(node, score) : ScoredNode
    }

    val childScores = moves.map { move =>
      val newBoard = node.board.makeMove(move, player)
      val newPlayer =
        if(player.equals('X')) 'O'
        else 'X'
      val childNode = Node(newBoard, newPlayer, Some(move), !node.isMax)
      val score = minimax(childNode, depth - 1, player).score
      (childNode, score)
    }

    val (bestChild, bestScore) = if (node.isMax) {
      childScores.maxBy(_._2)
    } else {
      childScores.minBy(_._2)
    }
    ScoredNode(bestChild, bestScore)
  }
}