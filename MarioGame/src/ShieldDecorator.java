
public class ShieldDecorator extends PowerUpDecorator {

    @Override
    public int getHealthBonus() {
        return 2;
    }

    @Override
    public int getStrengthBonus() {
        return 0;
    }

    @Override
    public double getSpeedBonus() {
        return 0;
    }
}
