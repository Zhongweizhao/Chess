package me.zhongezhao.Chess.Pieces;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import me.zhongezhao.Chess.Game.CheckmateHandler;
import me.zhongezhao.Chess.Game.PieceType;
import me.zhongezhao.Chess.Game.Player;
import me.zhongezhao.Chess.Game.PlayerType;
import me.zhongezhao.Chess.Util.Method;

/**
 * An abstract class for the Chess pieces
 * @author zhongweizhao
 *
*/
public abstract class Piece {
	
	public int x, y;
	public Player player;
	public String blackCodePoint;
	public String whiteCodePoint;
	private boolean selected;
	
	/**
	 * @param x the x location of the piece (x row)
	 * @param y the y location of the piece (y column)
	 * @param player the Player enum associated with the Piece
	 */
	public Piece(int x, int y, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}

	public void render(Graphics g) {
		
		if (player.playerType == PlayerType.Black) {
			if (selected) 
				Method.renderSelectedPiece(g, x, y, blackCodePoint);
			else 
				Method.renderPiece(g, x, y, blackCodePoint);
		} else {
			if (selected)
				Method.renderSelectedPiece(g, x, y, whiteCodePoint);
			else
				Method.renderPiece(g, x, y, whiteCodePoint);
		}

	}

	/**
	 * A function that determine if their path is valid based on Piece Type
	 * @param finalX the final X location (row)
	 * @param finalY the final Y location (column)
	 * @return a boolean indicating weather the path is valid
	 */
	public abstract boolean isValidPath(int finalX, int finalY);
	
	public List<List<Integer>> drawPath() {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isValidPath(i, j)) {
					if (player.game.blackBeenCheckmate) {
						if (CheckmateHandler.doesThisMoveSolveBlackBeenChecked(player.game, x, y, i, j)){
							List<Integer> pair = new ArrayList<Integer>();
							pair.add(i);
							pair.add(j);
							result.add(pair);
							continue;
						} 
						continue;
					}
					if (player.game.whiteBeenCheckmate) {
						if (CheckmateHandler.doesThisMoveSolveWhiteBeenChecked(player.game, x, y, i, j)){
							List<Integer> pair = new ArrayList<Integer>();
							pair.add(i);
							pair.add(j);
							result.add(pair);
							continue;
						} 
						continue;
					}
					
					List<Integer> pair = new ArrayList<Integer>();
					pair.add(i);
					pair.add(j);
					result.add(pair);
 					
				}
			}
		}
		return result;
	}
	
	public abstract PieceType getType();
	
	public Rectangle2D getBorderBox() {
		return Method.getBorderBoxForPieceAt(x, y);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public String toString(){
		try {
			return getType().toString() + " " + x + " " + y ;
		} catch (NullPointerException e) {
			return "A null piece";
		}
		
	}
	
}
