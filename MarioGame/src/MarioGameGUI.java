
import java.awt.*;
import javax.swing.*;

public class MarioGameGUI extends JFrame {

    private Mario mario;
    private JTextArea logArea;

    public MarioGameGUI() {
        mario = new Mario();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Super Mario - Design Patterns Demo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status Panel
        JPanel statusPanel = new JPanel(new GridLayout(6, 2));
        statusPanel.setBorder(BorderFactory.createTitledBorder("Mario Status"));
        statusPanel.setPreferredSize(new Dimension(300, 200));

        statusPanel.add(new JLabel("State:"));
        JLabel stateLabel = new JLabel();
        statusPanel.add(stateLabel);

        statusPanel.add(new JLabel("Score:"));
        JLabel scoreLabel = new JLabel();
        statusPanel.add(scoreLabel);

        statusPanel.add(new JLabel("Lives:"));
        JLabel livesLabel = new JLabel();
        statusPanel.add(livesLabel);

        statusPanel.add(new JLabel("Speed:"));
        JLabel speedLabel = new JLabel();
        statusPanel.add(speedLabel);

        statusPanel.add(new JLabel("Strength:"));
        JLabel strengthLabel = new JLabel();
        statusPanel.add(strengthLabel);

        statusPanel.add(new JLabel("Health:"));
        JLabel healthLabel = new JLabel();
        statusPanel.add(healthLabel);

        add(statusPanel, BorderLayout.WEST);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(e -> {
            mario.move();
            log("Mario moved");
            updateStatus();
        });
        controlPanel.add(moveButton);

        JButton jumpButton = new JButton("Jump");
        jumpButton.addActionListener(e -> {
            mario.jump();
            log("Mario jumped");
            updateStatus();
        });
        controlPanel.add(jumpButton);

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            mario.attack();
            log("Mario attacked");
            updateStatus();
        });
        controlPanel.add(attackButton);

        JButton takeDamageButton = new JButton("Take Damage");
        takeDamageButton.addActionListener(e -> {
            mario.takeDamage();
            log("Mario took damage");
            updateStatus();
        });
        controlPanel.add(takeDamageButton);

        // Power-up selection
        JPanel powerUpPanel = new JPanel(new FlowLayout());
        powerUpPanel.add(new JLabel("Power-up:"));
        JComboBox<PowerUp> powerUpComboBox = new JComboBox<>(PowerUp.values());
        powerUpPanel.add(powerUpComboBox);

        JButton collectPowerUpButton = new JButton("Collect Power-up");
        collectPowerUpButton.addActionListener(e -> {
            PowerUp selected = (PowerUp) powerUpComboBox.getSelectedItem();
            mario.collectPowerUp(selected);
            log("Collected " + selected);
            updateStatus();
        });
        powerUpPanel.add(collectPowerUpButton);
        controlPanel.add(powerUpPanel);

        add(controlPanel, BorderLayout.CENTER);

        // Log Panel
        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game Log"));
        add(scrollPane, BorderLayout.SOUTH);

        // Timer to update status labels
        Timer statusTimer = new Timer(100, e -> {
            stateLabel.setText(mario.getState().getClass().getSimpleName());
            scoreLabel.setText(String.valueOf(mario.getScore()));
            livesLabel.setText(String.valueOf(mario.getLives()));
            speedLabel.setText(String.format("%.2f", mario.getTotalSpeed()));
            strengthLabel.setText(String.valueOf(mario.getTotalStrength()));
            healthLabel.setText(String.valueOf(mario.getTotalHealth()));
        });
        statusTimer.start();

        setVisible(true);
    }

    private void updateStatus() {
        // Status is updated by the timer
    }

    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarioGameGUI::new);
    }
}
