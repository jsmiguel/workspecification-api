package com.telus.workforcemgmt.workspecification.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.telus.workforcemgmt.workspecification.domain.entity.PriorityOverrideRule;
import com.telus.workforcemgmt.workspecification.domain.service.PriorityOverrideRuleService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/priority")
public class PriorityOverrideRuleController {

	@Autowired
	private PriorityOverrideRuleService priorityOverrideRuleService;
	
	   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public Mono<PriorityOverrideRule> findByDomainAndValue(@RequestParam String domainName, @RequestParam String domainValue) {
	        return priorityOverrideRuleService.findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(domainName, domainValue);
	    }
	
}
