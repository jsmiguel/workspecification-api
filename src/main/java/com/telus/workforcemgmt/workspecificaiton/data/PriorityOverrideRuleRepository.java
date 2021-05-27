package com.telus.workforcemgmt.workspecificaiton.data;

import java.time.LocalDateTime;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PriorityOverrideRuleRepository extends ReactiveCrudRepository<PriorityOverrideRule, Long>{

	public Mono<PriorityOverrideRule> findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String domainNm, String domainValueCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
