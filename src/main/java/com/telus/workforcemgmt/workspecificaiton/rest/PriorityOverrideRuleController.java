package com.telus.workforcemgmt.workspecificaiton.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.telus.workforcemgmt.workspecificaiton.data.PriorityOverrideRule;
import com.telus.workforcemgmt.workspecificaiton.service.PriorityOverrideRuleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/priority")
public class PriorityOverrideRuleController {

	@Autowired
	private PriorityOverrideRuleService priorityOverrideRuleService;
	
	   @GetMapping("/priority")
	    public Mono<PriorityOverrideRule> findByDomainAndValue(@RequestParam String domainName, String domainValue) {
	        return priorityOverrideRuleService.findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(domainName, domainValue);
	    }
	
}
