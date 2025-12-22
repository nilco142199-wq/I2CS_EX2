public class Index2D implements Pixel2D {
    private int _x;
    private int _y;

    /**
     * Constructs a 2D index using given x and y values.
     * @param w the x-coordinate
     * @param h the y-coordinate
     */
    public Index2D(int w, int h) {
        this._x = w;
        this._y = h;
    }

    /**
     * Copy constructor.
     * Creates a new Index2D object from another Pixel2D.
     * @param other another Pixel2D object to copy coordinates from
     */
    public Index2D(Pixel2D other) {
        this._x = other.getX();
        this._y = other.getY();
    }

    /**
     * Returns the x-coordinate of this pixel.
     * @return the x value
     */
    @Override
    public int getX() {

        return this._x;
    }

    /**
     * Returns the y-coordinate of this pixel.
     * @return the y value
     */

    @Override
    public int getY() {

        return this._y;
    }

    /**
     * Calculates the Euclidean distance between this pixel
     * and another Pixel2D.
     * @param p2 the other pixel
     * @return the 2D distance between the two pixels
     * @throws RuntimeException if p2 is null
     */

    @Override
    public double distance2D(Pixel2D p2) {
        if(p2==null) { throw new RuntimeException("p2 is Null"); }
        double dx = this._x - p2.getX();
        double dy = this._y - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a string representation of this pixel
     * in the format (x,y).
     * @return a string representing this pixel
     */

    @Override
    public String toString() {
        return"("+_x +"," +_y +")";
    }

    /**
     * Checks if this object is equal to another object.
     * Two Pixel2D objects are equal if they have the same
     * x and y coordinates.
     * @param p the object to compare with
     * @return true if the objects represent the same coordinates, false otherwise
     */

    @Override
    public boolean equals(Object p) {
        boolean ans = true;
        if (p==null || !(p instanceof Pixel2D)) {
            return false;
        }
        Pixel2D other = (Pixel2D) p;
        boolean xIsTheSame = (this._x == other.getX());
        boolean yIsTheSame = (this._y == other.getY());

        return  xIsTheSame && yIsTheSame;
    }
}
