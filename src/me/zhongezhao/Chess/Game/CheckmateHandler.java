package me.zhongezhao.Chess.Game;

import me.zhongezhao.Chess.Pieces.King;
import me.zhongezhao.Chess.Pieces.Piece;

public class CheckmateHandler {
	
	public static boolean isWhiteBeenChecked(Game game) {
		King whiteKing = null;
		
		searchKing:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++){
					Piece piece = game.gameBoard.boardArray[i][j];
					if (piece != null &&
							piece.getType() == PieceType.King && 
							piece.player.playerType == PlayerType.White) {
						whiteKing = (King) piece;
						break searchKing;
					}
				}
			}
	

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = game.gameBoard.boardArray[x][y];
				if (piece != null && piece.player.playerType == PlayerType.Black) {
					if (piece.isValidPath(whiteKing.x, whiteKing.y)) { // TODO fix a bug here
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isBlackBeenChecked(Game game) {
		King blackKing = null;
		
		searchKing:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++){
					Piece piece = game.gameBoard.boardArray[i][j];
					if (piece != null &&
							piece.getType() == PieceType.King && 
							piece.player.playerType == PlayerType.Black) {
						blackKing = (King) piece;
						break searchKing;
					}
				}
			}
	

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = game.gameBoard.boardArray[x][y];
				if (piece != null && piece.player.playerType == PlayerType.White) {
					if (piece.isValidPath(blackKing.x, blackKing.y)) {
						return true;
					}
				}
			}
		} 
		return false;
	}
	
	public static boolean doesThisMoveSolveBlackBeenChecked(Game game, int fromX, int fromY, int toX, int toY) {
		Piece from = game.gameBoard.boardArray[fromX][fromY];
		Piece to = game.gameBoard.boardArray[toX][toY];
		
		boolean result = false;
		
		from.x = toX;
		from.y = toY;
		game.gameBoard.boardArray[toX][toY] = from;
		game.gameBoard.boardArray[fromX][fromY] = null;
		
		if (!isBlackBeenChecked(game)) {
			result = true;
		}
		
		from.x = fromX;
		from.y = fromY;
		game.gameBoard.boardArray[toX][toY] = to;
		game.gameBoard.boardArray[fromX][fromY] = from;
		
		return result;
	}
	
	public static boolean doesThisMoveSolveWhiteBeenChecked(Game game, int fromX, int fromY, int toX, int toY) {
		Piece from = game.gameBoard.boardArray[fromX][fromY];
		Piece to = game.gameBoard.boardArray[toX][toY];
		
		boolean result = false;
		
		from.x = toX;
		from.y = toY;
		game.gameBoard.boardArray[toX][toY] = from;
		game.gameBoard.boardArray[fromX][fromY] = null;
		
		if (!isWhiteBeenChecked(game)) {
			result = true;
		}
		
		from.x = fromX;
		from.y = fromY;
		game.gameBoard.boardArray[toX][toY] = to;
		game.gameBoard.boardArray[fromX][fromY] = from;
		
		return result;
	}
	
	public static boolean doesThisMoveCauseBlackBeenChecked(Game game, int fromX, int fromY, int toX, int toY) {
		Piece from = game.gameBoard.boardArray[fromX][fromY];
		Piece to = game.gameBoard.boardArray[toX][toY];
		
		boolean result = false;
		
		from.x = toX;
		from.y = toY;
		game.gameBoard.boardArray[toX][toY] = from;
		game.gameBoard.boardArray[fromX][fromY] = null;
		
		if (isBlackBeenChecked(game)) {
			result = true;
		}
		
		from.x = fromX;
		from.y = fromY;
		game.gameBoard.boardArray[toX][toY] = to;
		game.gameBoard.boardArray[fromX][fromY] = from;
		
		return result;
	}
	
	public static boolean doesThisMoveCauseWhiteBeenChecked(Game game, int fromX, int fromY, int toX, int toY) {
		Piece from = game.gameBoard.boardArray[fromX][fromY];
		Piece to = game.gameBoard.boardArray[toX][toY];
		
		boolean result = false;
		
		from.x = toX;
		from.y = toY;
		game.gameBoard.boardArray[toX][toY] = from;
		game.gameBoard.boardArray[fromX][fromY] = null;
		
		if (isWhiteBeenChecked(game)) {
			result = true;
		}
		
		from.x = fromX;
		from.y = fromY;
		game.gameBoard.boardArray[toX][toY] = to;
		game.gameBoard.boardArray[fromX][fromY] = from;
		
		return result;
	}
	
	
}
