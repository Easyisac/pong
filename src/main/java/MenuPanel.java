import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    public int gameHeight = 500;
    public int gameWidth = 500;
    public int topBorder = 100;
    public int botBorder = 50;
    public int leftBorder = 50;
    public int rightBorder = 50;
    private Dimension panelSize = new Dimension(gameWidth + leftBorder + rightBorder,
            gameHeight + topBorder + botBorder);

    public MenuPanel(){

        setPreferredSize(panelSize);
        setBackground(Color.black);

        //setLayout(new GridLayout(3,1));
        setLayout(null);

        int height = 50;
        int width = 100;
        int center = (gameWidth + leftBorder + rightBorder)/2 - width/2;
        int posy = 2*topBorder;
        int gap = height+10;

        Button buttonP1 = new Button("One Player");
        buttonP1.setBounds(center,posy,width,height);
        buttonP1.addActionListener(this);

        Button buttonP2 = new Button("Two Players");
        buttonP2.setBounds(center,posy + gap, width, height);
        buttonP2.addActionListener(this);
        buttonP2.addActionListener(this);
        buttonP2.setActionCommand("Two Players");

        Button buttonQuit = new Button("Quit");
        buttonQuit.setBounds(center,posy + gap*2, width, height);
        buttonQuit.addActionListener(this);
        buttonQuit.setActionCommand("Quit");

        add(buttonP1);
        add(buttonP2);
        add(buttonQuit);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        String title = "Ping"; // for copyright
        int stringWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (gameWidth + leftBorder + rightBorder)/2 - stringWidth/2, topBorder);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Quit":
                System.exit(0);
                break;
            case "Two Players":
                Pong.startGame();
                break;
            case "One Player":
                break;
            default:
                break;
        }
    }
}
