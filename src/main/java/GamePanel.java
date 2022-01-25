import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(){
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.black);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(50, 50, 500, 500);
        Paddle p1 = new Paddle(0, 100, 100, 55, 550);
        PaddleDrawer pd1 = new PaddleDrawer(p1);
        pd1.draw(g2);
        Paddle p2 = new Paddle(1, 500, 300, 55, 550);
        PaddleDrawer pd2 = new PaddleDrawer(p2);
        pd2.draw(g2);
    }


    public static void main(String[] args){
        GamePanel p = new GamePanel();

        JFrame jf = new JFrame();
        jf.setTitle("PONG");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(p);
        jf.pack();
    }
}
