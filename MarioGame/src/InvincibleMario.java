public class InvincibleMario implements MarioState {
    private MarioState previousState;

    public InvincibleMario(MarioState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void move(Mario mario) {
        System.out.println("Invincible Mario moves fast!");
        mario.setScore(mario.getScore() + 5);
    }

    @Override
    public void jump(Mario mario) {
        System.out.println("Invincible Mario jumps very high!");
    }

    @Override
    public void attack(Mario mario) {
        System.out.println("Invincible Mario attacks powerfully!");
    }

    @Override
    public void takeDamage(Mario mario) {
        System.out.println("Invincible Mario is immune to damage!");
        // Revert to previous state
        mario.setState(previousState);
        System.out.println("Invincibility ended.");
    }

    @Override
    public void collectPowerUp(Mario mario, PowerUp powerUp) {
        System.out.println("Invincible Mario collects power-up, but stays invincible.");
    }
}