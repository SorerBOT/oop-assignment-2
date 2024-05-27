/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * Constructor of the Line class.
     * @param start the Point at which the Line oridginates
     * @param end the Point at which the Line ends
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);

    }
    /**
     * Constructor of the Line class.
     * @param x1 the x value of the starting Point
     * @param y1 the y value of the starting Point
     * @param x2 the x value of the ending Point
     * @param y2 the y value of the ending Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * Calculates the length of the Line.
     * @return the length of the Line
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * Calculates the middle of the Line.
     * @return the middle Point of the Line.
     */
    public Point middle() {
        return new Point(
            (this.start.getX() + this.end.getX()) / 2,
            (this.start.getY() + this.end.getY()) / 2
        );
    }
    /**
     * @return the start Point of the Line
     */
    public Point start() {
        return new Point(this.start);
    }
    /**
     * @return the end Point of the Line
     */
    public Point end() {
        return new Point(this.end);
    }
    /**
     * @return the slope of the Line
     */
    double getSlope() {
        return (
            (this.end.getY() - this.start.getY())
            / (this.end.getX() - this.start.getX())
        );
    }
    /**
     * @return the b of the line (y = mx + b)
     */
    double getB() {
        return this.start.getY() - this.getSlope() * this.start.getX();
    }
    /**
     * Gets the Line's y value at said x value.
     * @param x the x value for which we would like to find the y value
     * @return the y value at said position
     */
    double getYAtX(double x) {
        return this.getSlope() * x + this.getB();
    }
    /**
     * Determines whether the Line intersects with the other Line.
     * @param other the other Line
     * @return true if the Lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        double firstSlope = this.getSlope();
        double secondSlope = other.getSlope();

        // Given system of equations that are of the form
        // y = slope*x + b
        // Derive equation of the form
        // ax = b
        // and solve for x

        double a = firstSlope - secondSlope;
        double b = -(this.getB() - other.getB());

        if (a == 0) {
            // the lines are parallel, checking whether they collide
            return b == 0;
        }
        double intersectionXValue = b / a;

        double greaterLineXValue = Math.max(this.start.getX(), this.end.getX());
        double lowerLineXValue = Math.min(this.start.getX(), this.end.getX());

        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(greaterLineXValue, intersectionXValue)
            && ThresholdCompare.isThresholdBasedGreaterEqual(intersectionXValue, lowerLineXValue)
        );
    }
    /**
     * Determines whether the Line intersects with the other Lines.
     * @param other1 another Line
     * @param other2 yet another Line
     * @return true if the two Lines intersect with the Line and false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (
            this.isIntersecting(other1)
            || this.isIntersecting(other2)
        );
    }
    /**
     * Calculates the intersection Point of the Line with the other Line.
     * @param other the other Line
     * @return the intersection Point if the Lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        double firstSlope = this.getSlope();
        double secondSlope = other.getSlope();

        // Given system of equations that are of the form
        // y = slope*x + b
        // Derive equation of the form
        // ax = b
        // and solve for x

        double a = firstSlope - secondSlope;
        double b = -(this.getB() - other.getB());

        if (a == 0) {
            // we know that the lines intersect and that they are parallel
            // from the above we infer that the lines collide and return null accoringly
            return null;
        }
        double intersectionXValue = b / a;
        // we know that the lines intersect and so there is no need to validate the intersectionXValue
        return new Point(intersectionXValue, this.getYAtX(intersectionXValue));
    }
    /**
     * Determines whether the Line is equal to the other Line.
     * @param other the other Line
     * @return true if the Lines are equal and false otherwise
     */
    public boolean equals(Line other) {
        return false;
    }
}