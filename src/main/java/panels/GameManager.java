package panels;

import javax.swing.*;

public class GameManager {

    private static final GamePanel gamePanel = new GamePanel();
    private static final JFrame jf = new JFrame();

    private static final StartMenu startMenu = new StartMenu();
    private static final InsertNameMenu insertNameMenu = new InsertNameMenu();
    private static final SettingsMenu settingsMenu = new SettingsMenu(gamePanel);
    private static final EndMenu endMenu = new EndMenu();

    private static String playerLeftName, playerRightName;
    private static boolean singlePlayerModeFlag;

    public static void setPlayerLeftName(String playerLeftName) {
        GameManager.playerLeftName = playerLeftName;
    }

    public static void setPlayerRightName(String playerRightName) {
        GameManager.playerRightName = playerRightName;
    }

    public static void setSinglePlayerModeFlag(boolean singlePlayerModeFlag) {
        GameManager.singlePlayerModeFlag = singlePlayerModeFlag;
    }

    public static void setup(){
        jf.setResizable(false);
        jf.setTitle("PONG");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

    private static void setPanel(JPanel jPanel) {
        jf.getContentPane().removeAll();
        jf.add(jPanel);
        jf.pack();
        jPanel.repaint();
    }

    public static void startGame() {
        setPanel(gamePanel);
        gamePanel.requestFocus();
        gamePanel.startGame(playerLeftName, playerRightName, singlePlayerModeFlag);
    }

    public static void startMenu() {
        setPanel(startMenu);
    }

    public static void insertNameMenu() {
        setPanel(insertNameMenu);
    }

    public static void settingsMenu() {
        setPanel(settingsMenu);
    }

    public static void endMenu(String winner) {
        endMenu.setWinnerName(winner);
        setPanel(endMenu);
    }
}
