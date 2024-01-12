package com.example.demo.controller;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Users; 
import com.example.demo.services.UsersServices;
import com.razorpay.Order; import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired UsersServices service;
	@GetMapping("/pay")
public String pay() {
	return "pay";
}

@SuppressWarnings("finally")
@PostMapping("/createOrder")
@ResponseBody
public String createOrder(HttpSession session) {

	int  amount  = 1;
	Order order=null;
	try {
		RazorpayClient razorpay=new RazorpayClient("rzp_test_IqHiXdxloU726E","b8T0Gi4KXEAAHcE1Aaf6Lg8J");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount*100); // amount in the smallest currency unit
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "order_rcptid_11");
		order = razorpay.orders.create(orderRequest);
		String mail =  (String) session.getAttribute("email");
		Users u = service.getUsers(mail);
		u.setPremium(true);
		service.updatUsers(u);

	} catch (RazorpayException e) {
		e.printStackTrace();
	}
	finally {
		return order.toString();
	}
}
}
