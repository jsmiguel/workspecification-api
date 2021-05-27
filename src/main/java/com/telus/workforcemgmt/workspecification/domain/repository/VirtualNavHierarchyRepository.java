package com.telus.workforcemgmt.workspecification.domain.repository;

import java.time.LocalDateTime;

import com.telus.workforcemgmt.workspecification.domain.entity.VirtualNavHierarchy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface VirtualNavHierarchyRepository extends ReactiveCrudRepository<VirtualNavHierarchy, Long>{

	public Mono<VirtualNavHierarchy> findByWorkOrderCategoryCdAndServiceAreaClliCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String workOrderCategoryCd, String serviceAreaClliCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
