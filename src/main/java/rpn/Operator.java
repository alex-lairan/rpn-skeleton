package rpn;

public abstract class Operator implements Item {
    Item x;
    Item y;

    Operator(Item x, Item y) {
        this.x = x;
        this.y = y;
    }

    abstract public double process();
}
