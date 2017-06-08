package me.zhongezhao.Chess.Game;

import me.zhongezhao.Chess.Pieces.Piece;

public class DeathHandler {

	public static boolean isWhiteDead(Game game){
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = game.gameBoard.boardArray[i][j];
				if (piece != null && piece.player.playerType == PlayerType.White) {
					for (int x = 0; x < 8; x++) {
						for (int y = 0; y < 8; y++) {
							if (piece.isValidPath(x, y) && CheckmateHandler.doesThisMoveSolveBlackBeenChecked(game, i, j, x, y)) {
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isBlackDead(Game game) {
	
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = game.gameBoard.boardArray[i][j];
				if (piece != null && piece.player.playerType == PlayerType.Black) {
					for (int x = 0; x < 8; x++) {
						for (int y = 0; y < 8; y++) {
							if (piece.isValidPath(x, y) && CheckmateHandler.doesThisMoveSolveBlackBeenChecked(game, i, j, x, y)) {
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
}
