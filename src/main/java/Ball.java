import java.util.Random;

public class Ball {
    
    private double x;
    private double y;
    private final int xStart;
    private final int yStart;
    private final int topLim = GameProperties.TOP_LIM;
    private final int botLim = GameProperties.BOT_LIM;
    private final int leftLim = GameProperties.LEFT_LIM;
    private final int rightLim = GameProperties.RIGHT_LIM;

    private double velModule;
    private double velAngle;

    private final Paddle pLeft;
    private final Paddle pRight;
    private final Player playerLeft;
    private final Player playerRight;

    private final int BALL_RADIUS = 10;
    private final int bOff = BALL_RADIUS;

    private final boolean LEFT = false;
    private final boolean RIGHT = true;

    private final Random random = new Random();

    public Ball(Paddle pLeft, Paddle pRight, Player playerLeft, Player playerRight, int velModule) {
        xStart = (rightLim + leftLim) / 2;
        yStart = (topLim + botLim) / 2;
        x = xStart;
        y = yStart;
        this.pLeft = pLeft;
        this.pRight = pRight;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.velModule = velModule;
        velAngle = generateStartingAngle(random.nextInt(1));
    }

    public double generateStartingAngle(int direction){
        double range = 5/6.0;
        double angle = (range * random.nextDouble() + (1-range)/2.0 + 1/2.0 + direction) % 2;
        return angle * Math.PI;
    }

    public void move() {
        if (checkCollisionsLeft()){
            goalScored(playerRight, LEFT);
            return ;
        }
        else if (checkCollisionsRight()){
            goalScored(playerLeft, RIGHT);
            return ;
        }
        else if (checkCollisionsTop() || checkCollisionsBottom()){
            if (velAngle == 0) {
                velAngle = Math.PI;
            }
            else if (velAngle == Math.PI) {
                velAngle = 0;
            }
            else {
                velAngle = 2.0 * Math.PI - velAngle;
            }
        }
        else if (checkCollisionsPaddleLeft()){
            bounceOffPaddle(pLeft, LEFT);
        }
        else if (checkCollisionsPaddleRight()){
            bounceOffPaddle(pRight, RIGHT);
        }
        y += Math.sin(velAngle) * velModule;
        x += Math.cos(velAngle) * velModule;
    }

    private void bounceOffPaddle(Paddle paddle, boolean side){
        double yImpact = y + Math.sin(velAngle) * velModule;
        double yMid = paddle.getyPosition() + paddle.getPADDLE_HEIGHT() / 2.0;
        double yRel = (yMid - yImpact) / (paddle.getPADDLE_HEIGHT() / 2.0);
        yRel = yRel /2.0 + 0.5;
        yRel = Math.min(1, yRel);
        yRel = Math.max(0, yRel);
        double range = 2 / 3.0;
        if (side){
            velAngle = (range * yRel + (1- range)/2.0 + 1/2.0) * Math.PI;
        } else {
            velAngle = Math.PI * (2 - ((range * yRel + (1- range)/2.0 + 1/2.0 + 1) % 2));
        }
    }

    private void goalScored(Player player, boolean side){
        player.incScore();
        if (side) {
            reset(0);
        } else {
            reset(1);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelModule() {
        return velModule;
    }

    public double getVelAngle() {
        return velAngle;
    }

    public void setVelModule(double velModule) {
        this.velModule = velModule;
    }

    public void setVelAngle(double velAngle) {
        this.velAngle = velAngle % (2 * Math.PI);
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public int getxStart() { return xStart; }

    public int getyStart() { return yStart; }

    public int getBALL_RADIUS() { return BALL_RADIUS; }

    public void reset(int direction) {
        x = xStart;
        y = yStart;
        velAngle = generateStartingAngle(direction);
    }

    public boolean checkCollisionsTop() {
        return (y - BALL_RADIUS + Math.sin(velAngle) * velModule) <= topLim;
    }

    public boolean checkCollisionsBottom() {
        return (y + BALL_RADIUS + Math.sin(velAngle) * velModule) >= botLim;
    }

    public boolean checkCollisionsLeft() {
        return (x - BALL_RADIUS + Math.cos(velAngle) * velModule) <= leftLim;
    }

    public boolean checkCollisionsRight() {
        return (x + BALL_RADIUS + Math.cos(velAngle) * velModule) >= rightLim;
    }

    public boolean checkCollisionsPaddleLeft(){
        return (checkCollisionsHorizontalSide(pLeft, LEFT) || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight(){
        return (checkCollisionsHorizontalSide(pRight, RIGHT) || checkCollisionsPaddleRightVerticalSide());
    }

    public boolean checkCollisionsPaddleLeftVerticalSide(){

        double nextX = x + Math.cos(velAngle) * velModule;
        double nextY = y + Math.sin(velAngle) * velModule;

        boolean condX = pLeft.getxPosition() + pLeft.getPADDLE_WIDTH() <= nextX && nextX <= pLeft.getxPosition() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condY = pLeft.getyPosition() <= nextY && nextY <= pLeft.getyPosition() + pLeft.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleRightVerticalSide(){

        double nextX = x + Math.cos(velAngle) * velModule;
        double nextY = y + Math.sin(velAngle) * velModule;

        boolean condX = pRight.getxPosition() - bOff <= nextX && nextX <= pRight.getxPosition();
        boolean condY = pRight.getyPosition() <= nextY && nextY <= pRight.getyPosition() + pRight.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsHorizontalSide(Paddle paddle, boolean side){

        boolean condX;
        double nextX = x + Math.cos(velAngle) * velModule;
        double nextY = y + Math.sin(velAngle) * velModule;

        if (side){
            condX = paddle.getxPosition() - bOff <= nextX && nextX <= paddle.getxPosition() + paddle.getPADDLE_WIDTH();
        } else {
            condX = paddle.getxPosition() <= nextX && nextX <= paddle.getxPosition() + paddle.getPADDLE_WIDTH() + bOff;
        }

        boolean condYTop = paddle.getyPosition() - bOff <= nextY && nextY <= paddle.getyPosition();
        boolean condYBot = paddle.getyPosition() + paddle.getPADDLE_HEIGHT() <= nextY && nextY <= paddle.getyPosition() + paddle.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));

    }
}
