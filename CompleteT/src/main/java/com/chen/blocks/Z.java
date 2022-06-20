package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class Z extends Tetromino {
        public Z() {
                cells[0] = new Cell(1,5, Tetris.Z);
                cells[1] = new Cell(0,4, Tetris.Z);
                cells[2] = new Cell(0,5, Tetris.Z);
                cells[3] = new Cell(1,6, Tetris.Z);

                //两种旋转状态
                states = new State[2];
                //初始化旋转状态方块位置
                states[0] = new State(0,0,-1,-1,-1,0,0,1);
                states[1] = new State(0,0,-1,1,0,1,1,0);
        }
}
