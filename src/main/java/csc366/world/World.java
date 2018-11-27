package csc366.world;

import java.util.ArrayList;
import java.util.Optional;

public class World {

    private Table table;
    private ArrayList<Block> blocks;
    private ArrayList<Pyramid> pyramids;
    private Shape lifted;

    public World() {
        // initialize objects
        Block redBlock = new Block(Shape.Color.RED);
        Block blueBlock = new Block(Shape.Color.BLUE);
        Block greenBlock = new Block(Shape.Color.GREEN);
        Pyramid redPyramid = new Pyramid(Shape.Color.RED);
        Pyramid bluePyramid = new Pyramid(Shape.Color.BLUE);
        Pyramid greenPyramid = new Pyramid(Shape.Color.GREEN);
        table = new Table();
        blocks = new ArrayList<>();
        pyramids = new ArrayList<>();
        // add shapes to arrays of all shapes
        blocks.add(redBlock);
        blocks.add(blueBlock);
        blocks.add(greenBlock);
        pyramids.add(redPyramid);
        pyramids.add(bluePyramid);
        pyramids.add(greenPyramid);
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
    private void on(Shape shape, Supporter support) {
        shape.setSupportedBy(support);
        support.setSupporting(shape);
    }

    /**
     * Establish the relationship: One shape is beside another.
     * @param left The left shape
     * @param right The right shape
     */
    private void beside(Shape left, Shape right) {
        left.setRight(right);
        right.setLeft(left);
    }

    /**
     * Establish the relationship: One shape is behind another.
     * @param back The shape which is farther to the back
     * @param front The shape which is farther to the front
     */
    private void behind(Shape back, Shape front) {
        back.setInFront(front);
        front.setBehind(back);
    }

    /**
     * Pick up the specified object
     * @param type Type of the object
     * @param color Color of the object
     */
    public void liftObject(Shape.Type type, Shape.Color color) {
        if (type.equals(Shape.Type.BLOCK)) {
            for (Block block : blocks) {
                if (block.getColor().equals(color)) {
                    if (block.isSupporting()) {
                        return;
                    }
                    block.removeRelations();
                    this.lifted = block;
                }
            }
        } else if (type.equals(Shape.Type.PYRAMID)) {
            for (Pyramid pyramid : pyramids) {
                if (pyramid.getColor().equals(color)) {
                    pyramid.removeRelations();
                    this.lifted = pyramid;
                }
            }
        }
        // TODO: Can you pick up shape of type 'it'. No for now.
    }

    /**
     * Pick up some object of the specified type
     * @param type Type of the object
     */
    public void liftObject(Shape.Type type) {
        if (type.equals(Shape.Type.BLOCK)) {
            for (Block block : blocks) {
                if (block.getSupporting().isEmpty()) {
                    block.removeRelations();
                    this.lifted = block;
                }
            }
        } else if (type.equals(Shape.Type.PYRAMID)) {
            if (!pyramids.isEmpty()) {
                Pyramid pyramid = pyramids.get(0);
                pyramid.removeRelations();
                this.lifted = pyramid;
            }
        }
        // TODO: Can you pick up shape of type 'it'. No for now.
        // If type is block, there is no top block
    }

    public void dropObject(Shape.Type type, Shape.Color color) {
        if (this.lifted == null || !type.equals(Shape.Type.BLOCK)) {
            return;
        }
        for (Block block : blocks) {
            if (block.getColor().equals(color) && block.getSupporting().isEmpty()) {
                on(lifted, block);
            }
        }
    }
}
