package com.telus.workforcemgmt.workspecificaiton.data;

import java.time.LocalDateTime;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface VirtualNavHierarchyRepository extends ReactiveCrudRepository<VirtualNavHierarchy, Long>{

	public Mono<VirtualNavHierarchy> findByWorkOrderCategoryCdAndServiceAreaClliCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String workOrderCategoryCd, String serviceAreaClliCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
