package chess;

import org.junit.Test;


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
    public void testMove(){



    }



}
