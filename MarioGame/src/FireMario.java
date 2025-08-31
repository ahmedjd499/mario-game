
public class FireMario implements MarioState {

    @Override
    public void move(Mario mario) {
        System.out.println("Fire Mario moves normally.");
        mario.setScore(mario.getScore() + 3);
    }

    @Override
    public void jump(Mario mario) {
        System.out.println("Fire Mario jumps high.");
    }

    @Override
    public void attack(Mario mario) {
        System.out.println("Fire Mario throws fireballs!");
    }

    @Override
    public void takeDamage(Mario mario) {
        System.out.println("Fire Mario takes damage and becomes Super Mario.");
        mario.setState(new SuperMario());
    }

    @Override
    public void collectPowerUp(Mario mario, PowerUp powerUp) {
        switch (powerUp) {
            case ICE:
                mario.setState(new IceMario());
                System.out.println("Mario becomes Ice Mario!");
                break;
            case FIRE:
                System.out.println("Already Fire Mario.");
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
