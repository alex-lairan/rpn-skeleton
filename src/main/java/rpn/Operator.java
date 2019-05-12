package rpn;

public abstract class Operator implements Item {
    protected Item x;
    protected Item y;

    Operator(Item x, Item y) {
        this.x = x;
        this.y = y;
    }

    abstract public double process();
}
