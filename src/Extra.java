public interface Extra {
    void printInfo(Character character);

    double attack();

    double calculateWeight();

    void printInventory();

    void add(Item item);

    Item drop();

    void changeHandledItem();
}
