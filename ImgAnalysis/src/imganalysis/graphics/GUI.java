package imganalysis.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	public GUI(int[][] firstImage, int[][] secondImage, int[][] map) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Output");
				frame.add(new TestPane(firstImage, secondImage, map));
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public class TestPane extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private int[][] map;
		private int[][] firstImage;
		private int[][] secondImage;

		public TestPane(int[][] firstImage, int[][] secondImage, int[][] map) {
			this.map = map;
			this.firstImage = firstImage;
			this.secondImage = secondImage;
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(map.length * 16, map[0].length * 16);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.scale(16, 16);
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[0].length; x++) {
					if (map[y][x] != 0)
						g2d.setColor(Color.RED);

					if (map[y][x] == 1)
						g2d.setColor(Color.RED);
					if (map[y][x] == 2)
						g2d.setColor(Color.GREEN);
					if (map[y][x] == 3)
						g2d.setColor(Color.BLUE);
					if (map[y][x] == 4)
						g2d.setColor(Color.YELLOW);
					if (map[y][x] == 5)
						g2d.setColor(Color.CYAN);
					if (map[y][x] == 6)
						g2d.setColor(Color.GRAY);
					if (map[y][x] == 7)
						g2d.setColor(Color.MAGENTA);
					if (map[y][x] == 8)
						g2d.setColor(Color.ORANGE);
					if (map[y][x] == 9)
						g2d.setColor(Color.PINK);

					if (map[y][x] == 0)
						g2d.setColor(Color.BLACK);

					g2d.drawRect(x + 1, y + 1, 1, 1);
				}
			}

			//
			int addColor = 225;
			for (int y = 0; y < firstImage.length; y++) {
				for (int x = 0; x < firstImage[0].length; x++) {
					if (map[y][x] != 0) {
						g2d.setColor(new Color(firstImage[y][x], firstImage[y][x],
								(firstImage[y][x] < 255 - addColor) ? firstImage[y][x] + addColor : 255));
					} else {
						g2d.setColor(new Color(firstImage[y][x], firstImage[y][x], firstImage[y][x]));
					}
					g2d.drawRect(x + 34, y + 1, 1, 1);
				}
			}
			for (int y = 0; y < secondImage.length; y++) {
				for (int x = 0; x < secondImage[0].length; x++) {
					if (map[y][x] != 0) {
						g2d.setColor(
								new Color((secondImage[y][x] < 255 - addColor) ? secondImage[y][x] + addColor : 255,
										secondImage[y][x], secondImage[y][x]));
					} else {
						g2d.setColor(new Color(secondImage[y][x], secondImage[y][x], secondImage[y][x]));
					}

					g2d.drawRect(x + 66, y + 1, 1, 1);
				}
			}
			g2d.dispose();
		}

	}
}