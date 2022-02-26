import entities.Ball;
import entities.Paddle;
import entities.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import panels.GamePanel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBall {

    private final Player playerLeft = new Player("Player0", 0);
    private final Player playerRight = new Player("Player1", 1);

    private final Paddle paddleLeft = new Paddle(playerLeft);
    private final Paddle paddleRight = new Paddle(playerRight);
    private final Ball ball = new Ball(paddleLeft, paddleRight, playerLeft, playerRight, 5);
    private final int xPositionStart = ball.getxPositionStart();
    private final int yPositionStart = ball.getyPositionStart();
    private final double eps = 1E-5;

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "10,20", "25, 30"})
    public void ball_shifts_position_after_move_command(double velAngle, double velModule) {
        ball.setVelocityAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();

        assertEquals(xPositionStart + Math.cos(velAngle) * velModule, ball.getxCenter(), eps);
        assertEquals(yPositionStart + Math.sin(velAngle) * velModule, ball.getyCenter(), eps);
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_in_starting_position_after_reset(double velAngle, double velModule) {
        ball.setVelocityAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();
        ball.reset(1);

        assertEquals(xPositionStart, ball.getxCenter());
        assertEquals(yPositionStart, ball.getyCenter());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_collides_on_top_boundary_when_moving_outside_top_limit(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle( 3/2.0 * Math.PI);
        ball.setyCenter(GamePanel.GAME_COURT_TOP_LIMIT + 15);
        boolean checkCollisionsTopLimit = ball.getyCenter() - ball.getBALL_RADIUS() + ball.yVelocityProjection() <= GamePanel.GAME_COURT_TOP_LIMIT;
        assertTrue(checkCollisionsTopLimit);
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_collides_on_bottom_boundary_when_moving_outside_bottom_limit(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI/2);
        ball.setyCenter(GamePanel.GAME_COURT_BOTTOM_LIMIT - 15);
        boolean checkCollisionsBottomLimit = ball.getyCenter() + ball.getBALL_RADIUS() + ball.yVelocityProjection() >= GamePanel.GAME_COURT_BOTTOM_LIMIT;
        assertTrue(checkCollisionsBottomLimit);
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_collides_on_left_boundary_when_moving_outside_left_limit(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI);
        ball.setxCenter(GamePanel.GAME_COURT_LEFT_LIMIT + 15);
        boolean checkCollisionsLeftLimit = ball.getxCenter() - ball.getBALL_RADIUS() + ball.xVelocityProjection() <= GamePanel.GAME_COURT_LEFT_LIMIT;
        assertTrue(checkCollisionsLeftLimit);
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_collides_on_right_boundary_when_moving_outside_right_limit(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(0);
        ball.setxCenter(GamePanel.GAME_COURT_RIGHT_LIMIT - 15);
        boolean checkCollisionsRightLimit = ball.getxCenter() + ball.getBALL_RADIUS() + ball.xVelocityProjection() >= GamePanel.GAME_COURT_RIGHT_LIMIT;
        assertTrue(checkCollisionsRightLimit);
    }

    @ParameterizedTest
    @CsvSource({"0.6, 2", "0.7, 4", "1, 6", "1.1, 7", "1.2, 8"})
    public void ball_collides_with_left_paddle_when_ball_is_inside_collision_zones(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyCenter(paddleLeft.getyPosition() + paddleLeft.getPADDLE_HEIGHT()/2.0);
        ball.setxCenter(paddleLeft.getxPosition() + paddleLeft.getPADDLE_WIDTH() + 10);

        assertTrue(ball.checkCollisionsHorizontalSide(paddleLeft, Ball.LEFT) || ball.checkCollisionsPaddleLeftVerticalSide());
    }

    @ParameterizedTest
    @CsvSource({"0.1, 2", "0.3, 4", "0.4, 6", "1.6, 7", "1.9, 8"})
    public void ball_collides_with_right_paddle_when_ball_is_inside_collision_zones(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyCenter(paddleRight.getyPosition() + paddleRight.getPADDLE_HEIGHT()/2.0);
        ball.setxCenter(paddleRight.getxPosition() - 10);

        assertTrue(ball.checkCollisionsHorizontalSide(paddleRight, Ball.RIGHT) || ball.checkCollisionsPaddleRightVerticalSide());
    }

    @ParameterizedTest
    @CsvSource({"1.1, 1", "1.2, 2", "1.3, 3", "1.5, 4", "1.8, 5"})
    public void ball_bounces_when_hitting_top_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyCenter(GamePanel.GAME_COURT_TOP_LIMIT +10);
        ball.setxCenter((GamePanel.GAME_COURT_RIGHT_LIMIT + GamePanel.GAME_COURT_LEFT_LIMIT)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelocityAngle();
        double final_positionX = ball.getxCenter() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getyCenter() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getxCenter(), eps);
        assertEquals(final_positionY, ball.getyCenter(), eps);
    }


    @ParameterizedTest
    @CsvSource({"0.1, 1", "0.4, 2", "0.5, 3", "0.7, 4", "0.9, 5"})
    public void ball_bounces_when_hitting_bottom_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyCenter(GamePanel.GAME_COURT_TOP_LIMIT -10);
        ball.setxCenter((GamePanel.GAME_COURT_RIGHT_LIMIT + GamePanel.GAME_COURT_LEFT_LIMIT)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelocityAngle();
        double final_positionX = ball.getxCenter() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getyCenter() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getxCenter(), eps);
        assertEquals(final_positionY, ball.getyCenter(), eps);
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 6})
    public void ball_resets_position_after_goal_scored_left(double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI);
        ball.setyCenter(GamePanel.GAME_COURT_TOP_LIMIT +10);
        ball.setxCenter(GamePanel.GAME_COURT_LEFT_LIMIT +10);

        ball.move();

        assertEquals(ball.getxPositionStart(), ball.getxCenter(), eps);
        assertEquals(ball.getyPositionStart(), ball.getyCenter(), eps);
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 6})
    public void ball_resets_position_after_goal_scored_right(double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(0);
        ball.setyCenter(GamePanel.GAME_COURT_TOP_LIMIT +10);
        ball.setxCenter(GamePanel.GAME_COURT_RIGHT_LIMIT -10);

        ball.move();

        assertEquals(ball.getxPositionStart(), ball.getxCenter(), eps);
        assertEquals(ball.getyPositionStart(), ball.getyCenter(), eps);
    }

}
