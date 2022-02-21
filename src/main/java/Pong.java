import javax.swing.*;

public class Pong{

    private static JFrame jf;
    private static final GamePanel gamePanel = new GamePanel();
    private static final MenuPanel menuPanel = new MenuPanel(gamePanel);

    public static void main(String[] args) {
        jf = new JFrame();
        startMenu();
    }

    public static void startMenu() {
        jf.getContentPane().removeAll();
        jf.add(menuPanel);
        menuPanel.startMenu();
        jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void exitGame(String sName0, String sName1, String winner) {
        jf.getContentPane().removeAll();
        jf.add(menuPanel);
        menuPanel.endMenu(sName0, sName1, winner);
        jf.setResizable(false);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    public static void startGame(String sName0, String sName1) {
        jf.getContentPane().removeAll();
        jf.add(gamePanel);
        gamePanel.startGame(sName0, sName1);
        jf.setResizable(false);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        gamePanel.requestFocus();
    }
}
