package displayFiles;

public class Vector extends Point {

    public Vector(double x, double y) {
        super(x, y);
    }

    public Vector(Point point) {
        this(point.getX(), point.getY());
    }

    public Vector(double x1, double y1, double x2, double y2) {
        this(x2 - x1, y2 - y1);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector(Point point1, Point point2) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vector normalize() {
        return new Vector((1 / magnitude()) * x, (1 / magnitude()) * y);
    }

    public Vector add(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY());
    }

    public Vector scale(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public double dot(Vector vector) {
        return x * vector.getX() + y * vector.getY();
    }

    public String toString() {
        return "<" + x + "," + y + ">";
    }

    public double heading() {
        return Math.toDegrees(Math.atan2(y,x));
    }
}
