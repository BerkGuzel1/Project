public class Item {
    private String name;
    private int weight;
    private int value;
    protected  String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public void printInfo(){
        System.out.println("Category: " + getCategory());
        System.out.println("Name: " + getName());
        System.out.println("Weight: " + getWeight());
        System.out.println("Value: " + getValue());
    }
}
