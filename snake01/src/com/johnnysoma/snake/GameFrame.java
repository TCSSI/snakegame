package com.johnnysoma.snake;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class GameFrame extends JFrame implements KeyListener, ActionListener {

    public Snake snake;
    public Map map;
    public Food food;
    public NonePlace nonePlace;
    public Timer timer;
    private boolean unLockKey = true;
    private boolean unLockGame = false;
    private int[] timeDelay = {400, 200, 100, 50, 20};
    private int speed = 1;
    private int core = 0;

    JMenuBar jMenuBar = new JMenuBar();
    JMenu jMenu1 = new JMenu("游戏");
    JMenuItem restartItem = new JMenuItem("重新游戏");
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenu jMenu2 = new JMenu("模式");
    JMenuItem noWallItem = new JMenuItem("无墙");
    JMenuItem haveWallItem = new JMenuItem("一般");
    JMenuItem moreWallItem = new JMenuItem("困难");
    JMenu jMenu3 = new JMenu("速度");
    JMenuItem speed1Item = new JMenuItem("速度1");
    JMenuItem speed2Item = new JMenuItem("速度2");
    JMenuItem speed3Item = new JMenuItem("速度3");
    JMenuItem speed4Item = new JMenuItem("速度4");
    JMenuItem speed5Item = new JMenuItem("速度5");

    JLabel background = new JLabel();

    public GameFrame(int numX, int numY) throws HeadlessException {
        buildFrame();
        buildGame(numX, numY, 3);
        replaceMap();
        setVisible(true);
        runGame(timeDelay[speed - 1]);
    }

    public void runGame(int speed) {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(unLockGame){
                    if (snake.isDead(map, nonePlace)) {
                        System.out.println("You die!");
                        timer.cancel();
                    } else
                        oneGame();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, Math.abs(speed));
    }

    public void buildGame(int x, int y, int snakeLength) {
        map = new Map(x, y);
        snake = new Snake(map, snakeLength);
        nonePlace = new NonePlace(snake);
        food = new Food(map.getX() - 3 + (map.getY() - 3) * map.getX());
        map.rebuildMap(nonePlace, food);

    }

    public void reSetGame() {
        snake = new Snake(map, 3);
        nonePlace.rebuildNoneList(snake);
        food.setFoodLocate(map.getX() - 3 + (map.getY() - 3) * map.getX());
        map.rebuildMap(nonePlace, food);
        core = 0;
        replaceMap();
        unLockGame = false;
        runGame(timeDelay[speed - 1]);
    }

    public void replaceMap() {


        this.getContentPane().removeAll();
        int x = map.getX();
        int y = map.getY();
        rebuildBackground();
        JLabel jLabel;
        jLabel = new JLabel();
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.white);
        jLabel.setBorder(new BevelBorder(0));
        jLabel.setBounds(30 * (food.getFoodLocate() % x), 30 * (food.getFoodLocate() / x), 30, 30);
        JLabel jLabelE = new JLabel();
        jLabelE.setOpaque(true);
        jLabelE.setBackground(Color.yellow);
        jLabelE.setBorder(new BevelBorder(1));
        jLabelE.setBounds(10, 10, 10, 10);
        jLabel.add(jLabelE);
        background.add(jLabel);

        for (int i = 0; i < nonePlace.wallList.size(); i++) {
            int tempX = nonePlace.wallList.get(i) % x;
            int tempY = nonePlace.wallList.get(i) / x;
            jLabel = new JLabel();
            jLabel.setOpaque(true);
            jLabel.setBackground(Color.white);
            jLabel.setBounds(30 * tempX, 30 * tempY, 30, 30);
            jLabel.setBorder(new BevelBorder(0));
            background.add(jLabel);
        }

        for (int i = 0; i < snake.snakeList.size(); i++) {
            int tempX = snake.snakeList.get(i) % x;
            int tempY = snake.snakeList.get(i) / x;
            jLabel = new JLabel();
            jLabel.setOpaque(true);
            jLabel.setBackground(Color.green);
            jLabel.setBounds(30 * tempX, 30 * tempY, 30, 30);
            jLabel.setBorder(new BevelBorder(0));
            background.add(jLabel);
        }

        JLabel coreCount = new JLabel("分数：" + core);
        coreCount.setBounds(20, 15, 60, 10);
        this.getContentPane().add(coreCount);

        this.getContentPane().repaint();
    }

    public void oneGame() {

        unLockKey = true;
        if (snake.eat(map, food)) {
            food.ate(map);
            core += speed;
        } else
            snake.move(map);

        nonePlace.rebuildNoneList(snake);
        map.rebuildMap(nonePlace, food);
        replaceMap();

    }

    public void rebuildBackground() {
        background.removeAll();
        background.setBounds(800 - 15 * map.getX(), 500 - 15 * map.getY(), 30 * map.getX(), 30 * map.getY());
        background.setOpaque(true);
        background.setBackground(Color.black);
        this.getContentPane().add(background);
    }

    private void buildFrame() {
        this.setTitle("贪吃蛇");
        this.setSize(1600, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        restartItem.addActionListener(this);
        exitItem.addActionListener(this);
        jMenu1.add(restartItem);
        jMenu1.add(exitItem);
        jMenuBar.add(jMenu1);

        noWallItem.addActionListener(this);
        haveWallItem.addActionListener(this);
        moreWallItem.addActionListener(this);
        jMenu2.add(noWallItem);
        jMenu2.add(haveWallItem);
        jMenu2.add(moreWallItem);
        jMenuBar.add(jMenu2);

        speed1Item.addActionListener(this);
        speed2Item.addActionListener(this);
        speed3Item.addActionListener(this);
        speed4Item.addActionListener(this);
        speed5Item.addActionListener(this);
        jMenu3.add(speed1Item);
        jMenu3.add(speed2Item);
        jMenu3.add(speed3Item);
        jMenu3.add(speed4Item);
        jMenu3.add(speed5Item);

        jMenuBar.add(jMenu3);

        this.setJMenuBar(jMenuBar);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        unLockGame = true;
        int code = e.getKeyCode();

        if (unLockKey) {
            switch (code) {
                case 87 -> {
                    if (snake.getDirect() != 2) {
                        snake.setDirect(1);
                        unLockKey = false;
                    }
                }
                case 83 -> {
                    if (snake.getDirect() != 1) {
                        snake.setDirect(2);
                        unLockKey = false;
                    }
                }
                case 65 -> {
                    if (snake.getDirect() != 4) {
                        snake.setDirect(3);
                        unLockKey = false;
                    }
                }
                case 68 -> {
                    if (snake.getDirect() != 3) {
                        snake.setDirect(4);
                        unLockKey = false;
                    }
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objItem = e.getSource();
        if (objItem == restartItem) {
            if (timer != null)
                timer.cancel();
            reSetGame();
        } else if (objItem == exitItem) {
            if (timer != null)
                timer.cancel();
            System.exit(0);
        } else if (objItem == noWallItem) {
            if (timer != null)
                timer.cancel();
            nonePlace.clearWallList();
            reSetGame();
        } else if (objItem == haveWallItem) {
            if (timer != null)
                timer.cancel();
            int x = map.getX();
            int y = map.getY();
            for (int i = 0; i < x * y; i++) {
                if (i / x == 0 || i % x == 0 || i / x == (y - 1) || i % x == (x - 1)) {
                    nonePlace.wallList.add(i);
                }
            }
            reSetGame();
        } else if (objItem == moreWallItem) {
            if (timer != null)
                timer.cancel();
            reSetGame();
        } else if (objItem == speed1Item) {
            if (timer != null)
                timer.cancel();
            speed = 1;
            reSetGame();
        } else if (objItem == speed2Item) {
            if (timer != null)
                timer.cancel();
            speed = 2;
            reSetGame();
        } else if (objItem == speed3Item) {
            if (timer != null)
                timer.cancel();
            speed = 3;
            reSetGame();
        } else if (objItem == speed4Item) {
            if (timer != null)
                timer.cancel();
            speed = 4;
            reSetGame();
        } else if (objItem == speed5Item) {
            if (timer != null)
                timer.cancel();
            speed = 5;
            reSetGame();
        }

    }

}
