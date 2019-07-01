package com.globant.bootcamp.client;

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
		ResponseEntity<Store> entity = restTemplate.getForEntity("http://localhost:8100/client/stores/1", Store.class);

		// then:
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(entity.getBody().getId_store()).isEqualTo(1);
		BDDAssertions.then(entity.getBody().getName()).isEqualTo("Exito");
		BDDAssertions.then(entity.getBody().getPhone()).isEqualTo("12345");

	}

}
