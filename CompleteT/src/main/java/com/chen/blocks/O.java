package com.chen.blocks;

import com.chen.elements.Cell;
import com.chen.elements.Tetris;
import com.chen.elements.Tetromino;

public class O extends Tetromino {
        public O() {
                cells[0] = new Cell(0,4, Tetris.O);
                cells[1] = new Cell(0,5, Tetris.O);
                cells[2] = new Cell(1,4, Tetris.O);
                cells[3] = new Cell(1,5, Tetris.O);
                //无旋转状态
                states = new Tetromino.State[0];
        }
}
