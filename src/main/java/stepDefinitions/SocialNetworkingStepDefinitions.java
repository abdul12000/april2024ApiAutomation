package stepDefinitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocialNetworkingStepDefinitions extends BaseSteps {
    Response responseForGetAPostCall, responseForCreateAPostCall, responseForUpdateAPostCall, responseForDeleteAPostCall;

    @Given("service jsonplaceholder service is up and running")
    public void service_jsonplaceholder_service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(ServiceUrl);
        Response responseForServiceCall = getCall();
        assertThat(responseForServiceCall.statusCode(), equalTo(200));
    }

    @When("I send GET request to get a specific post with {string}")
    public void i_send_get_request_to_get_a_specific_post_with(String id) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl + id);
        responseForGetAPostCall = getCall();
    }

    @Then("the specific post details {string}, {string} and {string}  are returned with status code of {int}")
    public void the_specific_post_details_and_are_returned_with_status_code_of(String id, String title, String body, Integer sCode) {
assertThat(responseForGetAPostCall.statusCode(), equalTo(sCode));
        assertThat(responseForGetAPostCall.body().jsonPath().get("id"), equalTo(Integer.parseInt(id)));
        assertThat(responseForGetAPostCall.body().jsonPath().get("title"), equalTo(title));
        assertThat(responseForGetAPostCall.body().jsonPath().get("body"), equalTo(body));

    }

    @When("I make a post with the following details {string}, {string} and {string}")
    public void i_make_a_post_with_the_following_details_and(String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(PostPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody, uId, title, body);
        responseForCreateAPostCall = getPostCall();
    }
    @Then("I should get a response with status code of {int} and the following {string}, {string} and {string}")
    public void i_should_get_a_response_with_status_code_of_and_the_following_and(Integer sCode, String uId, String title, String body) {
      assertThat(responseForCreateAPostCall.statusCode(), equalTo(sCode));
        assertThat(responseForCreateAPostCall.jsonPath().get("userId"), equalTo(uId));
        assertThat(responseForCreateAPostCall.jsonPath().get("title"), equalTo(title));
        assertThat(responseForCreateAPostCall.jsonPath().get("body"), equalTo(body));
    }

    @When("I update an existing post with {string}  using the following details {string}, {string} and {string}")
    public void iUpdateAnExistingPostWithUsingTheFollowingDetailsAnd(String id, String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl + id);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(PostPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody, uId, title, body);
        responseForUpdateAPostCall = getPutCall();
    }

    @Then("I should get a response with status code of {int} and the following {string}, {string} and {string} for Put")
    public void iShouldGetAResponseWithStatusCodeOfAndTheFollowingAndForPut(int sCode, String uId, String title, String body) {
        assertThat(responseForUpdateAPostCall.statusCode(), equalTo(sCode));
        assertThat(responseForUpdateAPostCall.jsonPath().get("userId"), equalTo(uId));
        assertThat(responseForUpdateAPostCall.jsonPath().get("title"), equalTo(title));
        assertThat(responseForUpdateAPostCall.jsonPath().get("body"), equalTo(body));
    }

    @When("I send DELETE request for a specific post with {string}")
    public void iSendDELETERequestForASpecificPostWith(String id) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl + id);
        responseForDeleteAPostCall = getCall();
    }

    @Then("the status code of {int} is returned")
    public void theStatusCodeOfIsReturned(int sCode) {
            assertThat(responseForDeleteAPostCall.statusCode(), equalTo(sCode));
    }
}
