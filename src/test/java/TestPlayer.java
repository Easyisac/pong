import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    private final Player p0 = new Player("p0", 0);

    @ParameterizedTest
    @ValueSource(ints={2, 3, 4})
    public void test_player_score(int points) {
        int startScore = p0.getScore();
        for(int i = 0; i < points; i++) {
            p0.increaseScore();
        }
        assertEquals(p0.getScore(), startScore+points);
    }
}
