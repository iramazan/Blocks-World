package csc366.world;

import java.util.ArrayList;
import java.util.List;

public class Table implements Supporter {

    private List<Block> blocks;
    private List<Pyramid> pyramids;

    public Table() {
        blocks = new ArrayList<>();
        pyramids = new ArrayList<>();
    }

    @Override
    public void setSupporting(Shape shape) {
        if (shape.getType() == Shape.Type.PYRAMID) {
            pyramids.add((Pyramid) shape);
        } else if (shape.getType() == Shape.Type.BLOCK) {
            blocks.add((Block) shape);
        }
    }
}
