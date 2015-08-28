package pl.codepot.aggregatr.aggregation.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.aggregatr.aggregation.model.Ingredient;
import pl.codepot.aggregatr.aggregation.model.ItemsRequestBody;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;
import pl.codepot.aggregatr.aggregation.model.Version;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@RestController
@RequestMapping(consumes = Version.AGGREGATOR_V1)
public class AggregatorController {

    @RequestMapping(value = "/ingredients", method = RequestMethod.POST)
    public IngredientsResponse getIngredients(@RequestBody ItemsRequestBody ingredients){
        IngredientsResponse response = new IngredientsResponse();
        for (String ingredient : ingredients.getItems()) {
            response.addIngredient(new Ingredient(ingredient, 200));
        }
        return response;
    }

}
