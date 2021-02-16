package com.interview.test.orderservice.service

import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.KafkaProducer
import java.util.Properties
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import com.interview.test.orderservice.model.Item
import com.interview.test.orderservice.exception.OrderServiceException


class OrderService {

	fun placeOrder(cart: Array<String>): Double {

		println("Placing an order with the given cart. $cart")
		var cartPrice: Double = 0.0


		if (!cart.isEmpty()) {

			//group the cart items
			val cartItems = cart.groupingBy { it.apply {} }.eachCount()

			for ((item, count) in cartItems) {

				//check if item is valid
				val selectedItem: Item? = Item.getItem(item)
				if (selectedItem === null) {
					println("Invalid cart item Found.")
					throw OrderServiceException("Invalid Item: $item")
				} else {
					//Add item to the bill
					println("Adding $count $item(s) to the order")
					cartPrice = cartPrice + (count * selectedItem?.price)
				}
			}

			cartPrice = (cartPrice / 100)

			println("Order placed successfully. Total Cart Price: " + cartPrice)
		} else {
			throw OrderServiceException("No items found in the cart. Please chose some items for Billing")
		}

		return cartPrice
	}

}


