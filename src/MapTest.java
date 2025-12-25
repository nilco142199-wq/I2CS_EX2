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
    void testGetSetPixel() {
        Map2D m = new Map(4,4,0);
        m.setPixel(2, 3, 7);
        assertEquals(7, m.getPixel(2,3));
        assertEquals(-1, m.getPixel(-1,0));
    }
    @Test
    void testIsInside() {
        Map2D m = new Map(3,3,0);
        assertTrue(m.isInside(new Index2D(0,0)));
        assertFalse(m.isInside(new Index2D(-1,0)));
        assertFalse(m.isInside(null));
    }
    @Test
    void testSameDimensions() {
        Map2D m1 = new Map(3,3,0);
        Map2D m2 = new Map(3,3,1);
        Map2D m3 = new Map(4,3,0);
        assertTrue(m1.sameDimensions(m2));
        assertFalse(m1.sameDimensions(m3));
    }
    @Test
    void testAddMap2D() {
        Map m1 = new Map(2,2,1);
        Map m2 = new Map(2,2,2);
        m1.addMap2D(m2);
        assertEquals(2, m1.getPixel(0,0));
    }
    @Test
    void testRescaleUp() {
        Map m = new Map(2,2,5);
        m.rescale(2,2);
        assertEquals(4, m.getWidth());
        assertEquals(4, m.getHeight());
    }@Test
    void testDrawRect() {
        Map m = new Map(5,5,0);
        m.drawRect(new Index2D(1,1), new Index2D(3,3), 9);
        assertEquals(9, m.getPixel(2,2));
        assertEquals(0, m.getPixel(0,0));
    }
    @Test
    void testDrawCircle() {
        Map m = new Map(5,5,0);
        m.drawCircle(new Index2D(2,2), 1.5, 8);
        assertEquals(8, m.getPixel(2,2));
    }
    @Test
    void testDrawLine() {
        Map m = new Map(5,5,0);
        m.drawLine(new Index2D(0,0), new Index2D(4,4), 7);
        assertEquals(7, m.getPixel(2,2));
    }
    @Test
    void testFillCyclic() {
        Map m = new Map(3,3,0);
        m.setPixel(1,1,1);
        int filled = m.fill(new Index2D(0,0), 2, true);
        assertEquals(8, filled);
    }
    @Test
    void testShortestPathNoPath() {
        Map m = new Map(3,3,0);
        m.drawRect(new Index2D(0,1), new Index2D(2,1), 1);
        Pixel2D[] path = m.shortestPath(
                new Index2D(0,0),
                new Index2D(2,2),
                1,
                false
        );
        assertNull(path);
    }
    @Test
    void testAllDistanceCyclic() {
        Map m = new Map(3,3,0);
        Map2D d = m.allDistance(new Index2D(0,0), 9, true);
        assertEquals(1, d.getPixel(2,0)); // wrap
    }



    @Test
    void testMul() {
        Map m = new Map(2,2,3);
        m.mul(2);
        assertEquals(6, m.getPixel(0,0));
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