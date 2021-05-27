package com.telus.workforcemgmt.workspecificaiton.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.workforcemgmt.workspecificaiton.data.PriorityOverrideRule;
import com.telus.workforcemgmt.workspecificaiton.data.PriorityOverrideRuleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PriorityOverrideRuleService {

	@Autowired
	private PriorityOverrideRuleRepository priorityOverrideRuleRepository;
	
	public Mono<PriorityOverrideRule> findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String domainNm, String domainValueCd) {
        return priorityOverrideRuleRepository.findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(domainNm, domainValueCd, LocalDateTime.now(), LocalDateTime.now());
    }
}
