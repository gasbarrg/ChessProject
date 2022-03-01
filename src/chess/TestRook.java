package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRook {

    ChessPiece[][] board;
    Rook whiteRook;
    Rook blackRook;
    Move move;

    public TestRook()
    {
        board = new ChessPiece[8][8];
        whiteRook = new Rook(Player.WHITE);
        blackRook = new Rook(Player.BLACK);
    }

    @Test
    public void testValidMove(){
        //set Rook starting position
        board[3][0] = whiteRook;
        //Move Rook Vertically North
        move = new Move(3,0,0,0);
        assertTrue("Vertical move", whiteRook.isValidMove(move, board));
        //Move Rook Vertically South
        move = new Move(3,0,7,0);
        assertTrue("Vertical move", whiteRook.isValidMove(move, board));

        //set new Rook starting position
        board[3][4] = whiteRook;
        //Move Rook Horizontally East
        move = new Move(3,4,3,7);
        assertTrue("Horizontal move", whiteRook.isValidMove(move, board));
        //Move Rook Horizontally West
        move = new Move(3,4,3,1);
        assertTrue("Horizontal move", whiteRook.isValidMove(move, board));
    }
    @Test
    public void testInvalidMove(){
        //set Rook starting position
        board[3][3] = whiteRook;
        board[3][7] = whiteRook;
        //Test if you can move into own piece
        move = new Move(3,7,3,3);
        assertFalse("Cant move if another white piece already there", whiteRook.isValidMove(move, board));
        //Test if you can move over own piece
        move = new Move(3,7,3,3);
        assertFalse("Cant move over own piece", whiteRook.isValidMove(move, board));

    }
    @Test
    public void testCaptureMove(){
        //set Rook starting position
        board[3][0] = blackRook;
        board[3][4] = whiteRook;
        //Test if you can move onto back piece to capture
        move = new Move(3,4,3,0);
        assertTrue("Capture black", whiteRook.isValidMove(move, board));
    }



}
