import java.awt.*;

public class PlayerDrawer {
    Player player;
    int topBorder = GameProperties.TOP_FRAME;
    int leftBorder = GameProperties.LEFT_FRAME;
    int gameWidth = GameProperties.GAME_COURT_WIDTH;

    public PlayerDrawer(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2){
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        int stringWidth = g2.getFontMetrics().stringWidth(player.getName());
        int stringHeight = g2.getFontMetrics().getHeight();
        if (player.getId() == 0){
            int position = gameWidth/4 + leftBorder - stringWidth/2;
            g2.drawString(player.getName(), position, topBorder/2);
            g2.drawString(String.valueOf(player.getScore()), position+stringWidth/2, topBorder/2 + stringHeight);
        } else {
            int position = leftBorder + (gameWidth/4)*3 - stringWidth/2;
            g2.drawString(player.getName(), position, topBorder/2);
            g2.drawString(String.valueOf(player.getScore()), position+stringWidth/2, topBorder/2 + stringHeight);
        }
    }
}
