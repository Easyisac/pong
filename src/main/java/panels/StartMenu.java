package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartMenu extends Menu {

    public StartMenu() {
        super();

        JLabel title = createLabel("Pong", GamePanel.TOP_FRAME, 40);
        Button buttonOnePlayer = createButton("One Player", buttonYPosition);
        Button buttonTwoPlayers = createButton("Two Players", buttonYPosition + gapBetweenButtons);
        Button buttonSettings = createButton("Settings", buttonYPosition + gapBetweenButtons * 2);
        Button buttonQuit = createButton("Quit", buttonYPosition + gapBetweenButtons * 3);

        add(buttonOnePlayer);
        add(buttonTwoPlayers);
        add(buttonSettings);
        add(buttonQuit);
        add(title);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Quit":
                System.exit(0);
                break;
            case "One Player":
                MenuManager.setPlayerLeftName("Player");
                MenuManager.setPlayerRightName("COM");
                MenuManager.setSinglePlayerModeFlag(true);
                MenuManager.startGame();
                break;
            case "Two Players":
                MenuManager.insertNameMenu();
                break;
            case "Settings":
                MenuManager.settingsMenu();
                break;
            default:
                break;
        }
    }
}
