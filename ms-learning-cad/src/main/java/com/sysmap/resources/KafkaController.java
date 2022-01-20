package com.sysmap.resources;

import com.sysmap.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final EventService eventService;

    @Autowired
    public KafkaController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/send")
    public void send(){
        eventService.send("Mensagem de teste enviada ao tópico");
    }
}
