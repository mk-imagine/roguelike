package roguelike.world;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import roguelike.entities.Creature;
import roguelike.entities.Tile;

public class WorldBuilder {
    private int width;
    private int height;
    private Tile[][] tiles;
    private Set<Creature> creatures;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.creatures = new HashSet<Creature>();
    }

    public Tile createTile(String type, int x, int y) {
        if (type == "ground") {
            return new Tile("ground", '.', Color.white, Color.black, x, y, false);
        } else if (type == "wall") {
            return new Tile("wall", '#', Color.white, Color.black, x, y, true);
        } else {
            return null;
        }
    }

    public WorldBuilder fill(String tileType) {
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                tiles[x][y] = createTile(tileType, x, y);
            }
        }
        return this;
    }

    public WorldBuilder createRandomWalkCave(int seed, int startX, int startY, int length) {
        Random rnd = new Random(seed);
        int direction;
        int x = startX;
        int y = startY;

        for (int i=0; i<length; i++) {
            direction = rnd.nextInt(4);
            if (direction == 0 && (x+1) < (width-1)) {
                x += 1;
            } else if (direction == 1 && (x-1) > 0) {
                x -= 1;
            } else if (direction == 2 && (y+1) < (height-1)) {
                y += 1;
            } else if (direction == 3 && (y-1) > 0) {
                y -= 1;
            }

            tiles[x][y] = createTile("ground", x, y);
        }

        return this;
    }

    public World build() {
        return new World(tiles, creatures);
    }
}
