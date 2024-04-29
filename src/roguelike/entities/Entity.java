package roguelike.entities;
import java.awt.Color;
public class Entity {
    protected int xCoord;
    protected int yCoord;

    protected String type;
    protected char glyph;
    protected Color color;

    public int getX() {return xCoord;}
    public int getY() {return yCoord;}
    public char getGlyph() {return this.glyph;}
    public String getType() {return type;}
    public Color getColor() {return this.color;}

    public Entity(String name, char glyph, Color color, int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.type = name;
        this.glyph = glyph;
        this.color = color;
    }
}
