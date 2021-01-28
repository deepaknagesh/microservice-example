/**
 * 
 */
package org.lazygoose.authservice.resource;

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
@RequestMapping("auth")
public class AuthResource {

	@GetMapping("test")
	public void test() {
		log.info("I am a auth test end point in auth service.");
	}
}

