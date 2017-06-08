package me.zhongezhao.Chess.Game;

import me.zhongezhao.Chess.Pieces.Piece;

public class NoPieceBetweenHandler {
	
	/**
	 * @param game 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return A boolean value, true if no pieces between, false otherwise.
	 */
	public static boolean assertNoPieceBetween(Game game, int startX, int startY, int endX, int endY) {
		// same point, return true
		if (startX == endX && startY == endY) return true;
			
		if (!((Math.abs(endX - startX) == Math.abs(endY - startY)) || 
				(endX == startX) || 
				(endY == startY))) {
			throw new IllegalArgumentException("Two piece are not at same col or row or diagonal");
		}
		
		int[] delta = new int[2];
		Piece mid;
		delta[0] = startX == endX ? 0 : (endX - startX > 0 ? 1 : -1);
		delta[1] = startY == endY ? 0 : (endY - startY > 0 ? 1 : -1);
		
		// delta = -1, 0
		// startX = 7, 7
		
		startX += delta[0];
		startY += delta[1];
		while (! (startX == endX && startY == endY)) {
			mid = game.gameBoard.boardArray[startX][startY];
			if (mid != null) return false;
			startX += delta[0];
			startY += delta[1];
		}
		return true;
	}
	
}
