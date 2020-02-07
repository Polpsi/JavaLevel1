package HomeWork7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private static final int WIN_WIDTH = 250;
    private static final int WIN_HEIGHT = 400;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int MIN_WIN = 3;
    private static final int MAX_WIN = 10;
    private static final String FIELD_SIZE_PREFIX = "Set field size:";
    private static final String WIN_SET_PREFIX = "Set win length size:";
    private JRadioButton rbnHumVsAI;
    private JRadioButton rbnHumVsHum;
    private JRadioButton rbnNetMode;
    private JSlider slFieldSize;
    private JSlider slWinLength;


    private final GameWindow gameWindow;

    Settings(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setTitle("Settings");
        Rectangle gameWinBounds = gameWindow.getBounds();
        setLocation((int) gameWinBounds.getCenterX() - WIN_WIDTH / 2,
                (int) gameWinBounds.getCenterY() - WIN_HEIGHT / 2);
        setLayout(new GridLayout(9, 1));
        setResizable(false);
        addControls();
    }

    private void addControls() {
        addGameMode();
        addSizeAndWin();
        JButton btnOk = new JButton("START!");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendSettings();
            }
        });
        add(btnOk);
    }

    private void addGameMode() {
        JLabel lblGameType = new JLabel("Choose Game Type");
        rbnHumVsAI = new JRadioButton("Human vs AI");
        rbnHumVsHum = new JRadioButton("Human vs Human");
        rbnNetMode = new JRadioButton("Net mode");
        rbnHumVsAI.setSelected(true);
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(rbnHumVsAI);
        modeGroup.add(rbnHumVsHum);
        modeGroup.add(rbnNetMode);
        add(lblGameType);
        add(rbnHumVsAI);
        add(rbnHumVsHum);
        add(rbnNetMode);
    }

    private void addSizeAndWin() {
        JLabel lblFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel lblWinLength = new JLabel(WIN_SET_PREFIX + MIN_WIN);
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_WIN);
        slWinLength = new JSlider(MIN_WIN, MAX_WIN, MIN_WIN);
        slWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slWinLength.getValue() > slFieldSize.getValue()) slWinLength.setValue(slFieldSize.getValue());
                else lblWinLength.setText(WIN_SET_PREFIX + slWinLength.getValue());
            }
        });
        slFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblFieldSize.setText(FIELD_SIZE_PREFIX + slFieldSize.getValue());
                if (slWinLength.getValue() > slFieldSize.getValue()) slWinLength.setValue(slFieldSize.getValue());
            }
        });
        add(lblFieldSize);
        add(slFieldSize);
        add(lblWinLength);
        add(slWinLength);
    }

    private void sendSettings() {
        int gameMode;
        if (rbnHumVsAI.isSelected())
            gameMode = Map.GAME_MODE_HVA;
        else if (rbnHumVsHum.isSelected())
            gameMode = Map.GAME_MODE_HVH;
        else if (rbnNetMode.isSelected())
            gameMode = Map.GAME_MODE_NET;
        else throw new RuntimeException("Unknown GameMode");
        int fieldSize = slFieldSize.getValue();
        int winLength = slWinLength.getValue();
        setVisible(false);
        gameWindow.startGame(gameMode, fieldSize, winLength);
    }

}
