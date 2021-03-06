package ro.elemental.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import ro.elemental.Appconfig;
import ro.elemental.TestBase;
import ro.elemental.pageobjects.Header;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps extends TestBase {

    private Header header = PageFactory.initElements(driver, Header.class);
    private ro.elemental.pageobjects.Login login = PageFactory.initElements(driver, ro.elemental.pageobjects.Login.class);

    @Given("^User start the homepage$")
    public void userStartTheHomepage() {
        driver.get(Appconfig.getSiteUrl());
        driver.manage().window().maximize();
    }


    @And("^User click to the my account link$")
    public void userClickToTheMyAccountLink() {

        header.clickONlogin();
    }

    @When("^User submit the correct password and username and click to submit button$")
    public void userSubmitTheCorrectPasswordAndUsernameAndClickToSubmitButton() throws InterruptedException {

        Thread.sleep(2000);





        login.loginToMyAccount(getEmail(), getPassword());

    }

    @Then("^User will be logged in my account area$")
    public void userWillBeLoggedInMyAccountArea() throws InterruptedException {

        Thread.sleep(2000);
        String pageTitle = driver.getTitle();
        String expactedTitle = "Contul Meu - Elemental";

        assertThat("You are not in the account page. ", pageTitle, is(expactedTitle));



    }

    @When("^User submit the incorrect password and username and click to submit button$")
    public void userSubmitTheIncorrectPasswordAndUsernameAndClickToSubmitButton() throws InterruptedException {
        Thread.sleep(2000);


       String password = "password";


        login.loginToMyAccount(getEmail(), password);
    }

    @Then("^User get an error message$")
    public void userGetAnErrorMessage() {

        String alert = login.getAlertMessage().getText();
        String waitedAlert = "Autentificare esuata";
        assertThat("You submit the wrong credentials, please verify your password, or email adress. ", waitedAlert, is(alert));
    }

    @When("^User submit the correct password but no email adress and click to submit button$")
    public void userSubmitTheCorrectPasswordButNoEmailAdressAndClickToSubmitButton() {

        String emptyEmail = "";

        login.loginToMyAccount(emptyEmail, getPassword());
    }

    @Then("^User get the empty email error message$")
    public void userGetTheEmptyEmailErrorMessage() {

        String alert = login.getAlertMessage().getText();
        String waitedAlert = "Adresa de e-mail este obligatorie";
        assertThat("You submit the wrong credentials, please verify your password, or email adress. ", waitedAlert, is(alert));
    }

    @Then("^User get the empty password error message$")
    public void userGetTheEmptyPasswordErrorMessage() {

        String alert = login.getAlertMessage().getText();
        String waitedAlert = "Parola este obligatorie";
        assertThat("You submit the wrong credentials, please verify your password, or email adress. ", waitedAlert, is(alert));


    }

    @When("^User submit  correct username but empty password and click to submit button$")
    public void userSubmitCorrectUsernameButEmptyPasswordAndClickToSubmitButton() {

        String emptyPassword = "";

        login.loginToMyAccount(getEmail(), emptyPassword);
    }
}

