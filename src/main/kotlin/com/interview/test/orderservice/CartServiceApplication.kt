package com.interview.test.orderservice

import  com.interview.test.orderservice.service.OrderService

//Accepts any Program Arguments from command line
fun main(args: Array<String>) {

	val orderService: OrderService = OrderService()

	println("Recevied items: " + args)
	orderService.placeOrder(args);

}

