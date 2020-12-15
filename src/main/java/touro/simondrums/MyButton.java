package touro.simondrums;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MyButton extends JButton {

    private Color backgroundColor;

    public MyButton(String filename, Color backgroundColor) {
        setButtonImage(filename, this);
        this.backgroundColor = backgroundColor;
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    private void setButtonImage(String fileName, JButton button) {
        try {
            File file = new File(fileName);
            Image image = ImageIO.read(file);
            image = image.getScaledInstance(125,175,Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}