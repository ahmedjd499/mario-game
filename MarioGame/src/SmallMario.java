
public class SmallMario implements MarioState {

    @Override
    public void move(Mario mario) {
        System.out.println("Small Mario moves slowly.");
        mario.setScore(mario.getScore() + 1);
    }

    @Override
    public void jump(Mario mario) {
        System.out.println("Small Mario jumps low.");
    }

    @Override
    public void attack(Mario mario) {
        System.out.println("Small Mario punches.");
    }

    @Override
    public void takeDamage(Mario mario) {
        System.out.println("Small Mario takes damage and dies.");
        mario.setLives(mario.getLives() - 1);
        if (mario.getLives() <= 0) {
            System.out.println("Game Over!");
        }
    }

    @Override
    public void collectPowerUp(Mario mario, PowerUp powerUp) {
        switch (powerUp) {
            case SUPER:
                mario.setState(new SuperMario());
                mario.setHealth(2);
                System.out.println("Mario becomes Super Mario!");
                break;
            case FIRE:
                mario.setState(new FireMario());
                System.out.println("Mario becomes Fire Mario!");
                break;
            case ICE:
                mario.setState(new IceMario());
                System.out.println("Mario becomes Ice Mario!");
                break;
            case INVINCIBLE:
                mario.setState(new InvincibleMario(mario.getState()));
                System.out.println("Mario becomes Invincible!");
                break;
            case SPEED_BOOST:
                mario.addDecorator(new SpeedBoostDecorator());
                System.out.println("Speed boosted!");
                break;
            case SHIELD:
                mario.addDecorator(new ShieldDecorator());
                System.out.println("Shield activated!");
                break;
            case FLYING:
                mario.addDecorator(new FlyingDecorator());
                System.out.println("Flying ability gained!");
                break;
            case DOUBLE_JUMP:
                mario.addDecorator(new DoubleJumpDecorator());
                System.out.println("Double jump unlocked!");
                break;
            default:
                System.out.println("Power-up collected, but no state change.");
                break;
        }
    }
}
