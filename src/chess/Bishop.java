package chess;
import java.lang.Math;
public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		//Verify is a generally valid move
		if (!(super.isValidMove(move, board)))
			return false;

		//Boolean for North-West / North-East
		boolean NW = (move.toRow <= move.fromRow && (move.toColumn <= move.fromColumn));
		boolean NE = (move.toRow <= move.fromRow && (move.toColumn >= move.fromColumn));
		//Boolean for South-West / South-East
		boolean SW = (move.toRow >= move.fromRow && (move.toColumn <= move.fromColumn));
		boolean SE = (move.toRow >= move.fromRow && (move.toColumn >= move.fromColumn));
		//Boolean for Diagonal Move
		boolean diagonal = Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn);


		if (this.player() == Player.WHITE) {
/*<<<<<<< Updated upstream
			for (int i = 0; i <= 7  ; i++) {
				if ((move.toRow + i == move.fromRow && (move.toColumn + i == move.fromColumn))){
					return true;
				}
				if ((move.toRow + i == move.fromRow && (move.toColumn  - i == move.fromColumn))) {
					return true;
				}
				if ((move.toRow - i == move.fromRow && (move.toColumn + i == move.fromColumn))){
					return true;
				}
				if ((move.toRow - i == move.fromRow && (move.toColumn  - i == move.fromColumn))) {
					return true;
=======*/
			//Verify on a diagonal:
			if (diagonal) {
				//Check North-West Condition:
				if (NW) {
					int moves = move.fromRow - move.toRow;
					//Get clear path on NW direction:
					for (int m = 1; m < moves; m++)
						if(board[move.fromRow - m][move.fromColumn - m] != null)
							return false;
					return board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null;
				}
				//Check North-East Condition:
				if (NE) {
					int moves = move.fromRow - move.toRow;
					//Get clear path on NE direction:
					for (int m = 1; m < moves; m++)
						if(board[move.fromRow - m][move.fromColumn + m] != null)
							return false;
					return board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null;
				}
				//Check South-West Condition:
				if (SW) {
					int moves = move.toRow - move.fromRow;
					//Get clear path on SW direction:
					for (int m = 1; m < moves; m++)
						if(board[move.fromRow + m][move.fromColumn - m] != null)
							return false;
					return board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null;
				}
				//Check South-East Condition:
				if (SE) {
					int moves = move.toRow - move.fromRow;
					//Get clear path on SE direction:
					for (int m = 1; m < moves; m++)
						if(board[move.fromRow + m][move.fromColumn + m] != null)
							return false;
					return board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null;
				}
			}
		}
		//Else, return false 
		return false;
		// More code is needed


	}
}



