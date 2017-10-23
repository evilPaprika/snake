package app.game;

import java.util.LinkedList;

interface CompoundObject extends GameObject {
    LinkedList<? extends SimpleObject> getParts();
}
