package club.oguri.game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private Image offScreenImage = null;

	PlaneObject planeObject1 = new PlaneObject("image/plane.png", 250, 450);
	ShellObject shell = new ShellObject(200, 200, 1, 1, 10);
	ShellObject[] shellObjects = new ShellObject[50];
	Image bgImage = GameUtil.getImage("image/bg.jpg");
	Explode explode = null;

	public void init() {
		this.setTitle("Aeroplane Game");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		addKeyListener(new keyMonitor());
		new PaintThread().start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		for (int i = 0; i < shellObjects.length; i++) {
			shellObjects[i] = new ShellObject(200, 200, 5, 5, 20);
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bgImage, 0, 0, null);
		planeObject1.draw(g);
		// shell.draw(g);
		for (int i = 0; i < shellObjects.length; i++) {
			shellObjects[i].draw(g);
			boolean hit = shellObjects[i].getRectangle().intersects(planeObject1.getRectangle());
			if (hit) {
				planeObject1.alive = false;
				if (explode == null) {
					explode = new Explode(planeObject1.x, planeObject1.y);
				}
				explode.draw(g);
			}
		}
	}

	@Override
	public void update(Graphics g) {
		// 双缓冲解决闪烁问题
		if (offScreenImage == null)
			offScreenImage = this.createImage(500, 500);// 这是游戏窗口的宽度和高度

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	class PaintThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @author Administrator keyboard listener
	 */
	class keyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			planeObject1.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			planeObject1.minusDirection(e);
		}
	}

	public static void main(String[] args) {
		MyGameFrame game = new MyGameFrame();
		game.init();
	}
}
