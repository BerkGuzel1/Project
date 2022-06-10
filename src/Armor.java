public class Armor extends Item {
    public Armor(String name, int weight, int value) {
        super(name, weight, value);
        super.category = "armor";
    }

    @Override
    public void printInfo() {
        super.printInfo();
    }
}
