package com.johnnysoma.snake;

import java.util.ArrayList;

public class Snake {

    private int direct = 4;

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public ArrayList<Integer> snakeList = new ArrayList<>();

    public Snake() {
    }

    public Snake(Map map, int snakeLength) {
        int x = map.getX();
        int y = map.getY();
        for (int i = 0; i < snakeLength; i++) {
            snakeList.add(x * (y / 2) + 2 + i);
        }
    }

    public void move(Map map) {
        int mapX = map.getX();
        int mapY = map.getY();
        int snakeHeadX = snakeList.get(snakeList.size() - 1) % mapX;
        int snakeHeadY = snakeList.get(snakeList.size() - 1) / mapX;
        snakeList.remove(0);
        switch (direct) {
            case 1 -> snakeList.add(((snakeHeadY - 1 + mapY) % mapY) * mapX + snakeHeadX);
            case 2 -> snakeList.add(((snakeHeadY + 1) % mapY) * mapX + snakeHeadX);
            case 3 -> snakeList.add((snakeHeadY) * mapX + (snakeHeadX - 1 + mapX) % mapX);
            case 4 -> snakeList.add((snakeHeadY) * mapX + (snakeHeadX + 1) % mapX);
        }
    }

    public boolean eat(Map map, Food food) {
        int mapX = map.getX();
        int mapY = map.getY();
        int foodLocate = food.getFoodLocate();
        int front;
        int snakeHeadX = snakeList.get(snakeList.size() - 1) % mapX;
        int snakeHeadY = snakeList.get(snakeList.size() - 1) / mapX;
        switch (direct) {
            case 1 -> {
                front = ((snakeHeadY - 1 + mapY) % mapY) * mapX + snakeHeadX;
                if (front == food.getFoodLocate()) {
                    snakeList.add(front);
                    return true;
                }
            }
            case 2 -> {
                front = ((snakeHeadY + 1) % mapY) * mapX + snakeHeadX;
                if (front == food.getFoodLocate()) {
                    snakeList.add(front);
                    return true;
                }
            }
            case 3 -> {
                front = (snakeHeadY) * mapX + (snakeHeadX - 1 + mapX) % mapX;
                if (front == food.getFoodLocate()) {
                    snakeList.add(front);
                    return true;
                }
            }
            case 4 -> {
                front = (snakeHeadY) * mapX + (snakeHeadX + 1) % mapX;
                if (front == food.getFoodLocate()) {
                    snakeList.add(front);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDead(Map map, NonePlace nonePlace) {
        int mapX = map.getX();
        int mapY = map.getY();
        int snakeHeadX = snakeList.get(snakeList.size() - 1) % mapX;
        int snakeHeadY = snakeList.get(snakeList.size() - 1) / mapX;
        switch (direct) {
            case 1 -> {
                return nonePlace.noneList.contains(((snakeHeadY - 1 + mapY) % mapY) * mapX + snakeHeadX);
            }
            case 2 -> {
                return nonePlace.noneList.contains(((snakeHeadY + 1) % mapY) * mapX + snakeHeadX);
            }
            case 3 -> {
                return nonePlace.noneList.contains((snakeHeadY) * mapX + (snakeHeadX - 1 + mapX) % mapX);
            }
            case 4 -> {
                return nonePlace.noneList.contains((snakeHeadY) * mapX + (snakeHeadX + 1) % mapX);
            }
        }
        return false;
    }

}
