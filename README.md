# Reversi AI (Scala)

A Scala-based implementation of Reversi (Othello) featuring a Minimax AI with Alpha-Beta pruning, customizable heuristics, and support for future machine learning enhancements.

---

## 🚀 Features

- ✅ Core Reversi game logic
- ✅ Minimax algorithm with:
    - Heuristic scoring
    - Move tracking
    - Evaluation caching
- ✅ Alpha-Beta pruning for optimized performance
- 🧠 Support for machine-learning-driven heuristics (WIP)
- 🗃️ Database integration for persistent evaluation caching
- 🔄 Iterative deepening and move ordering optimizations

---

## REST API


* This is a very rough draft of the design and will be updated as we make progress
* The Rest API methods are defined in the conf/routes file and are listed below for documentation
* Methods to implement:
  GET     /games/create-sample                    
  GET     /games/get-sample                       
  GET     /games/new-game                         
  GET     /games/:gameId                         
  GET     /games/:gameId/valid-moves/:player     
  POST    /games/:gameId/move               
  GET     /games/:gameId/ai-move               
  POST    /games/:gameId/ai-move                


API Notes:
The game board is stored as a single string with \n as the delimiter separating rows.
The getValidMoves method current returns a list of String values using the chess algebra notation.
Player and turn variables are currently stored as 'X' and 'O' values
with 'X' mapping to black pieces and 'O' mapping to white pieces and empty squares denoted by ' ' char.

I don't know if I am getting the terminology right.
Please correct me if I am wrong.

Database board object should have the following fields
* gameId : Long
* boardStateID : Long
* gameState : String
* currentPlayer : Char
* aiPlayer : Char
* lastMove : String
* isAIEnabled : Boolean


## Project Structure
```text
| | |-scala
| | | |-org
| | | | |-esotericcode
| | | | | |-reversi
| | | | | | |-gameengine
| | | | | | | |-reversigameengine
| | | | | | | | |-CommandLineGame.scala
| | | | | | | | |-model
| | | | | | | | | |-ImmutableReversiBoard.scala
| | | | | | | | | |-ReversiAIHeuristic.scala
| | | | | | | | | |-ReversiMinMaxTree.scala
| |-test
| | |-scala
| | | |-org
| | | | |-esotericcode
| | | | | |-reversi
| | | | | | |-gameengine
| | | | | | | |-reversigameengine
| | | | | | | | |-ImmutableReversiEngineGetValidMovesTest.scala
| | | | | | | | |-ImmutableReversiEngineMakeMoveTest.scala
| | | | | | | | |-model
| | | | | | | | | |-ReversiMinMaxTreeTest.scala
```

### 🛠 Requirements

- Scala 3.6.4
- Maven 3.9.9

### 🔧 Setup

1. **Clone the repository:**

```bash
git clone https://github.com/DanielLevenstein/reversi-game-engine.git
cd reversi-scala-ai
```

## 📦 Build & Run (Maven)
```bash
    mvn clean package
    java -cp reversi-game-engine-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.esotericcode.reversi.gameengine.reversigameengine.CommandLineGame --ai-player O 
```

