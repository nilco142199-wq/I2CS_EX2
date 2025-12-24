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
    /**
     * Initializes a w*h map with all cells set to value v.
     */
    @Override
    public void init(int w, int h, int v) {
        this._map = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                this._map[x][y] = v;
            }
        }

    }
    /**
     * Initializes map from a 2D array copy.
     */
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
    /** Returns a deep copy of the map. */
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
    /** Returns map width. */
    @Override
    public int getWidth() {
        return this._map.length;

    }
    /** Returns map height. */
    @Override
    public int getHeight() {

        return this._map[0].length;
    }
    /** Returns pixel value at (x, y), or -1 if out of bounds. */
    @Override
    public int getPixel(int x, int y) {
        if (x < 0 || y < 0 || x >= this.getWidth() || y >= this.getHeight()) {
            return -1;
        }

        return this._map[x][y];
    }
    /** Returns pixel value at Pixel2D object. */
    @Override
    public int getPixel(Pixel2D p) {
        if (p == null) {
            return 0;
        }
        return this.getPixel(p.getX(), p.getY());

    }
    /** Sets the value of a pixel at (x, y). Throws exception if out of bounds. */
    @Override
    public void setPixel(int x, int y, int v) {
        if (x < 0 || y < 0 || x >= this.getWidth() || y >= this.getHeight()) {
            throw new IndexOutOfBoundsException("The pixel is out of bound");
        }
        this._map[x][y] = v;
    }
    /** Sets the value of a pixel at Pixel2D object. */
    @Override
    public void setPixel(Pixel2D p, int v) {
        if (p == null) {
            return;
        }
        this.setPixel(p.getX(), p.getY(), v);
    }
    /** Checks if a Pixel2D object is inside the map bounds. */
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
    /** Checks if another map has the same dimensions as this map. */
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
    /** Adds the values of another map to this map (not implemented). */
    @Override
    public void addMap2D(Map2D p) {
        int w = p.getWidth();
        int h = p.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                _map[x][y] += getPixel(x, y);
            }
        }
    }
    /** Multiplies all pixels by a scalar value. */
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
    /** Rescales the map to new dimensions using nearest neighbor. */
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
    /** Draws a filled circle with a given center, radius, and color. */
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
    /** Draws a line between two points with given color (Bresenham-like). */
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
    /** Draws a filled rectangle between two points with a given color. */
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
    /** Checks if this map equals another Map2D (deep comparison). */
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
     * @param xy starting Pixel2D
     * @param new_v new color to fill
     * @param cyclic wrap around map edges
     * @return number of pixels filled
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
         * BFS-based shortest path computation avoiding obstacles.
         * @param p1 start
         * @param p2 end
         * @param obsColor obstacle value
         * @param cyclic wrap-around edges
         * @return array of Pixel2D forming path from p1 to p2 (null if no path)
         */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
            if (p1 == null || p2 == null) return null;
            int w = getWidth();
            int h = getHeight();
            boolean[][] visited = new boolean[w][h];
            Pixel2D[][] parent = new Pixel2D[w][h];
            java.util.Queue<Pixel2D> queue = new java.util.LinkedList<>();
            queue.add(p1);
            visited[p1.getX()][p1.getY()] = true;
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            while (!queue.isEmpty()) {
                Pixel2D p = queue.poll();
                int x = p.getX();
                int y = p.getY();

                if (x == p2.getX() && y == p2.getY()) {
                    java.util.List<Pixel2D> path = new java.util.ArrayList<>();
                    Pixel2D cur = p2;
                    while (cur != null) {
                        path.add(0, cur);
                        cur = parent[cur.getX()][cur.getY()];
                    }
                    return path.toArray(new Pixel2D[0]);
                } else {

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (cyclic) {
                            nx = (nx + w) % w;
                            ny = (ny + h) % h;
                        } else {
                            if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                        }

                        if (!visited[nx][ny] && getPixel(nx, ny) != obsColor) {
                            visited[nx][ny] = true;
                            parent[nx][ny] = p;
                            queue.add(new Index2D(nx, ny));
                        }
                    }
                }
            }
            return null;
        }
    /**
     * Computes shortest distance (BFS) from start to all cells, avoiding obstacles.
     * @param start starting Pixel2D
     * @param obsColor obstacle value
     * @param cyclic wrap-around edges
     * @return Map2D with distances; inaccessible cells marked -1
     */
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
            int w = getWidth();
            int h = getHeight();
            Map distanceMap = new Map(w, h, -1);

            if (start == null || getPixel(start) == obsColor) return distanceMap;

            boolean[][] visited = new boolean[w][h];
            Queue<int[]> queue = new LinkedList<>();

            queue.add(new int[]{start.getX(), start.getY()});
            distanceMap.setPixel(start, 0);
            visited[start.getX()][start.getY()] = true;

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];
                int curDist = distanceMap.getPixel(x, y);

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (cyclic) {
                        nx = (nx + w) % w;
                        ny = (ny + h) % h;
                    } else {
                        if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    }

                    if (!visited[nx][ny] && getPixel(nx, ny) != obsColor) {
                        visited[nx][ny] = true;
                        distanceMap.setPixel(nx, ny, curDist + 1);
                        queue.add(new int[]{nx, ny});
                    }
                }
            }

            return distanceMap;
        }

    }
	////////////////////// Private Methods ///////////////////////

