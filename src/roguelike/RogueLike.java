package roguelike;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;

import roguelike.entities.Entity;
import roguelike.entities.Creature;
import roguelike.ui.UserInterface;
import roguelike.world.World;
import roguelike.world.WorldBuilder;

public class RogueLike {
    private String name;

    private World world;
    private Creature player;

    private int mapWidth;
    private int mapHeight;

    private boolean isRunning;
    private int framesPerSecond = 60;
    private int timePerLoop = 1000000000 / framesPerSecond;

    private UserInterface ui;

    public RogueLike(String name, int mapWidth, int mapHeight) {
        this.name = name;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        ui = new UserInterface(this.name, mapWidth, mapHeight); // 80,24

        createWorld();
    }

    public void render() {
        ui.clear();
        ui.drawChar(player.getGlyph(), player.getX(), player.getY(), player.getColor());
        ui.refresh();
    }

    public void run() {
        isRunning = true;

        while (isRunning) {
            long startTime = System.nanoTime();

            processInput();
            render();

            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }

    public void processInput() {
        InputEvent event = ui.getNextInput();
        if (event instanceof KeyEvent) {
            KeyEvent keypress = (KeyEvent)event;
            switch (keypress.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    // code to move player left
                    player.move(world, -1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    // code to move player right
                    player.move(world, 1, 0);
                    break;
                case KeyEvent.VK_UP:
                    // code to move player up
                    player.move(world, 0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    player.move(world, 0, 1);
                    // code to move player down
                    break;
            }
        }
    }

    private void createWorld(){
        this.player = new Creature("player", '@', Color.white, 10, 10);
        this.world = new WorldBuilder(mapWidth, mapHeight)
                .fill("wall")
                .createRandomWalkCave(12232, 10, 10, 6000)
                .build();
        this.world.player = player;
        this.world.addCreature(player);
    }

    public static void main(String[] args) {
        RogueLike game = new RogueLike("A small roguelike game", 84, 24);
        game.run();
    }
}
