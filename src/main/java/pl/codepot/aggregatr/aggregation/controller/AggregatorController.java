package pl.codepot.aggregatr.aggregation.controller;

import com.google.common.collect.Maps;
import org.bouncycastle.asn1.crmf.POPOSigningKey;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.aggregatr.aggregation.model.Ingredient;
import pl.codepot.aggregatr.aggregation.model.IngredientsRequestBody;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;
import pl.codepot.aggregatr.aggregation.model.Version;

import java.util.List;
import java.util.Map;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@RestController
@RequestMapping(consumes = Version.AGGREGATOR_V1)
public class AggregatorController {

    @RequestMapping(value = "/ingredients", method = RequestMethod.POST)
    public IngredientsResponse getIngredients(@RequestBody IngredientsRequestBody ingredients){
        IngredientsResponse response = new IngredientsResponse();
        for (String ingredient : ingredients.getIngredients()) {
            response.addIngredient(new Ingredient(ingredient, 200));
        }
        return response;
    }

}
