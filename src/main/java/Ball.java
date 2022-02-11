import java.util.Random;

public class Ball {
    
    private double x;
    private double y;
    private final int xStart;
    private final int yStart;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;

    private double velModule;
    private double velAngle;

    private final Paddle pLeft;
    private final Paddle pRight;
    private final Player playerLeft;
    private final Player playerRight;

    private final int BALL_RADIUS = 10;
    private final int bOff = BALL_RADIUS;

    private final Random random = new Random();

    public Ball(int topLim, int botLim, int leftLim, int rightLim, Paddle pLeft, Paddle pRight,
                Player playerLeft, Player playerRight) {
        xStart = (rightLim + leftLim) / 2;
        yStart = (topLim + botLim) / 2;
        x = xStart;
        y = yStart;
        this.topLim = topLim;
        this.botLim = botLim;
        this.leftLim = leftLim;
        this.rightLim = rightLim;
        this.pLeft = pLeft;
        this.pRight = pRight;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        velAngle = generateStartingAngle(random.nextInt(1));
        velModule = 3;
    }

    public double generateStartingAngle(int direction){
        double range = 5/6.0;
        double angle = (range * random.nextDouble() + (1-range)/2.0 + 1/2.0 + direction) % 2;
        return angle * Math.PI;
    }

    public void move() {
        if (checkCollisionsLeft()){
            goalScoredLeft();
            return ;
        }
        else if (checkCollisionsRight()){
            goalScoredRight();
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
            bounceOffPaddleLeft();
        }
        else if (checkCollisionsPaddleRight()){
            bounceOffPaddleRight();
        }
        y += Math.sin(velAngle) * velModule;
        x += Math.cos(velAngle) * velModule;
    }

    private void bounceOffPaddleRight() {
        double yImpact = y + Math.sin(velAngle) * velModule;
        double yMid = pRight.getY() + pRight.getPADDLE_HEIGHT()/2.0;
        double yRel = (yMid - yImpact)/(pRight.getPADDLE_HEIGHT()/2.0);
        yRel = yRel / 2.0 + 0.5;
        yRel = Math.min(1, yRel);
        yRel = Math.max(0, yRel);
        double range = 2/3.0;
        velAngle = (range * yRel + (1-range)/2.0 + 1/2.0) * Math.PI;
        velModule = 5;
    }

    private void bounceOffPaddleLeft() {
        double yImpact = y + Math.sin(velAngle) * velModule;
        double yMid = pLeft.getY() + pLeft.getPADDLE_HEIGHT()/2.0;
        double yRel = (yMid - yImpact)/(pLeft.getPADDLE_HEIGHT()/2.0);
        yRel = yRel/2.0 + 0.5;
        yRel = Math.min(1, yRel);
        yRel = Math.max(0, yRel);
        double range = 2/3.0;
        velAngle = Math.PI * (2 - ((range * yRel + (1-range)/2.0 + 1/2.0 + 1) % 2));
        velModule = 5;
    }

    private void goalScoredRight() {
        playerLeft.incScore();
        reset(0);
    }

    private void goalScoredLeft() {
        playerRight.incScore();
        reset(1);
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
        velModule = 3;
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
        return (checkCollisionsPaddleLeftHorizontalSide() || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight(){
        return (checkCollisionsPaddleRightHorizontalSide() || checkCollisionsPaddleRightVerticalSide());
    }

    public boolean checkCollisionsPaddleLeftVerticalSide(){
        boolean condX = pLeft.getX() + pLeft.getPADDLE_WIDTH() <= (x + Math.cos(velAngle) * velModule) &&
                (x + Math.cos(velAngle) * velModule) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condY = pLeft.getY() <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleLeftHorizontalSide(){
        boolean condX = pLeft.getX() <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condYTop = pLeft.getY() - bOff <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pLeft.getY();
        boolean condYBot = pLeft.getY() + pLeft.getPADDLE_HEIGHT() <= (y + Math.sin(velAngle) * velModule) &&
                (y + Math.sin(velAngle) * velModule) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }

    public boolean checkCollisionsPaddleRightVerticalSide(){
        boolean condX = pRight.getX() - bOff <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pRight.getX();
        boolean condY = pRight.getY() <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pRight.getY() + pRight.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleRightHorizontalSide(){
        boolean condX = pRight.getX() - bOff <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pRight.getX() + pRight.getPADDLE_WIDTH();
        boolean condYTop = pRight.getY() - bOff <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pRight.getY();
        boolean condYBot = pRight.getY() + pRight.getPADDLE_HEIGHT() <= (y + Math.sin(velAngle) * velModule) &&
                (y + Math.sin(velAngle) * velModule) <= pRight.getY() + pRight.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }



}
