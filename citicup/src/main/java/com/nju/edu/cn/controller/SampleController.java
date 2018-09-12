package com.nju.edu.cn.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.util.GetAccounts;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//@Controller
public class SampleController {
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(SampleController.class);
//	}

	APIContext context = new APIContext();
	
//	@RequestMapping("/")
//	public String index(Model model,HttpServletRequest request) throws IOException {
//        GetAccounts accs = new GetAccounts();
//		accs.getBizToken(request.getServletContext());
//		String modulus = (String) map.get("modulus");
//		String exponent = (String) map.get("exponent");
//		String eventId = (String) map.get("eventId");
//		model.addAttribute("modulus", modulus);
//		model.addAttribute("exponent", exponent);
//		model.addAttribute("eventId", eventId);
//		if(modulus == null || exponent == null || eventId == null) {
//			return "errorPage";
//		}else {
//			return "index";
//		}
//	}
	
//    @RequestMapping("/login")
//    public String login(HttpServletRequest request, Model model) throws IOException{
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        GetAccounts accs = new GetAccounts();
//       	String accounts = accs.getAccounts(username, password,context);
//        if(accounts != null){
//    		model.addAttribute("accounts", accounts);
//    		return "accountSummary";
//        }else{
//        	return "errorPage";
//        }
//    }
//
//
//    @RequestMapping("/account")
//    public String account(HttpServletRequest request, Model model) throws IOException{
//        String accountId = request.getParameter("accountId");
//        GetAccounts accs = new GetAccounts();
//       	String accountDetails = accs.getAccountDetail(accountId, context);
//       	String transactionDetails = accs.getTransactions(accountId, context);
//       	if(accountDetails != null) {
//    		model.addAttribute("accountDetails", accountDetails);
//       	}
//       	if(transactionDetails != null){
//    		model.addAttribute("transactionDetails", transactionDetails);
//       	}
//        return "account";
//    }
//
//    @RequestMapping("/backtoAccountSummary")
//    public String backtoAccountSummary(HttpServletRequest request, Model model) throws IOException{
//    	String username = context.getUsername();
//        String password = context.getPassword();
//        GetAccounts accs = new GetAccounts();
//       	String accounts = accs.getAccounts(username, password,context);
//        if(accounts != null){
//    		model.addAttribute("accounts", accounts);
//    		return "accountSummary";
//        }else{
//        	return "errorPage";
//        }
//    }
}