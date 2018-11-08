package csc366;

public class ObjectNode {

    public enum Shape {
        BLOCK, PYRAMID, IT
    }
    public enum Color {
        RED, GREEN, BLUE
    }

    private final Shape shape;
    private final Color color;
    private final boolean hasColor;

    public ObjectNode(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hasColor = true;
    }

    public ObjectNode(Shape shape) {
        this.shape = shape;
        this.color = null;
        this.hasColor = false;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public boolean hasColor() {
        return this.hasColor;
    }
}
