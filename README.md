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
- 🗃️ Optional database integration for persistent evaluation caching
- 🔄 Planned: Iterative deepening and move ordering optimizations

---

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

