package pl.codepot.aggregatr.aggregation.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@Service
public class ExternalIngredientService {

    Random random = new Random();

    public int obtainIngredient(String name){
        return random.nextInt(50) + 50;
    }
}
