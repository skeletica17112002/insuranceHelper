package com.example.insuranceAssist.service;

import com.example.insuranceAssist.dto.ClaimCreateRequestDTO;
import com.example.insuranceAssist.dto.ClaimResponseDTO;
import com.example.insuranceAssist.entity.*;
import com.example.insuranceAssist.exception.*;
import com.example.insuranceAssist.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClaimService {

    private final AuthorizationRepository authorizationRepository;
    private final AuthorizationLogRepository authorizationLogRepository;
    private final ClaimTypeMasterRepository claimTypeMasterRepository;
    private final UserMasterRepository userMasterRepository;
    private final PolicyMasterRepository policyMasterRepository;
    private final StatusTypeMasterRepository statusTypeMasterRepository;
    private final AgentAllocationService agentAllocationService;

    public ClaimService(AuthorizationRepository authorizationRepository, AuthorizationLogRepository authorizationLogRepository, ClaimTypeMasterRepository claimTypeMasterRepository, UserMasterRepository userMasterRepository, PolicyMasterRepository policyMasterRepository, StatusTypeMasterRepository statusTypeMasterRepository, AgentAllocationService agentAllocationService) {
        this.authorizationRepository = authorizationRepository;
        this.authorizationLogRepository = authorizationLogRepository;
        this.claimTypeMasterRepository = claimTypeMasterRepository;
        this.userMasterRepository = userMasterRepository;
        this.policyMasterRepository = policyMasterRepository;
        this.statusTypeMasterRepository = statusTypeMasterRepository;
        this.agentAllocationService = agentAllocationService;
    }

    public UUID createClaim(ClaimCreateRequestDTO request) throws ClientNotFoundException, ClaimTypeNotFoundException, StatusTypeNotFoundException, PolicyClaimCoverageNotEnoughException {

        UserMaster client = userMasterRepository.findById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + request.getClientId()));

        UserMaster agent = agentAllocationService.getAllocatedAgent();

        PolicyMaster policy = policyMasterRepository.findByClient(client);

        if(request.getClaimAmount() > policy.getRemainingCoverage()){
            throw new PolicyClaimCoverageNotEnoughException("Claim cannot be created due to insufficient remaining coverage");
        }

        PolicyTypeMaster policyType = policy.getPolicyType();

        ClaimTypeMaster claimType = claimTypeMasterRepository.findById(request.getClaimType())
                .orElseThrow(() -> new ClaimTypeNotFoundException("Claim type not found with id: " + request.getClaimType()));

        Long statusCreated = 1L;
        StatusTypeMaster status = statusTypeMasterRepository.findById(statusCreated)
                .orElseThrow(() -> new StatusTypeNotFoundException("Status type not found with id: " + statusCreated));

        Authorization claim = new Authorization(
                policy,
                claimType,
                agent,
                client,
                request.getClaimAmount(),
                status,
                LocalDateTime.now(),
                null,
                request.getProcedureNotes(),
                LocalDateTime.now()
        );

        UUID claimId = authorizationRepository.save(claim).getId();

        AuthorizationLog log = new AuthorizationLog(
                claim,
                null,
                status,
                LocalDateTime.now()
        );

        authorizationLogRepository.save(log);
        return claimId;

    }

    public ClaimResponseDTO getClaim(UUID claimId) throws ClaimNotFoundException {

        Authorization claim = authorizationRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

        return new ClaimResponseDTO(
            claim.getClient().getId(),
            claim.getPolicy().getPolicyId(),
            claim.getOpenDate(),
            claim.getProcedureNotes(),
            claim.getClaimType().getClaimType(),
            claim.getStatus().getStatusType(),
            claim.getClaimAmount(),
            claim.getUpdatedAt()
        );

    }

    public List<ClaimResponseDTO> getClaimByAgent(UUID agentId) throws AgentNotFoundException {

        UserMaster agent = userMasterRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with id: " + agentId));

        List<Authorization> claims = authorizationRepository.findAllByAgent(agent);

        List<ClaimResponseDTO> response = new ArrayList<>();

        for(Authorization claim: claims){
            ClaimResponseDTO dto = new ClaimResponseDTO(
                    claim.getId(),
                    claim.getPolicy().getPolicyId(),
                    claim.getOpenDate(),
                    claim.getProcedureNotes(),
                    claim.getClaimType().getClaimType(),
                    claim.getStatus().getStatusType(),
                    claim.getClaimAmount(),
                    claim.getUpdatedAt()
            );
            response.add(dto);
        }

        return response;

    }

    public List<ClaimResponseDTO> getClaimByClient(UUID clientId) throws ClientNotFoundException {

        UserMaster client = userMasterRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));

        List<Authorization> claims = authorizationRepository.findAllByClient(client);

        List<ClaimResponseDTO> response = new ArrayList<>();

        for(Authorization claim: claims){
            ClaimResponseDTO dto = new ClaimResponseDTO(
                    claim.getId(),
                    claim.getPolicy().getPolicyId(),
                    claim.getOpenDate(),
                    claim.getProcedureNotes(),
                    claim.getClaimType().getClaimType(),
                    claim.getStatus().getStatusType(),
                    claim.getClaimAmount(),
                    claim.getUpdatedAt()
            );
            response.add(dto);
        }

        return response;

    }

    public ClaimResponseDTO updateClaim(UUID claimId, Long updatedStatus) throws ClaimNotFoundException, StatusTypeNotFoundException {

        Authorization claim = authorizationRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

        StatusTypeMaster currStatus = statusTypeMasterRepository.findById(updatedStatus)
                .orElseThrow(() -> new StatusTypeNotFoundException("Status type not found with id: " + updatedStatus));

        StatusTypeMaster prevStatus = claim.getStatus();

        claim.setStatus(currStatus);

        StatusTypeMaster approvedStatus = statusTypeMasterRepository.findById(3L)
                        .orElseThrow(() -> new StatusTypeNotFoundException("Status type not found with id: " + 3));

        authorizationRepository.save(claim);

        if(currStatus == approvedStatus) {

            PolicyMaster policy = claim.getPolicy();
            policy.setRemainingCoverage(policy.getRemainingCoverage() - claim.getClaimAmount());
            policyMasterRepository.save(policy);

        }

        AuthorizationLog log = new AuthorizationLog(
                claim,
                prevStatus,
                currStatus,
                LocalDateTime.now()
        );

        authorizationLogRepository.save(log);

        return new ClaimResponseDTO(
                claim.getId(),
                claim.getPolicy().getPolicyId(),
                claim.getOpenDate(),
                claim.getProcedureNotes(),
                claim.getClaimType().getClaimType(),
                claim.getStatus().getStatusType(),
                claim.getClaimAmount(),
                claim.getUpdatedAt()
        );

    }

    public ClaimResponseDTO updateClaimWithPreAuth(UUID claimId, Long updatedStatus, Long claimAmount) throws ClaimNotFoundException, StatusTypeNotFoundException {

        Authorization claim = authorizationRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

        StatusTypeMaster currStatus = statusTypeMasterRepository.findById(updatedStatus)
                .orElseThrow(() -> new StatusTypeNotFoundException("Status type not found with id: " + updatedStatus));

        StatusTypeMaster prevStatus = claim.getStatus();

        claim.setStatus(currStatus);

        StatusTypeMaster approvedStatus = statusTypeMasterRepository.findById(3L)
                .orElseThrow(() -> new StatusTypeNotFoundException("Status type not found with id: " + 3));

        authorizationRepository.save(claim);

        if(currStatus == approvedStatus) {

            PolicyMaster policy = claim.getPolicy();
            policy.setRemainingCoverage(policy.getRemainingCoverage() - claimAmount);
            policyMasterRepository.save(policy);

        }

        AuthorizationLog log = new AuthorizationLog(
                claim,
                prevStatus,
                currStatus,
                LocalDateTime.now()
        );

        authorizationLogRepository.save(log);

        return new ClaimResponseDTO(
                claim.getId(),
                claim.getPolicy().getPolicyId(),
                claim.getOpenDate(),
                claim.getProcedureNotes(),
                claim.getClaimType().getClaimType(),
                claim.getStatus().getStatusType(),
                claim.getClaimAmount(),
                claim.getUpdatedAt()
        );

    }

    public void deleteClaim(UUID claimId) {

        authorizationRepository.deleteById(claimId);

    }

    public Page<ClaimResponseDTO> getClaimByAgentWithPagination(UUID agentId, int offset, int pageSize) throws AgentNotFoundException {

        UserMaster agent = userMasterRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with id: " + agentId));

        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Authorization> claims = authorizationRepository.findAllByAgent(agent, pageable);

        Page<ClaimResponseDTO> response = claims
                .map(claim -> new ClaimResponseDTO(
                        claim.getId(),
                        claim.getPolicy().getPolicyId(),
                        claim.getOpenDate(),
                        claim.getProcedureNotes(),
                        claim.getClaimType().getClaimType(),
                        claim.getStatus().getStatusType(),
                        claim.getClaimAmount(),
                        claim.getUpdatedAt()
                ));

        return response;

    }

    public Page<ClaimResponseDTO> getClaimByClientWithPagination(UUID clientId, int offset, int pageSize) throws AgentNotFoundException {

        UserMaster client = userMasterRepository.findById(clientId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with id: " + clientId));

        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Authorization> claims = authorizationRepository.findAllByClient(client, pageable);

        Page<ClaimResponseDTO> response = claims
                .map(claim -> new ClaimResponseDTO(
                        claim.getId(),
                        claim.getPolicy().getPolicyId(),
                        claim.getOpenDate(),
                        claim.getProcedureNotes(),
                        claim.getClaimType().getClaimType(),
                        claim.getStatus().getStatusType(),
                        claim.getClaimAmount(),
                        claim.getUpdatedAt()
                ));

        return response;

    }


}
