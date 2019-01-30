package com.ineriam.games.vanegas.gamejam.actors;

import com.badlogic.gdx.math.Rectangle;

public class Health {
    public static int HIDDEN = 1;
    public static int VISIBLE = 2;
    private static float TIME_LIFE = 5f;
    public int state = HIDDEN;
    public float stateTime = 0;

    public Rectangle bounds = new Rectangle();

    public Health () {
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = bounds.height = 1;
    }

    public void hit (float x, float y) {
        if (state != VISIBLE) {
            bounds.x = x;
            bounds.y = y;
            state = VISIBLE;
            stateTime = 0;
        }
    }

    public void update (float deltaTime) {
        if (state == VISIBLE) {
            if (stateTime > TIME_LIFE) {
                state = HIDDEN;
                stateTime = 0;
            }
        }

        stateTime += deltaTime;
    }
}

