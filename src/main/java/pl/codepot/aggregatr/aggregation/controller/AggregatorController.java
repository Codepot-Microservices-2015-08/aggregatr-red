package pl.codepot.aggregatr.aggregation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.aggregatr.aggregation.model.Ingredient;
import pl.codepot.aggregatr.aggregation.model.ItemsRequestBody;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;
import pl.codepot.aggregatr.aggregation.model.Version;
import pl.codepot.aggregatr.aggregation.service.IngredientService;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@RestController
@RequestMapping(consumes = Version.AGGREGATOR_V1)
public class AggregatorController {

    public static final Logger log = LoggerFactory.getLogger(AggregatorController.class);

    private IngredientService ingredientService;

    @Autowired
    public AggregatorController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.POST)
    public IngredientsResponse getIngredients(@RequestBody ItemsRequestBody ingredients){

        log.info("Items:" + ingredients);

        ingredientService.fillThreshold(ingredients.getItems());
        if(ingredientService.areThresholdsMet()){
            ingredientService.produceBeer();
        }
        IngredientsResponse response = ingredientService.getCurrentIngredientsState(ingredients.getItems());

        return response;
    }

}
