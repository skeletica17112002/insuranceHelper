package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.HospitalCreateRequestDTO;
import com.example.insuranceAssist.dto.HospitalDetailsResponseDTO;
import com.example.insuranceAssist.dto.HospitalResponseDTO;
import com.example.insuranceAssist.exception.HospitalNotFoundException;
import com.example.insuranceAssist.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/private/hospital")
@RestController
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
    public ResponseEntity<UUID> createHospital(@RequestBody HospitalCreateRequestDTO request){
        UUID hospitalId = hospitalService.create(request);
        return new ResponseEntity<>(hospitalId, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('CLIENT','AGENT')")
    @GetMapping("/get")
    public ResponseEntity<List<HospitalResponseDTO>> getHospital(){
        List<HospitalResponseDTO> hospitalList = hospitalService.getHospital();
        return new ResponseEntity<>(hospitalList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT','AGENT')")
    @GetMapping("/get/{hospitalId}")
    public ResponseEntity<HospitalDetailsResponseDTO> getHospitalDetails(@PathVariable UUID hospitalId)
            throws HospitalNotFoundException {
        HospitalDetailsResponseDTO hospital = hospitalService.getHospitalDetails(hospitalId);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AGENT')")
    @PutMapping("/update/{hospitalId}")
    public ResponseEntity<HospitalDetailsResponseDTO> updateHospital(
            @RequestBody HospitalCreateRequestDTO request, @PathVariable UUID hospitalId)
            throws HospitalNotFoundException {
        HospitalDetailsResponseDTO hospital = hospitalService.updateHospital(request, hospitalId);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AGENT')")
    @DeleteMapping("/delete/{hospitalId}")
    public ResponseEntity<String> deleteHospital(@PathVariable UUID hospitalId){
        hospitalService.deleteHospital(hospitalId);
        return new ResponseEntity<>("Hospital information deleted successfully", HttpStatus.OK);
    }
}
