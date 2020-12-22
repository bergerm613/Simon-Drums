package touro.simondrums;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private JButton crashCymbal;
    private JButton snare;
    private JButton bass;
    private JButton hiHatCymbal;

    private JLabel highScore = new JLabel("High Score: 0", SwingConstants.CENTER);

    public Frame(SimonGame game) {
        super();
        ListenerEvents listenerEvents = new ListenerEvents(game, highScore);

        setSize(700, 275);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simon");
        setLayout(new BorderLayout());
        setButtons();

        highScore.setOpaque(false);
        add(highScore, BorderLayout.PAGE_START);

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

    private void setButtons() {
        crashCymbal = new JButton();
        crashCymbal.setOpaque(true);
        crashCymbal.setBackground(Color.RED);
        setButtonImage("images/crash-cymbal.png", crashCymbal);

        snare = new JButton();
        snare.setOpaque(true);
        snare.setBackground(Color.BLUE);
        setButtonImage("images/snare.png", snare);

        bass = new JButton();
        bass.setOpaque(true);
        bass.setBackground(new Color(17, 138, 61));
        setButtonImage("images/bass.png", bass);

        hiHatCymbal = new JButton();
        hiHatCymbal.setOpaque(true);
        hiHatCymbal.setBackground(Color.YELLOW);
        setButtonImage("images/hi-hat-cymbal.png", hiHatCymbal);
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