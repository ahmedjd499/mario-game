
public class Main {

    public static void main(String[] args) {
        Mario mario = new Mario();
        System.out.println("Initial status:");
        mario.displayStatus();

        System.out.println("\nMario moves:");
        mario.move();

        System.out.println("\nCollecting SPEED_BOOST:");
        mario.collectPowerUp(PowerUp.SPEED_BOOST);
        mario.displayStatus();

        System.out.println("\nCollecting SUPER power-up:");
        mario.collectPowerUp(PowerUp.SUPER);
        mario.displayStatus();

        System.out.println("\nCollecting FIRE:");
        mario.collectPowerUp(PowerUp.FIRE);
        mario.displayStatus();

        System.out.println("\nTaking damage:");
        mario.takeDamage();
        mario.displayStatus();

        System.out.println("\nCollecting INVINCIBLE:");
        mario.collectPowerUp(PowerUp.INVINCIBLE);
        mario.displayStatus();

        System.out.println("\nTaking damage while invincible:");
        mario.takeDamage();
        mario.displayStatus();
    }
}
