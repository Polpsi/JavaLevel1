package HomeWork7;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.util.Random;

public class Map extends JPanel {
    public static final int GAME_MODE_HVH = 0;
    public static final int GAME_MODE_HVA = 1;
    public static final int GAME_MODE_NET = 2;

    private static final int DOT_EMPTY = 0;
    private static final int DOT_HUMAN = 1;
    private static final int DOT_AI = 2;
    private static final int DOT_PADDING = 5;

    private int stateGameOver;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_WRONG_GAMEMODE = 3;

    private static final String MSG_WIN_HUMAN = "Human WIN!";
    private static final String MSG_WIN_AI = "AI WIN!";
    private static final String MSG_DRAW = "DRAW!";
    private static final String MSG_WRONG_GAMEMODE = "GameMode not realised now :(";

    private static final Random RANDOM = new Random();

    private int[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private int cellWidth;
    private int cellHeight;
    private boolean isGameOver;
    private boolean initialized;

    Map() {
        setBackground(new Color(40, 90, 100));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        initialized = false;
    }

    private void setGameOver(int gameOverState) {
        isGameOver = true;
        stateGameOver = gameOverState;
        repaint();
    }

    private void update(MouseEvent e) {
        if (isGameOver) return;
        if (!initialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isCellValid(cellX, cellY) || !isCellEmpty(cellX, cellY))
            return;
        field[cellY][cellX] = DOT_HUMAN;
        if (checkWin(DOT_HUMAN)) {
            setGameOver(STATE_WIN_HUMAN);
            return;
        }
        if (isDraw()) {
            setGameOver(STATE_DRAW);
            return;
        }
        aiTurn();
        repaint();
        if (checkWin(DOT_AI)) {
            setGameOver(STATE_WIN_AI);
            return;
        }
        if (isDraw()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeY = fieldSizeY;
        this.fieldSizeX = fieldSizeX;
        this.winLength = winLength;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        if (gameMode != 1) {setGameOver(STATE_WRONG_GAMEMODE);}
        initialized = true;
        repaint();
    }

    private void render(Graphics g) {
        if (!initialized) return;
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) continue;
                if (field[y][x] == DOT_HUMAN)
                    g.setColor(new Color(255, 179, 60));
                else if (field[y][x] == DOT_AI)
                    g.setColor(new Color(255, 86, 251));
                else {
                    throw new RuntimeException(
                            String.format("Can't recognize cell field[%d][%d]: %d",
                                    y, x, field[y][x]));
                }
                g.fillOval(x * cellWidth + DOT_PADDING,
                        y * cellHeight + DOT_PADDING,
                        cellWidth - 2 * DOT_PADDING,
                        cellHeight - 2 * DOT_PADDING);
            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("times new roman", Font.BOLD, 48));
        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 100, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            case STATE_WRONG_GAMEMODE:
                g.setFont(new Font("times new roman", Font.BOLD, 24));
                g.drawString(MSG_WRONG_GAMEMODE, 20, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + stateGameOver);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private boolean isDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ход компьютера
    private void aiTurn() {
        if(turnAIWinCell()) return;		// проверим, не выиграет-ли комп на следующем ходу
        if(turnHumanWinCell()) return;	// проверим, не выиграет-ли игрок на следующем ходу
        int x, y;
        do {							// или комп ходит в случайную клетку
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }
    // Проверка, может ли выиграть комп
    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellEmpty(j, i)) {				// поставим нолик в каждую клетку поля по очереди
                    field[i][j] = DOT_AI;
                    if (checkWin(DOT_AI)) return true;	// если мы выиграли, вернём истину, оставив нолик в выигрышной позиции
                    field[i][j] = DOT_EMPTY;			// если нет - вернём обратно пустоту в клетку и пойдём дальше
                }
            }
        }
        return false;
    }
    // Проверка, выиграет-ли игрок своим следующим ходом
    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellEmpty(j, i)) {
                    field[i][j] = DOT_HUMAN;			// поставим крестик в каждую клетку по очереди
                    if (checkWin(DOT_HUMAN)) {			// если игрок победит
                        field[i][j] = DOT_AI;			// поставить на то место нолик
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;			// в противном случае вернуть на место пустоту
                }
            }
        }
        return false;
    }
    // проверка на победу
    private boolean checkWin(int c) {
        for (int i = 0; i < fieldSizeX; i++) {			// ползём по всему полю
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, c)) return true;	// проверим линию по х
                if (checkLine(i, j, 1, 1, winLength, c)) return true;	// проверим по диагонали х у
                if (checkLine(i, j, 0, 1, winLength, c)) return true;	// проверим линию по у
                if (checkLine(i, j, 1, -1, winLength, c)) return true;	// проверим по диагонали х -у
            }
        }
        return false;
    }
    // проверка линии
    private boolean checkLine(int x, int y, int vx, int vy, int len, int c) {
        final int far_x = x + (len - 1) * vx;			// посчитаем конец проверяемой линии
        final int far_y = y + (len - 1) * vy;
        if (!isCellValid(far_x, far_y)) return false;	// проверим не выйдет-ли проверяемая линия за пределы поля
        for (int i = 0; i < len; i++) {					// ползём по проверяемой линии
            if (field[y + i * vy][x + i * vx] != c) return false;	// проверим одинаковые-ли символы в ячейках
        }
        return true;
    }

}
