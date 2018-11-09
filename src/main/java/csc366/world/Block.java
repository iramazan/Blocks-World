package csc366.world;

public class Block extends Shape {

    private final Type type;
    private final Shape shape;
    private Shape supportedBy;
    private Shape supporting;

    public Block(Type type, Shape shape) {
        this.type = type;
        this.shape = shape;
    }

    public Type getType() {
        return type;
    }

    public Shape getShape() {
        return shape;
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
     * Returns true if this shape can support the specified block.
     *
     * @param block block object to query with
     * @return True if the shape can support the block
     */
    @Override
    public boolean canSupport(Block block) {
        return true;
    }

    /**
     * Returns true if this shape can support the specified pyramid.
     *
     * @param pyramid pyramid object to query with
     * @return True if the shape can support the pyramid
     */
    @Override
    public boolean canSupport(Pyramid pyramid) {
        return true;
    }
}
