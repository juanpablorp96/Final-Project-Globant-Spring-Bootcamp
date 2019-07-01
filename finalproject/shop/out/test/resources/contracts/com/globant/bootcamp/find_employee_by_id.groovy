package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return employee by id=1"

	request {
		url "/stores/1/employees/1"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id_employee: "1",
			name: "Juan Pablo",
			phone: "320-3684334"
		)
	}
}