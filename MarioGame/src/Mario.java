
import java.util.ArrayList;
import java.util.List;

public class Mario {

    private MarioState state;
    private int score = 0;
    private int lives = 3;
    private double speed = 1.0;
    private int strength = 1;
    private int health = 1;
    private List<PowerUpDecorator> decorators = new ArrayList<>();

    public Mario() {
        this.state = new SmallMario(); // Will create SmallMario later
    }

    public void setState(MarioState state) {
        this.state = state;
    }

    public MarioState getState() {
        return state;
    }

    public void addDecorator(PowerUpDecorator decorator) {
        decorators.add(decorator);
    }

    public int getTotalHealth() {
        return health + decorators.stream().mapToInt(PowerUpDecorator::getHealthBonus).sum();
    }

    public int getTotalStrength() {
        return strength + decorators.stream().mapToInt(PowerUpDecorator::getStrengthBonus).sum();
    }

    public double getTotalSpeed() {
        return speed * (1 + decorators.stream().mapToDouble(PowerUpDecorator::getSpeedBonus).sum());
    }

    public void move() {
        state.move(this);
    }

    public void jump() {
        state.jump(this);
    }

    public void attack() {
        state.attack(this);
    }

    public void takeDamage() {
        state.takeDamage(this);
    }

    public void collectPowerUp(PowerUp powerUp) {
        state.collectPowerUp(this, powerUp);
    }

    // Getters and setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void displayStatus() {
        System.out.println("\n+=================== MARIO STATUS ===================+");
        System.out.printf("| State: %-12s Score: %-5d Lives: %-2d         |\n",
                state.getClass().getSimpleName(), score, lives);
        System.out.println("+===================================================+");
        System.out.printf("| Base Speed: %-5.1f | Total Speed: %-5.2f            |\n",
                speed, getTotalSpeed());
        System.out.printf("| Base Strength: %-2d | Total Strength: %-2d            |\n",
                strength, getTotalStrength());
        System.out.printf("| Base Health: %-3d | Total Health: %-3d              |\n",
                health, getTotalHealth());
        System.out.println("+===================================================+");
        if (!decorators.isEmpty()) {
            System.out.println("| Active Power-ups:                                 |");
            for (PowerUpDecorator d : decorators) {
                System.out.printf("|   * %-43s |\n", d.getClass().getSimpleName());
            }
        } else {
            System.out.println("| No active power-ups                              |");
        }
        System.out.println("+===================================================+");
    }
}
