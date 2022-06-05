package com.ahlquist.commio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.commio.service.VideoStreamingService;

import reactor.core.publisher.Mono;
@RestController
public class VideoStreamingController {

    @Autowired
    private VideoStreamingService service;

    @GetMapping(value = "video/{name}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range) {
          return service.getVideo(title);
    }
}
