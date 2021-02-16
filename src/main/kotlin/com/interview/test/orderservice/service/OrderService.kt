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
import com.interview.test.orderservice.model.DiscountCode

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
					cartPrice = cartPrice + calculatePrice(selectedItem, count)
				}
			}

			cartPrice = (cartPrice / 100)

			println("Order placed successfully. Total Cart Price: " + cartPrice)
		} else {
			throw OrderServiceException("No items found in the cart. Please chose some items for Billing")
		}

		return cartPrice
	}
	
	fun calculatePrice(item:Item, count:Int): Int {
		//cal regualar price
		val regualrPrice = count * item.price
		//apply discount if applicable
		val discount = DiscountCode.applyDiscount(item, count)
		
		println("Applied a disocunt of $discount on $item")
		return (regualrPrice-discount) 
	}

}


