package me.zhongezhao.Chess.Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.zhongezhao.Chess.Pieces.Bishop;
import me.zhongezhao.Chess.Pieces.King;
import me.zhongezhao.Chess.Pieces.Knight;
import me.zhongezhao.Chess.Pieces.Pawn;
import me.zhongezhao.Chess.Pieces.Piece;
import me.zhongezhao.Chess.Pieces.Queen;
import me.zhongezhao.Chess.Pieces.Rook;
import me.zhongezhao.Chess.Util.Method;

public class GameBoard {

	public Piece[][] boardArray;
	public List<List<Piece>> captured;
	private Game game;
	public final int BLACK_ADVANCING_DIRECTION, WHITE_ADVANCING_DIRECTION;
	
	public GameBoard(Game game) {
		
		boardArray = new Piece[8][8];
		this.game = game;
		
		captured = new ArrayList<List<Piece>>();
		
		List<Piece> blackCaptured = new ArrayList<Piece>();
		List<Piece> whiteCaptured = new ArrayList<Piece>();
		captured.add(blackCaptured);
		captured.add(whiteCaptured);
		
		// set up board
		BLACK_ADVANCING_DIRECTION = 1;
		WHITE_ADVANCING_DIRECTION = -1;
		setUp();
		
	}
	
	public void setUp() {
		boardArray[0][0] = new Rook(0, 0, game.playerBlack);
		boardArray[0][1] = new Knight(0, 1, game.playerBlack);
		boardArray[0][2] = new Bishop(0, 2, game.playerBlack);
		boardArray[0][3] = new Queen(0, 3, game.playerBlack);
		boardArray[0][4] = new King(0, 4, game.playerBlack);
		boardArray[0][5] = new Bishop(0, 5, game.playerBlack);
		boardArray[0][6] = new Knight(0, 6, game.playerBlack);
		boardArray[0][7] = new Rook(0, 7, game.playerBlack);
		
		// black pawns
		for (int i = 0; i < 8; i++) {
			boardArray[1][i] = new Pawn(1, i, game.playerBlack);
		}
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				boardArray[i][j] = null;
			}
		}
		
		// white pawns
		for (int i = 0; i < 8; i++) {
			boardArray[6][i] = new Pawn(6, i, game.playerWhite);
		}
		boardArray[7][0] = new Rook(7, 0, game.playerWhite);
		boardArray[7][1] = new Knight(7, 1, game.playerWhite);
		boardArray[7][2] = new Bishop(7, 2, game.playerWhite);
		boardArray[7][3] = new Queen(7, 3, game.playerWhite);
		boardArray[7][4] = new King(7, 4, game.playerWhite);
		boardArray[7][5] = new Bishop(7, 5, game.playerWhite);
		boardArray[7][6] = new Knight(7, 6, game.playerWhite);
		boardArray[7][7] = new Rook(7, 7, game.playerWhite);
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardArray[i][j] != null) {
					boardArray[i][j].render(g);
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardArray[i][j] != null && boardArray[i][j].isSelected()) {
					// render possible path when selected
					Method.renderPath(g, boardArray[i][j].drawPath());
				}
			}
		}
		
	}
	
	/**
	 * Capture a piece on the board by another piece
	 * @param byX the X location of the piece that capture others
	 * @param byY the Y location of the piece that capture others
	 * @param onX the X location of the piece that is being captured
	 * @param onY the Y location of the piece that is being captured
	 */
	public void capture(int byX, int byY, int onX, int onY) {
		boolean isCastling = false;
		int[] castleTo = new int[2];
		boolean isPawnPromition = false;
		Piece on = boardArray[onX][onY];
		Piece by = boardArray[byX][byY];
		Rook rook = null;
		
		// rook and king, isMoved handling
		if (by instanceof Rook) {
			((Rook) by).setMoved(true);
		} else if (by instanceof King) {
			((King) by).setMoved(true);
		}
		
		// promotion handling
		if (by.getType() == PieceType.Pawn) {
			if (by.player.playerType == PlayerType.Black){
				if (by.x == 6) {
					isPawnPromition = true;
				}
			} else {
				if (by.x == 1) {
					isPawnPromition = true;
				}
			}
		}
		
		// castling handling
		if (by.getType() == PieceType.King && Math.abs(byY - onY) == 2) {
			isCastling = true;
			if (onY - byY > 0) {
				rook = (Rook) boardArray[by.x][7];
				castleTo[0] = by.x;
				castleTo[1] = 5;
			} else {
				rook = (Rook) boardArray[by.x][0];
				castleTo[0] = by.x;
				castleTo[1] = 3;
			}
		}
		
		// normal capturing, add captured piece to the captured array
		if (on != null) {
			if (on.player.playerType == PlayerType.Black) {
				captured.get(0).add(on);
			} else {
				captured.get(1).add(on);
			}
		}
		
		// log the move
		if (on != null) {
			game.log.saveCapture(by, on, isPawnPromition, isCastling);
		} else {
			game.log.saveMove(by, onX, onY, isPawnPromition, isCastling);
		}
		
			
		boardArray[byX][byY] = null;
		
		// change the location of the piece that capture others
		by.x = onX;
		by.y = onY;
		boardArray[onX][onY] = by;
		
		if (isCastling) {
			capture(rook.x, rook.y, castleTo[0], castleTo[1]);
		}
		
		if (isPawnPromition) {
			boardArray[onX][onY] = new Queen(onX, onY, by.player);
		}
		
		// check if checkmate
		if (CheckmateHandler.isBlackBeenChecked(game)) {
			game.blackBeenCheckmate = true;
		} else {
			game.blackBeenCheckmate = false;
		}
		if (CheckmateHandler.isWhiteBeenChecked(game)) {
			game.whiteBeenCheckmate = true;
		} else {
			game.whiteBeenCheckmate = false;
		}
		
		if (game.blackBeenCheckmate && DeathHandler.isBlackDead(game)) {
			game.winner = game.playerWhite;
		}
		
		if (game.whiteBeenCheckmate && DeathHandler.isWhiteDead(game)) {
			game.winner = game.playerBlack;
		}
		
	}

}
