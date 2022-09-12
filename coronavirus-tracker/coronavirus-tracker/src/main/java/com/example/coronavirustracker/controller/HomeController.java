package com.example.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.coronavirustracker.models.LocationStats;
import com.example.coronavirustracker.services.CoronaVirusDataService;


@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
  
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allstats = coronaVirusDataService.getAllStats();
		int totalcases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allstats);
		model.addAttribute("totalcases",totalcases);
		model.addAttribute("totalNewCases",totalNewCases);
		
		
		
		
		return "home";
	} 	
	
}
