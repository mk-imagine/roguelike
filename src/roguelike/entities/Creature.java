package roguelike.entities;

import java.awt.*;

public class Creature extends Entity {

    public Creature (String name, char glyph, Color color, int xPos, int yPos) {
        super(name, glyph, color, xPos, yPos);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
