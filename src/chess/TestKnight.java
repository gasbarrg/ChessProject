package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestKnight {

    ChessPiece[][] board;
    Knight whiteKnight;
    Knight blackKnight;
    Move move;

    public TestKnight()
    {
        board = new ChessPiece[8][8];
        whiteKnight = new Knight(Player.WHITE);
        blackKnight = new Knight(Player.BLACK);
    }

    @Test
    public void testValidMove(){
        //set knight position
        board[3][3] = whiteKnight;
        //Test move up 2 and over 1
        move = new Move(3,3,1,4);
        assertTrue("up two and over one move", whiteKnight.isValidMove(move, board));
    }
    @Test
    public void testInvalidMove(){
        //set knight position
        board[3][3] = whiteKnight;
        //Test move up 2 and over 2
        move = new Move(3,3,1,5);
        assertFalse("Vertical move", whiteKnight.isValidMove(move, board));

        board[1][4] = whiteKnight;
        //Test move up 2 and over 1 onto its own white piece
        move = new Move(3,3,1,4);
        assertFalse("cant move on white piece", whiteKnight.isValidMove(move, board));

    }
    @Test
    public void testCaptureMove(){
        //set knight position
        board[3][3] = whiteKnight;
        board[1][4] = blackKnight;
        //Test move up 2 and over 1 to capture
        move = new Move(3,3,1,4);
        assertTrue("up two and over one move", whiteKnight.isValidMove(move, board));

    }


}
