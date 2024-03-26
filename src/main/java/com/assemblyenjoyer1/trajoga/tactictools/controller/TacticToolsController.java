package com.assemblyenjoyer1.trajoga.tactictools.controller;

import com.assemblyenjoyer1.trajoga.tactictools.service.TacticToolsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tft/items")
@CrossOrigin
public class TacticToolsController {

    private final TacticToolsService service;

    public TacticToolsController(TacticToolsService service) {
        this.service = service;
    }

    @GetMapping("/{unitName}")
    public ResponseEntity<List<String>> getBestItems(@PathVariable String unitName) {
        return ResponseEntity.ok(service.getBestItemsForUnit(unitName));
    }

    @GetMapping("trio/{unitName}")
    public ResponseEntity<List<String>> getBestItemsForUnit(@PathVariable String unitName) {
        return ResponseEntity.ok(service.getBestTrioForUnit(unitName));
    }

    @GetMapping("delta/{unitName}")
    public ResponseEntity<List<String>> getBestItemsForUnitByDelta(@PathVariable String unitName) {
        return ResponseEntity.ok(service.getBestItemsForUnitByDelta(unitName));
    }

}
