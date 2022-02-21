import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.stream.IntStream;

public class MenuPanel extends JPanel implements ActionListener {

    public int gameHeight = GameProperties.GAME_HEIGHT;
    public int gameWidth = GameProperties.GAME_WIDTH;
    public int topBorder = GameProperties.TOP_BORDER;
    public int botBorder = GameProperties.BOT_BORDER;
    public int leftBorder = GameProperties.LEFT_BORDER;
    public int rightBorder = GameProperties.RIGHT_BORDER;

    private final int height = 50;
    private final int width = 100;
    private final int center = (gameWidth + leftBorder + rightBorder)/2 - width/2;
    private final int posy = 2*topBorder;
    private final int gap = height+10;

    private TextField name0;
    private TextField name1;
    private JSlider velSlider;
    private JSlider maxScoreSlider;

    private String sName0;
    private String sName1;
    private boolean singlePlayer;
    private final GamePanel gamePanel;


    public MenuPanel(GamePanel gamepanel){
        this.gamePanel = gamepanel;
        Dimension panelSize = new Dimension(gameWidth + leftBorder + rightBorder,
                gameHeight + topBorder + botBorder);
        setPreferredSize(panelSize);
        setBackground(Color.black);

        setLayout(null);

        startMenu();

    }

    public void startMenu() {
        this.removeAll();
        repaint();

        JLabel title = new JLabel("Ping");
        title.setBounds(center, topBorder, 100,100);
        title.setBackground(Color.white);
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 28));

        Button buttonP1 = new Button("One Player");
        buttonP1.setBounds(center, posy, width, height);
        buttonP1.addActionListener(this);
        buttonP1.setActionCommand("One Player");

        Button buttonP2 = new Button("Two Players");
        buttonP2.setBounds(center,posy + gap, width, height);
        buttonP2.addActionListener(this);
        buttonP2.setActionCommand("Two Players");

        Button buttonSettings = new Button("Settings");
        buttonSettings.setBounds(center,posy + gap*2, width, height);
        buttonSettings.addActionListener(this);
        buttonSettings.setActionCommand("Settings");

        Button buttonQuit = new Button("Quit");
        buttonQuit.setBounds(center,posy + gap*3, width, height);
        buttonQuit.addActionListener(this);
        buttonQuit.setActionCommand("Quit");

        add(buttonP1);
        add(buttonP2);
        add(buttonSettings);
        add(buttonQuit);
        add(title);
    }

    public void endMenu(String sName0, String sName1, String winner, boolean singlePlayer){
        this.removeAll();
        repaint();
        this.sName0 = sName0;
        this.sName1 = sName1;
        this.singlePlayer = singlePlayer;

        JLabel title = new JLabel("The winner is " + winner);
        title.setBounds(leftBorder, topBorder, 500,100);
        title.setBackground(Color.white);
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 20));

        Button buttonPlayAgain = new Button("Play Again");
        buttonPlayAgain.setBounds(center, posy, width, height);
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
        add(title);

    }

    private void insertNameMenu() {
        this.removeAll();
        repaint();

        JLabel title = new JLabel("Insert names");
        title.setBounds(center-25, topBorder, 150,100);
        title.setBackground(Color.white);
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 20));

        name0 = new TextField("Player1");
        name0.setBounds(center-(width+gap)/2, posy, width, height/2);

        name1 = new TextField("Player2");
        name1.setBounds(center+(width+gap)/2, posy, width, height/2);

        Button buttonPlay = new Button("Play");
        buttonPlay.setBounds(center,posy + gap, width, height);
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
        add(title);
    }

    private void settingsMenu(){
        this.removeAll();
        repaint();

        int gap = height+50;
        int maxVelocity = 5;

        JLabel title = new JLabel("Settings");
        title.setBounds(leftBorder, topBorder, 500,100);
        title.setBackground(Color.white);
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 20));

        Button buttonSave = new Button("Save");
        buttonSave.setBounds(center,posy + gap*2, width, height);
        buttonSave.addActionListener(this);
        buttonSave.setActionCommand("Save");

        Hashtable<Integer, JLabel> velLabels = new Hashtable<>();
        IntStream.range(1,maxVelocity+1).forEach(
                x -> velLabels.put(x, new JLabel(Integer.toString(x)))
        );

        JLabel selectBallVelocity = new JLabel("Select ball velocity");
        selectBallVelocity.setBounds(center-width/2, posy-40, width*2, height);
        selectBallVelocity.setBackground(Color.white);
        selectBallVelocity.setForeground(Color.WHITE);
        selectBallVelocity.setHorizontalAlignment(JLabel.CENTER);

        velSlider = new JSlider(1,maxVelocity, gamePanel.getVelModule());
        velSlider.setBounds(center, posy, width, height);
        velSlider.setMinorTickSpacing(1);
        velSlider.setSnapToTicks(true);
        velSlider.setOpaque(true);
        velSlider.setPaintLabels(true);
        velSlider.setPaintTrack(true);
        velSlider.setPaintTicks(true);
        velSlider.setLabelTable(velLabels);


        Hashtable<Integer, JLabel> maxScoreLabels = new Hashtable<>();
        IntStream.range(1,5).forEach(
                x -> maxScoreLabels.put(x*5, new JLabel(Integer.toString(x*5)))
        );

        JLabel selectMaxScore = new JLabel("Select maximum score");
        selectMaxScore.setBounds(center-width/2, posy+gap-40, width*2, height);
        selectMaxScore.setBackground(Color.white);
        selectMaxScore.setForeground(Color.WHITE);
        selectMaxScore.setHorizontalAlignment(JLabel.CENTER);

        maxScoreSlider = new JSlider(5,20, gamePanel.getMaxScore());
        maxScoreSlider.setBounds(center, posy+gap, width, height);
        maxScoreSlider.setMinorTickSpacing(5);
        maxScoreSlider.setSnapToTicks(true);
        maxScoreSlider.setOpaque(true);
        maxScoreSlider.setPaintLabels(true);
        maxScoreSlider.setPaintTrack(true);
        maxScoreSlider.setPaintTicks(true);
        maxScoreSlider.setLabelTable(maxScoreLabels);


        add(buttonSave);
        add(velSlider);
        add(maxScoreSlider);
        add(selectBallVelocity);
        add(selectMaxScore);
        add(title);
    }

    private void startGame(){
        String sName0 = name0.getText();
        String sName1 = name1.getText();
        Pong.startGame(sName0, sName1, false);
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
                Pong.startGame("Player", "COM", true);
                break;
            case "Play":
                startGame();
                break;
            case "Main Menu":
                startMenu();
                break;
            case "Play Again":
                Pong.startGame(sName0, sName1, singlePlayer);
                break;
            case "Settings":
                settingsMenu();
                break;
            case "Save":
                gamePanel.setVelModule(velSlider.getValue());
                gamePanel.setMaxScore(maxScoreSlider.getValue());
                startMenu();
                break;
            default:
                break;
        }
    }
}
