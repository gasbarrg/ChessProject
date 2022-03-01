package chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBishop {

    ChessPiece[][] board;
    Bishop whiteBishop;
    Pawn whitePawn;
    Bishop blackBishop;
    Move move;

    public TestBishop() {
        board = new ChessPiece[8][8];
        whiteBishop = new Bishop(Player.WHITE);
        whitePawn = new Pawn(Player.WHITE);
        blackBishop = new Bishop(Player.BLACK);
    }

    @Test
    public void testValidMove() {
        //set Bishop  position
        board[3][3] = whiteBishop;
        //Move Bishop  NE
        move = new Move(3, 3, 1, 5);
        assertTrue("NE move", whiteBishop.isValidMove(move, board));
        //Move Bishop  SW
        move = new Move(3, 3, 0, 6);
        assertTrue("SW move", whiteBishop.isValidMove(move, board));
        //Move Bishop  SE
        move = new Move(3, 3, 7, 7);
        assertTrue("SE move", whiteBishop.isValidMove(move, board));
        //Move Bishop  NW
        move = new Move(3, 3, 0, 0);
        assertTrue("NW move", whiteBishop.isValidMove(move, board));
    }
        @Test
        public void testInvalidMove(){
            //set Bishop  position
            board[3][3] = whiteBishop;
            board[1][5] = whitePawn;
            //Test if you can move into own piece
            move = new Move(3,3,1,5);
            assertFalse("Cant move if another white piece already there", whiteBishop.isValidMove(move, board));
            //Test if you can move over own piece
            move = new Move(3,7,0,6);
            assertFalse("Cant move over own piece", whiteBishop.isValidMove(move, board));

        }
        @Test
        public void testCaptureMove(){
            //set Bishop  position
            board[1][5] = blackBishop;
            board[3][3] = whiteBishop;
            //Test if you can move onto back piece to capture
            move = new Move(3,3,1,5);
            assertTrue("Capture black", whiteBishop.isValidMove(move, board));
        }

}
