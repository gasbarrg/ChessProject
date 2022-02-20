package chess;

public class Rook extends ChessPiece {

	public Rook(Player player) {super(player);}

	public String type() {return "Rook";}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//Verify is a generally valid move
		if(!(super.isValidMove(move, board)))
			return false;

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
