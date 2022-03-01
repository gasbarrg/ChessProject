package chess;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class ChessModel implements IChessModel {
	private IChessPiece[][] board;
	private Player player;


	/**
	 * Handles King's Row Position
	 */
	int kRow;
	int kRowBlack;
	int kRowWhite;
	/**
	 * Handles King's Column Position
	 */
	int kCol;
	int kColBlack;
	int kColWhite;

	/**
	 * Handles Temporary Move
	 */
	Move m;
	/**
	 * Handles Undo Move
	 */
	Move undo;

	/**Potential Moves for king to get out of check*/
	public ArrayList<Move> kingMoves = new ArrayList<>();

	/**List of all movees taken in the game */
	public ArrayList<Move> moveList = new ArrayList<>();

	/**List of all pieces taken in the game */
	public ArrayList<IChessPiece> pieceList = new ArrayList<>();

	/**List of all moves to put white king in check*/
	public ArrayList<Move> inCheckList = new ArrayList<>();

	/**
	 * Handles Board Size
	 */
	private final int BOARD_SIZE = 8;

	Random rand = new Random();


	/**
	 * prints out all chess pieces to board
	 */
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
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		for (int i = 0; i < 8; i++)
			board[6][i] = new Pawn(Player.WHITE);


		//Initialize Black Pieces
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		for (int i = 0; i < 8; i++)
			board[1][i] = new Pawn(Player.BLACK);
	}

	/**
	 * Checks to see if your king can't move to any spaces without still being in check
	 * the return sees if your number of checks is equal to the number of available moves.
	 *
	 * @return numChecks == numLoops && numLoops != 0
	 */
	public boolean isComplete() {
		//Update King Positions
		getKingPos();
		//
		int numChecks = 0, numLoops = 0;
		if (inCheck(Player.BLACK)) {
			for (int newKingRow = 0; newKingRow < 8; newKingRow++)
				for (int newKingCol = 0; newKingCol < 8; newKingCol++) {
					if (isValidMove(new Move(kRowBlack, kColBlack, newKingRow, newKingCol))) {
						move(new Move(kRowBlack, kColBlack, newKingRow, newKingCol));
						numLoops++;
						//Check for a valid move to Kings position
						for (int row = 0; row < 8; row++) //Row Incrementation
							for (int col = 0; col < 8; col++) { //Col incrementation
								if (board[row][col] != null && !pieceAt(row, col).type().equalsIgnoreCase("King") && !pieceAt(row, col).player().equals(Player.BLACK)) {
									//Make a new move to kings pos.
									m = new Move(row, col, newKingRow, newKingCol);
									//Check if move is valid
									if (board[row][col].isValidMove(m, board)) {
										numChecks++;
									}
								}
							}
						undoMove(new Move(kRowBlack, kColBlack, newKingRow, newKingCol));
						//Undo List Alterations done by previous move
						moveList.remove(moveList.size() - 1);
						pieceList.remove(pieceList.size() - 1);
					}
				}
			if (numChecks == numLoops && numLoops != 0)
				return true;
		}
		numChecks = 0;
		numLoops = 0;
		if (inCheck(Player.WHITE)) {
			for (int newKingRow = 0; newKingRow < 8; newKingRow++)
				for (int newKingCol = 0; newKingCol < 8; newKingCol++) {
					if (isValidMove(new Move(kRowWhite, kColWhite, newKingRow, newKingCol))) {
						//Temp move king to that pos
						move(new Move(kRowWhite, kColWhite, newKingRow, newKingCol));
						numLoops++;
						//Check for a valid move to Kings position
						for (int row = 0; row < 8; row++) //Row Incrementation
							for (int col = 0; col < 8; col++) { //Col incrementation
								if (board[row][col] != null && !pieceAt(row, col).type().equalsIgnoreCase("King") && !pieceAt(row, col).player().equals(Player.WHITE)) {
									//Make a new move to kings pos.
									m = new Move(row, col, newKingRow, newKingCol);
									//Check if move is valid
									if (board[row][col].isValidMove(m, board)) {
										numChecks++;
									}
								}
							}
						undoMove(new Move(kRowWhite, kColWhite, newKingRow, newKingCol));
						//Undo List Alterations done by previous move
						moveList.remove(moveList.size() - 1);
						pieceList.remove(pieceList.size() - 1);
					}
				}
			if (numChecks == numLoops && numLoops != 0)
				return true;
		}
		return false;
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
		//First, add piece to list AT destination
		pieceList.add(pieceAt(move.toRow, move.toColumn));
		//Make Move
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
		//Store Move:
		moveList.add(move);
	}

	/**
	 * Report whether the current player p is in check.
	 *
	 * @param p {@link chess.Move} the Player being checked
	 * @return {@code true} if the current player is in check, {@code false} otherwise.
	 */
	public boolean inCheck(Player p) {
		//Get position of king
		for (int row = 0; row < 8; row++) //Row Incrementation
			for (int col = 0; col < 8; col++) { //Col incrementation
				//Check Each position for a "King" Piece under player P
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == p) {
					//Assign to Kings pos.
					kRow = row;
					kCol = col;
				}
			}
		//Check for a valid move to Kings position
		for (int row = 0; row < 8; row++) //Row Incrementation
			for (int col = 0; col < 8; col++) { //Col incrementation
				if (board[row][col] != null) {
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

	/**
	 * Returns the current player
	 *
	 * @return player
	 */
	public Player currentPlayer() {
		return player;
	}

	/**
	 * Returns number of rows
	 *
	 * @return int
	 */
	public int numRows() {
		return BOARD_SIZE;
	}

	/**
	 * Returns number of columns
	 *
	 * @return int
	 */
	public int numColumns() {
		return BOARD_SIZE;
	}

	/**
	 * Returns type of piece at location
	 *
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
	public void undoMove(Move move) {
		//Create move to be undone
		undo = new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn);
		//Undo move
		move(undo);
		//Remove the "undo" move from the list
		moveList.remove(moveList.get(moveList.size() - 1));
		//Remove the "undo" piece added by undoing move
		pieceList.remove(pieceList.size() - 1);
		//Set old piece
		setPiece(move.toRow, move.toColumn, pieceList.get(pieceList.size() - 1));
	}

	/**
	 * Updates the positions of each player's king piece
	 */
	private void getKingPos() {
		//Get position of king
		for (int row = 0; row < 8; row++) { //Row Incrementation
			for (int col = 0; col < 8; col++) { //Col incrementation
				//Check Each position for a "King" Piece under player P
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == Player.BLACK) {
					//Assign to Kings pos.
					kRowBlack = row;
					kColBlack = col;
				}
				if (board[row][col] != null && pieceAt(row, col).type().equalsIgnoreCase("King")
						&& board[row][col].player() == Player.WHITE) {
					//Assign to Kings pos.
					kRowWhite = row;
					kColWhite = col;
				}
			}
		}
	}


	private Move getKingMoves() {
		int numChecks = 0, numLoops = 0;
			for (int newKingRow = 0; newKingRow < 8; newKingRow++)
				for (int newKingCol = 0; newKingCol < 8; newKingCol++) {
					if (isValidMove(new Move(kRowBlack, kColBlack, newKingRow, newKingCol))) {
						move(new Move(kRowBlack, kColBlack, newKingRow, newKingCol));
						numLoops++;
						//Check for a valid move to Kings position
						for (int row = 0; row < 8; row++) //Row Incrementation
							for (int col = 0; col < 8; col++) { //Col incrementation
								if (board[row][col] != null && !pieceAt(row, col).type().equalsIgnoreCase("King") && !pieceAt(row, col).player().equals(Player.BLACK)) {
									//Make a new move to kings pos.
									m = new Move(row, col, newKingRow, newKingCol);
									//Check if move is valid
									if (board[row][col].isValidMove(m, board)) {
										numChecks++;
									}
								}
							}
						//Move king back to original pos.
						undoMove(new Move(kRowBlack, kColBlack, newKingRow, newKingCol));
						//Undo List Alterations done by previous move
						moveList.remove(moveList.size() - 1);
						pieceList.remove(pieceList.size() - 1);
					}
					if (numLoops > numChecks && numLoops != 0) {
						kingMoves.add(new Move(kRowBlack, kColBlack, newKingRow, newKingCol));
						numLoops = 0; numChecks = 0;
					}
				}

			//Return a random move that is valid and won't result in checkmate
			if(kingMoves.size() == 0)
				return null;
			else {
				Move randMove = kingMoves.get(rand.nextInt(kingMoves.size()));
				kingMoves.clear();
				return randMove;
			}
	}

	private void tempMove(Move m){
		//Temporarily move king to new test pos.
		move(m);
		moveList.remove(moveList.size() - 1);
		pieceList.remove(pieceList.size() - 1);
	}

	private void inDanger() {
		//TODO
	}

	private Move putInCheck(){
		//Check for moves to put white king in check:
		getKingPos();
		//Check each piece
		for (int testRow = 0; testRow < 8; testRow++)
			for (int testCol = 0; testCol < 8; testCol++) {
				//Make a random move
				for (int moveRow = 0; moveRow < 8; moveRow++)
					for (int moveCol = 0; moveCol < 8; moveCol++) {
						if(isValidMove(new Move(testRow, testCol, moveRow, moveCol)) && !board[testRow][testCol].type().equalsIgnoreCase("King")) {
							move(new Move(testRow, testCol, moveRow, moveCol));
							//If There is then a valid move to king, add the potential move to the list.
							if (isValidMove(new Move(moveRow, moveCol, kRowWhite, kColWhite))) {
								inCheckList.add(new Move(testRow, testCol, moveRow, moveCol));
							}
							undoMove(new Move(testRow, testCol, moveRow, moveCol));
							//Remove old move data
							moveList.remove(moveList.size() - 1);
							pieceList.remove(pieceList.size() - 1);
						}
				}
			}
		if(inCheckList.size() > 0) {
			Move randMove = inCheckList.get(rand.nextInt(inCheckList.size()));
			inCheckList.clear();
			return randMove;
		}
		else
			return null;
	}

	private void takeInCheck(){
		if(inCheck(Player.WHITE)){
			for (int testRow = 0; testRow < 8; testRow++)
				for (int testCol = 0; testCol < 8; testCol++) {
					if(isValidMove(new Move(testRow, testCol, kRowWhite, kColWhite))){
						move(new Move(testRow, testCol, kRowWhite, kColWhite));
					}
				}
		}
	}
	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order.
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


		// a. Check to see if you are in check.
		// 		i. If so, get out of check by moving the king or placing a piece to block the check
		if (inCheck(Player.BLACK)) {
			//Get King position and make a move if it is in check
			getKingPos();
			Move kingMove = getKingMoves();
			//If complete, Game over.
			if (kingMove != null)
				move(kingMove);
		}
		//Try to Take King if in check:
		takeInCheck();
		//Try to put enemy king in check
		Move inCheckMove = putInCheck();
		if(inCheckMove != null)
			move(inCheckMove);

	}
}
