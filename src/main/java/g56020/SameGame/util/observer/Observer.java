package g56020.SameGame.util.observer;

import g56020.SameGame.model.State;

public interface Observer {

    /**
     * Called whenever the observed object has changed
     */
    void update(Observable observable, State state, Object arg);
}
