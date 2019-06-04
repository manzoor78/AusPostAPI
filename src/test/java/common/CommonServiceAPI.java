package common;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import testBase.TestBaseCommon;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommonServiceAPI extends TestBaseCommon {

    public long getPostCode(String area, String state) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("q", area)
                .formParam("state", state)

                .when()
                .get(ausPostURL + "/postcode/search")

                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getLong("localities.locality.postcode");
    }

    public List<String> getDeliveryTypeCodes(long sourcePostcode, long destinationPostcode) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("from_postcode", sourcePostcode)
                .formParam("to_postcode", destinationPostcode)
                .formParam("length", 22)
                .formParam("width", 16)
                .formParam("height", 7.7)
                .formParam("weight", 1.5)

                .when()
                .get(ausPostURL + "/postage/parcel/domestic/service")

                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getList("services.service.code");

    }

    public String calculateDeliveryCost(long sourcePostcode, long destinationPostcode, String serviceCode) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("from_postcode", sourcePostcode)
                .formParam("to_postcode", destinationPostcode)
                .formParam("length", 22)
                .formParam("width", 16)
                .formParam("height", 7.7)
                .formParam("weight", 1.5)
                .formParam("service_code", serviceCode)

                .when()
                .get(ausPostURL + "/postage/parcel/domestic/calculate")
                
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("postage_result.total_cost");
    }
}
