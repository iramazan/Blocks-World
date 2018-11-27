package csc366.world;

import java.util.Optional;

public class Block extends Shape implements Supporter {

    private final Type type;
    private final Color color;
    private Supporter supportedBy;
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

    public Supporter getSupportedBy() {
        return supportedBy;
    }

    public void setSupportedBy(Supporter supportedBy) {
        this.supportedBy = supportedBy;
    }

    @Override
    public boolean isSupporting() {
        return supporting != null;
    }

    public Optional<Shape> getSupporting() {
        return Optional.of(supporting);
    }

    @Override
    public void setSupporting(Shape shape) {
        this.supporting = shape;
    }

    /**
     * Returns true if this shape can support a shape.
     *
     * @return True if the shape can support another shape
     */
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

    @Override
    public void removeRelations() {
        getLeft().ifPresent(l -> l.setRight(right));
        getRight().ifPresent(r -> r.setLeft(left));
        getBehind().ifPresent(b -> b.setInFront(inFront));
        getInFront().ifPresent(f -> f.setBehind(behind));
        getSupporting().ifPresent(s -> {
            s.setSupportedBy(supportedBy);
            supportedBy.setSupporting(s);
        });
        supportedBy = null;
        supporting = left = right = inFront = behind = null;
    }
}
