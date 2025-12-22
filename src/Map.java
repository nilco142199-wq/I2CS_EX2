import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int [][] _map;


    // edit this class below
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {
        init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        this._map = new int[w][h];
        for(int x=0; x<w; x++) {
            for (int y=0; y<h; y++) {
                this._map[x][y] = v;
            }
        }

	}
	@Override
	public void init(int[][] arr) {
        this._map = arr;
        if(_map==null) {
            throw new RuntimeException("Array is Null");
        }
        int w =arr.length;
        int h =arr[0].length;
        this._map = new int[w][h];
        for(int x=0; x<w; x++) {
            for(int y=0; y<h; y++) {
                this._map[x][y] = arr[x][y];
            }
        }

	}
	@Override
	public int[][] getMap() {
        int w = this.getWidth();
        int h = this.getHeight();
        int[][] ans = new int[w][h];
        for(int x=0; x<w; x++) {
            for (int y=0; y<h; y++) {
                ans[x][y] = this._map[x][y];
            }
        }
		return ans;
	}
	@Override
	public int getWidth() {
        return this._map.length;

    }
	@Override
	public int getHeight() {

        return this._map[0].length;
    }
	@Override
	public int getPixel(int x, int y) {
        if (x < 0 || y < 0 || x >= this.getWidth() || y >= this.getHeight()) {
            return -1;
        }

        return this._map[x][y];
    }
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;

        return ans;
	}
	@Override
	public void setPixel(int x, int y, int v) {

    }
	@Override
	public void setPixel(Pixel2D p, int v) {

	}

    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;

        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
