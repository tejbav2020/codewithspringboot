/**
 * 
 */
package com.redhat.demo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tebavish
 *
 */
@RestController
@RequestMapping("/accounts-transactions-resteasy")
public class BankAccountController {

	public static final Logger logger = Logger.getLogger(BankAccountController.class.toString());

	public BankAccountController() {
		accountscollection
				.add(new BankAccount("Mr Harry", "123456789", "London", new BigDecimal(123456.66), "Premium"));
		accountscollection
				.add(new BankAccount("Mr James", "1234567890", "USA", new BigDecimal(12345688.66), "Standard"));
	}

	private Set<BankAccount> accountscollection = Collections
			.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	@GetMapping("/hello")
	public String hello() {
		return "Hello RESTEasy";
	}

	@GetMapping("/getdefaultcurrency")
	public String getdefaultcurrency() {
		return "The default currency is INR";
	}

	@GetMapping(path = "/getsingleaccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getsingleaccount() {
		return accountscollection.iterator().next().toString();
	}

	@GetMapping(path = "/getallaccounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getallaccounts() {
		return accountscollection.toString();
	}

	
	@PostMapping(path = "/addcustomer")
	public ResponseEntity<BankAccount> addcustomer(@RequestBody BankAccount account) {
		logger.warning("**adding customer with name ,  " + account.getName());
		accountscollection.add(account);
		return ResponseEntity.ok(account);

	}

}
