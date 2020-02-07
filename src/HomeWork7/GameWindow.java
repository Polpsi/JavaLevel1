package HomeWork7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_POSX = 650;
    private static final int WIN_POSY = 250;

    private Map map;
    private Settings settings;

    GameWindow() {
        setDefaultCloseOperation(3);
        setTitle("TicTac Game");
        setLocation(WIN_POSX, WIN_POSY);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        add(addControl(), BorderLayout.NORTH);
        settings = new Settings(this);
        map = new Map();
        add(map, BorderLayout.CENTER);
        setVisible(true);
    }

    private Component addControl() {
        JButton btnStart = new JButton("Start");
        JButton btnExit = new JButton("Exit");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel panelButtons = new JPanel(new GridLayout(1, 2));
        panelButtons.add(btnStart);
        panelButtons.add(btnExit);
        return panelButtons;
    }

    void startGame(int gameMode, int fieldSize, int winLength) {
        map.startGame(gameMode, fieldSize, winLength);
    }

}
