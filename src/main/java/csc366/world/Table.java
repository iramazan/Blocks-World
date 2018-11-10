package csc366.world;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Block> blocks;
    private List<Pyramid> pyramids;

    public Table() {
        blocks = new ArrayList<>();
        pyramids = new ArrayList<>();
    }

    public void put(Block block) {
        blocks.add(block);
    }

    public void put(Pyramid pyramid) {
        pyramids.add(pyramid);
    }
}
