
public class IceMario implements MarioState {

    @Override
    public void move(Mario mario) {
        System.out.println("Ice Mario moves normally.");
        mario.setScore(mario.getScore() + 3);
    }

    @Override
    public void jump(Mario mario) {
        System.out.println("Ice Mario jumps high.");
    }

    @Override
    public void attack(Mario mario) {
        System.out.println("Ice Mario throws ice balls!");
    }

    @Override
    public void takeDamage(Mario mario) {
        System.out.println("Ice Mario takes damage and becomes Super Mario.");
        mario.setState(new SuperMario());
    }

    @Override
    public void collectPowerUp(Mario mario, PowerUp powerUp) {
        switch (powerUp) {
            case FIRE:
                mario.setState(new FireMario());
                System.out.println("Mario becomes Fire Mario!");
                break;
            case ICE:
                System.out.println("Already Ice Mario.");
                break;
            case INVINCIBLE:
                mario.setState(new InvincibleMario(mario.getState()));
                System.out.println("Mario becomes Invincible!");
                break;
            default:
                System.out.println("Power-up collected.");
                break;
        }
    }
}
