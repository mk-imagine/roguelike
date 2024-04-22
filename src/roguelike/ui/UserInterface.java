package roguelike.ui;

import asciiPanel.AsciiPanel;
import asciiPanel.AsciiFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class UserInterface extends JFrame implements KeyListener, MouseListener {

    private AsciiPanel terminal;
    private Queue<InputEvent> inputQueue;

    public UserInterface(String name, int screenWidth, int screenHeight) {
        super(name);

        inputQueue = new LinkedList<>();

        AsciiFont font = AsciiFont.CP437_9x16;

        terminal = new AsciiPanel(screenWidth * font.getWidth(),
                screenHeight * font.getHeight(),
                font);

        super.add(terminal);
        super.addKeyListener(this);
        super.setSize(screenWidth*9, screenHeight*16);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();
    }

    public InputEvent getNextInput() {
        return inputQueue.poll();
    }

    public void refresh() {
        terminal.repaint();
    }

    public void drawChar(char glyph, int x, int y, Color c) {
        terminal.write(glyph, x, y, c);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
