package com.telus.workforcemgmt.workspecification.domain.repository;

import com.telus.workforcemgmt.workspecification.domain.entity.WfmScopeRule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WfmScopeRuleRepository extends ReactiveCrudRepository<WfmScopeRule, Long> {

}
