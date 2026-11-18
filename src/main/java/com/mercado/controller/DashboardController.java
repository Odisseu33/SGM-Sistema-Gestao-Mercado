package com.mercado.controller;
import com.mercado.service.DashboardService; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
@Controller public class DashboardController {private final DashboardService service;public DashboardController(DashboardService s){service=s;}@GetMapping("/") public String home(Model m){m.addAllAttributes(service.dados());return "dashboard";}}
