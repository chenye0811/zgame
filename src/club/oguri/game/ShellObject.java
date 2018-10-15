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

		// ���´��룬����ʵ�������߽磬�ڵ���������(ԭ��ʹ�̨����Ϸһ��)
		if (y > Constant.GAME_HEIGHT - height || y < 30) {
			degree = -degree;
		}
		if (x < 0 || x > Constant.GAME_WIDTH - width) {
			degree = Math.PI - degree;
		}
		// ���ظ��ⲿ�������ǰ����ɫ
		g.setColor(c);
	}
}
