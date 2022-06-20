package com.chen.elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


//编写Tetris主类
public class Tetris extends JPanel {
        //正在下落方块
        private Tetromino currentOne = Tetromino.randomOne();
        //下一个方块
        private Tetromino nextOne = Tetromino.randomOne();
        //创建游戏主区域
        private Cell[][] wall = new Cell[18][10];
        //声明单元格大小
        private static final int CELL_SIZE = 40;
        //分数池
        int[] scorespool = {0, 5, 10, 25, 50};
        //当前游戏分数
        private int totalScore = 0;
        //已消除行数
        private int totalLine = 0;
        //三种游戏状态，游戏中、暂停、游戏结束
        public static final int PLAYING = 0;
        public static final int PAUSING = 1;
        public static final int GAMEOVER = 2;
        //当前状态
        private int game_state;
        String[] show_state = {"游戏暂停中，按C继续", "游戏结束，vege"};


        //载入方块图片
        public static BufferedImage I;
        public static BufferedImage J;
        public static BufferedImage L;
        public static BufferedImage O;
        public static BufferedImage S;
        public static BufferedImage T;
        public static BufferedImage Z;
        public static BufferedImage bgImage;


        //指定图片路径
         {
                try {
                        //I = ImageIO.read(new File("MainGame/src/images/I.png"));
                        URL Iurl = this.getClass().getResource("/images/I.png");
                        I = ImageIO.read(Iurl);
                        //J = ImageIO.read(new File("MainGame/src/images/J.png"));
                        URL Jurl = this.getClass().getResource("/images/J.png");
                        J = ImageIO.read(Jurl);
                        //L = ImageIO.read(new File("MainGame/src/images/L.png"));
                        URL Lurl = this.getClass().getResource("/images/L.png");
                        L = ImageIO.read(Lurl);
                        //O = ImageIO.read(new File("MainGame/src/images/O.png"));
                        URL Ourl = this.getClass().getResource("/images/O.png");
                        O = ImageIO.read(Ourl);
                        //S = ImageIO.read(new File("MainGame/src/images/S.png"));
                        URL Surl = this.getClass().getResource("/images/S.png");
                        S = ImageIO.read(Surl);
                        //T = ImageIO.read(new File("MainGame/src/images/T.png"));
                        URL Turl = this.getClass().getResource("/images/T.png");
                        T = ImageIO.read(Turl);
                        //Z = ImageIO.read(new File("MainGame/src/images/Z.png"));
                        URL Zurl = this.getClass().getResource("/images/Z.png");
                        Z = ImageIO.read(Zurl);
                        //随机生成背景
                        int num = (int) (Math.random() * 4);
                        switch (num) {
                                case 0:
                                        //bgImage = ImageIO.read(new File("MainGame/src/images/bg1.png"));
                                        URL bg1url = this.getClass().getResource("/images/bg1.png");
                                        bgImage = ImageIO.read(bg1url);
                                        break;
                                case 1:
                                        //bgImage = ImageIO.read(new File("MainGame/src/images/bg2.png"));
                                        URL bg2url = this.getClass().getResource("/images/bg2.png");
                                        bgImage = ImageIO.read(bg2url);
                                        break;
                                case 2:
                                        //bgImage = ImageIO.read(new File("MainGame/src/images/bg3.png"));
                                        URL bg3url = this.getClass().getResource("/images/bg3.png");
                                        bgImage = ImageIO.read(bg3url);
                                        break;
                                case 3:
                                        //bgImage = ImageIO.read(new File("MainGame/src/images/bg4.png"));
                                        URL bg4url = this.getClass().getResource("/images/bg4.png");
                                        bgImage = ImageIO.read(bg4url);
                                        break;
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        //绘制
        @Override
        public void paint(Graphics g) {
                g.drawImage(bgImage, 0, 0, null);
                //平移主区域坐标对齐图片
                g.translate(69, 90);
                //绘制主区域
                paintWall(g);
                //绘制正在下落方块
                paintCurrentOne(g);
                //绘制下一个
                paintNextOne(g);
                //绘制分数
                paintScore(g);
                //绘制操作
                paintControl(g);
                //绘制状态
                paintState(g);

        }

        private void paintState(Graphics g) {
                if (game_state == PLAYING) {
                } else if (game_state == PAUSING) {
                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
                        g.setColor(Color.blue);
                        g.drawString(show_state[0], 30, 350);
                } else if (game_state == GAMEOVER) {
                        g.setFont(new Font(Font.SERIF, Font.BOLD, 35));
                        g.setColor(Color.blue);
                        g.drawString(show_state[1], 70, 350);
                }
        }

        private void paintControl(Graphics g) {
                g.setColor(Color.black);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                g.drawString("操作说明：", 500, 600);
                g.drawString("←：向左", 500, 625);
                g.drawString("→：向右", 620, 625);
                g.drawString("↑：变换", 500, 650);
                g.drawString("↓：下移", 620, 650);
                g.drawString("Space：下移到底", 500, 675);
                g.drawString("ESC：暂停", 500, 700);
                g.drawString("R：重新开始", 620, 700);
                //g.drawString("C：继续",500,725);
        }

        private void paintScore(Graphics g) {
                g.setColor(Color.black);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                g.drawString("分数：" + totalScore, 530, 355);
                g.drawString("已消除行：" + totalLine, 530, 505);
        }

        private void paintNextOne(Graphics g) {
                Cell[] cells = nextOne.cells;
                for (Cell cell : cells) {
                        int x = cell.getCol() * CELL_SIZE + 410;
                        int y = cell.getRow() * CELL_SIZE + 155;
                        g.drawImage(cell.getImage(), x, y, null);
                }
        }

        private void paintCurrentOne(Graphics g) {
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int x = cell.getCol() * CELL_SIZE;
                        int y = cell.getRow() * CELL_SIZE;
                        g.drawImage(cell.getImage(), x, y, null);
                }
        }

        private void paintWall(Graphics g) {
                for (int i = 0; i < wall.length; i++) {
                        for (int j = 0; j < wall[i].length; j++) {
                                int x = j * CELL_SIZE;
                                int y = i * CELL_SIZE;
                                Cell cell = wall[i][j];
                                if (cell == null) {
                                        g.setColor(Color.black);
                                        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                                } else {
                                        g.drawImage(cell.getImage(), x, y, null);
                                }
                        }
                }
        }
        //判断是否出界
        public boolean OutofBounds(){
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int col = cell.getCol();
                        int row = cell.getRow();
                        if (row<0 || row>wall.length-1 || col<0 || col>wall[0].length-1){
                                return true;
                        }
                }
                return false;
        }
        //判断是否重合
        public boolean isCoincide(){
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int col = cell.getCol();
                        int row = cell.getRow();
                        if (wall[row][col] != null){
                                return true;
                        }
                }
                return false;
        }
        //按键左移
        public void moveLeftAction(){
                currentOne.moveLeft();
                //判断是否出界或重合
                if(OutofBounds() || isCoincide()){
                        currentOne.moveRight();
                }
                /*Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int col = cell.getCol();
                        int row = cell.getRow();
                        if (col >0 && wall[col-1][row] == null){
                                currentOne.moveLeft();
                                break;
                        }
                }*/
        }
        //按键右移
        public void moveRightAction(){
                currentOne.moveRight();
                if(OutofBounds() || isCoincide()){
                        currentOne.moveLeft();
                }
                /*Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int col = cell.getCol();
                        int row = cell.getRow();
                        if (col < wall[0].length &&wall[col+1][row] == null){
                                currentOne.moveRight();
                                break;~~
                        }
                }*/
        }
        //判断是否游戏结束
        public boolean isGameOver(){
                Cell[] cells = nextOne.cells;
                for (Cell cell : cells) {
                        int row = cell.getRow();
                        int col = cell.getCol();
                        if (wall[row][col] != null){
                                return true;
                        }
                }
                return false;
        }
        //消除满行
        public void destroyLine(){
                //单次消除行数
                int line = 0;
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int row = cell.getRow();
                        if(isFullLine(row)){
                                line++;
                                for(int i=row;i>0;i--){
                                        System.arraycopy(wall[i-1],0,wall[i],0,wall[0].length);
                                }
                                wall[0] = new Cell[10];
                        }
                }
                totalScore += scorespool[line];
                totalLine += line;
        }
        //判断一行是否满了
        public boolean isFullLine(int row){
                Cell[] cells = wall[row];
                for (Cell cell : cells) {
                        if(cell == null){
                                return false;
                        }
                }
                return true;
        }
        //判断能否下落
        public boolean canDrop(){
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int row = cell.getRow();
                        int col = cell.getCol();
                        if (row == wall.length-1){
                                return false;
                        }else if(wall[row+1][col]!=null){
                                return false;
                        }
                }
                return true;
        }
        //按键一次下落一格
        public void DropOneAction(){
                if(canDrop()){
                        currentOne.softDrop();
                }else{
                        landToWall();
                        destroyLine();
                        if(isGameOver()){
                                game_state = GAMEOVER;
                        }else{
                                currentOne = nextOne;
                                nextOne = Tetromino.randomOne();
                        }
                }
        }
        //嵌入墙中
        private void landToWall() {
                Cell[] cells = currentOne.cells;
                for (Cell cell : cells) {
                        int row = cell.getRow();
                        int col = cell.getCol();
                        wall[row][col] = cell;
                }
        }
        //瞬间下落
        public void InstantDropAction(){
                while(true){
                        if(canDrop()){
                                currentOne.softDrop();
                        }else{
                                break;
                        }
                }
                landToWall();
                destroyLine();
                if(isGameOver()){
                        game_state = GAMEOVER;
                }else {
                        currentOne = nextOne;
                        nextOne = Tetromino.randomOne();
                }
        }
        //执行顺时针旋转动作
        public void rotateRightAction(){
                currentOne.rotateRight();
                if(OutofBounds() || isCoincide()){
                        currentOne.rotateLeft();
                }
        }
        //启动游戏
        public void start(){
                game_state = GAMEOVER;
                KeyListener kl = new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                int code = e.getKeyCode();
                                switch (code){
                                        case KeyEvent.VK_DOWN:
                                                if(game_state == PLAYING) {
                                                        DropOneAction();
                                                }
                                                break;
                                        case KeyEvent.VK_LEFT:
                                                if(game_state == PLAYING) {
                                                        moveLeftAction();
                                                }
                                                break;
                                        case KeyEvent.VK_RIGHT:
                                                if(game_state == PLAYING) {
                                                        moveRightAction();
                                                }
                                                break;
                                        case KeyEvent.VK_UP:
                                                if(game_state == PLAYING) {
                                                        rotateRightAction();
                                                }
                                                break;
                                        case KeyEvent.VK_SPACE:
                                                if(game_state == PLAYING) {
                                                        InstantDropAction();
                                                }
                                                break;
                                        case KeyEvent.VK_ESCAPE:
                                                if(game_state == PLAYING){
                                                        game_state = PAUSING;
                                                }
                                                break;
                                        case KeyEvent.VK_C:
                                                if(game_state == PAUSING){
                                                        game_state = PLAYING;
                                                }
                                                break;
                                        case KeyEvent.VK_R:
                                                game_state = PLAYING;
                                                wall = new Cell[18][10];
                                                currentOne = Tetromino.randomOne();
                                                nextOne = Tetromino.randomOne();
                                                totalScore = 0;
                                                totalLine = 0;
                                                break;
                                }
                        }
                };
                //设置焦点
                this.addKeyListener(kl);
                this.requestFocus();
                while (true){
                        if(game_state == PLAYING){
                                try {
                                        Thread.sleep(100);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                                if (canDrop()){
                                        currentOne.softDrop();
                                }else {
                                        landToWall();
                                        destroyLine();
                                        if(isGameOver()){
                                                game_state = GAMEOVER;
                                        }else {
                                                currentOne=nextOne;
                                                nextOne = Tetromino.randomOne();
                                        }
                                }
                        }
                repaint();
                }
        }
}
