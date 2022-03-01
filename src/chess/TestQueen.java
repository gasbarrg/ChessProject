package chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestQueen {

    ChessPiece[][] board;
    Queen whiteQueen ;
    Queen blackQueen ;
    Move move;

    public TestQueen () {
        board = new ChessPiece[8][8];
        whiteQueen = new Queen(Player.WHITE);
        blackQueen = new Queen(Player.BLACK);
    }
    @Test
    public void testValidMove() {
        //set Queen  position
        board[3][3] = whiteQueen;
        //Move Queen  NE
        move = new Move(3, 3, 1, 5);
        assertTrue("NE move", whiteQueen.isValidMove(move, board));
        //Move Queen  SW
        move = new Move(3, 3, 0, 6);
        assertTrue("SW move", whiteQueen.isValidMove(move, board));
        //Move Queen  SE
        move = new Move(3, 3, 7, 7);
        assertTrue("SE move", whiteQueen.isValidMove(move, board));
        //Move Queen  NW
        move = new Move(3, 3, 0, 0);
        assertTrue("NW move", whiteQueen.isValidMove(move, board));

        //set Queen position
        board[3][0] = whiteQueen;
        //Move Queen Vertically North
        move = new Move(3,0,0,0);
        assertTrue("Vertical move", whiteQueen.isValidMove(move, board));
        //Move Queen Vertically South
        move = new Move(3,0,7,0);
        assertTrue("Vertical move", whiteQueen.isValidMove(move, board));

        //set new Queen starting position
        board[4][4] = whiteQueen;
        //Move Queen Horizontally East
        move = new Move(4,4,4,7);
        assertTrue("Horizontal move", whiteQueen.isValidMove(move, board));
        //Move Queen Horizontally West
        move = new Move(4,4,4,1);
        assertTrue("Horizontal move", whiteQueen.isValidMove(move, board));
    }
    @Test
    public void testInvalidMove(){
        //set Queen  position
        board[3][3] = whiteQueen;
        board[3][5] = whiteQueen;
        //Test if you can move into own piece
        move = new Move(3,5,3,3);
        assertFalse("Cant move if another white piece already there", whiteQueen.isValidMove(move, board));
        //Test if you can move over own piece
        move = new Move(3,5,3,0);
        assertFalse("Cant move over own piece", whiteQueen.isValidMove(move, board));

    }
    @Test
    public void testCaptureMove(){
        //set Queen  position
        board[1][5] = blackQueen;
        board[3][3] = whiteQueen;
        //Test if you can move onto back piece to capture
        move = new Move(3,3,1,5);
        assertTrue("Capture black", whiteQueen.isValidMove(move, board));
    }

}

