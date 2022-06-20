package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class J extends Tetromino {
        public J() {
                cells[0] = new Cell(0,5, Tetris.J);
                cells[1] = new Cell(0,4, Tetris.J);
                cells[2] = new Cell(0,6, Tetris.J);
                cells[3] = new Cell(1,6, Tetris.J);

                //四种旋转状态
                states = new Tetromino.State[4];
                //初始化旋转状态方块位置
                states[0] = new Tetromino.State(0,0,0,-1,0,1,1,1);
                states[1] = new Tetromino.State(0,0,-1,0,1,0,1,-1);
                states[2] = new Tetromino.State(0,0,0,1,0,-1,-1,-1);
                states[3] = new Tetromino.State(0,0,1,0,-1,0,-1,1);
        }
}
