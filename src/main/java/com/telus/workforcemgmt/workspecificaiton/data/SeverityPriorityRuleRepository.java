package com.telus.workforcemgmt.workspecificaiton.data;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


import reactor.core.publisher.Mono;

public interface SeverityPriorityRuleRepository extends ReactiveCrudRepository<SeverityPriorityRule, Long>{

	public Mono<SeverityPriorityRule> findByWorkOrderCategoryCdAndSeverityCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String workOrderCategoryCd, String severityCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
