package roguelike.entities;
import java.awt.Color;
public class Entity {
    protected int x;
    protected int y;

    protected String type;
    protected char glyph;
    protected Color color;

    public int getX() {return x;}
    public int getY() {return y;}
    public char getGlyph() {return this.glyph;}
    public String getType() {return type;}
    public Color getColor() {return this.color;}

    public Entity(String name, char glyph, Color color, int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
        this.type = name;
        this.glyph = glyph;
        this.color = color;
    }
}
