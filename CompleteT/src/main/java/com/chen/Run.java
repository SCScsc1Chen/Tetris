package com.chen;

import com.chen.elements.Tetris;

import javax.swing.*;

public class Run {
        public static void main(String[] args) {
                //创建窗口
                JFrame frame = new JFrame("俄罗斯方块");
                //创建面板窗口
                Tetris tetris = new Tetris();
                //面板嵌入窗口
                frame.add(tetris);
                //设置窗口可见
                frame.setVisible(true);
                //设置窗口大小
                frame.setSize(900,900);
                //设置居中
                frame.setLocationRelativeTo(null);
                //窗口关闭程序终止
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //主要逻辑封装
                tetris.start();
        }
}
