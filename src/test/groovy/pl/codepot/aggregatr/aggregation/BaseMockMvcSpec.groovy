package pl.codepot.aggregatr.aggregation
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import pl.codepot.aggregatr.aggregation.controller.AggregatorController
import spock.lang.Specification

abstract class BaseMockMvcSpec extends Specification {

    def setup() {
        RestAssuredMockMvc.standaloneSetup(new AggregatorController())
    }

}
