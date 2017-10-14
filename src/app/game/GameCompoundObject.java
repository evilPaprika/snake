package app.game;

import java.util.LinkedList;

public interface GameCompoundObject {
    LinkedList<? extends GameObject> getChildren();
    void actionWhenColided(GameObject g);
}
