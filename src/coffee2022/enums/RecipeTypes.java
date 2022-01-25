package coffee2022.enums;

public enum RecipeTypes {
    FOOD("Food items"),
    BEVERAGE("Beverages"),
    PASTRIES("Pastries");

    public final String label;

    private RecipeTypes(String label) {
        this.label = label;
    }
}