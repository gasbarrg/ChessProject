package chess;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
	private Player player;


	/** Handles King's Row Position */
	int kRow;
	/** Handles King's Column Position */
	int kCol;

	/** Handles Temporary Move */
	Move m;
	/** Handles Undo Move */
	Move undo;


	public ArrayList<Move> moveList = new ArrayList<>();

	/** Handles Board Size */
	private final int BOARD_SIZE = 8;


	public ChessModel() {
		board = new IChessPiece[8][8];
		player = Player.WHITE;

		//Initialize White Pieces
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
		for(int i = 0; i < 8; i++)
			board[6][i] = new Pawn(Player.WHITE);


		//Initialize Black Pieces
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		for(int i = 0; i < 8; i++)
			board[1][i] = new Pawn(Player.BLACK);
	}

	public boolean isComplete() {
		boolean valid = false;
		return valid;
	}

	@Override
	public boolean isValidMove(Move move) {
		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

		return valid;
	}

	@Override
	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;

		//Store Move:
		moveList.add(move);
	}

	/**
	 * Report whether the current player p is in check.
	 * @param  p {@link chess.Move} the Player being checked
	 * @return {@code true} if the current player is in check, {@code false} otherwise.
	 */
	public boolean inCheck(Player p) {
		//Get position of king
		for(int row = 0; row < 8; row++) //Row Incrementation
			for(int col = 0; col < 8; col++) { //Col incrementation
				//Check Each position for a "King" Piece under player P
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == p) {
					//Assign to Kings pos.
					kRow = row;
					kCol = col;
				}
			}
			//Check for a valid move to Kings position
			for(int row = 0; row < 8; row++) //Row Incrementation
				for(int col = 0; col < 8; col++) { //Col incrementation
					if (board[row][col] != null){
						//Make a new move to kings pos.
						m = new Move(row, col, kRow, kCol);
						//Check if move is valid
						if (board[row][col].isValidMove(m, board))
							return true;
					}
				}
		//Else, return false.
		return false;
	}

	/**Returns the current player
	 * @return player
	 */
	public Player currentPlayer() {
		return player;
	}

	/**
	 * Returns number of rows
	 * @return int
	 */
	public int numRows() {
		return BOARD_SIZE;
	}

	/**
	 * Returns number of columns
	 * @return int
	 */
	public int numColumns() {
		return BOARD_SIZE;
	}

	/**
	 * Returns type of piece at location
	 * @return IChessPiece
	 */
	public IChessPiece pieceAt(int row, int column) {		
		return board[row][column];
	}

	/**
	 * Sets current player to the next player by calling player.next()
	 */
	public void setNextPlayer() {
		player = player.next();
	}

	/**
	 * Places a piece of type "piece" at location row, column.
	 *
	 * @param row
	 * @param column
	 * @param piece
	 */
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	/**
	 * Undoes a move.
	 */
	public void undoMove(Move move){
			undo = new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn);
			move(undo);
			//Remove the "undo" move from the list
			moveList.remove(moveList.get(moveList.size() - 1));
	}

	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		}
}
