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

    private final int buttonHeight = 50;
    private final int buttonWidth = 100;
    private final int buttonXPosition = (gameWidth + leftBorder + rightBorder)/2 - buttonWidth /2;
    private final int buttonYPosition = 2 * topBorder;
    private final int gapBetweenButtons = buttonHeight + 10;

    private TextField player0NameField;
    private TextField player1NameField;
    private JSlider velocitySlider;
    private JSlider maxScoreSlider;

    private String player0Name;
    private String player1Name;
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
        label.setBounds(leftBorder, yPosition, 500,100);
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

        JLabel title = createLabel("Ping", topBorder, 28);
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

    public void endMenu(String player0Name, String player1Name, String winnerName, boolean singlePlayerModeFlag){
        this.removeAll();
        repaint();
        this.player0Name = player0Name;
        this.player1Name = player1Name;
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

        JLabel title = createLabel("Insert names", topBorder, 20);

        player0NameField = new TextField("Player1");
        player0NameField.setBounds(buttonXPosition -(buttonWidth + gapBetweenButtons)/2, buttonYPosition /2 + gapBetweenButtons, buttonWidth, buttonHeight/2);

        player1NameField = new TextField("Player2");
        player1NameField.setBounds(buttonXPosition +(buttonWidth + gapBetweenButtons)/2, buttonYPosition /2 + gapBetweenButtons, buttonWidth, buttonHeight/2);

        Button buttonPlay = createButton("Play", buttonYPosition + gapBetweenButtons);
        Button buttonBack = createButton("Main Menu", buttonYPosition + gapBetweenButtons *2);

        add(buttonPlay);
        add(buttonBack);
        add(player0NameField);
        add(player1NameField);
        add(title);
    }

    private void settingsMenu(){
        this.removeAll();
        repaint();

        int gap = buttonHeight +50;
        int maxVelocity = 5;

        JLabel title = createLabel("Settings", topBorder, 20);
        Button buttonSave = createButton("Save", buttonYPosition + gap*2);

        Hashtable<Integer, JLabel> velLabels = new Hashtable<>();
        IntStream.range(1,maxVelocity+1).forEach(
                x -> velLabels.put(x, new JLabel(Integer.toString(x)))
        );

        JLabel selectBallVelocity = createLabel("Select ball velocity", buttonYPosition - 28, 15);

        velocitySlider = new JSlider(1,maxVelocity, gamePanel.getVelModule());
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
        String player0Name = player0NameField.getText();
        String player1Name = player1NameField.getText();
        Pong.startGame(player0Name, player1Name, false);
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
                Pong.startGame(player0Name, player1Name, singlePlayerModeFlag);
                break;
            case "Settings":
                settingsMenu();
                break;
            case "Save":
                gamePanel.setVelModule(velocitySlider.getValue());
                gamePanel.setMaxScore(maxScoreSlider.getValue());
                startMenu();
                break;
            default:
                break;
        }
    }
}
