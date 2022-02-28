package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Menu extends JPanel implements ActionListener {

    protected final int buttonHeight = 50;
    protected final int buttonWidth = 100;
    protected final int buttonXPosition = (GamePanel.GAME_COURT_WIDTH + GamePanel.SIDE_FRAME + GamePanel.SIDE_FRAME) / 2 - buttonWidth / 2;
    protected final int buttonYPosition = 2 * GamePanel.TOP_FRAME;
    protected final int gapBetweenButtons = buttonHeight + 10;

    public Menu() {
        Dimension panelSize = new Dimension(GamePanel.GAME_COURT_WIDTH + GamePanel.SIDE_FRAME + GamePanel.SIDE_FRAME,
                GamePanel.GAME_COURT_HEIGHT + GamePanel.TOP_FRAME + GamePanel.BOTTOM_FRAME);
        setPreferredSize(panelSize);
        setBackground(Color.black);
        setLayout(null);
    }

    protected Button createButton(String name, int yPosition) {
        Button button = new Button(name);
        button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        button.setBounds(buttonXPosition, yPosition, buttonWidth, buttonHeight);
        button.addActionListener(this);
        button.setActionCommand(name);
        return button;
    }

    protected JLabel createLabel(String text, int yPosition, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(GamePanel.SIDE_FRAME, yPosition, GamePanel.GAME_COURT_WIDTH, 100);
        label.setBackground(Color.white);
        label.setForeground(Color.WHITE);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        return label;
    }

}
