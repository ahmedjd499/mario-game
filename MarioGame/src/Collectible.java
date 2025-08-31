
import java.awt.*;

public class Collectible {

    private int x, y;
    private int width = 20;
    private int height = 20;
    private PowerUp powerUp;
    private boolean collected = false;
    private int bounceOffset = 0;
    private int bounceDirection = 1;

    public Collectible(int x, int y, PowerUp powerUp) {
        this.x = x;
        this.y = y;
        this.powerUp = powerUp;
    }

    public void update() {
        // Simple bouncing animation
        bounceOffset += bounceDirection;
        if (bounceOffset > 5 || bounceOffset < -5) {
            bounceDirection *= -1;
        }
    }

    public void draw(Graphics g) {
        if (collected) {
            return;
        }

        int drawY = y + bounceOffset;

        switch (powerUp) {
            case SPEED_BOOST:
                // Draw speed boost as a lightning bolt
                g.setColor(Color.YELLOW);
                g.fillRect(x + 5, drawY, 10, 15);
                g.setColor(Color.ORANGE);
                g.fillPolygon(new int[]{x + 5, x + 10, x + 15}, new int[]{drawY + 5, drawY, drawY + 5}, 3);
                break;

            case SHIELD:
                // Draw shield as a blue circle with border
                g.setColor(Color.BLUE);
                g.fillOval(x, drawY, width, height);
                g.setColor(Color.CYAN);
                g.drawOval(x, drawY, width, height);
                break;

            case FLYING:
                // Draw flying power-up as wings
                g.setColor(Color.PINK);
                g.fillOval(x, drawY + 5, 8, 10);
                g.fillOval(x + 12, drawY + 5, 8, 10);
                g.setColor(Color.MAGENTA);
                g.fillOval(x + 4, drawY, 12, 8);
                break;

            case DOUBLE_JUMP:
                // Draw double jump as springs
                g.setColor(Color.GREEN);
                g.fillRect(x + 8, drawY, 4, 20);
                g.fillRect(x + 4, drawY + 4, 12, 4);
                g.fillRect(x + 4, drawY + 12, 12, 4);
                break;

            case SUPER:
                // Draw Super Mushroom
                g.setColor(Color.RED);
                g.fillOval(x, drawY, width, height);
                g.setColor(Color.WHITE);
                g.fillOval(x + 3, drawY + 3, 6, 6);
                g.fillOval(x + 11, drawY + 3, 6, 6);
                g.fillOval(x + 7, drawY + 11, 6, 6);
                break;

            case FIRE:
                // Draw Fire Flower
                g.setColor(Color.ORANGE);
                g.fillOval(x + 7, drawY, 6, 15);
                g.setColor(Color.RED);
                g.fillOval(x + 5, drawY + 2, 10, 8);
                g.setColor(Color.YELLOW);
                g.fillOval(x + 7, drawY + 4, 6, 4);
                break;

            case ICE:
                // Draw Ice Flower
                g.setColor(Color.CYAN);
                g.fillOval(x + 7, drawY, 6, 15);
                g.setColor(Color.BLUE);
                g.fillOval(x + 5, drawY + 2, 10, 8);
                g.setColor(Color.WHITE);
                g.fillOval(x + 7, drawY + 4, 6, 4);
                break;

            case INVINCIBLE:
                // Draw Star
                g.setColor(Color.YELLOW);
                int[] xPoints = {x + 10, x + 13, x + 20, x + 13, x + 10, x + 7, x, x + 7};
                int[] yPoints = {drawY, drawY + 7, drawY + 7, drawY + 13, drawY + 20, drawY + 13, drawY + 7, drawY + 7};
                g.fillPolygon(xPoints, yPoints, 8);
                break;
        }

        // Draw power-up name
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 8));
        g.drawString(powerUp.toString().replace("_", " "), x - 10, drawY - 5);
    }

    public boolean collidesWith(int marioX, int marioY, int marioWidth, int marioHeight) {
        if (collected) {
            return false;
        }

        Rectangle marioRect = new Rectangle(marioX, marioY, marioWidth, marioHeight);
        Rectangle collectibleRect = new Rectangle(x, y, width, height);

        return marioRect.intersects(collectibleRect);
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void collect() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
