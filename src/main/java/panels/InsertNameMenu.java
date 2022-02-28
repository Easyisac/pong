package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InsertNameMenu extends Menu {

    private final TextField playerLeftNameField;
    private final TextField playerRightNameField;

    public InsertNameMenu() {
        super();

        JLabel title = createLabel("Insert names", GamePanel.TOP_FRAME, 28);

        playerLeftNameField = new TextField("Player1");
        playerLeftNameField.setBounds(buttonXPosition - (buttonWidth + gapBetweenButtons) / 2, buttonYPosition / 2 + gapBetweenButtons, buttonWidth, buttonHeight / 2);
        playerLeftNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (playerLeftNameField.getText().length() >= 15)
                    e.consume();
            }
        });

        playerRightNameField = new TextField("Player2");
        playerRightNameField.setBounds(buttonXPosition + (buttonWidth + gapBetweenButtons) / 2, buttonYPosition / 2 + gapBetweenButtons, buttonWidth, buttonHeight / 2);
        playerRightNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (playerRightNameField.getText().length() >= 15)
                    e.consume();
            }
        });

        Button buttonPlay = createButton("Play", buttonYPosition + gapBetweenButtons);
        Button buttonBack = createButton("Main Menu", buttonYPosition + gapBetweenButtons * 2);

        add(buttonPlay);
        add(buttonBack);
        add(playerLeftNameField);
        add(playerRightNameField);
        add(title);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Play" -> {
                GameManager.setPlayerLeftName(playerLeftNameField.getText());
                GameManager.setPlayerRightName(playerRightNameField.getText());
                GameManager.setSinglePlayerModeFlag(false);
                GameManager.startGame();
            }
            case "Main Menu" -> GameManager.startMenu();
            default -> {
            }
        }
    }
}
