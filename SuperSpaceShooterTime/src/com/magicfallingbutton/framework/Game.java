package com.magicfallingbutton.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public interface Game {

    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen(FileInputStream fis, FileOutputStream fos);
}
