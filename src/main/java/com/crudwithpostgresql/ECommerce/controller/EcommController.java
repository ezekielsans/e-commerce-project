package com.crudwithpostgresql.ECommerce.controller;


import com.crudwithpostgresql.ECommerce.model.Products;
import com.crudwithpostgresql.ECommerce.service.EcommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EcommController {

    @Autowired
    EcommService ecommService;


    @GetMapping({"/", "/home"})
    public String viewHomepage(@ModelAttribute("message") String message, Model model) {

        model.addAttribute("productList", ecommService.getAllProducts());
        model.addAttribute("message", message);
        //model.addAttribute("product", new Products());

        return "home";
    }


    @GetMapping("/viewProducts")
    public String viewProducts(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("productList", ecommService.getAllProducts());
        model.addAttribute("message", message);
        //model.addAttribute("product", new Products());

        return "viewProducts";
    }

    @GetMapping("/addProduct")

    public String addProduct(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("productList", new Products());
        return "addProduct";

    }


    @PostMapping("/saveProduct")
    public String saveProduct(Products products, RedirectAttributes redirectAttributes) {
        if (ecommService.saveOrUpdateProduct(products)) {
            redirectAttributes.addFlashAttribute("message", "Product saved");
            return "home";

        }
        redirectAttributes.addFlashAttribute("message", "Failed to save product");
        return "home";

    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Products products = ecommService.getProductById(id);
        model.addAttribute("productList", ecommService.getProductById(id));
        return "editProduct";
    }

    @PostMapping("/editProduct")
    public String editSaveProduct(Products products, RedirectAttributes redirectAttributes) {
        if (ecommService.saveOrUpdateProduct(products)) {


            redirectAttributes.addFlashAttribute("message", "Product deleted");
            return "/ViewProduct";
        }
        redirectAttributes.addFlashAttribute("message", "Product deleted");
        return "/editProduct/" + products.getId();


    }
}
