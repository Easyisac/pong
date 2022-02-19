import javax.swing.*;

public class Pong{

    private static JFrame jf;

    public static void main(String[] args) {
        jf = new JFrame();
        startMenu();
    }

    public static void startMenu() {
        jf.getContentPane().removeAll();
        MenuPanel menuPanel = new MenuPanel();
        jf.add(menuPanel);
        jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void exitGame(Player pl0, Player pl1) {

        boolean winner = pl0.getScore() < pl1.getScore();

        jf.getContentPane().removeAll();
        MenuPanel endPanel = new MenuPanel(pl0.getName(), pl1.getName(), winner);
        jf.add(endPanel);
        jf.setResizable(false);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    public static void startGame(String sName0, String sName1) {
        jf.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(sName0, sName1);
        jf.add(gamePanel);
        jf.setResizable(false);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        gamePanel.requestFocus();
        Thread game = new Thread(gamePanel);
        game.start();

    }
}
