package steps;

import common.CommonServiceAPI;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import testBase.TestBaseCommon;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CalculateShippingCost extends TestBaseCommon {
    public CommonServiceAPI commonServiceAPIObject = new CommonServiceAPI();
    public long sourcePostCode;
    public long destinationPostCode;
    public String deliveryServiceTypeCode;
    public String totalShippingCost;

    @Before
    public void before() throws IOException, InterruptedException {
        initialise();
    }

    @Given("^User finds postcode for the source and destination$")
    public void findPostcodeForSourceAndDestination() {
        sourcePostCode = commonServiceAPIObject.getPostCode(sourceArea, sourceState);
        Assert.assertEquals(sourcePostCode, 2142);
        destinationPostCode = commonServiceAPIObject.getPostCode(destinationArea, destinationState);
        Assert.assertEquals(destinationPostCode, 3032);
    }

    /*Assumption: Values for parcel size is as per the Australia Post standard parcel sizes*/
    @When("^User selects a delivery type$")
    public void selectRandomDeliveryType() {
        List<String> deliveryServiceCodes = commonServiceAPIObject.getDeliveryTypeCodes(sourcePostCode, destinationPostCode);
        /*Randomly select a delivery service type*/
        Random rand = new Random();
        deliveryServiceTypeCode = deliveryServiceCodes.get(rand.nextInt(deliveryServiceCodes.size()));
    }

    @Then("^Calculate the total shipping cost$")
    public void calculateShippingCost() {
        totalShippingCost = commonServiceAPIObject.calculateDeliveryCost(sourcePostCode, destinationPostCode, deliveryServiceTypeCode);
        if (deliveryServiceTypeCode.equalsIgnoreCase("AUS_PARCEL_REGULAR")) {
            Assert.assertEquals(totalShippingCost, "16.25");
        } else if (deliveryServiceTypeCode.equalsIgnoreCase("AUS_PARCEL_EXPRESS")) {
            Assert.assertEquals(totalShippingCost, "26.40");
        } else if (deliveryServiceTypeCode.equalsIgnoreCase("AUS_PARCEL_EXPRESS_SATCHEL_3KG")) {
            Assert.assertEquals(totalShippingCost, "17.30");
        } else if (deliveryServiceTypeCode.equalsIgnoreCase("AUS_PARCEL_REGULAR_SATCHEL_3KG")) {
            Assert.assertEquals(totalShippingCost, "14.55");
        }
    }

}
