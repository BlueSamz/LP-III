package act;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

public class ReproductorMusica extends JFrame {
    private Clip clip;
    private JButton btnReproducir, btnPausar, btnDetener;
    private AudioInputStream audioStream;

    public ReproductorMusica() {
        setTitle("Reproductor Multimedia");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        btnReproducir = new JButton("Reproducir Audio");
        btnPausar = new JButton("Pausar");
        btnDetener = new JButton("Detener");

        btnPausar.setEnabled(false);
        btnDetener.setEnabled(false);

        add(btnReproducir);
        add(btnPausar);
        add(btnDetener);

        btnReproducir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File audioFile = fileChooser.getSelectedFile();
                playAudio(audioFile);
            }
        });

        btnPausar.addActionListener(e -> pauseAudio());

        btnDetener.addActionListener(e -> stopAudio());
    }

    private void playAudio(File audioFile) {
        try {
            stopAudio();

            audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            btnPausar.setEnabled(true);
            btnDetener.setEnabled(true);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void pauseAudio() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    private void stopAudio() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        btnPausar.setEnabled(false);
        btnDetener.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReproductorMusica().setVisible(true));
    }
}
