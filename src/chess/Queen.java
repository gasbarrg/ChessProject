package chess;

public class Queen extends ChessPiece {

	public Queen(Player player) {super(player);}

	public String type() {return "Queen";}
	
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//Verify is a generally valid move
		if (!(super.isValidMove(move, board)))
			return false;

		//First, Check Bishop Moves:
		////////////////////////////////////////////////////////////////////////////////
		//Boolean for North-West / North-East
		boolean NW = (move.toRow <= move.fromRow && (move.toColumn <= move.fromColumn));
		boolean NE = (move.toRow <= move.fromRow && (move.toColumn >= move.fromColumn));
		//Boolean for South-West / South-East
		boolean SW = (move.toRow >= move.fromRow && (move.toColumn <= move.fromColumn));
		boolean SE = (move.toRow >= move.fromRow && (move.toColumn >= move.fromColumn));
		//Boolean for Diagonal Move
		boolean diagonal = Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn);


		//Verify on a diagonal:
		if (diagonal) {
			//Check North-West Condition:
			if (NW) {
				int moves = move.fromRow - move.toRow;
				//Get clear path on NW direction:
				for (int m = 1; m < moves; m++)
					if(board[move.fromRow - m][move.fromColumn - m] != null)
						return false;
				return true;
			}
			//Check North-East Condition:
			if (NE) {
				int moves = move.fromRow - move.toRow;
				//Get clear path on NE direction:
				for (int m = 1; m < moves; m++)
					if(board[move.fromRow - m][move.fromColumn + m] != null)
						return false;
				return true;
			}
			//Check South-West Condition:
			if (SW) {
				int moves = move.toRow - move.fromRow;
				//Get clear path on SW direction:
				for (int m = 1; m < moves; m++)
					if(board[move.fromRow + m][move.fromColumn - m] != null)
						return false;
				return true;
			}
			//Check South-East Condition:
			if (SE) {
				int moves = move.toRow - move.fromRow;
				//Get clear path on SE direction:
				for (int m = 1; m < moves; m++)
					if(board[move.fromRow + m][move.fromColumn + m] != null)
						return false;
				return true;
			}
			//Else, return false
			return false;
		}

		//Second, Check Rook Moves:
		////////////////////////////////////////////////////////////////////////////////
		//Verify moving in straight line
		if(move.fromRow != move.toRow && move.fromColumn != move.toColumn)
			return false;

		//Verify no pieces between
		if(this.player() == Player.WHITE){
			//Check for vertical movement:
			if(move.fromColumn == move.toColumn && move.fromRow != move.toRow){
				//If Vertical movement, check that there are no pieces DOWN
				for(int row = move.fromRow + 1; row < move.toRow; row++) {
					if (board[row][move.fromColumn] != null) {
						return false;}
				}
				//If Vertical movement, check that there are no pieces UP
				for(int row = move.fromRow - 1; row > move.toRow; row--) {
					if (board[row][move.fromColumn] != null) {
						return false;}
				}
			}
			//Check for horizontal movement:
			if(move.fromColumn != move.toColumn && move.fromRow == move.toRow) {
				//If horizontal movement, check that there are no pieces RIGHT
				for(int col = move.fromColumn + 1; col < move.toColumn; col++) {
					if (board[move.fromRow][col] != null) {
						return false;}
				}
				//If horizontal movement, check that there are no pieces LEFT
				for(int col = move.fromColumn - 1; col > move.toColumn; col--) {
					if (board[move.fromRow][col] != null) {
						return false;}
				}
			}
		}
		//Verify for black pieces
		if(this.player() == Player.BLACK){
			//Check for vertical movement:
			if(move.fromColumn == move.toColumn && move.fromRow != move.toRow){
				//If Vertical movement, check that there are no pieces DOWN
				for(int row = move.fromRow - 1; row > move.toRow; row--) {
					if (board[row][move.fromColumn] != null) {
						return false;}
				}
				//If Vertical movement, check that there are no pieces UP
				for(int row = move.fromRow + 1; row < move.toRow; row++) {
					if (board[row][move.fromColumn] != null) {
						return false;}
				}
			}
			//Check for horizontal movement:
			if(move.fromColumn != move.toColumn && move.fromRow == move.toRow) {
				//If horizontal movement, check that there are no pieces RIGHT
				for(int col = move.fromColumn + 1; col < move.toColumn; col++) {
					if (board[move.fromRow][col] != null) {
						return false;}
				}
				//If horizontal movement, check that there are no pieces LEFT
				for(int col = move.fromColumn - 1; col > move.toColumn; col--) {
					if (board[move.fromRow][col] != null) {
						return false;}
				}
			}
		}

		//All other cases, return true;
		return true;
	}
}
