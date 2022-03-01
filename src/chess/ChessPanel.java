package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class ChessPanel extends JPanel {

    private JButton undo;
    private JButton[][] board;
    private ChessModel model;


    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;


    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other intance variables as needed

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));


        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if (model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);
                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        //Add Undo Button
        undo = new JButton("UNDO", null);
        buttonpanel.add(undo);
        undo.addActionListener(listener);

        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }
    /**************************************************************************/

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }
    /**************************************************************************/

    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }
    /**************************************************************************/

    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }
    /**************************************************************************/

    /**
     * Method that assigned each piece an icon
     */
    private void createIcons() {
        // Sets the Image for white player pieces
        String path = System.getProperty("user.dir");
        wRook = new ImageIcon(path + "./src/chess/wRook.png");
        wBishop = new ImageIcon(path + "./src/chess/wBishop.png");
        wQueen = new ImageIcon(path + "./src/chess/wQueen.png");
        wKing = new ImageIcon(path + "./src/chess/wKing.png");
        wPawn = new ImageIcon(path + "./src/chess/wPawn.png");
        wKnight = new ImageIcon(path + "./src/chess/wKnight.png");

        // Sets icons for black player pieces
        bRook = new ImageIcon(path + "./src/chess/bRook.png");
        bBishop = new ImageIcon(path + "./src/chess/bBishop.png");
        bQueen = new ImageIcon(path + "./src/chess/bQueen.png");
        bKing = new ImageIcon(path + "./src/chess/bKing.png");
        bPawn = new ImageIcon(path + "./src/chess/bPawn.png");
        bKnight = new ImageIcon(path + "./src/chess/bKnight.png");
    }
/**************************************************************************/


    /**
     * Method that updates the board
     */
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else {
                    if (model.pieceAt(r, c).player() == Player.WHITE) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(wPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(wRook);

                        if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(wKnight);

                        if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(wBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(wQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(wKing);
                    }

                    if (model.pieceAt(r, c).player() == Player.BLACK) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                                board[r][c].setIcon(bPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                                board[r][c].setIcon(bRook);

                        if (model.pieceAt(r, c).type().equals("Knight"))
                                board[r][c].setIcon(bKnight);

                        if (model.pieceAt(r, c).type().equals("Bishop"))
                                board[r][c].setIcon(bBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                                board[r][c].setIcon(bQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                                board[r][c].setIcon(bKing);
                    }
                }
        }
        repaint();
    }

    /**
     * Sets Board back to default colors.
     * Usually used to undo Highlights
     */
    private void recolorBoard(){
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                setBackGroundColor(r, c);
        }
    }
/**************************************************************************/


    /**
     * Highlights all possible moves of a chess piece
     * @param pRow
     * @param pCol
     */
    private void highlightMoves(int pRow, int pCol) {
        //Sets Selected Piece to GREEN
        board[pRow][pCol].setBackground(Color.GREEN.darker());
        //Highlights all possible moves
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++) {
                if (model.isValidMove(new Move(pRow, pCol, row, col)))
                    board[row][col].setBackground(Color.gray);}
        }
/**************************************************************************/

    /**
     * Undoes the last move
      */
    private void undoLast(){
        if(model.moveList.size() < 1)
            System.out.println("No Moves Left");
        else {
            //Get Last Move:
            Move lastMove = model.moveList.get(model.moveList.size() - 1);
            //Undo Last Move:
            model.undoMove(lastMove);
            //Remove Last Move from list
            model.moveList.remove(lastMove);
            //Remove last Piece from list
            model.pieceList.remove(model.pieceList.size() - 1);

            //Switch player
            model.setNextPlayer();
            displayBoard();
        }
    }
/**************************************************************************/

    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //Check for undo button:
            if(undo == event.getSource())
                undoLast();

            //Check for each board spot:
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())

                        //First Click
                        if (firstTurnFlag == true) {
                            //Check that current player is clicking their piece
                            //if(model.pieceAt(r,c) != null)
                                //if(model.pieceAt(r, c).player() == model.currentPlayer()) {
                                    fromRow = r;
                                    fromCol = c;
                                    firstTurnFlag = false;
                                    highlightMoves(r, c);
                            //}
                        }
                        //Second Click
                        else {
                            //Remove Highlights
                            recolorBoard();
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m))) {
                                model.move(m);
                                model.setNextPlayer();
                                displayBoard();
                                //Check for Game over
                                if (model.isComplete())
                                    JOptionPane.showMessageDialog(null, "Game Over");
                                else {
                                    if (model.inCheck(Player.BLACK))
                                        JOptionPane.showMessageDialog(null, "White - Check");
                                    if (model.inCheck(Player.WHITE))
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                }
                                //Do AI moves:
                                model.AI();
                                displayBoard();
                                //Check for Game over
                                if (model.isComplete()) {
                                    JOptionPane.showMessageDialog(null, "Game Over");
                                }
                                else {
                                    if (model.inCheck(Player.BLACK))
                                        JOptionPane.showMessageDialog(null, "White - Check");
                                    if (model.inCheck(Player.WHITE))
                                        JOptionPane.showMessageDialog(null, "Black - Check");
                                }
                            }

                        }
            }
        }
}
/**************************************************************************/