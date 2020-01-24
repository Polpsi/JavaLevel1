package HomeWork3;

import java.util.Random;
import java.util.Scanner;

public class HomeWork3 {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final char humanDot = 'X';
    private static final char aiDot = 'O';
    private static final char emptyDot = '.';
    private static int maxFieldX = 3;
    private static int maxFieldY = 3;
    private static char[][] field = new char[maxFieldY][maxFieldX];
    private static int[] emptyY = new int[maxFieldY];
    private static int lengthToWin = 3;
    private static int turnY;
    private static int turnX;
    private static int[] bordersY = new int[2];
    private static int[] bordersX = new int[2];
    private static int[][] diagLeftUp = new int[2][2];
    private static int[][] diagRightUp = new int[2][2];
    private static int[] hintToAI = new int[5]; //(y,x,есть хинт,индекс в линии,номер линии)


    public static void main(String[] args) {
        //Инициализация переменных


        //Генерация поля
        //Инициализация поля
        //Заполнение точками
        field = initField(maxFieldY, maxFieldX);
        //Заполнение показателей пустых рядов
        for (int i = 0; i < maxFieldY; i++)
            emptyY[i] = maxFieldX;
        //print field
        printField(field);
        while (true) {
            humanTurn();
            printField(field);
            if (checkWin(turnY, turnX, humanDot)) {
                System.out.println("Человек вин");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn(hintToAI);
            printField(field);
            if (checkWin(turnY, turnX, aiDot)) {
                System.out.println("ИИ вин");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
            //break;
        }
        //Ход человека
        //Проверка на возможность хода
        //Установка хода (отрисовка поля)
        //Проверка на победу
        //получить возможные ряды
        //проверить последовательность ходов
        //поставить подсказки для ИИ
        //Проверка на зарисованность поля
        //Выбрать пустые ряды
        //выбрать пустые ячейки


    }

    private static boolean checkDraw() {
        for (int i = 0; i < emptyY.length; i++) {
            if (emptyY[i] > 0) return false;
        }
        return true;
    }

    private static void aiTurn(int[] hintToAI) {
        if (hintToAI[4] == 1) {
            turnY = hintToAI[0];
            turnX = hintToAI[1];
            hintToAI[4] = 0;
        } else {
            int[] validVal = new int[maxFieldY];
            int j = 0;
            for (int i = 0; i < emptyY.length; i++) {
                if (emptyY[i] > 0) {
                    validVal[j] = i;
                    j++;
                }
            }
            turnY = validVal[RANDOM.nextInt(j - 1)];
            j = 0;
            for (int i = 0; i < validVal.length; i++) {
                if (field[turnY][i] == emptyDot) {
                    validVal[j] = i;
                    j++;
                }
            }
            turnX = validVal[RANDOM.nextInt(j - 1)];
        }
        field[turnY][turnX] = aiDot;
        emptyY[turnY]--;
    }

    private static boolean checkWin(int y, int x, char dot) {
        bordersY = getBorder(y, maxFieldY);
        bordersX = getBorder(x, maxFieldX);
        char[][] lines = getWinLines(bordersY, bordersX);
        for (int i = 0; i < 4; i++) {
            if (checkLine(lines[i], dot, i)) return true;
            else if (hintToAI[2] == 1) {
                sethint(hintToAI[3], i);
                hintToAI[2] = 0;
            }
        }
        return false;
    }

    private static void sethint(int index, int line) {
        switch (line) {
            case 0:
                hintToAI[0] = bordersY[0] + index;
                hintToAI[1] = turnX;
                break;
            case 1:
                hintToAI[0] = turnY;
                hintToAI[1] = bordersX[0] + index;
                break;
            case 2:
                hintToAI[0] = bordersY[0] + index;
                hintToAI[1] = bordersX[0] + index;
                break;
            case 3:
                hintToAI[0] = bordersY[0] + index;
                hintToAI[1] = getDiagonalsCoord(bordersY[0], bordersX[1])[1] - index;
        }
    }

    private static boolean checkLine(char[] line, char dot, int numLine) {
        int score = 0;
        if (line[0] == emptyDot) hintToAI[3] = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i] == dot) {
                score++;
                if (score == lengthToWin) return true;
                if ((score == lengthToWin - 1) && dot == humanDot) {
                    if (i < line.length - 1 && line[i+1] == emptyDot) {
                        hintToAI[2] = 1;
                        hintToAI[3] = i;
                        hintToAI[4] = 1;
                    } else if (i >= score-1 && line[i-score] == emptyDot) {
                        hintToAI[2] = 1;
                        hintToAI[3] = i - score;
                        hintToAI[4] = 1;
                    }
                }
            } else {
                score = 0;
            }
        }


        return false;
    }

    private static char[][] getWinLines(int[] bordersY, int[] bordersX) {
        int j = 0;
        //вертикаль и горизонталь, левая(сверху) и правая(сверху) диагонали
        char[][] winLines = new char[4][lengthToWin * 2 - 1];
        for (int i = 0; i < lengthToWin * 2 - 1; i++) {
            if (bordersY[0] + i <= bordersY[1]) winLines[0][j] = field[bordersY[0] + i][turnX]; //вертикаль
            if (bordersX[0] + i <= bordersX[1]) winLines[1][j] = field[turnY][bordersX[0] + i]; //горизонталь
            j++;
        }
        //диагонали
        int[][] diagLeftUp = {getDiagonalsCoord(bordersY[0], bordersX[0]), getDiagonalsCoord(bordersY[1], bordersX[1])};
        int[][] diagRightUp = {getDiagonalsCoord(bordersY[0], bordersX[1]), getDiagonalsCoord(bordersY[1], bordersX[0])};
        winLines[2] = getDiag(diagLeftUp, 1);
        winLines[3] = getDiag(diagRightUp, -1);
        return winLines;
    }

    private static char[] getDiag(int[][] diag, int direct) {
        char[] line = new char[lengthToWin * 2 - 1];
        int diagLength = diag[1][0] - diag[0][0] + 1;
        if (diagLength >= lengthToWin - 1)
            for (int i = 0; i < diagLength; i++) {
                line[i] = field[diag[0][0] + i][diag[0][1] + direct * i];
            }
        return line;
    }

    private static int[] getDiagonalsCoord(int y, int x) {
        if (Math.abs(turnY - y) < Math.abs(turnX - x))
            x = (turnX > x) ? turnX - Math.abs(turnY - y) : turnX + Math.abs(turnY - y);
        else if (Math.abs(turnY - y) > Math.abs(turnX - x))
            y = (turnY > y) ? turnY - Math.abs(turnX - x) : turnY + Math.abs(turnX - x);
        // System.out.println("y=" + y + " x=" + x);
        return new int[]{y, x};
    }

    private static int[] getBorder(int turn, int maxBorder) {
        int[] border = new int[2];
        int length = lengthToWin - 1;
        border[0] = Math.max((turn - length), 0);
        border[1] = Math.min(turn + length, maxBorder - 1);
        return border;
    }

    //Генерация поля и Заполнение точками
    private static char[][] initField(int maxY, int maxX) {
        char[][] field = new char[maxY][maxX];
        for (int i = 0; i < maxY; i++)
            for (int j = 0; j < maxX; j++)
                field[i][j] = '.';
        return field;
    }

    //print field
    private static void printField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("|" + field[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    //Ход человека
    private static void humanTurn() {
        do {
            System.out.println("input x y");
            turnX = SCANNER.nextInt() - 1;
            turnY = SCANNER.nextInt() - 1;
        } while (!checkValid(turnY, turnX) || !checkEmpty(turnY, turnX));
        field[turnY][turnX] = humanDot;
        emptyY[turnY]--;
    }

    private static boolean checkEmpty(int y, int x) {
        return (field[y][x] == '.');
    }

    private static boolean checkValid(int y, int x) {
        return (0 <= y && y < maxFieldY && 0 <= x && x < maxFieldX);
    }
}
