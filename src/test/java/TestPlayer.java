import entities.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    private final Player playerLeft = new Player("player0", 0);

    @ParameterizedTest
    @ValueSource(ints={2, 3, 4})
    public void player_score_increases_by_one_on_every_call(int points) {
        int startScore = playerLeft.getScore();
        for(int i = 0; i < points; i++) {
            playerLeft.increaseScore();
        }
        assertEquals(playerLeft.getScore(), startScore+points);
    }
}
