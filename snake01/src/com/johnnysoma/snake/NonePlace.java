package com.johnnysoma.snake;

import java.util.ArrayList;

public class NonePlace {
    public ArrayList<Integer> wallList = new ArrayList<>();
    public ArrayList<Integer> noneList = new ArrayList<>();

    public NonePlace() {
    }

    public NonePlace(Snake snake) {
        buildNoneList(snake);
    }

    public void rebuildNoneList(Snake snake){
        clearNoneList();
        buildNoneList(snake);
    }

    public void clearNoneList(){
        int size = noneList.size();
        for (int i = 0; i < size; i++) {
            noneList.remove(0);
        }
    }

    public void clearWallList(){
        int size = wallList.size();
        for (int i = 0; i < size; i++) {
            wallList.remove(0);
        }
    }

    public void buildNoneList(Snake snake){
        for (int i = 0; i < wallList.size(); i++) {
            noneList.add(wallList.get(i));
        }
        for (int i = 0; i < snake.snakeList.size(); i++) {
            noneList.add(snake.snakeList.get(i));
        }
    }
}
