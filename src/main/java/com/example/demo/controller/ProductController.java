package com.example.demo.controller;

import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.helpers.ZXingHelper;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String index(Model model) {
		model.addAttribute("products", productService.findAll());
		return "index";
	}

	@GetMapping("/product/{id}/qrcode")
	public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage(id, 200, 200));
		outputStream.flush();
		outputStream.close();
	}

}