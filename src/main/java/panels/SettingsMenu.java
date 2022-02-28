package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.stream.IntStream;

public class SettingsMenu extends Menu {

    private final GamePanel gamePanel;
    private final JSlider velocitySlider;
    private final JSlider maxScoreSlider;


    public SettingsMenu(GamePanel gamePanel) {
        super();

        this.gamePanel = gamePanel;

        int gap = buttonHeight + 50;
        int maxVelocity = 5;

        JLabel title = createLabel("Settings", GamePanel.TOP_FRAME, 28);
        Button buttonSave = createButton("Save", buttonYPosition + gap * 2);

        Hashtable<Integer, JLabel> velocityLabels = new Hashtable<>();
        IntStream.range(1, maxVelocity + 1).forEach(
                x -> velocityLabels.put(x, new JLabel(Integer.toString(x)))
        );

        JLabel selectBallVelocity = createLabel("Select ball velocity", buttonYPosition - 28, 15);

        velocitySlider = new JSlider(1, maxVelocity, gamePanel.getBallVelocityModule() - 2);
        velocitySlider.setBounds(buttonXPosition, buttonYPosition, buttonWidth, buttonHeight);
        velocitySlider.setMinorTickSpacing(1);
        velocitySlider.setSnapToTicks(true);
        velocitySlider.setOpaque(true);
        velocitySlider.setPaintLabels(true);
        velocitySlider.setPaintTrack(true);
        velocitySlider.setPaintTicks(true);
        velocitySlider.setLabelTable(velocityLabels);

        Hashtable<Integer, JLabel> maxScoreLabels = new Hashtable<>();
        IntStream.range(1, 5).forEach(
                x -> maxScoreLabels.put(x * 5, new JLabel(Integer.toString(x * 5)))
        );

        JLabel selectMaxScore = createLabel("Select maximum score", buttonYPosition + gap - 28, 15);

        maxScoreSlider = new JSlider(5, 20, gamePanel.getMaxScore());
        maxScoreSlider.setBounds(buttonXPosition, buttonYPosition + gap, buttonWidth, buttonHeight);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("Save".equals(actionEvent.getActionCommand())) {
            gamePanel.setBallVelocityModule(velocitySlider.getValue() + 2);
            gamePanel.setMaxScore(maxScoreSlider.getValue());
            GameManager.startMenu();
        }
    }
}
