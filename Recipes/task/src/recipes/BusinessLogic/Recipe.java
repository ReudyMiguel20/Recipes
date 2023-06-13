package recipes.BusinessLogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Recipes")
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "ingredients")
    @Size(min = 1)
    @NotNull
    private ArrayList<String> ingredients;

    @Column(name = "directions")
    @Size(min = 1)
    @NotNull
    private ArrayList<String> directions;

    public Recipe() {
    }

    public Recipe(String name, String description, String ingredients, String directions) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>(List.of(ingredients));
        this.directions = new ArrayList<>(List.of(directions));
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
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
