package com.ineriam.games.vanegas.gamejam.actors;

public class Door {
    public int x;
    public int y;
    public int direction;
    public boolean isOpen = false;

    public Door(int x, int y, int direction, boolean isOpen) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.isOpen = isOpen;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
