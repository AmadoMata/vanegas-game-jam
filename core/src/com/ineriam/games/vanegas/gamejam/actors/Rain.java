package com.ineriam.games.vanegas.gamejam.actors;

import com.badlogic.gdx.math.MathUtils;
import com.ineriam.games.vanegas.gamejam.data.Const;

public class Rain {
    public float x;
    public float y;

    public enum State {
        START, MOVE, END
    }

    public State state = State.START;

    public Rain () {

    }

    public void update (float delta) {
        switch (state) {
            case START:
                x = MathUtils.random(0, 15.0f);
                y = Const.MAP_ROWS;
                state = State.MOVE;
                break;
            case MOVE:
                y -= Const.RAIN_SPEED;
                if (y < 0) {
                    state = State.END;
                }
                break;
            case END:

                break;
        }
    }


}

