package com.johnnysoma.snake;

import java.util.ArrayList;

public class Map {
    private int x;
    private int y;
    public ArrayList<Integer> mapList = new ArrayList<>();

    public Map() {
    }

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        buildMap();
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

    public void buildMap() {
        for (int i = 0; i < x * y; i++) {
            mapList.add(0);
        }
    }

    public void clearMap() {
        int size = mapList.size();
        for (int i = 0; i < size; i++) {
            mapList.remove(0);
        }
    }

    public void rebuildMap(NonePlace nonePlace, Food food) {
        clearMap();
        buildMap();
        int foodLocate = food.getFoodLocate();
        mapList.remove(foodLocate);
        mapList.add(foodLocate, 4);
        int changeNum;
        for (int i = 0; i < nonePlace.noneList.size(); i++) {
            changeNum = nonePlace.noneList.get(i);
            mapList.remove(changeNum);
            mapList.add(changeNum, 1);
        }
    }

    public void presentMap() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.println(mapList.get(i * x + j));
            }
        }
    }
}
