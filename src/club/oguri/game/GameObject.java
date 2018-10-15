package club.oguri.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	private Image image;
	double x;
	double y;
	int width;
	int height;
	int speed;

	public GameObject() {
	}

	public GameObject(String path, double x, double y, int width, int height, int speed) {
		super();
		ifPathExsits(path);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}

	public GameObject(String path, double x, double y) {
		super();
		ifPathExsits(path);
		this.x = x;
		this.y = y;
		if (image != null) {
			this.width = image.getWidth(null);
			this.height = image.getHeight(null);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void setPath(String path) {
	}

	private void ifPathExsits(String path) {
		if (null == path) {
			image = GameUtil.getImage("image/plane.png");
		} else {
			image = GameUtil.getImage(path);
		}
	}

}
