package app.game;

public interface GameObject {
    boolean isDead();
    void collideWith(GameObject g);
}
