public class Weapon extends Item {
    protected  double damage;
    protected String type;

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Weapon(String name, int weight, int value, double damage) {
        super(name, weight, value);
        this.damage =damage;
        this.type = "Null";
        this.category= "weapon";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Damage: " + getDamage());
    }
}