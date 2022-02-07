import java.awt.*;

public class PlayerDrawer {
    Player player;
    int topBorder;
    int leftBorder;
    int rightBorder;
    int gameWidth;

    public PlayerDrawer(Player player, int topBorder, int leftBorder, int rightBorder, int gameWidth) {
        this.player = player;
        this.topBorder = topBorder;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.gameWidth = gameWidth;
    }

    public void draw(Graphics2D g2){
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
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
