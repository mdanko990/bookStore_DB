package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller

public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/bookForm")
    public String newBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "newBook";
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return new ModelAndView("redirect:/updateList");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookService.get(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        this.bookService.delete(id);
        return "redirect:/updateList";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/bookList")
    public String bookList(Model model) {
        model.addAttribute("bookList", bookService.list());
        return "bookList";
    }

    @GetMapping("/updateList")
    public String updateList(Model model) {
        List<Book> updateList = bookService.list();
        model.addAttribute("updateList", updateList);
        return "updateList";
    }
/*
	@GetMapping("/bookList/buy/{id}")
	public ModelAndView buy(@ModelAttribute("user") User user, @ModelAttribute("book") Book book) {
		orderService.buy(user, book);
		return new ModelAndView("redirect:/bookList");
	}*/

    @GetMapping("/orderList")
    public String orderList(Model model) {
        List<Order> orderList = orderService.list();
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

}
