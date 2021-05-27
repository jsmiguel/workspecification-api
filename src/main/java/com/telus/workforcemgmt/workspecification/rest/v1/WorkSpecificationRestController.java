package com.telus.workforcemgmt.workspecification.rest.v1;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telus.workforcemgmt.workspecification.domain.service.WorkSpecificationServiceImpl;
import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecification;
import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecificationRequest;
import com.telus.workforcemgmt.workspecification.domain.service.WorkSpecificationRole;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("v1/workorderspecification")
@Validated
public class WorkSpecificationRestController {
	@Autowired
	private WorkSpecificationServiceImpl workSpecficationService;

	@GetMapping("/id")
	public Mono<WorkSpecification> getWorkSpecification(@PathVariable @NotBlank String id) {
		return workSpecficationService.getWorkSpecificationById(id, WorkSpecificationRole.WORK_ORDER);
	}

	@GetMapping
	public Mono<WorkSpecification> getWorkSpecification(@Valid WorkSpecificationRequest workSpecificationRequest) {
		return workSpecficationService.getWorkSpecificationByWorkSpecificationRequest(workSpecificationRequest, WorkSpecificationRole.WORK_ORDER);
	}
	

}
