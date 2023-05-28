package org.jrgf.chip8.emu;

import org.jrgf.chip8.emu.chip.Chip;

public class Main extends Thread{
    private Chip chip8;
    private ChipFrame frame;
    public Main() {
        chip8 = new Chip();
        chip8.init();
        chip8.loadProgram("");
        frame = new ChipFrame(chip8);
    }

    public void run() {
        //60 hz, 60 updates per second
        while(true) {
            chip8.run();
            if(chip8.needsRedraw()) {
                frame.repaint();
                chip8.removeDrawFlag();
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                //Unthrown exception
            }
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
