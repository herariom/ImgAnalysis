package imganalysis.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;

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
		private HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>();

		public TestPane(int[][] firstImage, int[][] secondImage, int[][] map) {
			this.map = map;
			this.firstImage = firstImage;
			this.secondImage = secondImage;
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(map.length * 6, map[0].length * 2);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.scale(2, 2);

			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[0].length; x++) {

					g2d.setColor(setMapColor(map[y][x]));

					g2d.drawRect(x + 1, y + 1, 1, 1);
				}
			}

			int addColor = 225;
			for (int y = 0; y < firstImage.length; y++) {
				for (int x = 0; x < firstImage[0].length; x++) {
					if (map[y][x] != 0) {
						g2d.setColor(new Color(firstImage[y][x], firstImage[y][x],
								(firstImage[y][x] < 255 - addColor) ? firstImage[y][x] + addColor : 255));
					} else {
						g2d.setColor(new Color(firstImage[y][x], firstImage[y][x], firstImage[y][x]));
					}
					g2d.drawRect(x + firstImage.length + 2, y, 1, 1);
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

					g2d.drawRect(x + firstImage.length * 2 + 2, y, 1, 1);
				}
			}
			g2d.dispose();
		}

		private Random random = new Random();

		private Color setMapColor(int mapData) {
			if (mapData == 0) {
				return Color.BLACK;
			} else {
				if (colorMap.get(mapData) != null) {
					return colorMap.get(mapData);
				} else {
					Color mapColor = randomColor();
					colorMap.put(mapData, mapColor);
					return mapColor;
				}
			}
		}

		private Color randomColor() {
			switch (random.nextInt(12)) {
			case 0:
				return Color.RED;
			case 1:
				return Color.GREEN;
			case 2:
				return Color.BLUE;
			case 3:
				return Color.YELLOW;
			case 4:
				return Color.CYAN;
			case 5:
				return Color.GRAY;
			case 6:
				return Color.MAGENTA;
			case 7:
				return Color.ORANGE;
			case 8:
				return Color.PINK;
			case 9:
				return Color.GRAY;
			case 10:
				return Color.LIGHT_GRAY;
			case 11:
				return Color.WHITE;
			}

			return Color.BLACK;
		}
	}
}