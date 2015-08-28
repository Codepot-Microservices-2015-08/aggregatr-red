package pl.codepot.aggregatr.aggregation;

import pl.codepot.aggregatr.aggregation.model.Ingredient;
import pl.codepot.aggregatr.aggregation.model.IngredientType;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;
import pl.codepot.aggregatr.aggregation.service.CommunicationService;
import pl.codepot.aggregatr.aggregation.service.ExternalIngredientService;
import pl.codepot.aggregatr.aggregation.service.IngredientService;

import java.util.List;
import java.util.Map;

/**
 * Created by Pawelccb on 2015-08-28.
 */
public class IngredientServiceUberMock extends IngredientService {

    public IngredientServiceUberMock(ExternalIngredientService externalIngredientService, CommunicationService communicationService) {
        super(null, null);
    }

    @Override
    public boolean areThresholdsMet(){
        return false;
    }

    @Override
    public void produceBeer(){

    }

    @Override
    public void fillThreshold(List<String> ingredients){

    }

    @Override
    public IngredientsResponse getCurrentIngredientsState(List<String> items) {
        IngredientsResponse ingredientsResponse = new IngredientsResponse();
        for (String item : items) {
            ingredientsResponse.addIngredient(new Ingredient(item, 200));
        }
        return ingredientsResponse;
    }


}
