package stepDefinations;

import org.testng.annotations.Parameters;

import com.qa.hubspot.keyword.Engine.KeyWordEngine;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination {
	KeyWordEngine keyWordEngine = new KeyWordEngine();
	
	@Given("user opens the given application")
	public void user_opens_the_given() {
		keyWordEngine.startExecution("login");
	}

	@When("user  search for Apple Mobile")
	public void user_search_for_Apple_Mobile() {

		
	}

	@Then("Count the results")
	public void count_the_results() {
		
	}

	@Then("print the mobile name")
	public void print_the_mobile_name() {
	
	}

	@Then("print the mobile price")
	public void print_the_mobile_price() {
		
	}
}
