package imganalysis.image;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {
	private static BufferedImage load(String path) throws IOException {
		File pathToFile = new File(path);

		return ImageIO.read(pathToFile);
	}

	public static int[][] loadImage(String path) {
		// Assumptions:
		// Image size greater than 0
		// Image is rectangular

		try {
			BufferedImage m = LoadImage.load(path);

			// Convert image to gray scale
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			m = op.filter(m, null);
			int[][] f = LoadImage.getImagePixels(m);

			for (int y = 0; y < f.length; y++) {
				for (int x = 0; x < f[0].length; x++) {
					f[y][x] = new Color(f[y][x]).getRed(); // RGB are the same
				}
			}
			return f;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int[][] getImagePixels(BufferedImage image) {

		int width = image.getWidth();
		int height = image.getHeight();
		int[][] result = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}

		return result;
	}
}
