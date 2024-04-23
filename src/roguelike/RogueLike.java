package roguelike;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import roguelike.entities.Entity;
import roguelike.entities.Creature;
import roguelike.ui.UserInterface;

public class RogueLike {
    private String name;

    private Entity thing;

    private Creature player;
    private boolean isRunning;
    private int framesPerSecond = 60;
    private int timePerLoop = 1000000000 / framesPerSecond;

    private UserInterface ui;

    public RogueLike(String name) {
        this.name = name;
        this.thing = new Entity("thing", '#', Color.white, 10, 10);
        this.player = new Creature("player", '@', Color.white, 15, 15);
        // let's add a player that can move around

        ui = new UserInterface(this.name, 80, 24);
    }

    public void render() {
        ui.clear();
        ui.drawChar(thing.getGlyph(), thing.getX(), thing.getY(), thing.getColor());
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
                    player.move(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    // code to move player right
                    player.move(1, 0);
                    break;
                case KeyEvent.VK_UP:
                    // code to move player up
                    player.move(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    player.move(0, 1);
                    // code to move player down
                    break;
            }
        }
    }

    public static void main(String[] args) {
        RogueLike game = new RogueLike("A small roguelike game");
        game.run();
    }
}
