package flappyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Bird bird;
	private boolean ingame;
	private final int BIRD_X = 40;
	private final int BIRD_Y = 60;
	private final int B_WIDTH = 1200;
	private final int B_HEIGHT = 630;
	private final int DELAY = 15;
	private final int FLOOR_Y = B_HEIGHT - 40;
	private ArrayList<Rectangle> columns;
	private Random random;
	private int score = 0;

	public Board() {

		initBoard();
	}

	private void initBoard() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		Color skyBlue = new Color(135, 206, 235);
		setBackground(skyBlue);
		ingame = true;

		random = new Random();

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

		bird = new Bird(BIRD_X, BIRD_Y);

		columns = new ArrayList<Rectangle>();

		for (int i = 0; i < 10; i++) {
			addColumn();
		}

		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void addColumn() {
		int space = 275;
		int width = 100;
		int height = 100 + random.nextInt(250);

		if (columns.size() == 0) {
			columns.add(new Rectangle(B_WIDTH + width + columns.size() * 300, B_HEIGHT - height, width, height));
			columns.add(
					new Rectangle(B_WIDTH + width + (columns.size() - 1) * 300, 0, width, B_HEIGHT - height - space));
		} else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + (2 * 300), B_HEIGHT - height + 50, width,
					height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, B_HEIGHT - height - space));
		}

	}

	public void paintColumn(Graphics g, Rectangle column) {

		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (ingame) {

			drawObjects(g);
		} else {

			drawGameOver(g);
		}

		for (Rectangle column : columns) {
			paintColumn(g, column);
		}

		drawScore(g);

		Toolkit.getDefaultToolkit().sync();

	}

	private void drawObjects(Graphics g) {

		if (bird.isVisible()) {
			g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
		}

		Color grass = new Color(63, 155, 11);
		g.setColor(grass);
		g.fillRect(0, FLOOR_Y - 20, B_WIDTH, 100);

		g.setColor(Color.orange);
		g.fillRect(0, FLOOR_Y, B_WIDTH, 200);
	}

	private void drawScore(Graphics g) {

		String msg = "Score: " + String.valueOf(score);
		Font small = new Font("Helvetica", Font.BOLD, 16);

		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, 100, 20);
	}

	public void checkCollisions() {
		Rectangle birdShape = new Rectangle(bird.getBounds());

		if (birdShape.getMaxY() >= B_HEIGHT) {
			ingame = false;
			bird.setVisible(false);
		}

		for (int i = 0; i < columns.size(); i++) {

			Rectangle column = columns.get(i);

			if (column.intersects(birdShape)) {
				bird.setVisible(false);
				ingame = false;
			}

			if (birdShape.getMaxX() == column.getMinX()) {

				if (i % 2 == 0) {
					score++;
				}
			}
		}

	}

	public void updateColumns() {
		for (Rectangle column : columns) {
			column.x -= 8;
		}

		for (int i = 0; i < columns.size(); i++) {

			Rectangle column = columns.get(i);

			if (column.x + column.width < 0) {
				columns.remove(column);
				addColumn();
			}
		}

	}

	public void drawGameOver(Graphics g) {

		String msg = "Game Over";

		Font small = new Font("Helvetica", Font.BOLD, 25);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (bird.isVisible()) {
			bird.move();
		}

		checkCollisions();
		updateColumns();
		repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			bird.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			bird.keyPressed(e);
		}
	}

}
