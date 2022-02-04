import javax.swing.*;

public class Pong{
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        GamePanel gamePanel = new GamePanel();
        Thread game = new Thread(gamePanel);
        game.start();
        jf.add(gamePanel);
        jf.setResizable(false);
        jf.pack();
        jf.setTitle("PONG");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
