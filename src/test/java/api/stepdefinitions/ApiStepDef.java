package api.stepdefinitions;

import api.pages.ApiPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ApiStepDef {
    // Inisialisasi objek halaman Java
    ApiPage apiPage = new ApiPage();

    @Given("base API URL {string}")
    public void baseAPIURL(String url) {
        apiPage.setBaseUrl(url);
    }

    @Given("authentication using app-id token")
    public void authenticationUsingAppIdToken() {
        apiPage.setAuthToken();
    }

    @When("I send a POST request to create a new user with body:")
    public void iSendAPOSTRequestToCreateANewUserWithBody(String docString) {
        apiPage.createUser(docString);
    }

    @When("I send a GET request to the endpoint to get all of the users list")
    public void iSendAGETRequestToTheEndpointToGetAllOfTheUsersList() {
        apiPage.getAllUsers();
    }

    @When("I send a PUT request for id {string} with body:")
    public void iSendAPUTRequestForIdWithBody(String userId, String docString) {
        apiPage.updateUser(userId, docString);
    }

    @When("I send a delete request for id {string}")
    public void iSendADeleteRequestForId(String userId) {
        apiPage.deleteUser(userId);
    }

    @Then("the API response status code should be {int}")
    public void theAPIResponseStatusCodeShouldBe(int expectedStatus) {
        if (apiPage.lastResponse.getStatusCode() != expectedStatus) {
            System.out.println("====== [DEBUG ERROR API SERVER] ======");
            System.out.println("Status Code Aktual: " + apiPage.lastResponse.getStatusCode());
            System.out.println("Detail Pesan Error dari Server:");
            apiPage.lastResponse.prettyPrint(); // Ini akan mencetak JSON Error secara rapi
            System.out.println("======================================");
        }
        Assert.assertEquals("Status code API tidak sesuai!", expectedStatus, apiPage.lastResponse.getStatusCode());
    }

    @Then("the response field {string} should be {string}")
    public void theResponseFieldShouldBe(String key, String expectedValue) {
        // Membaca path data JSON murni menggunakan metode bawaan RestAssured Java (.jsonPath)
        String actualValue = apiPage.lastResponse.jsonPath().getString(key);
        Assert.assertEquals("Nilai field response JSON tidak cocok!", expectedValue, actualValue);
    }

    @When("I send a GET request to the endpoint to get all of the tags")
    public void iSendAGETRequestToTheEndpointToGetAllOfTheTags() {
        apiPage.getAlltags();
    }

    @Then("the response should return the correct deleted user ID")
    public void theResponseShouldReturnTheCorrectDeletedUserID() {
        String actualDeletedId = apiPage.lastResponse.jsonPath().getString("id");
        Assert.assertNotNull("ID pengguna yang dihapus kosong atau null!", actualDeletedId);
    }

}