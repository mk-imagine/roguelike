package roguelike.world;

import java.util.HashSet;
import java.util.Set;

import roguelike.entities.Creature;
import roguelike.entities.Entity;
import roguelike.entities.Tile;

public class World {
    private Tile[][] tiles;
    private int width;
    private int height;
    public Creature player;
    public Set<Creature> creatures;

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public World(Tile[][] tiles, Set<Creature> creatures){
        this.creatures = new HashSet<>();
        this.creatures.addAll(creatures);
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
    }

    public void addCreature(Creature creature) {
        this.creatures.add(creature);
    }

    public Tile getTile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height)
            return null;
        else
            return tiles[x][y];
    }

    public <T extends Entity> T getEntityAt(Class<T> type, int x, int y) {
        if (type == Creature.class) {
            for (Creature creature : creatures) {
                if (creature.getX() == x && creature.getY() == y) {
                    return type.cast(creature);
                }
            }
            return(null);
        } else if (type == Tile.class) {
            return type.cast(tiles[x][y]);
        } else if (type == Entity.class) {
            Creature creature = getEntityAt(Creature.class, x, y);
            if (creature != null) {
                return type.cast(creature);
            } else {
                return type.cast(getEntityAt(Tile.class, x, y));
            }
        }
        return null;
    }

    public boolean isBlocked(int x, int y) {
        return (tiles[x][y].isObstruction() || getEntityAt(Creature.class, x, y) != null);
    }
}
