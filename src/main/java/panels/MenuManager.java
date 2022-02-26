package panels;

import javax.swing.*;

public class MenuManager {

    private static final GamePanel gamePanel = new GamePanel();
    private static final JFrame jf = new JFrame();

    private static final StartMenu startMenu = new StartMenu();
    private static final InsertNameMenu insertNameMenu = new InsertNameMenu();
    private static final SettingsMenu settingsMenu = new SettingsMenu(gamePanel);
    private static final EndMenu endMenu = new EndMenu();

    private static String playerLeftName, playerRightName;
    private static boolean singlePlayerModeFlag;

    public static void setPlayerLeftName(String playerLeftName) {
        MenuManager.playerLeftName = playerLeftName;
    }

    public static void setPlayerRightName(String playerRightName) {
        MenuManager.playerRightName = playerRightName;
    }

    public static void setSinglePlayerModeFlag(boolean singlePlayerModeFlag) {
        MenuManager.singlePlayerModeFlag = singlePlayerModeFlag;
    }

    private static void setPanel(JPanel jPanel) {
        jf.getContentPane().removeAll();
        jf.add(jPanel);
        jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
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