// Alon Filler 216872374
import java.util.Random;
import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Empty constructor of the Ball class.
     * The reason why this code is needed is the fact that CHECKSTYLES
     * won't allow using protected on the class.
     * This fact lead to a lot of spaghetti code, forcing me to use
     * getter and setters to mutate fields of the Ball class in its heir: ContainedBall
     */
    public Ball() {
        this.center = new Point(0, 0);
        this.radius = 0;
        this.color = new Color(0, 0, 0, 0);
        this.velocity = new Velocity();
    }
    /**
     * Constructor of the Ball class.
     * @param center the center of the Ball
     * @param r the radius of the Ball
     * @param color the color of the Ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = new Point(center);
        this.radius = r;
        this.color = new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
        this.velocity = new Velocity();
    }
    /**
     * Constructor of the Ball class.
     * @param x the X value of the Point
     * @param y the Y value of the Point
     * @param r the radius value of the Ball
     * @param color the color of the Ball
     */
    public Ball(double x, double y, int r, Color color) {
        this((int) x, (int) y, r, color);
    }
    /**
     * Constructs a Point then a Ball utilising it.
     * Calls the Point based constructor
     * @param x the X value of the Point
     * @param y the Y value of the Point
     * @param r the radius value of the Ball
     * @param color the color of the Ball
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }
    /**
     * Random Ball constructor.
     * @param r the radius of the Ball
     * @param color the color of the Ball
     */
    public Ball(int r, Color color) {
        Random random = new Random();
        boolean found = false;
        double x = 0, y = 0;
        while (!found) {
            x = random.nextInt(Screen.WIDTH + 1);
            y = random.nextInt(Screen.HEIGHT + 1);
            boolean isTouchingScreen = (
                ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, x)
                && ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, y)
                && ThresholdCompare.isThresholdBasedGreaterEqual(x, Screen.WIDTH - this.radius)
                && ThresholdCompare.isThresholdBasedGreaterEqual(y, Screen.HEIGHT - this.radius)
            );
            if (!isTouchingScreen) {
                this.center = new Point(x, y);
                found = true;
            }
        }
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
    }
    /**
     * Returns the X value of the center Point.
     * @return the X value of the center Point
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Returns the Y value of the center Point.
     * @return the Y value of the center Point
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Returns the radius of the Ball.
     * @return the radius of the Ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * Returns the color of the Ball.
     * @return Returns the color of the Ball
     */
    public Color getColor() {
        return new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
    }
    /**
     * Draws the Ball on the surface.
     * @param surface the surface on which the Ball must be drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(
            this.getX(),
            this.getY(),
            this.radius
        );
    }
    /**
     * Changes the Velocity of the Ball.
     * @param v the new Velocity of the Ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }
    /**
     * Changes the Velocity of the Ball.
     * @param dx the new dx value of the Velocity
     * @param dy the new dy value of the Velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Returns the Velocity object of the Ball.
     * @return the velocity object of the Ball
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity);
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        if (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, this.center.getX())
            || ThresholdCompare.isThresholdBasedGreaterEqual(this.center.getX(), Screen.WIDTH - this.radius)
        ) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, this.center.getY())
            || ThresholdCompare.isThresholdBasedGreaterEqual(this.center.getY(), Screen.HEIGHT - this.radius)
        ) {
            this.velocity.setDy(-this.velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * Getter of the center of the Ball.
     * @return the center of the Ball
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * Sets the center of the Ball.
     * @param newCenter the new center for the Ball
     */
    public void setCenter(Point newCenter) {
        this.center = new Point(newCenter);
    }
    /**
     * Sets the radius of the Ball.
     * @param radius new radius of the Ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    /**
     * Sets the color of the Ball.
     * @param color new radius of the Ball
     */
    public void setColor(Color color) {
        this.color = color;
    }
}