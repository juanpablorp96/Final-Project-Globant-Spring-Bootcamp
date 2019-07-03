package com.globant.bootcamp

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return the store"

	request {
		url "/stores/1"
		method DELETE()
	}

	response {
		status CREATED()
	}
}