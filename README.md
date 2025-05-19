# Reversi AI (Scala)

A Scala-based implementation of Reversi (Othello) featuring a Minimax AI with Alpha-Beta pruning, customizable heuristics, and support for future machine learning enhancements.
- Java Version: 11.0.27
- Scala Version: 2.13
- SBT Version: 1.10.11
---

## 🚀 Features

- Core Reversi game logic
- Minimax algorithm with:
  - Heuristic scoring
  - Move tracking
  - Evaluation caching
- Alpha-Beta pruning for optimized performance
- Support for machine-learning-driven heuristics (WIP)
- Database integration for persistent evaluation caching
- Iterative deepening and move ordering optimizations

---

## 🌐 REST API

- The REST API is implemented using the [Scala Play Framework](https://www.playframework.com/)
- Endpoints are defined in `conf/routes`
- This is a work-in-progress and subject to change
- Below is a list of currently supported methods:

### REST API Methods

| Method | Endpoint                             | Description                              |
|--------|--------------------------------------|------------------------------------------|
| GET    | `/games/create-sample`               | Create a sample game                     |
| GET    | `/games/get-sample`                  | Retrieve a sample game                   |
| GET    | `/games/new-game`                    | Create a new game                        |
| GET    | `/games/:gameId`                     | Get full game state                      |
| GET    | `/games/:gameId/valid-moves/:player` | Get valid moves for a given player       |
| POST   | `/games/:gameId/move`                | Apply a human player's move              |
| GET    | `/games/:gameId/ai-move`             | Retrieve the AI's suggested move         |
| POST   | `/games/:gameId/ai-move`             | Apply the AI's move to the game board    |

---

### 📝 API Notes

- The game board is stored as a single string with `\n` as the delimiter separating rows.
- Valid moves are returned using [chess algebra notation](https://en.wikipedia.org/wiki/Algebraic_notation_(chess)) (e.g., `E3`).
- Player tokens:
  - `'X'` → black pieces
  - `'O'` → white pieces
  - `' '` (space) → empty square
- The current player is tracked using the `currentTurn` field.


```scala
case class GameBoard(
  gameId: Long,              // Unique game identifier
  boardState: String,        // Serialized board state
  currentTurn: Char,         // 'X' or 'O'
  aiPlayer: Char,            // 'X' or 'O' (if AI is participating)
  isAIEnabled: Boolean       // Whether AI is enabled for the game
)
```

For future versions we could separate the board object from the meta-data
