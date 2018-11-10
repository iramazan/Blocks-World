package csc366.world;

import java.util.Optional;

public class Block extends Shape {

    private final Type type;
    private final Color color;
    private Shape supportedBy;
    private Shape supporting;
    private Shape behind;
    private Shape inFront;
    private Shape left;
    private Shape right;

    public Block(Color color) {
        this.type = Type.BLOCK;
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Shape getSupportedBy() {
        return supportedBy;
    }

    public void setSupportedBy(Shape supportedBy) {
        this.supportedBy = supportedBy;
    }

    public Shape getSupporting() {
        return supporting;
    }

    public void setSupporting(Shape supporting) {
        this.supporting = supporting;
    }

    /**
     * Returns true if this shape can support a shape.
     *
     * @return True if the shape can support another shape
     */
    @Override
    public boolean canSupport() {
        return true;
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
