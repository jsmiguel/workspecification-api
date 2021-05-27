package com.telus.workforcemgmt.workspecification.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.workforcemgmt.workspecification.domain.entity.PriorityOverrideRule;
import com.telus.workforcemgmt.workspecification.domain.repository.PriorityOverrideRuleRepository;

import reactor.core.publisher.Mono;

@Service
public class PriorityOverrideRuleService {

	@Autowired
	private PriorityOverrideRuleRepository priorityOverrideRuleRepository;
	
	public Mono<PriorityOverrideRule> findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String domainNm, String domainValueCd) {
        return priorityOverrideRuleRepository.findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(domainNm, domainValueCd, LocalDateTime.now(), LocalDateTime.now());
    }
}
