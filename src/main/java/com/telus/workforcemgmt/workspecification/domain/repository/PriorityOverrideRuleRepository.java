package com.telus.workforcemgmt.workspecification.domain.repository;

import java.time.LocalDateTime;

import com.telus.workforcemgmt.workspecification.domain.entity.PriorityOverrideRule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PriorityOverrideRuleRepository extends ReactiveCrudRepository<PriorityOverrideRule, Long>{

	public Mono<PriorityOverrideRule> findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String domainNm, String domainValueCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
