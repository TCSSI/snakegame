package com.johnnysoma.snake;

import java.util.Random;

public class Food {
    private int foodLocate;

    public int getFoodLocate() {
        return foodLocate;
    }

    public void setFoodLocate(int foodLocate) {
        this.foodLocate = foodLocate;
    }

    public Food() {
    }

    public Food(int foodLocate) {
        this.foodLocate = foodLocate;
    }

    public void ate(Map map) {
        Random random = new Random();
        int max = map.getX() * map.getY();
//        int size = nonePlace.noneList.size();
        int newFood;
        while (true) {
            newFood = random.nextInt(max);
/*                for (int i = 0; i < size; i++) {
                }*/
            if (map.mapList.get(newFood) == 0 && newFood != foodLocate) {
                foodLocate = newFood;
                break;
            }
        }
    }
}
