public class Player {

    private String name;
    private int score;
    private int id;

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

    public void incScore(){
        score++;
    }


}
