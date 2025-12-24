import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
    @BeforeEach
    public void setuo() {
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }
    @Test
    void testFillBasic() {
        Map2D map = new Map(3,3,0);
        map.setPixel(1, 1, 1);

        Pixel2D start = new Index2D(0, 0);
        int newColor = 2;
        int filled = map.fill(start, newColor, false);
        assertEquals(8, filled, "Should fill 8 pixels"); // כל הפיקסלים מלבד המחסום
        assertEquals(newColor, map.getPixel(0, 0));
        assertEquals(newColor, map.getPixel(0, 1));
        assertEquals(1, map.getPixel(1, 1)); // המחסום לא משתנה
    }
    @Test
    void testAllDistanceBasic() {
        Map map = new Map(3, 3, 0);
        map.setPixel(1, 1, 1);
        Pixel2D start = new Index2D(0, 0);
        Map2D distMap = map.allDistance(start, 1, false);
        assertEquals(0, distMap.getPixel(start));
        assertEquals(-1, distMap.getPixel(1, 1));
        assertEquals(1, distMap.getPixel(1, 0));
        assertEquals(1, distMap.getPixel(0, 1));
        assertEquals(2, distMap.getPixel(2, 0));
        assertEquals(2, distMap.getPixel(0, 2));
        assertEquals(3, distMap.getPixel(2, 1));
        assertEquals(3, distMap.getPixel(1, 2));
        assertEquals(4, distMap.getPixel(2, 2));
    }

    @Test
    void testShortestPathBasic() {
        Map2D map = new Map(3, 3, 0);
        map.setPixel(1, 1, 1);

        Pixel2D start = new Index2D(0, 0);
        Pixel2D end = new Index2D(2, 2);
        Pixel2D[] path = map.shortestPath(start, end, 1, false);

        assertNotNull(path, "Path should exist");
        assertEquals(start.getX(), path[0].getX());
        assertEquals(start.getY(), path[0].getY());
        assertEquals(end.getX(), path[path.length - 1].getX());
        assertEquals(end.getY(), path[path.length - 1].getY());

        for (Pixel2D p : path) {
            assertNotEquals(1, map.getPixel(p));
        }
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0,_m1);
    }
}