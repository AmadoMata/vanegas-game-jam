package com.ineriam.games.vanegas.gamejam.actors;

import com.badlogic.gdx.math.MathUtils;
import com.ineriam.games.vanegas.gamejam.data.Const;

import java.util.ArrayList;
import java.util.Iterator;

public class RainSystem {
    public enum State {
        NORMAL, RAINING, END
    }

    public State state = State.NORMAL;

    public float stateTime = 0;
    public float normalTime = 0;
    public float maxRainDrops = 0;
    public float maxRainTime = 0;
    public int countRainDrops = 0;

    private Iterator rainIterator;
    private Rain rain;
    public ArrayList<Rain> rainArrayList = new ArrayList<Rain>();

    public RainSystem () {
        normalTime = MathUtils.random(Const.RAIN_NORMAL_TIME_MIN, Const.RAIN_NORMAL_TIME_MAX);

    }

    public void update (float delta) {
        switch (state) {
            case NORMAL:
                stateTime += delta;

                if (stateTime > normalTime) {
                    stateTime = 0;
                    maxRainDrops = MathUtils.random(Const.RAIN_DROPS_MIN, Const.RAIN_DROPS_MAX);
                    maxRainTime = MathUtils.random(Const.RAIN_TIME_MIN, Const.RAIN_TIME_MAX);
                    state = State.RAINING;
                }
                break;

            case RAINING:
                stateTime += delta;

                if (countRainDrops < maxRainDrops) {
                    spawnRain();
                }

                rainIterator = rainArrayList.iterator();
                while (rainIterator.hasNext()) {
                    rain = (Rain) rainIterator.next();
                    if (rain.state == Rain.State.END) {
                        rainIterator.remove();
                        countRainDrops--;
                    } else {
                        rain.update(delta);
                    }
                }

                if (stateTime > maxRainTime) {
                    stateTime = 0;
                    state = State.END;
                }

                break;
            case END:

                if (countRainDrops <= 0) {
                    stateTime = 0;
                    normalTime = MathUtils.random(Const.RAIN_NORMAL_TIME_MIN, Const.RAIN_NORMAL_TIME_MAX);
                    state = State.NORMAL;
                }

                rainIterator = rainArrayList.iterator();
                while (rainIterator.hasNext()) {
                    rain = (Rain) rainIterator.next();
                    if (rain.state == Rain.State.END) {
                        rainIterator.remove();
                        countRainDrops--;
                    } else {
                        rain.update(delta);
                    }
                }

                break;

        }
    }

    private void spawnRain () {
        rainArrayList.add(new Rain());
        countRainDrops++;
    }
}
