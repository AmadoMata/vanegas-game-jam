package com.ineriam.games.vanegas.gamejam.actors;

public class Tile {
    public int x;
    public int y;
    public int type;
    public boolean solid;

    public Tile() {
    }

    public Tile(int x, int y, int type, boolean solid) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.solid = solid;
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

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
