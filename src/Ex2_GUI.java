import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        int w = map.getWidth();
        int h = map.getHeight();
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-0.5, w - 0.5);
        StdDraw.setYscale(-0.5, h - 0.5);
        StdDraw.enableDoubleBuffering();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int val = map.getPixel(x, y);
                if (val == 0) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                } else {
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
        StdDraw.show();
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;
        try {
            Scanner sc= new Scanner(new File(mapFileName));
            if(sc.hasNextInt()) {
                int w = sc.nextInt();
                int h = sc.nextInt();
                ans = new Map(w, h, 0);
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (sc.hasNextInt()) {
                            ans.setPixel(x, y, sc.nextInt());
                        }
                    }
                }
            }
            sc.close(); }
        catch (Exception e) {
            e.printStackTrace();    }
        return ans;
        }
    /**
     *
     * @param map
     * @param mapFileName
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
        } catch (Exception e) {
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] a) {
        String mapFile = "src/map.txt";
        Map2D map = loadMap(mapFile);
        if (map != null) {
            drawMap(map);
        } else {
            System.out.println("Couldn't load map.");
        }
    }
}
    /// ///////////// Private functions ///////////////

