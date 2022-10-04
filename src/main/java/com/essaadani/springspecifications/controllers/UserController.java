package com.essaadani.springspecifications.controllers;

import com.essaadani.springspecifications.entities.AppUser;
import com.essaadani.springspecifications.repositories.UserRepository;
import com.essaadani.springspecifications.specifications.UserSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<AppUser> search(@RequestParam(value = "search") String search) {

        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<AppUser> spec = builder.build();

        return userRepository.findAll(spec);
    }
}
