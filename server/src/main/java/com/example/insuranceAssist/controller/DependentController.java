package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.exception.ClientNotFoundException;
import com.example.insuranceAssist.exception.DependentNotFoundException;
import com.example.insuranceAssist.dto.DependentCreationRequestDTO;
import com.example.insuranceAssist.dto.DependentDetailsDTO;
import com.example.insuranceAssist.dto.DependentProfileViewDTO;
import com.example.insuranceAssist.exception.RelationNotFoundException;
import com.example.insuranceAssist.service.DependentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/private/dependent")
public class DependentController {

    private final DependentService dependentService;

    public DependentController(DependentService dependentService){
        this.dependentService = dependentService;
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createDependent(@RequestBody DependentCreationRequestDTO request) throws ClientNotFoundException, RelationNotFoundException {
        UUID dependentId = dependentService.createDependent(request);
        return new ResponseEntity<>(dependentId, HttpStatus.CREATED);
    }

    @GetMapping("/get/{clientId}")
    public ResponseEntity<?> getDependents(@PathVariable UUID clientId) throws ClientNotFoundException {
        List<DependentProfileViewDTO> response = dependentService.getDependents(clientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDetails/{dependentId}")
    public ResponseEntity<?> getDependentDetails(@PathVariable UUID dependentId) throws DependentNotFoundException {
        DependentDetailsDTO response = dependentService.getDependentDetails(dependentId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/update/{dependentId}")
    public ResponseEntity<?> updateDependent(@PathVariable UUID dependentId, @RequestBody DependentCreationRequestDTO request) throws DependentNotFoundException {
        DependentDetailsDTO response = dependentService.updateDependent(dependentId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dependentId}")
    public ResponseEntity<String> deleteDependent(@PathVariable UUID dependentId) throws DependentNotFoundException {
        dependentService.deleteDependent(dependentId);
        return new ResponseEntity<>("Dependent details deleted successfully", HttpStatus.OK);
    }
}
