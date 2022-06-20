package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class T extends Tetromino {
        public T() {
                cells[0] = new Cell(0,5, Tetris.T);
                cells[1] = new Cell(0,4, Tetris.T);
                cells[2] = new Cell(0,6, Tetris.T);
                cells[3] = new Cell(1,5, Tetris.T);

                //四种旋转状态
                states = new Tetromino.State[4];
                //初始化旋转状态方块位置
                states[0] = new Tetromino.State(0,0,0,-1,0,1,1,0);
                states[1] = new Tetromino.State(0,0,-1,0,1,0,0,-1);
                states[2] = new Tetromino.State(0,0,0,1,0,-1,-1,0);
                states[3] = new Tetromino.State(0,0,-1,0,1,0,0,1);
        }
}
