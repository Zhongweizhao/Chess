package me.zhongezhao.Chess.Util;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Constant {

	public final static int WINDOW_HEIGHT = 745, WINDOW_WIDTH = WINDOW_HEIGHT / 9 * 14;
	public final static int BOARD_X = 40, BOARD_Y = 65;
	public final static int BOARD_WIDTH = 640, BOARD_HEIGHT = 640;
	public final static int TOPBAR_HEIGHT = 25;
	public final static int CELL_LENGTH = 80;
	public final static Rectangle2D BOARD_BORDER_BOX = new Rectangle(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);
	public final static int DOT_RADIUS = 8;
	
	public final static int HUD_X = 720, HUD_Y = 65;
	
}
