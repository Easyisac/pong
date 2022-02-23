public class Player {

    private final String name;
    private final int id;
    private int score;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(){
        score++;
    }
}
