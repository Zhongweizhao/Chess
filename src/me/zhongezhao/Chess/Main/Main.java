package me.zhongezhao.Chess.Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.zhongezhao.Chess.GUI.Board;
import me.zhongezhao.Chess.GUI.HUD;
import me.zhongezhao.Chess.GUI.MyColor;
import me.zhongezhao.Chess.GUI.TopBar;
import me.zhongezhao.Chess.GUI.Window;
import me.zhongezhao.Chess.Game.Game;
import me.zhongezhao.Chess.Game.PieceSelectionListener;
import me.zhongezhao.Chess.Util.Constant;

public class Main extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5809503838617413185L;
	private final int HEIGHT = 745, WIDTH = HEIGHT / 9 * 14;
	private Thread thread;
	private boolean running;
	private Board board;
	private TopBar topBar;
	private Window window;
	private HUD hud;
	private Game game;
	private PieceSelectionListener pieceSelectionListener;
	
	public static void main(String[] args) {
		Main chess = new Main();
	}
	
	public Main() {
		board = new Board(Constant.BOARD_X, Constant.BOARD_Y);
		topBar = new TopBar(Constant.TOPBAR_HEIGHT, Constant.WINDOW_WIDTH);
		this.addMouseListener(topBar);
		this.addMouseMotionListener(topBar);
		window = new Window(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT, "Chess", this);
	}
	
	public void tick() {
		topBar.tick();
		board.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(MyColor.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(MyColor.BLUE);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		
		topBar.render(g);
		board.render(g);
		hud.render(g);
		game.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	public synchronized void start() {
		// game init
		game = new Game();
		// add Selection Listener to game
		hud = new HUD(game);
		pieceSelectionListener = new PieceSelectionListener(game);
		this.addMouseListener(pieceSelectionListener);
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TopBar getTopBar() {
		return topBar;
	}
	
	
}
