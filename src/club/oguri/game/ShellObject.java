package club.oguri.game;

import java.awt.Color;
import java.awt.Graphics;

public class ShellObject extends GameObject {
	double degree;

	public ShellObject() {
		super();
	}

	public ShellObject(double x, double y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		degree = Math.random() * Math.PI * 2;
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int) x, (int) y, width, height);

		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		// 如下代码，用来实现碰到边界，炮弹反弹回来(原理和打台球游戏一样)
		if (y > Constant.GAME_HEIGHT - height || y < 30) {
			degree = -degree;
		}
		if (x < 0 || x > Constant.GAME_WIDTH - width) {
			degree = Math.PI - degree;
		}
		// 返回给外部，变回以前的颜色
		g.setColor(c);
	}
}
