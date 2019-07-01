package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return product by id=1"

	request {
		url "/stores/1/products/1"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id_product: "1",
			name: "Tv",
			stock: "100"
		)
	}
}