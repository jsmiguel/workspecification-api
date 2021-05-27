package com.telus.workforcemgmt.workspecification.domain.repository;

import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkOrderRuleRepository extends ReactiveCrudRepository<WorkOrderRule, Long>{
	
	public Mono<WorkOrderRule> findByWorkOrderClassificationCdAndServiceClassCdAndServiceSubclassCdAndTechnologyCdAndJobTypeCdAndProductCdAndOutOfServiceInd(
			String workOrderClassificationCd, String serviceClassCd, String serviceSubclassCd, String technologyCd, String jobtType, String productCd, String outOfServiceInd
	);
	
	public Flux<WorkOrderRule> findByWorkOrderClassificationCdAndServiceClassCdAndServiceSubclassCd(String workOrderClassificationCd, String serviceClassCd, String serviceSubclassCd);
}
