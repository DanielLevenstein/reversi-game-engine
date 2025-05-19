package  org.esotericcode.reversi.gameengine.ai

import org.esotericcode.reversi.gameengine.ai.{Node, ScoredNode}
import org.esotericcode.reversi.gameengine.model.ImmutableReversiBoard
import org.junit.jupiter.api.Test

class ReversiMinMaxTreeTest {
  @Test
  def testMinMaxTree(): Unit = {
    val board = ImmutableReversiBoard.getEmptyBoard
    val board2 = board.makeMove("F4", 'X')
    val board3 = board2.makeMove("F5", 'O')
    val board4 = board3.makeMove("D6", 'X')
    board4.prettyPrint
    val scoredNode: ScoredNode = Node(board4, 'O', None).calculate(3)
    System.out.println(scoredNode.score)
    scoredNode.node.board.prettyPrint
  }
}
