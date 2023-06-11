package recipes.BusinessLogic;

import java.util.Objects;

public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;

    public Recipe() {
    }

    public Recipe(String name, String description, String ingredients, String directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipes = (Recipe) o;
        return Objects.equals(name, recipes.name) && Objects.equals(description, recipes.description) && Objects.equals(ingredients, recipes.ingredients) && Objects.equals(directions, recipes.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ingredients, directions);
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                '}';
    }
}
