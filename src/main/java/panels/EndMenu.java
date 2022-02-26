package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EndMenu extends Menu {

    private String winnerName = "";
    private JLabel title;

    public EndMenu() {
        super();

        title = createLabel("The winner is " + winnerName, GamePanel.TOP_FRAME, 20);
        Button buttonPlayAgain = createButton("Play Again", buttonYPosition);
        Button buttonMainMenu = createButton("Main Menu", buttonYPosition + gapBetweenButtons);
        Button buttonQuit = createButton("Quit", buttonYPosition + gapBetweenButtons * 2);

        add(buttonPlayAgain);
        add(buttonMainMenu);
        add(buttonQuit);
        add(title);
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
        title.setText("The winner is " + winnerName);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Quit":
                System.exit(0);
                break;
            case "Main Menu":
                MenuManager.startMenu();
                break;
            case "Play Again":
                MenuManager.startGame();
                break;
            default:
                break;
        }
    }
}
