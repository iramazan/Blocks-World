package csc366;

import csc366.world.Shape;

public class ObjectNode {

    private final Shape.Type shape;
    private final Shape.Color color;
    private final boolean hasColor;

    public ObjectNode(Shape.Type shape, Shape.Color color) {
        this.shape = shape;
        this.color = color;
        this.hasColor = true;
    }

    public ObjectNode(Shape.Type shape) {
        this.shape = shape;
        this.color = null;
        this.hasColor = false;
    }

    public Shape.Type getShape() {
        return shape;
    }

    public Shape.Color getColor() {
        return color;
    }

    public boolean hasColor() {
        return this.hasColor;
    }
}
