
import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;

public class SeamCarver {
	private static final boolean VERTICAL = true;
	private static final boolean HORIZONTAL = false;
	private Picture currentPic;
	private double[][] energies;
	private double[][] distTo;
	private Position[][] edgeTo;
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
		return Math.sqrt(gradient(x, y, VERTICAL) + gradient(x, y, HORIZONTAL));
	}

	private double gradient(int x, int y, boolean gradientX) {
		Color next, prev;
		if (gradientX == VERTICAL) {
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

	public int[] findVerticalSeam() { // sequence of indices for vertical seam
		energies = new double[width()][height()];
		distTo = new double[width()][height()];
		edgeTo = new Position[width()][height()];
		init();
		for(int y = 0; y < height(); y++){
			for(int x = 0; x < width(); x++){
				if(y == 0) {
					distTo[x][y] = energies[x][y];
					continue;
				}
				if(isValid(x-1, y-1) && distTo[x][y] > distTo[x-1][y-1] + energies[x][y]){
					distTo[x][y] = distTo[x-1][y-1] + energies[x][y];
					edgeTo[x][y] = new Position(x-1, y-1);
				}
				if(isValid(x, y-1) && distTo[x][y] > distTo[x][y-1] + energies[x][y]){
					distTo[x][y] = distTo[x][y-1] + energies[x][y];
					edgeTo[x][y] = new Position(x, y-1);
				}
				if(isValid(x+1, y-1) && distTo[x][y] > distTo[x+1][y-1] + energies[x][y]){
					distTo[x][y] = distTo[x+1][y-1] + energies[x][y];
					edgeTo[x][y] = new Position(x+1, y-1);
				}
			}
		}
		double minPath = Double.POSITIVE_INFINITY;
		int minIdx = -1;
		for(int x = 0; x < width(); x++){
			if(distTo[x][height()-1] < minPath){
				minPath = distTo[x][height()-1];
				minIdx = x;
			}
		}
		return traceSPVertical(minIdx);
	}
	
	private int[] traceSPVertical(int minIdx) {
		Stack<Integer> path = new Stack<>();
		Position current = new Position(minIdx, height()-1);
		do {
			int x = current.x;
			int y = current.y;
			path.push(x);
			current = edgeTo[x][y];
		} while (current.y > 0);
		path.push(current.x);
		int[] result = new int[height()];
		int count = 0;
		while (!path.isEmpty()) {
			result[count++] = path.pop();
		}
		return result;
	}
	
	private void init() {
		for(int i = 0; i < height(); i++){
			for(int j = 0; j < width(); j++){
				energies[j][i] = energy(j, i);
				distTo[j][i] = Double.POSITIVE_INFINITY;
			}
		}
	}
	
	public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
		energies = new double[width()][height()];
		distTo = new double[width()][height()];
		edgeTo = new Position[width()][height()];
		init();
		for(int x = 0; x < width(); x++){
			for(int y = 0; y < height(); y++){
				if(x == 0) {
					distTo[x][y] = energies[x][y];
					continue;
				}
				if(isValid(x-1, y-1) && distTo[x][y] > distTo[x-1][y-1] + energies[x][y]){
					distTo[x][y] = distTo[x-1][y-1] + energies[x][y];
					edgeTo[x][y] = new Position(x-1, y-1);
				}
				if(isValid(x-1, y) && distTo[x][y] > distTo[x-1][y] + energies[x][y]){
					distTo[x][y] = distTo[x-1][y] + energies[x][y];
					edgeTo[x][y] = new Position(x-1, y);
				}
				if(isValid(x-1, y+1) && distTo[x][y] > distTo[x-1][y+1] + energies[x][y]){
					distTo[x][y] = distTo[x-1][y+1] + energies[x][y];
					edgeTo[x][y] = new Position(x-1, y+1);
				}
			}
		}
		double minPath = Double.POSITIVE_INFINITY;
		int minIdx = -1;
		for(int y = 0; y < height(); y++){
			if(distTo[width()-1][y] < minPath){
				minPath = distTo[width()-1][y];
				minIdx = y;
			}
		}
		return traceSPHorizontal(minIdx);
	}
	
	private int[] traceSPHorizontal(int minIdx) {
		Stack<Integer> path = new Stack<>();
		Position current = new Position(width()-1, minIdx);
		do {
			int x = current.x;
			int y = current.y;
			path.push(y);
			current = edgeTo[x][y];
		} while (current.x > 0);
		path.push(current.y);
		int[] result = new int[height()];
		int count = 0;
		while (!path.isEmpty()) {
			result[count++] = path.pop();
		}
		return result;
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
	
	private boolean isValid(int x, int y) {
		if (x < 0 || x >= width() || y < 0 || y >= height()) return false;;
		return true;
	}
	
	private static class Position{
		private int x;
		private int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
