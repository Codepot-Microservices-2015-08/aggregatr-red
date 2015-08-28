package pl.codepot.aggregatr.aggregation.service;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codepot.aggregatr.aggregation.model.Ingredient;
import pl.codepot.aggregatr.aggregation.model.IngredientType;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@Service
public class IngredientService {

    private final static int treshold = 1000;
    private Map<IngredientType, Integer> availableAmounts = Maps.newConcurrentMap();

    private ExternalIngredientService externalIngredientService;
    private CommunicationService communicationService;

    @Autowired
    public IngredientService(ExternalIngredientService externalIngredientService, CommunicationService communicationService) {
        this.externalIngredientService = externalIngredientService;
        this.communicationService = communicationService;
        for (IngredientType ingredientType : IngredientType.values()) {
            availableAmounts.put(ingredientType, 0);
        }
    }

    public boolean areThresholdsMet(){
        for (Integer amount : availableAmounts.values()) {
            if(amount < treshold ){
                return false;
            }
        }
        return true;
    }

    public void produceBeer(){
        IngredientsResponse ingredientsResponse = new IngredientsResponse();
        for (Map.Entry<IngredientType, Integer> ingredient : availableAmounts.entrySet()) {
            availableAmounts.put(ingredient.getKey(), ingredient.getValue() - treshold);
            ingredientsResponse.addIngredient(new Ingredient(ingredient.getKey().name(), treshold));
        }
        communicationService.sendProductionRequest(ingredientsResponse);
    }

    public void fillThreshold(List<String> ingredients){
        for (String ingredient : ingredients) {
            IngredientType ingredientType = IngredientType.valueOf(ingredient);
            int amount = externalIngredientService.obtainIngredient(ingredient);
            Integer currentAmount = availableAmounts.get(ingredientType);
            availableAmounts.put(ingredientType, currentAmount + amount);
        }
    }

    public IngredientsResponse getCurrentIngredientsState(List<String> items) {
        IngredientsResponse ingredientsResponse = new IngredientsResponse();
        for (Map.Entry<IngredientType, Integer> ingredient : availableAmounts.entrySet()) {
            if(items.contains(ingredient.getKey())) {
                ingredientsResponse.addIngredient(new Ingredient(ingredient.getKey().name(), ingredient.getValue()));
            }
        }
        return ingredientsResponse;
    }
}
