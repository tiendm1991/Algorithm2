
import java.awt.Color;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
	private static final boolean X = true;
	private Picture currentPic;

	public SeamCarver(Picture picture) { // create a seam carver object based on
											// the given picture
		this.currentPic = picture;
	}

	public Picture picture() { // current picture
		return new Picture(currentPic);
	}

	public int width() { // width of current picture
		return picture().width();
	}

	public int height() { // height of current picture
		return picture().height();
	}

	public double energy(int x, int y) { // energy of pixel at column x and row y
		validCoordinate(x, y);
		if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
			return 1000;
		return Math.sqrt(gradient(x, y, X) + gradient(x, y, !X));
	}

	private double gradient(int x, int y, boolean gradientX) {
		Color next, prev;
		if (gradientX == X) {
			next = picture().get(x + 1, y);
			prev = picture().get(x - 1, y);
		} else {
			next = picture().get(x, y + 1);
			prev = picture().get(x, y - 1);
		}
		int r = next.getRed() - prev.getRed();
		int g = next.getGreen() - prev.getGreen();
		int b = next.getBlue() - prev.getBlue();
		return r * r + g * g + b * b;
	}

	public int[] findHorizontalSeam() { // sequence of indices for horizontal
										// seam
	}

	public int[] findVerticalSeam() { // sequence of indices for vertical seam
	}

	public void removeHorizontalSeam(int[] seam) { // remove horizontal seam
													// from current picture
		if (seam == null || seam.length <= 0 || height() <= 1)
			throw new IllegalArgumentException();
	}

	public void removeVerticalSeam(int[] seam) { // remove vertical seam from
													// current picture
		if (seam == null || seam.length <= 0 || width() <= 1)
			throw new IllegalArgumentException();
	}

	private void validCoordinate(int x, int y) {
		if (x < 0 || x >= width())
			throw new IllegalArgumentException();
		if (y < 0 || x >= height())
			throw new IllegalArgumentException();
	}
}
