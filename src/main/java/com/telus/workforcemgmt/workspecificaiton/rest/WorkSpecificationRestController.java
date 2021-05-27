package com.telus.workforcemgmt.workspecificaiton.rest;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.telus.workforcemgmt.workspecificaiton.service.WorkSpecficationServiceImpl;
import com.telus.workforcemgmt.workspecificaiton.service.WorkSpecification;
import com.telus.workforcemgmt.workspecificaiton.service.WorkSpecificationRequest;
import com.telus.workforcemgmt.workspecificaiton.service.WorkSpecificationRole;

import reactor.core.publisher.Mono;


@RestController
@Validated
public class WorkSpecificationRestController {
	@Autowired
	private WorkSpecficationServiceImpl workSpecficationService;

	@GetMapping("/workorderspecification/id")
	public Mono<WorkSpecification> getWorkSpecification(@PathVariable @NotBlank String id) {
		return workSpecficationService.getWorkSpecificationById(id, WorkSpecificationRole.WORK_ORDER);
	}

	@GetMapping("/workorderspecification")
	public Mono<WorkSpecification> getWorkSpecification(@Valid WorkSpecificationRequest workSpecificationRequest) {
		return workSpecficationService.getWorkSpecificationByWorkSpecificationRequest(workSpecificationRequest, WorkSpecificationRole.WORK_ORDER);
	}
	

}
