package csc366.world;

public class World {

    private Table table;

    public World() {
        // initialize objects
        Block redBlock = new Block(Shape.Color.RED);
        Block blueBlock = new Block(Shape.Color.BLUE);
        Block greenBlock = new Block(Shape.Color.GREEN);
        Pyramid redPyramid = new Pyramid(Shape.Color.RED);
        Pyramid bluePyramid = new Pyramid(Shape.Color.BLUE);
        Pyramid greenPyramid = new Pyramid(Shape.Color.GREEN);
        table = new Table();
        // establish object spatial relationships
        on(redBlock, table);
        on(greenPyramid, redBlock);
        on(greenBlock, table);
        on(blueBlock, greenBlock);
        on(redPyramid, table);
        on(bluePyramid, table);
        beside(redBlock, bluePyramid);
        beside(bluePyramid, greenBlock);
        behind(redBlock, bluePyramid);
        behind(redBlock, greenBlock);
    }

    /**
     * Establish the relationship: shape on support.
     * @param shape Shape which will be on top of some support
     * @param support support which will be supporting the object
     */
    public void on(Shape shape, Supporter support) {
        shape.setSupportedBy(support);
        support.setSupporting(shape);
    }

    /**
     * Establish the relationship: One shape is beside another.
     * @param left The left shape
     * @param right The right shape
     */
    public void beside(Shape left, Shape right) {
        left.setRight(right);
        right.setLeft(left);
    }

    /**
     * Establish the relationship: One shape is behind another.
     * @param back The shape which is farther to the back
     * @param front The shape which is farther to the front
     */
    public void behind(Shape back, Shape front) {
        back.setInFront(front);
        front.setBehind(back);
    }
}
