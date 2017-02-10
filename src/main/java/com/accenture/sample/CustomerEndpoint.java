package com.accenture.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCustomerRequest;
import io.spring.guides.gs_producing_web_service.GetCustomerResponse;

@Endpoint
public class CustomerEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerEndpoint(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
	@ResponsePayload
	public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
		GetCustomerResponse response = new GetCustomerResponse();
		response.setCustomer(customerRepository.findCustomer(request.getNino()));
		return response;
	}
}
