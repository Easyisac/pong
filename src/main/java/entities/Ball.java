package entities;

import panels.GamePanel;
import java.util.Random;

public class Ball {

    private final Paddle paddleLeft;
    private final Paddle paddleRight;
    private final Player playerLeft;
    private final Player playerRight;

    private final Random random = new Random();

    public static final int BALL_RADIUS = 10;
    public static final boolean LEFT = false;
    public static final boolean RIGHT = true;
    public static final double STARTING_RANGE = 5 / 6.0;
    public static final double BOUNCE_RANGE = 2 / 3.0;

    private final int xPositionStart = (GamePanel.GAME_COURT_RIGHT_LIMIT + GamePanel.GAME_COURT_LEFT_LIMIT) / 2;
    private final int yPositionStart = (GamePanel.GAME_COURT_TOP_LIMIT + GamePanel.GAME_COURT_BOTTOM_LIMIT) / 2;
    private double xCenter;
    private double yCenter;
    private double velocityModule;
    private double velocityAngle;

    public Ball(Paddle paddleLeft, Paddle paddleRight, Player playerLeft, Player playerRight, int velocityModule) {
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

    // Generates a random angle for the ball velocity, centered in 0 or PI, with value within STARTING_RANGE.
    public double generateStartingAngle(int direction) {
        return ((STARTING_RANGE * random.nextDouble() + (1 - STARTING_RANGE) / 2.0 + 1 / 2.0 + direction) % 2) * Math.PI;
    }

    public double xVelocityProjection() {
        return Math.cos(velocityAngle) * velocityModule;
    }

    public double yVelocityProjection() {
        return Math.sin(velocityAngle) * velocityModule;
    }

    private double nextXCenter() {
        return xCenter + xVelocityProjection();
    }

    private double nextYCenter() {
        return yCenter + yVelocityProjection();
    }

    // Checks for goals and collisions and moves the ball to the next position.
    public void move() {
        if (nextXCenter() - BALL_RADIUS <= GamePanel.GAME_COURT_LEFT_LIMIT) {
            goalScored(playerRight, LEFT);
        } else if (nextXCenter() + BALL_RADIUS >= GamePanel.GAME_COURT_RIGHT_LIMIT) {
            goalScored(playerLeft, RIGHT);
        } else {
            if (nextYCenter() - BALL_RADIUS <= GamePanel.GAME_COURT_TOP_LIMIT ||
                    nextYCenter() + BALL_RADIUS >= GamePanel.GAME_COURT_BOTTOM_LIMIT) {
                velocityAngle = 2.0 * Math.PI - velocityAngle;
            } else if (checkCollisionsHorizontalSide(paddleLeft, LEFT) || checkCollisionsPaddleLeftVerticalSide()) {
                bounceOffPaddle(paddleLeft, LEFT);
            } else if (checkCollisionsHorizontalSide(paddleRight, RIGHT) || checkCollisionsPaddleRightVerticalSide()) {
                bounceOffPaddle(paddleRight, RIGHT);
            }
            xCenter = nextXCenter();
            yCenter = nextYCenter();
        }
    }

    private void goalScored(Player player, boolean sideThatConcededGoal) {
        player.increaseScore();
        if (sideThatConcededGoal) { // right
            reset(0); // towards left
        } else { // left
            reset(1); // towards right
        }
    }

    public void reset(int direction) {
        xCenter = xPositionStart;
        yCenter = yPositionStart;
        velocityAngle = generateStartingAngle(direction);
    }

    // Computes the next angle after the ball bounces on the paddle, depending on the relative positions.
    private void bounceOffPaddle(Paddle paddle, boolean side) {
        double yPositionPaddleCenter = paddle.getyPosition() + Paddle.PADDLE_HEIGHT / 2.0;
        double yRelative = (yPositionPaddleCenter - nextYCenter()) / (Paddle.PADDLE_HEIGHT / 2.0) / 2.0 + 0.5;
        yRelative = Math.min(1, yRelative);
        yRelative = Math.max(0, yRelative);

        if (side) { // right
            velocityAngle = Math.PI * (BOUNCE_RANGE * yRelative + (1 - BOUNCE_RANGE) / 2.0 + 1 / 2.0);
        } else { // left
            velocityAngle = Math.PI * (2 - ((BOUNCE_RANGE * yRelative + (1 - BOUNCE_RANGE) / 2.0 + 1 / 2.0 + 1) % 2));
        }
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
}
