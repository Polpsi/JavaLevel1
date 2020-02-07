package HomeWork7;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    public static final int GAME_MODE_HVA = 0;
    public static final int GAME_MODE_HVH = 1;
    public static final int GAME_MODE_NET = 2;

    private Rectangle mapBounds = new Rectangle();
    private int mapWidth;
    private int mapHeight;
    private int size;


    Map() {
        setBackground(new Color(40, 90, 100));
    }

    void startGame(int gameMode, int fieldSize, int winLength) {
        createGrid(fieldSize);
        System.out.printf("game mode: %d\nfieldSize: %d\nwinLength: %d\n",
                gameMode, fieldSize, winLength);
    }

    private void createGrid(int fieldSize) {
        /*this.removeAll();
        this.setLayout(new GridLayout(fieldSize,fieldSize,2,2));
        JPanel[][] arrField= new JPanel[fieldSize][fieldSize];
        for (int i = 0; i < arrField.length; i++) {
            for (int j = 0; j < arrField[i].length; j++) {
                getPanel();
            }
        }
        //Почему если заполнять Grid кнопками - отлично работает this.revalidate() для перерисовки,
        // а если заполнять JPanel-ями, то ни revalidate, ни validate не работают? Только так:
        this.setVisible(false);
        this.setVisible(true);*/

        mapBounds = this.getBounds();
        mapWidth = (int) mapBounds.getWidth();
        mapHeight = (int) mapBounds.getHeight();
        size = fieldSize;
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(33, 213, 255));
        for (int i = 1; i < size; i++) {
            g.drawLine((mapWidth / size) * i, 0, (mapWidth / size) * i, mapHeight);
            g.drawLine(0, (mapHeight / size) * i, mapWidth, (mapHeight / size) * i);
        }
    }

/*    private void getPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        this.add(panel);
    }*/

    private void doMove() {
        //Метод делающий ход:
        //validate move
        //checkwin
        //nextmove
    }
}