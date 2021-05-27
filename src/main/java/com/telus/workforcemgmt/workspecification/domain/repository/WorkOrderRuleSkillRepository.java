package com.telus.workforcemgmt.workspecification.domain.repository;

import java.time.LocalDateTime;

import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRuleSkill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface WorkOrderRuleSkillRepository extends ReactiveCrudRepository<WorkOrderRuleSkill, Long>{
	
	public Flux<WorkOrderRuleSkill> findAllByWorkOrderClassificationCdAndTechnologyCdAndServiceSubclassCdAndServiceClassCdAndProductCdAndJobTypeCdAndEffectiveStartTsBeforeAndEffectiveEndTsAfter(
			String workOrderClassificationCd, String technologyCd, String serviceSubclassCd, String serviceClassC, String productCd, String jobTypeCd, 
			LocalDateTime effectiveStartTs, LocalDateTime effectiveEndTs);
}
