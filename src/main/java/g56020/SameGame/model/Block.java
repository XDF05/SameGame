package g56020.SameGame.model;

/**
 * Block representation for the model
 */
public class Block {
    private char color;

    /**
     * Constructor for the Block
     *
     * @param color color (randomly picked) of the block
     */
    public Block(char color) {
        this.color = color;
    }

    /**
     * Copy constructor
     *
     * @param other Block to copy
     */
    public Block(Block other) {
        this.color = other.color;
    }

    /**
     * getter for the color
     *
     * @return block's color
     */
    public char getColor() {
        return this.color;
    }

    /**
     * sets the color of a block to a given color
     *
     * @param color given color
     */
    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "" + color;
    }
}
