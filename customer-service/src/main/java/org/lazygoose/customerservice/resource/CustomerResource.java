/**
 * 
 */
package org.lazygoose.customerservice.resource;

import org.lazygoose.customerservice.dto.Customer;
import org.lazygoose.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author Deepak N
 *
 */

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerResource {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("test")
	public void test(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
		log.info("I am a test end point in customer service with auth token: {}", authHeader);
	}
	
	@GetMapping
	public Flux<Customer> listCustomers(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
		log.info("Listing customers with auth token: {}", authHeader);
		return customerService.listCustomers();
	}
}
