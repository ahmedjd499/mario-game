
public class SpeedBoostDecorator extends PowerUpDecorator {

    @Override
    public int getHealthBonus() {
        return 0;
    }

    @Override
    public int getStrengthBonus() {
        return 0;
    }

    @Override
    public double getSpeedBonus() {
        return 0.5;
    }
}
