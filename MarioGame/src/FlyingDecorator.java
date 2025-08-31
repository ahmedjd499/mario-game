
public class FlyingDecorator extends PowerUpDecorator {

    @Override
    public int getHealthBonus() {
        return 0;
    }

    @Override
    public int getStrengthBonus() {
        return 1;
    }

    @Override
    public double getSpeedBonus() {
        return 0.2;
    }
}
