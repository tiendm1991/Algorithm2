
import java.awt.Color;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
	private static final boolean X = true;
	private Picture currentPic;
	private double[][] energies;
	private double[][] distTo;
	private int[][] edgeTo;

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

	public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
		
	}

	public int[] findVerticalSeam() { // sequence of indices for vertical seam
		energies = new double[width()][height()];
		distTo = new double[width()][height()];
		edgeTo = new int[width()][height()];
		init();
		for(int y = 0; y < height(); y++){
			for(int x = 0; x < width(); x++){
				if(y == 0) distTo[x][y] = energies[x][y];
			}
		}
	}
	

	private void init() {
		for(int i = 0; i < height(); i++){
			for(int j = 0; j < width(); j++){
				energies[j][i] = energy(j, i);
				distTo[j][i] = Double.POSITIVE_INFINITY;
			}
		}
	}
	
	private int findMinVertex(int idx, boolean isVertical){
		double min = Double.POSITIVE_INFINITY;
		int minIdx = 0;
		if(isVertical){
			for(int x = 0; x < width(); x++){
				if(distTo[x][idx] < min){
					min = distTo[x][idx];
					minIdx = x;
				}
			}
		}else {
			for(int y = 0; y < height(); y++){
				if(distTo[idx][y] < min){
					min = distTo[idx][y];
					minIdx = y;
				}
			}
		}
		return minIdx;
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
