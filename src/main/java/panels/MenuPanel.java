package panels;

import pong.GameProperties;
import pong.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.stream.IntStream;

public class MenuPanel extends JPanel implements ActionListener {

    public int gameHeight = GameProperties.GAME_COURT_HEIGHT;
    public int gameWidth = GameProperties.GAME_COURT_WIDTH;
    public int topBorder = GameProperties.TOP_FRAME;
    public int botBorder = GameProperties.BOTTOM_FRAME;
    public int leftBorder = GameProperties.LEFT_FRAME;
    public int rightBorder = GameProperties.RIGHT_FRAME;

    private final int buttonHeight = 50;
    private final int buttonWidth = 100;
    private final int buttonXPosition = (gameWidth + leftBorder + rightBorder)/2 - buttonWidth /2;
    private final int buttonYPosition = 2 * topBorder;
    private final int gapBetweenButtons = buttonHeight + 10;

    private TextField playerLeftNameField;
    private TextField playerRightNameField;
    private JSlider velocitySlider;
    private JSlider maxScoreSlider;

    private String playerLeftName;
    private String playerRightName;
    private boolean singlePlayerModeFlag;
    private final GamePanel gamePanel;


    public MenuPanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        Dimension panelSize = new Dimension(gameWidth + leftBorder + rightBorder,
                gameHeight + topBorder + botBorder);
        setPreferredSize(panelSize);
        setBackground(Color.black);
        setLayout(null);
        startMenu();
    }

    private Button createButton(String name, int yPosition){
        Button button = new Button(name);
        button.setBounds(buttonXPosition, yPosition, buttonWidth, buttonHeight);
        button.addActionListener(this);
        button.setActionCommand(name);
        return button;
    }

    private JLabel createLabel(String text, int yPosition, int fontSize){
        JLabel label = new JLabel(text);
        label.setBounds(leftBorder, yPosition, GameProperties.GAME_COURT_WIDTH,100);
        label.setBackground(Color.white);
        label.setForeground(Color.WHITE);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }

    public void startMenu() {
        this.removeAll();
        repaint();

        JLabel title = createLabel("Pong", topBorder, 40);
        Button buttonOnePlayer = createButton("One Player", buttonYPosition);
        Button buttonTwoPlayers = createButton("Two Players", buttonYPosition + gapBetweenButtons);
        Button buttonSettings = createButton("Settings", buttonYPosition + gapBetweenButtons *2);
        Button buttonQuit = createButton("Quit", buttonYPosition + gapBetweenButtons *3);

        add(buttonOnePlayer);
        add(buttonTwoPlayers);
        add(buttonSettings);
        add(buttonQuit);
        add(title);
    }

    public void endMenu(String playerLeftName, String playerRightName, String winnerName, boolean singlePlayerModeFlag){
        this.removeAll();
        repaint();
        this.playerLeftName = playerLeftName;
        this.playerRightName = playerRightName;
        this.singlePlayerModeFlag = singlePlayerModeFlag;

        JLabel title = createLabel("The winner is " + winnerName, topBorder, 20);
        Button buttonPlayAgain = createButton("Play Again", buttonYPosition);
        Button buttonMainMenu = createButton("Main Menu", buttonYPosition + gapBetweenButtons);
        Button buttonQuit = createButton("Quit", buttonYPosition + gapBetweenButtons *2);

        add(buttonPlayAgain);
        add(buttonMainMenu);
        add(buttonQuit);
        add(title);

    }

    private void insertNameMenu() {
        this.removeAll();
        repaint();

        JLabel title = createLabel("Insert names", topBorder, 28);

        playerLeftNameField = new TextField("Player1");
        playerLeftNameField.setBounds(buttonXPosition -(buttonWidth + gapBetweenButtons)/2, buttonYPosition /2 + gapBetweenButtons, buttonWidth, buttonHeight/2);
        playerLeftNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (playerLeftNameField.getText().length() >= 15 )
                    e.consume();
            }
        });

        playerRightNameField = new TextField("Player2");
        playerRightNameField.setBounds(buttonXPosition +(buttonWidth + gapBetweenButtons)/2, buttonYPosition /2 + gapBetweenButtons, buttonWidth, buttonHeight/2);
        playerRightNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (playerRightNameField.getText().length() >= 15 )
                    e.consume();
            }
        });


        Button buttonPlay = createButton("Play", buttonYPosition + gapBetweenButtons);
        Button buttonBack = createButton("Main Menu", buttonYPosition + gapBetweenButtons *2);

        add(buttonPlay);
        add(buttonBack);
        add(playerLeftNameField);
        add(playerRightNameField);
        add(title);
    }

    private void settingsMenu(){
        this.removeAll();
        repaint();

        int gap = buttonHeight +50;
        int maxVelocity = 5;

        JLabel title = createLabel("Settings", topBorder, 28);
        Button buttonSave = createButton("Save", buttonYPosition + gap*2);

        Hashtable<Integer, JLabel> velLabels = new Hashtable<>();
        IntStream.range(1,maxVelocity+1).forEach(
                x -> velLabels.put(x, new JLabel(Integer.toString(x)))
        );

        JLabel selectBallVelocity = createLabel("Select ball velocity", buttonYPosition - 28, 15);

        velocitySlider = new JSlider(1,maxVelocity, gamePanel.getVelocityModule()-2);
        velocitySlider.setBounds(buttonXPosition, buttonYPosition, buttonWidth, buttonHeight);
        velocitySlider.setMinorTickSpacing(1);
        velocitySlider.setSnapToTicks(true);
        velocitySlider.setOpaque(true);
        velocitySlider.setPaintLabels(true);
        velocitySlider.setPaintTrack(true);
        velocitySlider.setPaintTicks(true);
        velocitySlider.setLabelTable(velLabels);


        Hashtable<Integer, JLabel> maxScoreLabels = new Hashtable<>();
        IntStream.range(1,5).forEach(
                x -> maxScoreLabels.put(x*5, new JLabel(Integer.toString(x*5)))
        );

        JLabel selectMaxScore = createLabel("Select maximum score", buttonYPosition + gap - 28, 15);

        maxScoreSlider = new JSlider(5,20, gamePanel.getMaxScore());
        maxScoreSlider.setBounds(buttonXPosition, buttonYPosition +gap, buttonWidth, buttonHeight);
        maxScoreSlider.setMinorTickSpacing(5);
        maxScoreSlider.setSnapToTicks(true);
        maxScoreSlider.setOpaque(true);
        maxScoreSlider.setPaintLabels(true);
        maxScoreSlider.setPaintTrack(true);
        maxScoreSlider.setPaintTicks(true);
        maxScoreSlider.setLabelTable(maxScoreLabels);


        add(buttonSave);
        add(velocitySlider);
        add(maxScoreSlider);
        add(selectBallVelocity);
        add(selectMaxScore);
        add(title);
    }

    private void startGame(){
        playerLeftName = playerLeftNameField.getText();
        playerRightName = playerRightNameField.getText();
        Pong.startGame(playerLeftName, playerRightName, false);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
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
                Pong.startGame(playerLeftName, playerRightName, singlePlayerModeFlag);
                break;
            case "Settings":
                settingsMenu();
                break;
            case "Save":
                gamePanel.setVelocityModule(velocitySlider.getValue() + 2);
                gamePanel.setMaxScore(maxScoreSlider.getValue());
                startMenu();
                break;
            default:
                break;
        }
    }
}
