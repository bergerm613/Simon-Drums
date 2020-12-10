package touro.simondrums;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
//    private LogicClass logic;
    private JButton crashCymbal;
    private JButton snare;
    private JButton bass;
    private JButton hiHatCymbal;
    public Frame(/*LogicClass logic*/) throws HeadlessException {
        super();
//        this.logic = logic;
//        ListenerEvents listenerEvents = new ListenerEvents(logic);

        setSize(700, 275);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon");
        setLayout(new BorderLayout());

        JPanel drumsPanel = new JPanel();
        drumsPanel.setLayout(new GridLayout(1,4));

        crashCymbal = setButtonImage("images/Crash-Cymbal.jpeg", "Crash Cymbal");
        drumsPanel.add(crashCymbal);

        snare = setButtonImage("images/Snare.jpeg", "Snare");
        drumsPanel.add(snare);

        bass = setButtonImage("images/Bass.jpeg", "Bass");
        drumsPanel.add(bass);

        hiHatCymbal = setButtonImage("images/Hi-Hat-Cymbal.jpeg", "Hi Hat Cymbal");
        drumsPanel.add(hiHatCymbal);

        add(drumsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(actionEvent -> {
//            listenerEvents.newGame();
        });

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
