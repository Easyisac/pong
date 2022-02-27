package entities;

import java.awt.event.KeyEvent;

public class PaddleMover {

    private final Paddle paddleLeft;
    private final Paddle paddleRight;

    private boolean paddleLeftUpFlag;
    private boolean paddleLeftDownFlag;
    private boolean paddleRightUpFlag;
    private boolean paddleRightDownFlag;

    private double paddleLeftSpeed = 5;
    private double paddleRightSpeed = 5;

    public PaddleMover(Paddle paddleLeft, Paddle paddleRight) {
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
    }

    public double getPaddleLeftSpeed() {
        return paddleLeftSpeed;
    }

    public void setPaddleLeftSpeed(double paddleLeftSpeed) {
        this.paddleLeftSpeed = paddleLeftSpeed;
    }

    public double getPaddleRightSpeed() {
        return paddleRightSpeed;
    }

    public void setPaddleRightSpeed(double paddleRightSpeed) {
        this.paddleRightSpeed = paddleRightSpeed;
    }

    private void setPaddleVelocity(Paddle paddle) {
        boolean pressedUp;
        boolean pressedDown;
        double paddleSpeed;

        if (paddle.getPlayer().getId() == 0) {
            pressedUp = paddleLeftUpFlag;
            pressedDown = paddleLeftDownFlag;
            paddleSpeed = paddleLeftSpeed;
        } else {
            pressedUp = paddleRightUpFlag;
            pressedDown = paddleRightDownFlag;
            paddleSpeed = paddleRightSpeed;
        }
        if (!pressedUp && !pressedDown) {
            paddle.setSpeed(0);
        } else if (pressedUp) {
            paddle.setSpeed(-paddleSpeed);
        } else {
            paddle.setSpeed(paddleSpeed);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                paddleLeftUpFlag = true;
                paddleLeftDownFlag = false;
                setPaddleVelocity(paddleLeft);
            }
            case KeyEvent.VK_S -> {
                paddleLeftDownFlag = true;
                paddleLeftUpFlag = false;
                setPaddleVelocity(paddleLeft);
            }
            case KeyEvent.VK_UP -> {
                paddleRightUpFlag = true;
                paddleRightDownFlag = false;
                setPaddleVelocity(paddleRight);
            }
            case KeyEvent.VK_DOWN -> {
                paddleRightDownFlag = true;
                paddleRightUpFlag = false;
                setPaddleVelocity(paddleRight);
            }
            default -> {
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                paddleLeftUpFlag = false;
                setPaddleVelocity(paddleLeft);
            }
            case KeyEvent.VK_S -> {
                paddleLeftDownFlag = false;
                setPaddleVelocity(paddleLeft);
            }
            case KeyEvent.VK_UP -> {
                paddleRightUpFlag = false;
                setPaddleVelocity(paddleRight);
            }
            case KeyEvent.VK_DOWN -> {
                paddleRightDownFlag = false;
                setPaddleVelocity(paddleRight);
            }
            default -> {
            }
        }
    }
}