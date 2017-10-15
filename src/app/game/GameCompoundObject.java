package app.game;

import java.util.LinkedList;

interface GameCompoundObject {
    LinkedList<? extends GameObject> getParts();
    void actionWhenCollided(GameObject g);
}
