package com.globant.bootcamp.client;

import com.globant.bootcamp.client.model.Address;
import com.globant.bootcamp.client.model.Employee;
import com.globant.bootcamp.client.model.Product;
import com.globant.bootcamp.client.model.Store;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
			.downloadStub("com.globant.bootcamp", "shop", "0.0.1-SNAPSHOT", "stubs")
			.withPort(8100)
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);

	@Test
	public void get_store_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();

		// when:
		ResponseEntity<Store> entity = restTemplate.getForEntity("http://localhost:8100/stores/1", Store.class);

		// then:
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(entity.getBody().getId_store() == 1);
		BDDAssertions.then(entity.getBody().getName()).isEqualTo("Exito");
		BDDAssertions.then(entity.getBody().getPhone()).isEqualTo("12345");

	}

	@Test
	public void get_product_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();

		// when:
		ResponseEntity<Product> entity = restTemplate.getForEntity("http://localhost:8100/stores/1/products/1", Product.class);

		// then:
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(entity.getBody().getId_product() == 1);
		BDDAssertions.then(entity.getBody().getName()).isEqualTo("Tv");
		BDDAssertions.then(entity.getBody().getStock()).isEqualTo(100);

	}

	@Test
	public void get_employee_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();

		// when:
		ResponseEntity<Employee> entity = restTemplate.getForEntity("http://localhost:8100/stores/1/employees/1", Employee.class);

		// then:
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(entity.getBody().getId_employee() == 1);
		BDDAssertions.then(entity.getBody().getName()).isEqualTo("Juan Pablo");
		BDDAssertions.then(entity.getBody().getPhone()).isEqualTo("320-3684334");

	}

	@Test
	public void get_address_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();

		// when:
		ResponseEntity<Address> entity = restTemplate.getForEntity(
				"http://localhost:8100/stores/1/employees/1/address/1", Address.class);

		// then:
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(entity.getBody().getId_address() == 1);
		BDDAssertions.then(entity.getBody().getCountry()).isEqualTo("USA");
		BDDAssertions.then(entity.getBody().getState()).isEqualTo("Florida");
		BDDAssertions.then(entity.getBody().getCity()).isEqualTo("Miami");
		BDDAssertions.then(entity.getBody().getStreet()).isEqualTo("St 123");
		BDDAssertions.then(entity.getBody().getPostalCode()).isEqualTo("010203");


	}

}
