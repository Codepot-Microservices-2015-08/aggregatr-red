package pl.codepot.aggregatr.aggregation.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Created by Pawelccb on 2015-08-28.
 */
public class IngredientsResponse {
    List<Ingredient> ingredients = Lists.newArrayList();

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
