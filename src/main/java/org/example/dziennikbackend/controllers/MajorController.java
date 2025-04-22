package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.services.MajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private/majors")
public class MajorController {
    private final MajorService majorService;
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }
}
