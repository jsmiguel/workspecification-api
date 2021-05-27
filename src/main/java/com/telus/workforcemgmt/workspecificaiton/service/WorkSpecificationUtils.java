package com.telus.workforcemgmt.workspecificaiton.service;

import java.util.List;

import com.telus.workforcemgmt.workspecificaiton.data.WorkOrderActionRule;



public class WorkSpecificationUtils {
		
    public static WorkOrderActionRule getWorkOrderActionRuleByWorkOrderActionUsageCd(List<WorkOrderActionRule> workOrderActionRules, String workOrderActionUsageCd) {
    	if (workOrderActionRules == null || workOrderActionRules.isEmpty()) return null;
    	for (WorkOrderActionRule rule : workOrderActionRules) {
    		if (workOrderActionUsageCd.equals(rule.getWorkOrderActionUsageCd())) return rule;
    	}
    	return null;
    }
    
}
