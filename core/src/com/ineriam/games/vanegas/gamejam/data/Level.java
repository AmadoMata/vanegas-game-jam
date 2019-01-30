package com.ineriam.games.vanegas.gamejam.data;

public class Level {
    public int levelId;
    public int hp;
    public int attack;

    public Level() {
    }

    public Level(int levelId, int hp, int attack) {
        this.levelId = levelId;
        this.hp = hp;
        this.attack = attack;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
