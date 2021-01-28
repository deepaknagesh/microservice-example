/**
 * 
 */
package org.lazygoose.customerservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Deepak N
 *
 */

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerResource {

	@GetMapping("test")
	public void test() {
		log.info("I am a test end point in customer service.");
	}
}
