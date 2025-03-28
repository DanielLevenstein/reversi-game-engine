package org.esotericcode.reversi.gameengine.reversigameengine.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.util.Arrays;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Data
//@Document
public class ReversiBoard {
    private @Id @GeneratedValue Long id;

    @Setter
    @Getter
    @Lob
    private String gameBoardStr;

    public char[][] getGameBoardAsArray() {
        return Arrays.stream(gameBoardStr.split("\n"))
                     .map(String::toCharArray)
                     .toArray(char[][]::new);
    }

    public void setGameBoardAsArray(char[][] gameBoardArray) {
        this.gameBoardStr = Arrays.stream(gameBoardArray)
                                   .map(String::new)
                                   .reduce((row1, row2) -> row1 + "\n" + row2)
                                   .orElse("");
    }

    private char getPiece(int letterIndex, int numIndex) {

        int charIndex = (numIndex * 9) + letterIndex;
        return this.gameBoardStr.charAt(charIndex);
    }

    public char getPiece(String algebraicNotation) {
        int letterIndex = algebraicNotation.charAt(0) - 'A';
        int numberIndex = 8 - (algebraicNotation.charAt(1) - '0');
        return getPiece(letterIndex, numberIndex);
    }

    public boolean isValidMove(String algebraicNotation, char player) {
        return isValidMove(algebraicNotation.charAt(0) - 'A', 8 - (algebraicNotation.charAt(1) - '0'), player);
    }

    public boolean makeMove(String algebraicNotation, char player) {
        if (!isValidMove(algebraicNotation.charAt(0) - 'A', 8 - (algebraicNotation.charAt(1) - '0'), player)) {
            return false;
        }
        else {
            makeMove(algebraicNotation.charAt(0) - 'A', 8 - (algebraicNotation.charAt(1) - '0'), player);
            return true;
        }
    }

    private void setPiece(int letterIndex, int numIndex, char piece) {
        // Calculate the index of the char to replace
        int charIndex = (numIndex * 9) + letterIndex;
        var sb = new StringBuilder(this.gameBoardStr);
        sb.setCharAt(charIndex, piece);
        this.gameBoardStr = sb.toString();
    }

    public void setPiece(String algebraicNotation, char piece) {
        int letter = algebraicNotation.charAt(0) - 'A';
        int number = 8 - (algebraicNotation.charAt(1) - '0');
        setPiece(letter, number, piece);
    }

    public ReversiBoard() {
        this.gameBoardStr = this.getEmptyBoard();
    }

    public ReversiBoard(char [][] gameBoardArray) {
        this.setGameBoardAsArray(gameBoardArray);
    }

    public ReversiBoard(String gameBoardStr) {
        this.gameBoardStr = gameBoardStr;
    }

    public String getEmptyBoard() {
        StringBuilder emptyBoard = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i == 3 && j == 3) {
                    emptyBoard.append("O");
                } else if(i == 3 && j == 4) {
                    emptyBoard.append("X");
                } else if(i == 4 && j == 3) {
                    emptyBoard.append("X");
                } else if(i == 4 && j == 4) {
                    emptyBoard.append("O");
                } else {
                    emptyBoard.append(" ");
                }
            }
            emptyBoard.append("\n");
        }
        return emptyBoard.toString();
    }

    public String prettyPrint() {
        String header = "_-A-B-C-D-E-F-G-H-";
        int row = 8;
        StringBuilder prettyBoard = new StringBuilder(header + "\n");
        for(int i = 0; i < 8; i++) {
            prettyBoard.append(row).append("|");
            for(int j = 0; j < 8; j++) {
                prettyBoard.append(this.getPiece(j, i)).append("|");
            }
            prettyBoard.append("\n");
            row --;
        }
        prettyBoard.append(header).append("\n");
        System.out.println(prettyBoard);
        return prettyBoard.toString();
    }


    private boolean isValidMove(int ix, int iy, char player) {

        if(!this.isEmptySpace(ix, iy)) {
            return false;
        }
        // Check 9 surrounding spaces for token belonging to other player.
        int xOffsetMin = - 1;
        int xOffsetMax = + 1;
        int yOffsetMin = - 1;
        int yOffsetMax = + 1;

        for(int xOffset = xOffsetMin; xOffset <= xOffsetMax; xOffset++) {
            for (int yOffset = yOffsetMin; yOffset <= yOffsetMax; yOffset++) {
                int i = ix + xOffset;
                int j = iy + yOffset;

                if(xOffset == 0 && yOffset == 0)  {
                    continue;
                }
                if(onBoard(i,j)) {
                    char checkingColor = this.getPiece(i, j);
                    if (isOpponent(checkingColor, player)) {
                        // Found a piece belonging to the other player.
                        i += xOffset;
                        j += yOffset;
                        while (onBoard(i,j) &&  isOpponent(checkingColor, player)) {
                            checkingColor = this.getPiece(i, j);
                        }
                        return checkingColor == player;
                    }
                }
            }
        }
        return false;
    }



    private void makeMove(int ix, int iy, char player) {
        setPiece(ix, iy, player);
        // Check 9 surrounding spaces for token belonging to other player.
        int xOffsetMin = - 1;
        int xOffsetMax = + 1;
        int yOffsetMin = - 1;
        int yOffsetMax = + 1;

        for(int xOffset = xOffsetMin; xOffset <= xOffsetMax; xOffset++) {
            for (int yOffset = yOffsetMin; yOffset <= yOffsetMax; yOffset++) {
                int i = ix + xOffset;
                int j = iy + yOffset;

                if(xOffset == 0 && yOffset == 0)  {
                    continue;
                }
                boolean flipRow = false;
                if(onBoard(i,j)) {
                    char checkingColor = this.getPiece(i, j);
                    if (isOpponent(checkingColor, player)) {
                        // Before flipping any pieces check of players piece is at the end of the row.
                        do {
                            checkingColor = this.getPiece(i, j);
                            i += xOffset;
                            j += yOffset;
                            if (this.getPiece(i, j) == player) {
                                flipRow = true;
                            }
                        } while (isOpponent(checkingColor, player) && onBoard(i, j));

                    }
                }
                if (flipRow) {
                    i = ix + xOffset;
                    j = iy + yOffset;
                    char checkingPiece = this.getPiece(i, j);
                    while (isOpponent(checkingPiece, player)) {
                        do {
                            this.setPiece(i, j, player);
                            i += xOffset;
                            j += yOffset;
                            checkingPiece = this.getPiece(i, j);
                        } while (isOpponent(checkingPiece, player) && onBoard(i, j));
                    }
                }
            }
        }
    }

    private boolean isEmptySpace(int ix, int iy) {
        return this.getPiece(ix, iy) == ' ';
    }

    private boolean isOpponent(char checkingPiece, char player) {
        return checkingPiece != player && checkingPiece != ' ';
    }

    private boolean onBoard(int ix, int iy) {
        return ix >= 0 && ix < 8 && iy >= 0 && iy < 8;
    }

    public String toString() {
        return this.gameBoardStr;
    }


}
