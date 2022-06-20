package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class I extends Tetromino {
        public I() {
                cells[0] = new Cell(0,4, Tetris.I);
                cells[1] = new Cell(0,3, Tetris.I);
                cells[2] = new Cell(0,5, Tetris.I);
                cells[3] = new Cell(0,6, Tetris.I);

                //两种旋转状态
                states = new Tetromino.State[2];
                //初始化旋转状态方块位置
                states[0] = new Tetromino.State(0,0,0,-2,0,-1,0,1);
                states[1] = new Tetromino.State(0,0,-2,0,-1,0,1,0);
        }
}
