/**
 * 
 */
package org.lazygoose.customerservice.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.lazygoose.customerservice.dto.Customer;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author Deepak N
 *
 */

@Slf4j
@Service
public class CustomerService {

	private static final List<Customer> CUSTOMERS = new ArrayList<>(3);
	
	static {
		CUSTOMERS.add(new Customer("Deepak D C", 30));
		CUSTOMERS.add(new Customer("Deepak N", 31));
		CUSTOMERS.add(new Customer("Deepak A S", 41));
	}
	
	public Flux<Customer> listCustomers() {
		log.info("Listing all customers");
		return Flux.fromIterable(CUSTOMERS).delayElements(Duration.ofMillis(200));
	}
}
