package app.game;

import java.util.LinkedList;

public interface GameCompoundObject {
    LinkedList<? extends GameObject> getParts();
    void actionWhenCollided(GameObject g);
}
