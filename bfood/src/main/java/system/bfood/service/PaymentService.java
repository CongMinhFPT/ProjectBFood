package system.bfood.service;

import com.stripe.exception.StripeException;

import system.bfood.model.Order;
import system.bfood.response.PaymentResponse;

public interface PaymentService {
	public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
