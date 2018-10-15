package club.oguri.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlaneObject extends GameObject {

	private boolean left, right, up, down;
	private int speed = 4;
	boolean alive = true;

	public PlaneObject(String path, int x, int y) {
		super(path, x, y);
	}

	@Override
	public void draw(Graphics g) {
		if (alive) {
			super.draw(g);
			move();
		}
	}

	public void move() {
		if (left) {
			x -= speed;
		} else if (right) {
			x += speed;
		} else if (up) {
			y -= speed;
		} else if (down) {
			y += speed;
		}
	}

	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}

	}

	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}

	}

}
