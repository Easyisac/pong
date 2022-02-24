import java.util.Random;

public class Ball {

    private final int topLimit = GameProperties.GAME_COURT_TOP_LIMIT;
    private final int bottomLimit = GameProperties.GAME_COURT_BOTTOM_LIMIT;
    private final int leftLimit = GameProperties.GAME_COURT_LEFT_LIMIT;
    private final int rightLimit = GameProperties.GAME_COURT_RIGHT_LIMIT;

    private final Paddle paddleLeft;
    private final Paddle paddleRight;
    private final Player playerLeft;
    private final Player playerRight;

    private final Random random = new Random();

    private final int BALL_RADIUS = 10;
    private final boolean LEFT = false;
    private final boolean RIGHT = true;
    private final int xPositionStart;
    private final int yPositionStart;
    private double xPosition;
    private double yPosition;
    private double velocityModule;
    private double velocityAngle;

    public Ball(Paddle paddleLeft, Paddle paddleRight, Player playerLeft, Player playerRight, int velocityModule) {
        xPositionStart = (rightLimit + leftLimit) / 2;
        yPositionStart = (topLimit + bottomLimit) / 2;
        xPosition = xPositionStart;
        yPosition = yPositionStart;
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.velocityModule = velocityModule;
        velocityAngle = generateStartingAngle(random.nextInt(1));
    }

    public int getxPositionStart() { return xPositionStart; }

    public int getyPositionStart() { return yPositionStart; }

    public int getBALL_RADIUS() { return BALL_RADIUS; }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
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

    public void setxPosition(double xPosition) { this.xPosition = xPosition; }

    public void setyPosition(double yPosition) { this.yPosition = yPosition; }

    public double generateStartingAngle(int direction){
        double range = 5/6.0;
        double angle = (range * random.nextDouble() + (1-range)/2.0 + 1/2.0 + direction) % 2;
        return angle * Math.PI;
    }

    public void move() {
        if (checkCollisionsLeftLimit()){
            goalScored(playerRight, LEFT);
        }
        else if (checkCollisionsRightLimit()){
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

            yPosition += Math.sin(velocityAngle) * velocityModule;
            xPosition += Math.cos(velocityAngle) * velocityModule;
        }
    }

    public boolean checkCollisionsTopLimit() {
        double nextYBallTopEdge = yPosition - BALL_RADIUS + Math.sin(velocityAngle) * velocityModule;
        return nextYBallTopEdge <= topLimit;
    }

    public boolean checkCollisionsBottomLimit() {
        double nextYBallBottomEdge = yPosition + BALL_RADIUS + Math.sin(velocityAngle) * velocityModule;
        return nextYBallBottomEdge >= bottomLimit;
    }

    public boolean checkCollisionsLeftLimit() {
        double nextXBallLeftEdge = xPosition - BALL_RADIUS + Math.cos(velocityAngle) * velocityModule;
        return nextXBallLeftEdge <= leftLimit;
    }

    public boolean checkCollisionsRightLimit() {
        double nextXBallRightEdge = xPosition + BALL_RADIUS + Math.cos(velocityAngle) * velocityModule;
        return nextXBallRightEdge >= rightLimit;
    }

    public boolean checkCollisionsPaddleLeft(){
        return (checkCollisionsHorizontalSide(paddleLeft, LEFT) || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight(){
        return (checkCollisionsHorizontalSide(paddleRight, RIGHT) || checkCollisionsPaddleRightVerticalSide());
    }

    private double nextXBallCenter() {
        return xPosition + Math.cos(velocityAngle) * velocityModule;
    }

    private double nextYBallCenter() {
        return yPosition + Math.sin(velocityAngle) * velocityModule;
    }

    public boolean checkCollisionsPaddleLeftVerticalSide(){
        // feature envy? consider making a method in paddle that outputs these three values
        double collisionZoneLeftLimit  = paddleLeft.getxPosition() + paddleLeft.getPADDLE_WIDTH();
        double collisionZoneRightLimit = paddleLeft.getxPosition() + paddleLeft.getPADDLE_WIDTH() + BALL_RADIUS;
        double collisionZoneBottomLimit = paddleLeft.getyPosition() + paddleLeft.getPADDLE_HEIGHT();

        boolean xBallCenterInsideCollisionZone =  collisionZoneLeftLimit <= nextXBallCenter() && nextXBallCenter() <= collisionZoneRightLimit;
        boolean yBallCenterInsideCollisionZone = paddleLeft.getyPosition() <= nextYBallCenter() && nextYBallCenter() <= collisionZoneBottomLimit;

        return (xBallCenterInsideCollisionZone && yBallCenterInsideCollisionZone);
    }

    public boolean checkCollisionsPaddleRightVerticalSide(){
        double collisionZoneLeftLimit  = paddleRight.getxPosition() - BALL_RADIUS;
        double collisionZoneBottomLimit = paddleRight.getyPosition() + paddleRight.getPADDLE_HEIGHT();

        boolean xBallCenterInsideCollisionZone = collisionZoneLeftLimit <= nextXBallCenter() && nextXBallCenter() <= paddleRight.getxPosition();
        boolean yBallCenterInsideCollisionZone = paddleRight.getyPosition() <= nextYBallCenter() && nextYBallCenter() <= collisionZoneBottomLimit;

        return (xBallCenterInsideCollisionZone && yBallCenterInsideCollisionZone);
    }

    public boolean checkCollisionsHorizontalSide(Paddle paddle, boolean side){

        boolean xBallCenterInsideCollisionZones;

        if (side) { // right
            xBallCenterInsideCollisionZones = paddle.getxPosition() - BALL_RADIUS <= nextXBallCenter()
                                              && nextXBallCenter() <= paddle.getxPosition() + paddle.getPADDLE_WIDTH();
        } else { // left
            xBallCenterInsideCollisionZones = paddle.getxPosition() <= nextXBallCenter()
                                              && nextXBallCenter() <= paddle.getxPosition() + paddle.getPADDLE_WIDTH() + BALL_RADIUS;
        }

        boolean yBallCenterInsideTopCollisionZone    = paddle.getyPosition() - BALL_RADIUS <= nextYBallCenter() && nextYBallCenter() <= paddle.getyPosition();
        boolean yBallCenterInsideBottomCollisionZone = paddle.getyPosition() + paddle.getPADDLE_HEIGHT() <= nextYBallCenter() && nextYBallCenter() <= paddle.getyPosition() + paddle.getPADDLE_HEIGHT()+ BALL_RADIUS;

        return (xBallCenterInsideCollisionZones && (yBallCenterInsideTopCollisionZone || yBallCenterInsideBottomCollisionZone));
    }

    private void bounceOffPaddle(Paddle paddle, boolean side){
        double range = 2 / 3.0;
        double yPositionPaddleCenter = paddle.getyPosition() + paddle.getPADDLE_HEIGHT() / 2.0;
        double yRelative = (yPositionPaddleCenter - nextYBallCenter()) / (paddle.getPADDLE_HEIGHT() / 2.0);
        yRelative = yRelative /2.0 + 0.5;
        yRelative = Math.min(1, yRelative);
        yRelative = Math.max(0, yRelative);

        if (side){ // right
            velocityAngle = (range * yRelative + (1- range)/2.0 + 1/2.0) * Math.PI;
        } else { // left
            velocityAngle = Math.PI * (2 - ((range * yRelative + (1- range)/2.0 + 1/2.0 + 1) % 2));
        }
    }

    // single responsibility principle?

    private void goalScored(Player player, boolean side){
        player.increaseScore();
        if (side) { // right
            reset(0); // angle 0
        } else { //left
            reset(1); // angle PI
        }
    }

    public void reset(int direction) {
        xPosition = xPositionStart;
        yPosition = yPositionStart;
        velocityAngle = generateStartingAngle(direction);
    }

}
