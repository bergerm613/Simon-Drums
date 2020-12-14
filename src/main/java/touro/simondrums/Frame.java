package touro.simondrums;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private JButton crashCymbal = new JButton();
    private JButton snare = new JButton();
    private JButton bass = new JButton();
    private JButton hiHatCymbal = new JButton();

    public Frame(SimonGame game) {
        super();
        ListenerEvents listenerEvents = new ListenerEvents(game);

        setSize(700, 275);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon");
        setLayout(new BorderLayout());

        JPanel drumsPanel = new JPanel();
        drumsPanel.setLayout(new GridLayout(1,4));

        setButtonImage("images/crash-cymbal.png", crashCymbal);
        crashCymbal.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.CRASH));
        crashCymbal.setBackground(Color.WHITE);
        drumsPanel.add(crashCymbal);

        setButtonImage("images/snare.png", snare);
        snare.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.SNARE));
        snare.setBackground(Color.WHITE);
        drumsPanel.add(snare);

        setButtonImage("images/bass.png", bass);
        bass.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.BASS));
        bass.setBackground(Color.WHITE);
        drumsPanel.add(bass);

        setButtonImage("images/hi-hat-cymbal.png", hiHatCymbal);
        hiHatCymbal.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.HIHAT));
        hiHatCymbal.setBackground(Color.WHITE);
        drumsPanel.add(hiHatCymbal);

        add(drumsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(actionEvent -> listenerEvents.newGame());

        buttonPanel.add(newGame);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setButtonImage(String fileName, JButton button) {
        try {
            File file = new File(fileName);
            Image image = ImageIO.read(file);
            image = image.getScaledInstance(150,200,Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
