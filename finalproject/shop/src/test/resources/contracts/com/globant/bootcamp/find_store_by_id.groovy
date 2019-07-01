package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return store by id=1"

	request {
		url "/client/stores/1"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id: 1,
			name: "Exito",
			phone: "12345"
		)
	}
}