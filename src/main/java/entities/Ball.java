package entities;

import panels.GamePanel;
import java.util.Random;

public class Ball {

    private final int topLimit = GamePanel.GAME_COURT_TOP_LIMIT;
    private final int bottomLimit = GamePanel.GAME_COURT_BOTTOM_LIMIT;
    private final int leftLimit = GamePanel.GAME_COURT_LEFT_LIMIT;
    private final int rightLimit = GamePanel.GAME_COURT_RIGHT_LIMIT;

    private final Paddle paddleLeft;
    private final Paddle paddleRight;
    private final Player playerLeft;
    private final Player playerRight;

    private final Random random = new Random();

    private final int BALL_RADIUS = 10;
    private final boolean LEFT = false;
    private final boolean RIGHT = true;
    private final double STARTING_RANGE = 5 / 6.0;
    private final double BOUNCE_RANGE = 2 / 3.0;

    private final int xPositionStart;
    private final int yPositionStart;
    private double xCenter;
    private double yCenter;
    private double velocityModule;
    private double velocityAngle;

    public Ball(Paddle paddleLeft, Paddle paddleRight, Player playerLeft, Player playerRight, int velocityModule) {
        xPositionStart = (rightLimit + leftLimit) / 2;
        yPositionStart = (topLimit + bottomLimit) / 2;
        xCenter = xPositionStart;
        yCenter = yPositionStart;
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.velocityModule = velocityModule;
        velocityAngle = generateStartingAngle(random.nextInt(1));
    }

    public int getxPositionStart() {
        return xPositionStart;
    }

    public int getyPositionStart() {
        return yPositionStart;
    }

    public int getBALL_RADIUS() {
        return BALL_RADIUS;
    }

    public double getxCenter() {
        return xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public double getVelocityModule() {
        return velocityModule;
    }

    public double getVelocityAngle() {
        return velocityAngle;
    }

    public void setVelModule(double velModule) {
        this.velocityModule = velModule;
    }

    public void setVelocityAngle(double velocityAngle) {
        this.velocityAngle = velocityAngle % (2 * Math.PI);
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }

    // Generates a random angle for the ball velocity, centered in 0 or PI, with aperture within STARTING_RANGE.
    public double generateStartingAngle(int direction) {
        double angle = (STARTING_RANGE * random.nextDouble() + (1 - STARTING_RANGE) / 2.0 + 1 / 2.0 + direction) % 2;
        return angle * Math.PI;
    }

    // Checks for goals and collisions and moves the ball to the next position.
    public void move() {
        if (checkCollisionsLeftLimit()) {
            goalScored(playerRight, LEFT);
        } else if (checkCollisionsRightLimit()) {
            goalScored(playerLeft, RIGHT);
        } else {
            if (checkCollisionsTopLimit() || checkCollisionsBottomLimit()) {
                if (velocityAngle == 0) {
                    velocityAngle = Math.PI;
                } else if (velocityAngle == Math.PI) {
                    velocityAngle = 0;
                } else {
                    velocityAngle = 2.0 * Math.PI - velocityAngle;
                }
            } else if (checkCollisionsPaddleLeft()) {
                bounceOffPaddle(paddleLeft, LEFT);
            } else if (checkCollisionsPaddleRight()) {
                bounceOffPaddle(paddleRight, RIGHT);
            }
            xCenter = nextXCenter();
            yCenter = nextYCenter();
        }
    }

    public boolean checkCollisionsTopLimit() {
        double nextYTopEdge = yCenter - BALL_RADIUS + Math.sin(velocityAngle) * velocityModule;
        return nextYTopEdge <= topLimit;
    }

    public boolean checkCollisionsBottomLimit() {
        double nextYBottomEdge = yCenter + BALL_RADIUS + Math.sin(velocityAngle) * velocityModule;
        return nextYBottomEdge >= bottomLimit;
    }

    public boolean checkCollisionsLeftLimit() {
        double nextXLeftEdge = xCenter - BALL_RADIUS + Math.cos(velocityAngle) * velocityModule;
        return nextXLeftEdge <= leftLimit;
    }

    public boolean checkCollisionsRightLimit() {
        double nextXRightEdge = xCenter + BALL_RADIUS + Math.cos(velocityAngle) * velocityModule;
        return nextXRightEdge >= rightLimit;
    }

    public boolean checkCollisionsPaddleLeft() {
        return (checkCollisionsHorizontalSide(paddleLeft, LEFT) || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight() {
        return (checkCollisionsHorizontalSide(paddleRight, RIGHT) || checkCollisionsPaddleRightVerticalSide());
    }

    private double nextXCenter() {
        return xCenter + Math.cos(velocityAngle) * velocityModule;
    }

    private double nextYCenter() {
        return yCenter + Math.sin(velocityAngle) * velocityModule;
    }

    public boolean checkCollisionsPaddleLeftVerticalSide() {
        boolean xCenterInsideCollisionZone = paddleLeft.getRightEdgePosition() <= nextXCenter() && nextXCenter() <= paddleLeft.getRightEdgePosition() + BALL_RADIUS;
        boolean yCenterInsideCollisionZone = paddleLeft.getyPosition() <= nextYCenter() && nextYCenter() <= paddleLeft.getBottomEdgePosition();

        return (xCenterInsideCollisionZone && yCenterInsideCollisionZone);
    }

    public boolean checkCollisionsPaddleRightVerticalSide() {
        boolean xCenterInsideCollisionZone = paddleRight.getxPosition() - BALL_RADIUS <= nextXCenter() && nextXCenter() <= paddleRight.getxPosition();
        boolean yCenterInsideCollisionZone = paddleRight.getyPosition() <= nextYCenter() && nextYCenter() <= paddleRight.getBottomEdgePosition();

        return (xCenterInsideCollisionZone && yCenterInsideCollisionZone);
    }

    public boolean checkCollisionsHorizontalSide(Paddle paddle, boolean side) {
        boolean xCenterInsideCollisionZones;

        if (side) { // right
            xCenterInsideCollisionZones = paddle.getxPosition() - BALL_RADIUS <= nextXCenter()
                    && nextXCenter() <= paddle.getRightEdgePosition();
        } else { // left
            xCenterInsideCollisionZones = paddle.getxPosition() <= nextXCenter()
                    && nextXCenter() <= paddle.getRightEdgePosition() + BALL_RADIUS;
        }
        boolean yCenterInsideTopCollisionZone = paddle.getyPosition() - BALL_RADIUS <= nextYCenter() && nextYCenter() <= paddle.getyPosition();
        boolean yCenterInsideBottomCollisionZone = paddle.getBottomEdgePosition() <= nextYCenter() && nextYCenter() <= paddle.getBottomEdgePosition() + BALL_RADIUS;

        return (xCenterInsideCollisionZones && (yCenterInsideTopCollisionZone || yCenterInsideBottomCollisionZone));
    }

    // Computes the next angle after the ball bounces on the paddle, depending on the relative positions.
    private void bounceOffPaddle(Paddle paddle, boolean side) {
        double yPositionPaddleCenter = paddle.getyPosition() + paddle.getPADDLE_HEIGHT() / 2.0;
        double yRelative = (yPositionPaddleCenter - nextYCenter()) / (paddle.getPADDLE_HEIGHT() / 2.0) / 2.0 + 0.5;
        yRelative = Math.min(1, yRelative);
        yRelative = Math.max(0, yRelative);

        if (side) { // right
            velocityAngle = Math.PI * (BOUNCE_RANGE * yRelative + (1 - BOUNCE_RANGE) / 2.0 + 1 / 2.0);
        } else { // left
            velocityAngle = Math.PI * (2 - ((BOUNCE_RANGE * yRelative + (1 - BOUNCE_RANGE) / 2.0 + 1 / 2.0 + 1) % 2));
        }
    }

    private void goalScored(Player player, boolean side) {
        player.increaseScore();
        if (side) { // right
            reset(0); // angle 0
        } else { // left
            reset(1); // angle PI
        }
    }

    public void reset(int direction) {
        xCenter = xPositionStart;
        yCenter = yPositionStart;
        velocityAngle = generateStartingAngle(direction);
    }
}
