package com.example.insuranceAssist.service;

import com.example.insuranceAssist.exception.HospitalNotFoundException;
import com.example.insuranceAssist.dto.HospitalCreateRequestDTO;
import com.example.insuranceAssist.dto.HospitalDetailsResponseDTO;
import com.example.insuranceAssist.dto.HospitalResponseDTO;
import com.example.insuranceAssist.entity.HospitalMaster;
import com.example.insuranceAssist.repository.HospitalMasterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HospitalService {

    private final HospitalMasterRepository hospitalMasterRepository;

    public HospitalService(HospitalMasterRepository hospitalMasterRepository){
        this.hospitalMasterRepository = hospitalMasterRepository;
    }

    public UUID create(HospitalCreateRequestDTO request) {

        HospitalMaster hospital = new HospitalMaster(
                request.getName(),
                request.getAddress(),
                request.getEmail(),
                request.getRating(),
                request.getClientContactEmail(),
                request.getClientContactNumber(),
                request.getNetwork()
        );

        return hospitalMasterRepository.save(hospital).getId();

    }

    public List<HospitalResponseDTO> getHospital() {

        List<HospitalMaster> hospitals = hospitalMasterRepository.findAll();

        List<HospitalResponseDTO> hospitalList = new ArrayList<>();

        for(HospitalMaster hospital: hospitals){
            HospitalResponseDTO hospitalDto = new HospitalResponseDTO(
                    hospital.getId(),
                    hospital.getName(),
                    hospital.getAddress(),
                    hospital.getRating(),
                    hospital.getClientContactEmail(),
                    hospital.getClientContactNumber(),
                    hospital.getNetwork()
            );
            hospitalList.add(hospitalDto);
        }

        return hospitalList;

    }

    public HospitalDetailsResponseDTO getHospitalDetails(UUID hospitalId) throws HospitalNotFoundException {

        HospitalMaster hospital = hospitalMasterRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + hospitalId));

        return new HospitalDetailsResponseDTO(
                hospital.getName(),
                hospital.getAddress(),
                hospital.getEmail(),
                hospital.getRating(),
                hospital.getClientContactEmail(),
                hospital.getClientContactNumber(),
                hospital.getNetwork()
        );

    }

    public HospitalDetailsResponseDTO updateHospital(HospitalCreateRequestDTO request, UUID hospitalId) throws HospitalNotFoundException {

        HospitalMaster hospital = hospitalMasterRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + hospitalId));

        hospital.setName(request.getName());
        hospital.setAddress(request.getAddress());
        hospital.setEmail(request.getEmail());
        hospital.setClientContactEmail(request.getClientContactEmail());
        hospital.setClientContactNumber(request.getClientContactNumber());
        hospital.setRating(request.getRating());
        hospital.setNetwork(request.getNetwork());

        HospitalMaster updatedHospital = hospitalMasterRepository.save(hospital);

        return new HospitalDetailsResponseDTO(
                updatedHospital.getName(),
                updatedHospital.getAddress(),
                updatedHospital.getEmail(),
                updatedHospital.getRating(),
                updatedHospital.getClientContactEmail(),
                updatedHospital.getClientContactNumber(),
                updatedHospital.getNetwork()
        );

    }

    public void deleteHospital(UUID hospitalId) {

        hospitalMasterRepository.deleteById(hospitalId);

    }
}
