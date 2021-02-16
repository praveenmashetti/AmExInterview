package com.interview.test.orderservice.service

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions

class OrderServiceTest {

	val service: OrderService = OrderService()

	@BeforeEach
	fun configureSystemUnderTest() {
	}

	@Test
	@DisplayName("place worder with 3 apples and 1 orange")
	fun placeOrderWithCartItems() {
		val service = OrderService()
		val items = arrayOf<String>("Apple", "Orange", "Apple", "Apple")
		var price = service.placeOrder(items)
		Assertions.assertEquals(price, 1.45)
	}

	@Test
	@DisplayName("place worder with 0 items")
	fun placeOrderWithZeroItems() {
		val service = OrderService()
		val items = arrayOf<String>()

		Assertions.assertThrows(Exception::class.java) {
			service.placeOrder(items)
		}

	}

	@Test
	@DisplayName("place worder with Invalid items")
	fun placeOrderWithInvalidItems() {
		val service = OrderService()
		val items = arrayOf<String>("Cherry")

		Assertions.assertThrows(Exception::class.java) {
			service.placeOrder(items)
		}

	}


}