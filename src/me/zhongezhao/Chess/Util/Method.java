package me.zhongezhao.Chess.Util;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.List;

import me.zhongezhao.Chess.GUI.MyColor;

public class Method {
	

	/**
	 * A function that render a piece based on its relative location
	 * @param x the x location of the piece
	 * @param y the y location of the piece
	 * @param s the Unicode representing the Piece
	 */
	public static void renderPiece(Graphics g, int x, int y, String s) {
		g.setColor(MyColor.BLACK);
		g.setFont(new Font("Open Sans", Font.PLAIN, 70));
		FontMetrics metrics = g.getFontMetrics();
		int cx = (80 - metrics.stringWidth(s)) / 2;
		int cy = -6 + metrics.getAscent();
		
		g.drawString(s, Constant.BOARD_X + y * 80 + cx, Constant.BOARD_Y + x * 80 + cy);
	}
	
	/**
	 * A function that render a piece based on its relative location with a larger font
	 * and shadow effect
	 * @param x the x location of the piece
	 * @param y the y location of the piece
	 * @param s the Unicode representing the Piece
	 */
	public static void renderSelectedPiece(Graphics g, int x, int y, String s) {
		g.setFont(new Font("Open Sans", Font.PLAIN, 80));
		FontMetrics metrics = g.getFontMetrics();
		int cx = (80 - metrics.stringWidth(s)) / 2;
		int cy = (80 - metrics.getHeight())  + metrics.getAscent();
		
		// draw shadow
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(0.2f));
		g.setColor(MyColor.BLACK);
		g.drawString(s, Constant.BOARD_X + y * 80 + cx + 4, Constant.BOARD_Y + x * 80 + cy + 4);
		g2d.setComposite(makeTransparent(1));
		
		g.setColor(MyColor.BLACK);
		g.drawString(s, Constant.BOARD_X + y * 80 + cx, Constant.BOARD_Y + x * 80 + cy);
	}
	
	public static Rectangle2D getBorderBoxForPieceAt(int x, int y) {
		return new Rectangle(Constant.BOARD_X + y * 80, Constant.BOARD_Y + x * 80, 80, 80);
	}
	
	public static int[] getPieceLocation(int x, int y) {
		int[] p = new int[2];
		
		p[1] = (x - Constant.BOARD_X) / Constant.CELL_LENGTH;
		p[0] = (y - Constant.BOARD_Y) / Constant.CELL_LENGTH;
		
		return p;
	}
	
	public static AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	public static void renderPath(Graphics g, List<List<Integer>> paths) {
		for (List<Integer> path : paths) {
			int x = path.get(0);
			int y = path.get(1);
			
			g.setColor(MyColor.RED);
			Graphics2D g2d = (Graphics2D) g;
			g2d.fillOval(Constant.BOARD_X + y * 80 + 40 - Constant.DOT_RADIUS, 
						Constant.BOARD_Y + x * 80 + 40 - Constant.DOT_RADIUS, 
						2 * Constant.DOT_RADIUS, 
						2 * Constant.DOT_RADIUS);
		}
	}
	
	
}
