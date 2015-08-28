package pl.codepot.aggregatr.aggregation.model;

import java.util.List;

/**
 * Created by Pawelccb on 2015-08-28.
 */
public class IngredientsRequestBody {

    private List<String> ingredients;

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
