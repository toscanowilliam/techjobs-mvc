package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="search/results", method= RequestMethod.GET)
    public String processSearch(Model model, @RequestParam String searchTerm, @RequestParam String searchType){

        if (searchType.equals("all")) {


            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm); //Creates the array List of hashmaps that will be displayed (before formating)

            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
        }
        else {

            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
        }


            return "search/results";
    }






}
