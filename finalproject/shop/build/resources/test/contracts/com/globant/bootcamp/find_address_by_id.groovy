package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return address by id=1"

	request {
		url "/stores/1/employees/1/address/1"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id_address: "1",
			country: "USA",
			state: "Florida",
			city: "Miami",
			street: "St 123",
			postalCode: "010203"
		)
	}
}