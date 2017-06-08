package me.zhongezhao.Chess.GUI;

import java.awt.Graphics;

public class Board {
	
	private int x, y;
	private int width = 640, height = 640;
	private int cellLength = 80;
	
	public Board(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.setColor(MyColor.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
		
		// render white black box;
		g.setColor(MyColor.WHITE);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					g.fillRect(x + i * cellLength, y + j * cellLength, cellLength, cellLength);
				}
			}
		}
	}
	
	public void tick() {
		
	}
	
}
