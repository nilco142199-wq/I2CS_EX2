import java.io.Serializable;
import java.util.Queue;
import java.util.LinkedList;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable {
    private int[][] _map;


    // edit this class below

    /**
     * Constructs a w*h 2D raster map with an init value v.
     *
     * @param w
     * @param h
     * @param v
     */
    public Map(int w, int h, int v) {
        init(w, h, v);
    }

    /**
     * Constructs a square map (size*size).
     *
     * @param size
     */
    public Map(int size) {
        this(size, size, 0);
    }

    /**
     * Constructs a map from a given 2D array.
     *
     * @param data
     */
    public Map(int[][] data) {
        init(data);
    }

    @Override
    public void init(int w, int h, int v) {
        this._map = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                this._map[x][y] = v;
            }
        }

    }

    @Override
    public void init(int[][] arr) {
        this._map = arr;
        if (_map == null) {
            throw new RuntimeException("Array is Null");
        }
        int w = arr.length;
        int h = arr[0].length;
        this._map = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                this._map[x][y] = arr[x][y];
            }
        }

    }

    @Override
    public int[][] getMap() {
        int w = this.getWidth();
        int h = this.getHeight();
        int[][] ans = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
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
        if (p == null) {
            return 0;
        }
        return this.getPixel(p.getX(), p.getY());

    }

    @Override
    public void setPixel(int x, int y, int v) {
        if (x < 0 || y < 0 || x >= this.getWidth() || y >= this.getHeight()) {
            throw new IndexOutOfBoundsException("The pixel is out of bound");
        }
        this._map[x][y] = v;
    }

    @Override
    public void setPixel(Pixel2D p, int v) {
        if (p == null) {
            return;
        }
        this.setPixel(p.getX(), p.getY(), v);
    }

    @Override
    public boolean isInside(Pixel2D p) {
        if (p == null) {
            return false;
        }
        int x = p.getX();
        int y = p.getY();
        if (x < 0 || y < 0 || x >= this.getWidth() || y >= this.getHeight()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        if (p == null) {
            return false;
        }
        if (this.getWidth() != p.getWidth() || this.getHeight() != p.getHeight()) {
            return false;
        }
        return true;
    }

    @Override
    public void addMap2D(Map2D p) {
        if (!this.sameDimensions(p)) {
            throw new RuntimeException("Map2D Dimensions Error");
        }
        int w = p.getWidth();
        int h = p.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

            }
        }

    }

    @Override
    public void mul(double scalar) {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int before = this.getPixel(x, y);
                int after = (int) (scalar * before);
                this.setPixel(x, y, after);
            }
        }

    }

    @Override
    public void rescale(double sx, double sy) {
        int oldW = this.getWidth();
        int oldH = this.getHeight();
        int newW = (int) Math.round(oldW * sx);
        int newH = (int) Math.round(oldH * sy);
        if (newW <= 0 || newH <= 0) {
            throw new RuntimeException("Illegal rescale size");
        }
        int[][] newMap = new int[newW][newH];
        for (int x = 0; x < newW; x++) {
            for (int y = 0; y < newH; y++) {
                int oldX = (int) (x / sx);
                int oldY = (int) (y / sy);
                if (oldX >= oldW) oldX = oldW - 1;
                if (oldY >= oldH) oldY = oldH - 1;
                newMap[x][y] = this._map[oldX][oldY];
            }
        }
        this._map = newMap;
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        int centerX = center.getX();
        int centerY = center.getY();
        double rSquare = rad * rad;
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int dx = (x - centerX);
                int dy = (y - centerY);
                int dist = (dx * dx) + (dy * dy);
                if (dist <= rSquare) {
                    setPixel(x, y, color);
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        int dx = Math.abs(p2.getX() - p1.getX());
        int dy = Math.abs(p2.getY() - p1.getY());
        if (dx == 0 && dy == 0) {
            setPixel(p1.getX(), p1.getY(), color);
            return;
        }
        if (dx >= dy) {
            if (p1.getX() > p2.getX()) {
                Pixel2D temp = p1;
                p1 = p2;
                p2 = temp;
            }
            double m = (double) (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            double b = p1.getY() - (m * p1.getX());
            for (int x = p1.getX(); x <= p2.getX(); x++) {
                double y = m * x + b;
                setPixel(x, (int) Math.round(y), color);
            }
        } else {
            if (p1.getY() > p2.getY()) {
                Pixel2D temp = p1;
                p1 = p2;
                p2 = temp;
            }
            double m = (double) (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
            double b = p1.getX() - (m * p1.getY());
            for (int y = p1.getY(); y <= p2.getY(); y++) {
                double x = m * y + b;
                setPixel((int) Math.round(x), y, color);
            }
        }
    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int xInStart = Math.min(p1.getX(), p2.getX());
        int xInEnd = Math.max(p1.getX(), p2.getX());
        int yInStart = Math.min(p1.getY(), p2.getY());
        int yInEnd = Math.max(p1.getY(), p2.getY());

        for (int x = xInStart; x <= xInEnd; x++) {
            for (int y = yInStart; y <= yInEnd; y++) {
                setPixel(x, y, color);
            }
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null) return false;
        if (!(ob instanceof Map2D)) return false;
        Map2D other = (Map2D) ob;
        if (this.getWidth() != other.getWidth()) return false;
        if (this.getHeight() != other.getHeight()) return false;
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                if (this.getPixel(x, y) != other.getPixel(x, y)) return false;
            }
        }
        return true;
    }

    @Override
    /**
     * Fills this map with the new color (new_v) starting from p.
     * https://en.wikipedia.org/wiki/Flood_fill
     */
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
            if (xy == null) return 0;
            int oldColor = getPixel(xy);
            if (oldColor == new_v) return 0;

            int countFill = 0;
            int w = getWidth();
            int h = getHeight();

            java.util.Queue<int[]> queue = new java.util.LinkedList<>();
            queue.add(new int[]{xy.getX(), xy.getY()});

            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];
                if (cyclic) {
                    x = (x + w) % w;
                    y = (y + h) % h;
                } else {
                    if (x < 0 || x >= w || y < 0 || y >= h) continue;
                }

                if (getPixel(x, y) != oldColor) continue;

                setPixel(x, y, new_v);
                countFill++;

                queue.add(new int[]{x + 1, y});
                queue.add(new int[]{x - 1, y});
                queue.add(new int[]{x, y + 1});
                queue.add(new int[]{x, y - 1});
            }

            return countFill;
        }


        @Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
            if (p1 == null || p2 == null) return null;
        }
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
