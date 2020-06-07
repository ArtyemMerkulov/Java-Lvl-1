package homework8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MyJButton extends JButton {

    private BufferedImage master;
    private int gridx;
    private int gridy;

    public MyJButton(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public int getGridx() { return gridx; }

    public int getGridy() { return gridy; }
}
