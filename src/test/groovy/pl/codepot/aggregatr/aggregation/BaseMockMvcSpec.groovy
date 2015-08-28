package pl.codepot.aggregatr.aggregation
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import pl.codepot.aggregatr.aggregation.controller.AggregatorController
import pl.codepot.aggregatr.aggregation.service.IngredientService
import spock.lang.Specification

abstract class BaseMockMvcSpec extends Specification {

    def setup() {
        IngredientService servie = new IngredientServiceUberMock(null, null);
        RestAssuredMockMvc.standaloneSetup(new AggregatorController(servie))
    }

}
