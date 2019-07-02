package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return the store"

	request {
		url "/stores"
		method POST()
	}

	response {
		status CREATED()
		headers {
			contentType applicationJson()
		}
		body (
			id_store: "1",
			name: "Exito",
			phone: "12345"
		)
	}
}