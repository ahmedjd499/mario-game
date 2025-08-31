
public class SuperMario implements MarioState {

    @Override
    public void move(Mario mario) {
        System.out.println("Super Mario moves normally.");
        mario.setScore(mario.getScore() + 2);
    }

    @Override
    public void jump(Mario mario) {
        System.out.println("Super Mario jumps high.");
    }

    @Override
    public void attack(Mario mario) {
        System.out.println("Super Mario kicks.");
    }

    @Override
    public void takeDamage(Mario mario) {
        System.out.println("Super Mario takes damage and becomes small.");
        mario.setState(new SmallMario());
        mario.setHealth(1);
    }

    @Override
    public void collectPowerUp(Mario mario, PowerUp powerUp) {
        switch (powerUp) {
            case FIRE:
                mario.setState(new FireMario());
                System.out.println("Mario becomes Fire Mario!");
                break;
            case ICE:
                mario.setState(new IceMario());
                System.out.println("Mario becomes Ice Mario!");
                break;
            case SUPER:
                System.out.println("Already Super Mario.");
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
