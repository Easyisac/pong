import javax.swing.*;

public class Pong{

    private static JFrame jf;

    public static void main(String[] args) {
        jf = new JFrame();
        startMenu();
        //jf.add(gamePanel);
        /*jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);*/
        //GamePanel gamePanel = new GamePanel();
        //Thread game = new Thread(gamePanel);
        //game.start();

    }

    public static void startMenu() {
        //jf.removeAll();
        MenuPanel menuPanel = new MenuPanel();
        jf.add(menuPanel);
        jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void startGame() {
        jf.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel();
        jf.add(gamePanel);
        jf.setResizable(false);
        jf.pack();
        //jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        gamePanel.requestFocus();
        Thread game = new Thread(gamePanel);
        game.start();

    }
}
