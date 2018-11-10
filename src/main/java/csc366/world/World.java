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
    }
}
