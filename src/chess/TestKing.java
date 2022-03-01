package chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestKing {


    ChessPiece[][] board;
    King whiteKing;
    King blackKing;
    Move move;

    public TestKing() {
        board = new ChessPiece[8][8];
        whiteKing = new King(Player.WHITE);
        blackKing = new King(Player.BLACK);
    }

    @Test
    public void testValidMove() {
        //set King  position
        board[3][3] = whiteKing;
        //Move King  NE 1
        move = new Move(3, 3, 2, 4);
        assertTrue("NE move", whiteKing.isValidMove(move, board));
        //Move King  SW 1
        move = new Move(3, 3, 4, 2);
        assertTrue("SW move", whiteKing.isValidMove(move, board));
        //Move King  SE 1
        move = new Move(3, 3, 4, 4);
        assertTrue("SE move", whiteKing.isValidMove(move, board));
        //Move King  NW 1
        move = new Move(3, 3, 2, 2);
        assertTrue("NW move", whiteKing.isValidMove(move, board));
        //Move King  UP 1
        move = new Move(3, 3, 2, 3);
        assertTrue("UP move", whiteKing.isValidMove(move, board));
        //Move King  DOWN 1
        move = new Move(3, 3, 4, 3);
        assertTrue("DOWN move", whiteKing.isValidMove(move, board));
        //Move King  LEFT 1
        move = new Move(3, 3, 3, 2);
        assertTrue("LEFT move", whiteKing.isValidMove(move, board));
        //Move King  RIGHT 1
        move = new Move(3, 3, 3, 4);
        assertTrue("RIGHT move", whiteKing.isValidMove(move, board));
    }
    @Test
    public void testInvalidMove(){
        //set King  position
        board[3][3] = whiteKing;
        board[3][4] = whiteKing;
        //See if can move on white piece
        move = new Move(3, 3, 3, 4);
        assertFalse("cant move on own color piece", whiteKing.isValidMove(move, board));

    }
    @Test
    public void testCaptureMove(){
        //set Bishop  position
        board[1][5] = whiteKing;
        board[3][3] = whiteKing;
        //Test if you can move onto back piece to capture
        move = new Move(3,3,1,5);
        assertTrue("Capture black", whiteKing.isValidMove(move, board));
    }

}


