public interface MarioState {
    void move(Mario mario);
    void jump(Mario mario);
    void attack(Mario mario);
    void takeDamage(Mario mario);
    void collectPowerUp(Mario mario, PowerUp powerUp);
}