
import java.awt.*;
import javax.swing.*;

public class MarioGameGUI extends JFrame {

    private Mario mario;
    private GamePanel gamePanel;

    public MarioGameGUI() {
        mario = new Mario();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Super Mario - Design Patterns Game");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Game Panel - Main game area
        gamePanel = new GamePanel(mario);
        add(gamePanel, BorderLayout.CENTER);

        // Info Panel - Right side information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(250, 600));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Game Info"));

        // Controls info
        JTextArea controlsInfo = new JTextArea(
                "CONTROLS:\n"
                + "Movement: WASD or Arrow Keys\n"
                + "Jump: W/↑/Space\n"
                + "Attack: X\n"
                + "Take Damage: Z\n\n"
                + "POWER-UPS (Number Keys):\n"
                + "1: Speed Boost\n"
                + "2: Shield\n"
                + "3: Flying\n"
                + "4: Double Jump\n"
                + "5: Super Mario\n"
                + "6: Fire Mario\n"
                + "7: Ice Mario\n"
                + "8: Invincible"
        );
        controlsInfo.setEditable(false);
        controlsInfo.setOpaque(false);
        controlsInfo.setFont(new Font("Monospaced", Font.PLAIN, 11));
        infoPanel.add(controlsInfo);

        // State information
        JPanel statePanel = new JPanel(new GridLayout(8, 2, 5, 5));
        statePanel.setBorder(BorderFactory.createTitledBorder("Current Status"));
        statePanel.setMaximumSize(new Dimension(230, 200));

        statePanel.add(new JLabel("State:"));
        JLabel stateLabel = new JLabel();
        statePanel.add(stateLabel);

        statePanel.add(new JLabel("Score:"));
        JLabel scoreLabel = new JLabel();
        statePanel.add(scoreLabel);

        statePanel.add(new JLabel("Lives:"));
        JLabel livesLabel = new JLabel();
        statePanel.add(livesLabel);

        statePanel.add(new JLabel("Health:"));
        JLabel healthLabel = new JLabel();
        statePanel.add(healthLabel);

        statePanel.add(new JLabel("Speed:"));
        JLabel speedLabel = new JLabel();
        statePanel.add(speedLabel);

        statePanel.add(new JLabel("Strength:"));
        JLabel strengthLabel = new JLabel();
        statePanel.add(strengthLabel);

        statePanel.add(new JLabel("Decorators:"));
        JLabel decoratorLabel = new JLabel();
        statePanel.add(decoratorLabel);

        infoPanel.add(statePanel);

        // Pattern explanation
        JTextArea patternInfo = new JTextArea(
                "\nPATTERNS USED:\n"
                + "• State: Mario's behavior changes\n"
                + "• Decorator: Power-ups stack\n\n"
                + "Watch Mario change color and\n"
                + "abilities as you collect power-ups!"
        );
        patternInfo.setEditable(false);
        patternInfo.setOpaque(false);
        patternInfo.setFont(new Font("Arial", Font.ITALIC, 10));
        infoPanel.add(patternInfo);

        add(infoPanel, BorderLayout.EAST);

        // Status update timer
        Timer statusTimer = new Timer(100, e -> {
            stateLabel.setText(mario.getState().getClass().getSimpleName());
            scoreLabel.setText(String.valueOf(mario.getScore()));
            livesLabel.setText(String.valueOf(mario.getLives()));
            healthLabel.setText(String.valueOf(mario.getTotalHealth()));
            speedLabel.setText(String.format("%.1f", mario.getTotalSpeed()));
            strengthLabel.setText(String.valueOf(mario.getTotalStrength()));
            decoratorLabel.setText(String.valueOf(mario.getDecorators().size()));
        });
        statusTimer.start();

        setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarioGameGUI::new);
    }
}
