import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tombol "Start Game"
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.startGame(); // Buka game
                dispose(); // Tutup menu
            }
        });

        // Tambahkan tombol ke panel
        JPanel panel = new JPanel();
        panel.add(startButton);
        add(panel);
    }
}