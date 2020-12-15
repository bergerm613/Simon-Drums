package touro.simondrums;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MyButton extends JButton {

    private Color pressedBackgroundColor;

    public MyButton(String filename, Color pressedBackgroundColor) {
        setButtonImage(filename, this);
        this.pressedBackgroundColor = pressedBackgroundColor;
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else {
            g.setColor(Color.white);
        }
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