package touro.simondrums;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Color red = new Color(237, 13, 9);
    Color blue = new Color(9, 32, 237);
    Color green = new Color(17, 138, 61);
    Color yellow = new Color(238, 247, 67);

    private MyButton crashCymbal = new MyButton("images/crash-cymbal.png", red);
    private MyButton snare = new MyButton("images/snare.png", blue);
    private MyButton bass = new MyButton("images/bass.png", green);
    private MyButton hiHatCymbal = new MyButton("images/hi-hat-cymbal.png", yellow);

    public Frame(SimonGame game) {
        super();
        ListenerEvents listenerEvents = new ListenerEvents(game, crashCymbal, snare, bass, hiHatCymbal);

        setSize(700, 275);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon");
        setLayout(new BorderLayout());

        JPanel drumsPanel = new JPanel();
        drumsPanel.setLayout(new GridLayout(1,4));

        crashCymbal.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.CRASH));
        crashCymbal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        drumsPanel.add(crashCymbal);

        snare.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.SNARE));
        snare.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        drumsPanel.add(snare);

        bass.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.BASS));
        bass.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        drumsPanel.add(bass);

        hiHatCymbal.addActionListener(actionEvent -> listenerEvents.drumClicked(Drum.HIHAT));
        hiHatCymbal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        drumsPanel.add(hiHatCymbal);

        add(drumsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton newGame = new JButton("Start new game");
        newGame.addActionListener(actionEvent -> listenerEvents.newGame());

        buttonPanel.add(newGame);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
