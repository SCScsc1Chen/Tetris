package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class S extends Tetromino {
        public S() {
                cells[0] = new Cell(0,5, Tetris.S);
                cells[1] = new Cell(0,6, Tetris.S);
                cells[2] = new Cell(1,4, Tetris.S);
                cells[3] = new Cell(1,5, Tetris.S);

                //两种旋转状态
                states = new Tetromino.State[2];
                //初始化旋转状态方块位置
                states[0] = new Tetromino.State(0,0,0,1,1,-1,1,0);
                states[1] = new Tetromino.State(0,0,1,0,-1,-1,0,-1);
        }
}
