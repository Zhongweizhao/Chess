package me.zhongezhao.Chess.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.zhongezhao.Chess.Pieces.Piece;
import me.zhongezhao.Chess.Util.Constant;
import me.zhongezhao.Chess.Util.Method;

public class PieceSelectionListener extends MouseAdapter {

	private Game game;
	private boolean selectedLock = false;
	private int[] selectedCoords = null;
	private int[] destCoords = null;
	
	public PieceSelectionListener(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		if (game.winner == null && Constant.BOARD_BORDER_BOX.contains(e.getPoint())) {
			if (!selectedLock) {
				selectedCoords = Method.getPieceLocation(e.getX(), e.getY());
				Piece selectedPiece = game.gameBoard.boardArray[selectedCoords[0]][selectedCoords[1]];
				if (selectedPiece != null && selectedPiece.player.playerType == game.currentPlayer.playerType) {
					selectedPiece.setSelected(true);
					selectedLock = true;
				}
			} else {
				destCoords = Method.getPieceLocation(e.getX(), e.getY());
				Piece selectedPiece = game.gameBoard.boardArray[selectedCoords[0]][selectedCoords[1]];
				
				if (selectedPiece.isValidPath(destCoords[0], destCoords[1])) {
					game.gameBoard.capture(selectedCoords[0], selectedCoords[1], destCoords[0], destCoords[1]);
					System.out.println("Move to " + destCoords[0] + " " + destCoords[1]);	
					game.switchPlayer();
				} 
				selectedPiece.setSelected(false);
				selectedLock = false;
				
				
			}
			
		}
	}
	
}
