package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPawn {

    ChessPiece[][] board;
    Pawn whitePawn;
    Pawn blackPawn;
    Move move;

    public TestPawn()
    {
        board = new ChessPiece[8][8];
        whitePawn = new Pawn(Player.WHITE);
        blackPawn = new Pawn(Player.BLACK);
    }

    @Test
    public void testValidFirstMove(){
        //set White pawn starting position so first move position
        board[6][0] = whitePawn;
        //Move White pawn up 1 position
        move = new Move(6,0,4,0);
        assertTrue("Moved 1 forward True", whitePawn.isValidMove(move, board));
        //Move White pawn up 2 position
        move = new Move(6,0,4,0);
        assertTrue("Moved 2 forward True", whitePawn.isValidMove(move, board));

        //set Black pawn starting position so first move position
        board[1][0] = blackPawn;
        //Move Black pawn up 1 position
        move = new Move(1,0,2,0);
        assertTrue("Moved 1 forward True", blackPawn.isValidMove(move, board));
        //Move Black pawn up 2 position
        move = new Move(1,0,3,0);
        assertTrue("Moved 2 forward True", blackPawn.isValidMove(move, board));

    }
    @Test
    public void testValidMove(){
        //set pawns starting position
        board[3][0] = whitePawn;
        board[2][1] = blackPawn;
        //Move pawn up 1 position
        move = new Move(3,0,2,0);
        assertTrue("Not first move moved 1 forward True", whitePawn.isValidMove(move, board));

    }
    @Test
    public void testInvalidMove(){
        //set White pawn not in starting position
        board[4][0] = whitePawn;
        //Move White pawn up 2 position
        move = new Move(4,0,2,0);
        assertFalse("Moved 2 forward True", whitePawn.isValidMove(move, board));
        //Tries to move horizontal
        move = new Move(5,1,5,4);
        assertFalse("Cant move horizontal", whitePawn.isValidMove(move, board));


    }
    @Test
    public void testCaptureMove(){
        board[3][0] = whitePawn;
        board[2][1] = blackPawn;
        //Captured black piece with diagonal move
        move = new Move(3,0,2,1);
        assertTrue("Captured black piece", whitePawn.isValidMove(move, board));

    }



}
