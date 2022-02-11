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

    private TextField name0;
    private TextField name1;
    private String sName0;
    private String sName1;

    private int stage = 0;
    private String winner;


    public MenuPanel(){

        setPreferredSize(panelSize);
        setBackground(Color.black);

        setLayout(null);

        startMenu();


    }

    public MenuPanel(String n0, String n1, boolean flagWinner) {
        sName0 = n0;
        sName1 = n1;
        winner = flagWinner ? n1 : n0;

        setPreferredSize(panelSize);
        setBackground(Color.black);

        setLayout(null);

        endMenu();
    }

    private void startMenu() {
        this.removeAll();

        stage = 0;
        repaint();

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
        buttonP2.setActionCommand("Two Players");

        Button buttonQuit = new Button("Quit");
        buttonQuit.setBounds(center,posy + gap*2, width, height);
        buttonQuit.addActionListener(this);
        buttonQuit.setActionCommand("Quit");

        add(buttonP1);
        add(buttonP2);
        add(buttonQuit);
    }

    private void endMenu(){
        this.removeAll();

        stage = 2;

        repaint();

        int height = 50;
        int width = 100;
        int center = (gameWidth + leftBorder + rightBorder)/2 - width/2;
        int posy = 2*topBorder;
        int gap = height+10;

        Button buttonPlayAgain = new Button("Play Again");
        buttonPlayAgain.setBounds(center,posy,width,height);
        buttonPlayAgain.addActionListener(this);
        buttonPlayAgain.setActionCommand("Play Again");

        Button buttonMainMenu = new Button("Main Menu");
        buttonMainMenu.setBounds(center,posy + gap, width, height);
        buttonMainMenu.addActionListener(this);
        buttonMainMenu.setActionCommand("Main Menu");

        Button buttonQuit = new Button("Quit");
        buttonQuit.setBounds(center,posy + gap*2, width, height);
        buttonQuit.addActionListener(this);
        buttonQuit.setActionCommand("Quit");

        add(buttonPlayAgain);
        add(buttonMainMenu);
        add(buttonQuit);

    }

    private void insertNameMenu() {
        this.removeAll();

        stage = 1;
        repaint();

        int height = 50;
        int width = 100;
        int center = (gameWidth + leftBorder + rightBorder)/2 - width/2;
        int posy = 2*topBorder;
        int gap = height+10;

        name0 = new TextField("Player1");
        name0.setBounds(center-(width+gap)/2,posy, width, height/2);
        name0.addActionListener(this);

        name1 = new TextField("Player2");
        name1.setBounds(center+(width+gap)/2,posy, width, height/2);
        name1.addActionListener(this);

        Button buttonPlay = new Button("Play");
        buttonPlay.setBounds(center,posy + gap,width,height);
        buttonPlay.addActionListener(this);
        buttonPlay.setActionCommand("Play");

        Button buttonBack = new Button("Back");
        buttonBack.setBounds(center,posy + gap*2, width, height);
        buttonBack.addActionListener(this);
        buttonBack.setActionCommand("Main Menu");


        add(buttonPlay);
        add(buttonBack);
        add(name0);
        add(name1);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        String title = "";
        int stringWidth;

        switch (stage){
            case 0:
                title = "Ping";
                g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
                break;
            case 1:
                title = "Insert Player names";
                g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                break;
            case 2:
                title = "The winner is "+ winner;
                g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                break;
            default:
                break;
        }
        stringWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (gameWidth + leftBorder + rightBorder)/2 - stringWidth/2, topBorder);
    }

    private void startGame(){
        String sName0 = name0.getText();
        String sName1 = name1.getText();
        Pong.startGame(sName0, sName1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Quit":
                System.exit(0);
                break;
            case "Two Players":
                insertNameMenu();
                break;
            case "One Player":
                break;
            case "Play":
                startGame();
                break;
            case "Main Menu":
                startMenu();
                break;
            case "Play Again":
                Pong.startGame(sName0, sName1);
                break;
            default:
                break;
        }
    }
}
