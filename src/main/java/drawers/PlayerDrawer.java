package drawers;

import java.awt.*;
import entities.Player;
import pong.GameProperties;

public class PlayerDrawer implements Drawer {
    // Draws players' names and scores during the match.

    private final Player player;

    public PlayerDrawer(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2){
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        int stringWidth = g2.getFontMetrics().stringWidth(player.getName());
        int stringHeight = g2.getFontMetrics().getHeight();
        int topFrame = GameProperties.TOP_FRAME;
        int leftFrame = GameProperties.LEFT_FRAME;
        int gameWidth = GameProperties.GAME_COURT_WIDTH;
        int position;
        position = player.getId() == 0 ? gameWidth / 4 + leftFrame - stringWidth / 2
                : leftFrame + (gameWidth / 4) * 3 - stringWidth / 2;
        g2.drawString(player.getName(), position, topFrame /2);
        g2.drawString(String.valueOf(player.getScore()), position+stringWidth/2, topFrame /2 + stringHeight);
    }
}
