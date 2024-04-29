package roguelike.entities;

import java.awt.Color;

public class Tile extends Entity {
    private Color backgroundColor;
    private boolean obstruction = false;

    public boolean isObstruction() {
        return this.obstruction;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public Tile(String type, char glyph,
                Color color, Color backgroundColor,
                int xCoord, int yCoord,
                boolean obstruction) {
        super(type, glyph, color, xCoord, yCoord);
        this.backgroundColor = backgroundColor;
        this.obstruction = obstruction;
    }
}
