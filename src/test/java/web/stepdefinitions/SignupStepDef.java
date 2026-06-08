package web.stepdefinitions;

import common.hooks.WebHook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import web.pages.SignUpPage;

public class SignupStepDef {
    private SignUpPage signUpPage;

    public SignupStepDef() {
        this.signUpPage = new SignUpPage(WebHook.getDriver());
    }

    @Given("User in the homepage")
    public void bukaHalamanUtama() {
        WebHook.getDriver().get("https://www.demoblaze.com/");
    }
    @When("User click on menu \"Sign up\"")
    public void klikMenuSignUp() {
        signUpPage.clickSignUpMenu();
    }
    @And("User fill out the username {string}")
    public void isiUsernameSignUp(String username) {
        WebHook.currentUsername = username;
        signUpPage.fillUsername(username);
    }
    @And("User fill out the password {string}")
    public void isiPasswordSignUp(String password) {
        signUpPage.fillPassword(password);
    }

    @And("User click on the sign up button")
    public void konfirmasiPendaftaran() {
        signUpPage.clickRegisterButton();
    }
    @Then("System shows the succesfull message {string}")
    public void validasiAlertDaftar(String expectedMsg) {
        Assertions.assertEquals(expectedMsg, signUpPage.getAlertTextAndAccept());
    }

}
