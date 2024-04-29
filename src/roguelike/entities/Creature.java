package roguelike.entities;

import java.awt.*;

import roguelike.world.World;

public class Creature extends Entity {

    public Creature (String name, char glyph, Color color, int xPos, int yPos) {
        super(name, glyph, color, xPos, yPos);
    }

    public void move(World world, int dx, int dy) {
        if (!world.isBlocked(xCoord + dx, yCoord + dy)) {
            this.xCoord += dx;
            this.yCoord += dy;
        }
    }
}
