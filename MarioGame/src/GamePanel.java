
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Mario mario;
    private int marioX = 100;
    private int marioY = 300;
    private int marioWidth = 30;
    private int marioHeight = 30;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean jumping = false;
    private int jumpVelocity = 0;
    private final int GROUND_Y = 300;
    private final int GRAVITY = 1;
    private Timer gameTimer;

    // Game environment
    private int cameraX = 0;
    private int levelWidth = 2000;

    // Collectibles
    private java.util.List<Collectible> collectibles;

    public GamePanel(Mario mario) {
        this.mario = mario;
        setPreferredSize(new Dimension(800, 400));
        setBackground(new Color(135, 206, 235)); // Sky blue
        setFocusable(true);
        addKeyListener(this);

        // Initialize collectibles
        initializeCollectibles();

        // Game loop - 60 FPS
        gameTimer = new Timer(16, this);
        gameTimer.start();
    }

    private void initializeCollectibles() {
        collectibles = new java.util.ArrayList<>();

        // Add various collectibles at different positions
        collectibles.add(new Collectible(200, 280, PowerUp.SPEED_BOOST));
        collectibles.add(new Collectible(400, 280, PowerUp.SHIELD));
        collectibles.add(new Collectible(600, 280, PowerUp.FLYING));
        collectibles.add(new Collectible(800, 280, PowerUp.DOUBLE_JUMP));
        collectibles.add(new Collectible(300, 280, PowerUp.SUPER));
        collectibles.add(new Collectible(500, 280, PowerUp.FIRE));
        collectibles.add(new Collectible(700, 280, PowerUp.ICE));
        collectibles.add(new Collectible(900, 280, PowerUp.INVINCIBLE));
        collectibles.add(new Collectible(1100, 280, PowerUp.SPEED_BOOST));
        collectibles.add(new Collectible(1300, 280, PowerUp.SHIELD));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw sky gradient
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint skyGradient = new GradientPaint(0, 0, new Color(135, 206, 235),
                0, 200, new Color(255, 255, 255));
        g2d.setPaint(skyGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw clouds
        g.setColor(Color.WHITE);
        drawCloud(g, 100, 50);
        drawCloud(g, 300, 80);
        drawCloud(g, 500, 60);
        drawCloud(g, 700, 40);

        // Draw ground
        g.setColor(new Color(34, 139, 34)); // Forest green
        g.fillRect(0, GROUND_Y + marioHeight, getWidth(), getHeight() - GROUND_Y - marioHeight);

        // Draw grass details
        g.setColor(new Color(0, 100, 0));
        for (int i = 0; i < getWidth(); i += 20) {
            g.fillRect(i, GROUND_Y + marioHeight, 10, 5);
        }

        // Draw collectibles
        drawCollectibles(g);

        // Draw Mario with state-based appearance
        drawMario(g);

        // Draw status overlay
        drawStatus(g);

        // Draw power-up indicators
        drawPowerUps(g);
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.fillOval(x, y, 40, 20);
        g.fillOval(x + 15, y - 5, 40, 20);
        g.fillOval(x + 30, y, 40, 20);
    }

    private void drawMario(Graphics g) {
        String stateName = mario.getState().getClass().getSimpleName();

        // Set color based on state
        switch (stateName) {
            case "SmallMario":
                g.setColor(Color.RED);
                break;
            case "SuperMario":
                g.setColor(Color.BLUE);
                break;
            case "FireMario":
                g.setColor(Color.ORANGE);
                break;
            case "IceMario":
                g.setColor(Color.CYAN);
                break;
            case "InvincibleMario":
                g.setColor(Color.YELLOW);
                // Add sparkle effect for invincibility
                g.setColor(Color.YELLOW);
                g.fillOval(marioX - 5, marioY - 5, marioWidth + 10, marioHeight + 10);
                break;
            default:
                g.setColor(Color.RED);
        }

        // Draw Mario body
        g.fillRect(marioX, marioY, marioWidth, marioHeight);

        // Draw eyes
        g.setColor(Color.WHITE);
        g.fillOval(marioX + 5, marioY + 5, 6, 6);
        g.fillOval(marioX + 19, marioY + 5, 6, 6);
        g.setColor(Color.BLACK);
        g.fillOval(marioX + 7, marioY + 7, 3, 3);
        g.fillOval(marioX + 21, marioY + 7, 3, 3);

        // Draw special features based on state
        if (stateName.equals("FireMario")) {
            // Fire effect
            g.setColor(Color.RED);
            g.fillOval(marioX - 5, marioY + marioHeight - 5, 10, 10);
            g.fillOval(marioX + marioWidth - 5, marioY + marioHeight - 5, 10, 10);
        } else if (stateName.equals("IceMario")) {
            // Ice effect
            g.setColor(Color.WHITE);
            g.fillOval(marioX - 3, marioY - 3, 8, 8);
            g.fillOval(marioX + marioWidth - 5, marioY - 3, 8, 8);
        }

        // Draw state label
        g.setColor(Color.BLACK);
        g.drawString(stateName.replace("Mario", ""), marioX, marioY - 5);
    }

    private void drawStatus(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // Background for status
        g.setColor(new Color(255, 255, 255, 200));
        g.fillRect(10, 10, 200, 80);

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 200, 80);

        // Status text
        g.drawString("Lives: " + mario.getLives(), 20, 30);
        g.drawString("Score: " + mario.getScore(), 20, 50);
        g.drawString("Health: " + mario.getTotalHealth(), 20, 70);
        g.drawString("Speed: " + String.format("%.1f", mario.getTotalSpeed()), 100, 30);
    }

    private void drawPowerUps(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 10));

        int y = 100;
        g.drawString("Power-ups:", 20, y);

        // This would need to be updated to show actual decorators
        // For now, just show a placeholder
        g.setColor(Color.BLUE);
        g.drawString("Active decorators would be listed here", 20, y + 20);
    }

    private void drawCollectibles(Graphics g) {
        if (collectibles != null) {
            for (Collectible collectible : collectibles) {
                collectible.draw(g);
            }
        }
    }

    private void checkCollectibleCollisions() {
        if (collectibles == null) {
            return;
        }

        java.util.Iterator<Collectible> iterator = collectibles.iterator();
        while (iterator.hasNext()) {
            Collectible collectible = iterator.next();
            if (!collectible.isCollected() && collectible.collidesWith(marioX, marioY, marioWidth, marioHeight)) {
                // Collect the item
                collectible.collect();
                mario.collectPowerUp(collectible.getPowerUp());

                // Remove from list
                iterator.remove();

                System.out.println("Collected: " + collectible.getPowerUp());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update Mario position
        if (movingLeft) {
            marioX -= (int) (5 * mario.getTotalSpeed());
            mario.move();
        }
        if (movingRight) {
            marioX += (int) (5 * mario.getTotalSpeed());
            mario.move();
        }

        // Handle jumping physics
        if (jumping) {
            marioY += jumpVelocity;
            jumpVelocity += GRAVITY;

            if (marioY >= GROUND_Y) {
                marioY = GROUND_Y;
                jumping = false;
                jumpVelocity = 0;
            }
        }

        // Keep Mario in bounds
        if (marioX < 0) {
            marioX = 0;
        }
        if (marioX > levelWidth - marioWidth) {
            marioX = levelWidth - marioWidth;
        }

        // Update collectibles
        if (collectibles != null) {
            for (Collectible collectible : collectibles) {
                collectible.update();
            }
        }

        // Check collisions with collectibles
        checkCollectibleCollisions();

        // Update camera to follow Mario
        cameraX = marioX - getWidth() / 2;
        if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraX > levelWidth - getWidth()) {
            cameraX = levelWidth - getWidth();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            movingLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            movingRight = true;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE) {
            if (!jumping) {
                jumping = true;
                jumpVelocity = -15;
                mario.jump();
            }
        }
        if (key == KeyEvent.VK_X) { // Attack
            mario.attack();
        }
        if (key == KeyEvent.VK_Z) { // Take damage
            mario.takeDamage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            movingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            movingRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle power-up collection with number keys
        char keyChar = e.getKeyChar();
        switch (keyChar) {
            case '1':
                mario.collectPowerUp(PowerUp.SPEED_BOOST);
                break;
            case '2':
                mario.collectPowerUp(PowerUp.SHIELD);
                break;
            case '3':
                mario.collectPowerUp(PowerUp.FLYING);
                break;
            case '4':
                mario.collectPowerUp(PowerUp.DOUBLE_JUMP);
                break;
            case '5':
                mario.collectPowerUp(PowerUp.SUPER);
                break;
            case '6':
                mario.collectPowerUp(PowerUp.FIRE);
                break;
            case '7':
                mario.collectPowerUp(PowerUp.ICE);
                break;
            case '8':
                mario.collectPowerUp(PowerUp.INVINCIBLE);
                break;
        }
    }

    public void collectPowerUp(PowerUp powerUp) {
        mario.collectPowerUp(powerUp);
    }
}
