import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Intro2CS_2026A
 * Updated GUI for Map2D with Pathfinding, Saving, and Loading.
 */
public class Ex2_GUI {
    private static final int WALL = 0, PATH = 1, START = 2, END = 3, ROUTE = 4;
    private static int startX = -1, startY = -1, endX = -1, endY = -1;

    /**
     * Draws a 2D map using the StdDraw library.
     * The method sets up the canvas size and scales to fit the map dimensions,
     * enables double buffering for smoother rendering, and then calls the
     * render function to draw the pixels.
     */
    public static void drawMap(Map2D map) {
        int w = map.getWidth();
        int h = map.getHeight();
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-0.5, w - 0.5);
        StdDraw.setYscale(-0.5, h - 0.5);
        StdDraw.enableDoubleBuffering();
        render(map);
    }

     /**
     * Loads a Map2D object from a text file.
     * The expected file format is:
     * First line: width and height of the map
     * Following lines: pixel values row by row
     */


    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;
        try {
            Scanner sc = new Scanner(new File(mapFileName));
            if (sc.hasNextInt()) {
                int w = sc.nextInt();
                int h = sc.nextInt();
                ans = new Map(w, h, PATH);
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (sc.hasNextInt()) {
                            ans.setPixel(x, y, sc.nextInt());
                        }
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     /**
     * Saves the given Map2D object to a text file.
     * The file format is:
     * First line: width and height of the map
     * Following lines: pixel values row by row

     */
    public static void saveMap(Map2D map, String mapFileName) {
        try {
            PrintWriter pw = new PrintWriter(new File(mapFileName));
            int w = map.getWidth();
            int h = map.getHeight();
            pw.println(w + " " + h);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pw.print(map.getPixel(x, y) + " ");
                }
                pw.println();
            }
            pw.close();
            System.out.println("Map saved to " + mapFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**
 * Renders the Map2D object onto the StdDraw canvas.
 *
 * Each cell is drawn according to its type:
 * - WALL: black square
 * - START: green square
 * - END: red square
 * - ROUTE: yellow square
 * - empty cells: light gray square outline
 *
 * Additionally, three buttons are drawn below the map:
 * - "CLEAR" (gray)
 * - "SOLVE" (blue)
 * - "SAVE" (green)
 *
 * The method also clears the canvas before drawing and shows the final frame.
 */

    public static void render(Map2D map) {
        StdDraw.clear(Color.WHITE);
        int w = map.getWidth();
        int h = map.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int val = map.getPixel(x, y);
                if (val == WALL) StdDraw.setPenColor(Color.BLACK);
                else if (val == START) StdDraw.setPenColor(Color.GREEN);
                else if (val == END) StdDraw.setPenColor(Color.RED);
                else if (val == ROUTE) StdDraw.setPenColor(Color.YELLOW);
                else {
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                    StdDraw.square(x, y, 0.5);
                    continue;
                }
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledRectangle(w * 0.2, -1.5, w * 0.15, 0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(w * 0.2, -1.5, "CLEAR");

        StdDraw.setPenColor(new Color(0, 120, 250));
        StdDraw.filledRectangle(w * 0.5, -1.5, w * 0.15, 0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(w * 0.5, -1.5, "SOLVE");

        StdDraw.setPenColor(new Color(0, 150, 0));
        StdDraw.filledRectangle(w * 0.8, -1.5, w * 0.15, 0.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(w * 0.8, -1.5, "SAVE");

        StdDraw.show();
    }
    /**
     * Resets the entire map to the default PATH value.
     *
     * This method iterates through all cells of the Map2D object
     * and sets each pixel to PATH. It also resets the start and end
     * coordinates to -1, effectively clearing any previous selections.
     *
     * @param map the Map2D object to reset
     */
    public static void resetMap(Map2D map) {
        for (int x = 0; x < map.getWidth(); x++)
            for (int y = 0; y < map.getHeight(); y++)
                map.setPixel(x, y, PATH);
        startX = startY = endX = endY = -1;
    }

    /**
     * Finds and marks the shortest path from the start to the end point on the map.
     *
     * This method uses the Map class's shortestPath function, which internally
     * uses BFS to compute the path avoiding WALL cells.
     *
     * Preconditions:
     * startX/startY and endX/endY must be set (not -1)
     */
    public static void solve(Map2D map) {
        if (startX == -1 || endX == -1) return;

        Index2D start = new Index2D(startX, startY);
        Index2D end = new Index2D(endX, endY);

        Pixel2D[] path = ((Map) map).shortestPath(start, end, WALL, false);
        if (path != null) {
            for (int i = 1; i < path.length - 1; i++) {
                Pixel2D p = path[i];
                map.setPixel(p.getX(), p.getY(), ROUTE);
            }
        }
    }

    public static void main(String[] a) {
        int w = 20, h = 20;
        Map2D map = new Map(w, h, PATH);

        StdDraw.setCanvasSize(600, 700);
        StdDraw.setXscale(-1, w);
        StdDraw.setYscale(-3, h);
        StdDraw.enableDoubleBuffering();

        while (true) {
            if (StdDraw.isMousePressed()) {
                double mx = StdDraw.mouseX(), my = StdDraw.mouseY();
                int ix = (int) Math.round(mx), iy = (int) Math.round(my);
                if (mx >= w*0.05 && mx <= w*0.35 && my <= -1) resetMap(map);
                else if (mx >= w*0.35 && mx <= w*0.65 && my <= -1) solve(map);
                else if (mx >= w*0.65 && mx <= w*0.95 && my <= -1) saveMap(map, "map_saved.txt");
                else if (ix >= 0 && ix < w && iy >= 0 && iy < h) {
                    if (startX == -1) { startX = ix; startY = iy; map.setPixel(ix, iy, START); }
                    else if (endX == -1 && (ix != startX || iy != startY)) { endX = ix; endY = iy; map.setPixel(ix, iy, END); }
                    else if ((ix != startX || iy != startY) && (ix != endX || iy != endY)) map.setPixel(ix, iy, WALL);
                }
            }
            render(map);
            StdDraw.pause(20);
        }
    }
}