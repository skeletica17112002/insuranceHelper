package com.example.insuranceAssist.service;

import com.example.insuranceAssist.exception.RoleNotFoundException;
import com.example.insuranceAssist.entity.RoleMaster;
import com.example.insuranceAssist.entity.UserMaster;
import com.example.insuranceAssist.repository.RoleMasterRepository;
import com.example.insuranceAssist.repository.UserMasterRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AgentAllocationService {

    private final UserMasterRepository userMasterRepository;
    private final RoleMasterRepository roleMasterRepository;
    private List<UserMaster> agentList;
    private AtomicInteger counter = new AtomicInteger(0);

    public AgentAllocationService(UserMasterRepository userMasterRepository, RoleMasterRepository roleMasterRepository){
        this.userMasterRepository = userMasterRepository;
        this.roleMasterRepository = roleMasterRepository;
    }

    @PostConstruct
    public void loadAgentList() throws RoleNotFoundException {

        RoleMaster agentRole = roleMasterRepository.findById(1L)
                .orElseThrow(() -> new RoleNotFoundException("Agent Role not found with id: " + 1L));

        agentList = userMasterRepository.findAllByRole(agentRole);

    }

    public UserMaster getAllocatedAgent(){

        return agentList.get(counter.getAndIncrement() % agentList.size());

    }

}
