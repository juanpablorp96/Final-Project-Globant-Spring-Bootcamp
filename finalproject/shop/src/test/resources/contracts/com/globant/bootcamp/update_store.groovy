package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return the store updated"

	request {
		url "/stores/2"
		method PUT()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id_store: "2",
			name: "PriceSmart",
			phone: "00000"
		)
	}
}