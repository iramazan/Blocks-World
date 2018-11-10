package csc366.world;

import java.util.Optional;

public class Pyramid extends Shape {

    private final Type type;
    private final Color color;
    private Supporter supportedBy;
    private Shape behind;
    private Shape inFront;
    private Shape left;
    private Shape right;

    public Pyramid(Color color) {
        this.color = color;
        this.type = Type.PYRAMID;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Supporter getSupportedBy() {
        return supportedBy;
    }

    public void setSupportedBy(Supporter supportedBy) {
        this.supportedBy = supportedBy;
    }

    public boolean canSupport() {
        return false;
    }

    @Override
    public Optional<Shape> getBehind() {
        return Optional.ofNullable(behind);
    }

    @Override
    public Optional<Shape> getInFront() {
        return Optional.ofNullable(inFront);
    }

    @Override
    public Optional<Shape> getRight() {
        return Optional.ofNullable(right);
    }

    @Override
    public Optional<Shape> getLeft() {
        return Optional.ofNullable(left);
    }

    @Override
    public void setBehind(Shape shape) {
        this.behind = shape;
    }

    @Override
    public void setInFront(Shape shape) {
        this.inFront = shape;
    }

    @Override
    public void setLeft(Shape shape) {
        this.left = shape;
    }

    @Override
    public void setRight(Shape shape) {
        this.right = shape;
    }
}
