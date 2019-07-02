package org.springframework.cloud.contract.verifier.tests.com.globant;

import com.globant.bootcamp.shop.BaseClass;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class BootcampTest extends BaseClass {

	@Test
	public void validate_create_store() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.post("/stores");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['phone']").isEqualTo("12345");
			assertThatJson(parsedJson).field("['id_store']").isEqualTo("1");
			assertThatJson(parsedJson).field("['name']").isEqualTo("Exito");
	}

	@Test
	public void validate_find_address_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/stores/1/employees/1/address/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['city']").isEqualTo("Miami");
			assertThatJson(parsedJson).field("['postalCode']").isEqualTo("010203");
			assertThatJson(parsedJson).field("['id_address']").isEqualTo("1");
			assertThatJson(parsedJson).field("['state']").isEqualTo("Florida");
			assertThatJson(parsedJson).field("['street']").isEqualTo("St 123");
			assertThatJson(parsedJson).field("['country']").isEqualTo("USA");
	}

	@Test
	public void validate_find_employee_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/stores/1/employees/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['name']").isEqualTo("Juan Pablo");
			assertThatJson(parsedJson).field("['phone']").isEqualTo("320-3684334");
			assertThatJson(parsedJson).field("['id_employee']").isEqualTo("1");
	}

	@Test
	public void validate_find_product_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/stores/1/products/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['stock']").isEqualTo("100");
			assertThatJson(parsedJson).field("['name']").isEqualTo("Tv");
			assertThatJson(parsedJson).field("['id_product']").isEqualTo("1");
	}

	@Test
	public void validate_find_store_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/stores/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['phone']").isEqualTo("12345");
			assertThatJson(parsedJson).field("['id_store']").isEqualTo("1");
			assertThatJson(parsedJson).field("['name']").isEqualTo("Exito");
	}

}
