package touro.simondrums;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private JButton crashCymbal;
    private JButton snare;
    private JButton bass;
    private JButton hiHatCymbal;

    public Frame(SimonGame game) {
        super();
        ListenerEvents listenerEvents = new ListenerEvents(game);

        setSize(700, 275);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon");
        setLayout(new BorderLayout());

        JPanel drumsPanel = new JPanel();
        drumsPanel.setLayout(new GridLayout(1,4));

        crashCymbal = setButtonImage("images/crash-cymbal.png", "Crash Cymbal");
        crashCymbal.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.CRASH));
        crashCymbal.setBackground(Color.WHITE);
        drumsPanel.add(crashCymbal);

        snare = setButtonImage("images/snare.png", "Snare");
        snare.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.SNARE));
        snare.setBackground(Color.WHITE);
        drumsPanel.add(snare);

        bass = setButtonImage("images/bass.png", "Bass");
        bass.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.BASS));
        bass.setBackground(Color.WHITE);
        drumsPanel.add(bass);

        hiHatCymbal = setButtonImage("images/hi-hat-cymbal.png", "Hi Hat Cymbal");
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

    private JButton setButtonImage(String fileName, String name) {
        try {
            File file = new File(fileName);
            Image image = ImageIO.read(file);
            image = image.getScaledInstance(150,200,Image.SCALE_SMOOTH);
            return new JButton(new ImageIcon(image));

        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(name);
        }
    }


}
