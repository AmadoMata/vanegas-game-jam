package com.ineriam.games.vanegas.gamejam.actors;


import com.badlogic.gdx.math.MathUtils;
import com.ineriam.games.vanegas.gamejam.data.Const;

public class Actor {
    public int x;
    public int y;
    public int type;
    public int hp;
    public int attack;
    public float stateTime = 0;
    public int level;
    public int visionDistance;
    public int actorId;

    public enum State {
        MONSTER, HEART, HEART_VALUE, MONSTER_TWO, ATTACK, ATTACK_VALUE, MONSTER_END
    }
    public State state = State.MONSTER;

    public Actor() {
    }

    public Actor(int x, int y, int type, int hp, int level, int visionDistance, int actorId) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.hp = hp;
        this.level = level;
        this.visionDistance = visionDistance;
        this.actorId = actorId;

        stateTime = MathUtils.random(0, 1.1f);
    }

    public void updateMonster (float delta) {
        switch (state) {
            case MONSTER:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME) {
                    stateTime = 0;
                    state = State.HEART;
                }
                break;
            case HEART:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_HEART) {
                    stateTime = 0;
                    state = State.HEART_VALUE;
                }
                break;
            case HEART_VALUE:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_HEART_VALUE) {
                    stateTime = 0;
                    state = State.MONSTER_TWO;
                }
                break;
            case MONSTER_TWO:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_TWO) {
                    stateTime = 0;
                    state = State.ATTACK;
                }
                break;
            case ATTACK:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_ATTACK) {
                    stateTime = 0;
                    state = State.ATTACK_VALUE;
                }
                break;
            case ATTACK_VALUE:
                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_ATTACK_VALUE) {
                    stateTime = 0;
                    state = State.MONSTER_END;
                }
                break;
            case MONSTER_END:

                stateTime += delta;
                if (stateTime > Const.STATE_MONSTER_TIME_END) {
                    stateTime = 0;
                    state = State.MONSTER;
                }

                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}

