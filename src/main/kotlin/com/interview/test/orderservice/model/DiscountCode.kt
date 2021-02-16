package com.interview.test.orderservice.model

enum class DiscountCode(val discount: Int) {

	BOGO(2),
	B2GO(3);


	companion object {
		fun applyDiscount(item: Item, totalItemCount: Int): Int {
			var itemDiscount = 0
			if (item.enableDiscount) {
				val discountNumber = item.discountCode.discount
				itemDiscount = (totalItemCount/ discountNumber) * item.price
			}
			return itemDiscount
		}
	}
}