package com.telus.workforcemgmt.workspecificaiton.service;

import java.util.List;


import org.apache.commons.lang3.ArrayUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.telus.workforcemgmt.workspecificaiton.data.*;

@JsonInclude(Include.NON_NULL)
public class WorkSpecification {
	
	private String id;
	private boolean hasComponent;
	private WorkOrderRule workOrderRule;
	private WfmScopeRule wfmScopeRule;
	private VirtualNavHierarchy virtualNavHierarchy;
	private WfmPartnerScopeRule wfmPartnerScopeRule;
	private List<WorkOrderActionRule> workOrderActionRuleList;
	private List<WorkOrderRuleSkill> workOrderRuleSkillList;
	private WorkOrderRuleSkillLevel workOrderRuleSkillLevel;
    private List<WorkSpecification> componentSpecificationList;
    private List<WorkSpecification> jobSpecificationList;
    private PriorityOverrideRule priorityOverrideRule;
    private SeverityPriorityRule severityPriorityRule;

    private Integer numberOfTechnicianRequired;
        
    private String[] label = null;
    @JsonIgnore
    private WorkSpecificationRole role;
    @JsonIgnore
    private String workOrderActionCd;
    @JsonIgnore
    private String installTypeCd;
    @JsonIgnore
    private Double estimatedDuration = 0.0D;
    @JsonIgnore
	private String engagmentLevel;
    @JsonIgnore
	private String severityCd;
    @JsonIgnore
    private Long sampleWorkOrderId;    
    
    public WorkSpecification(WorkSpecificationRole role) {
    	this.role = role;
    }
    
	public WorkOrderRule getWorkOrderRule() {
		return workOrderRule;
	}
	public void setWorkOrderRule(WorkOrderRule workOrderRule) {
		this.workOrderRule = workOrderRule;
	}
	public WfmScopeRule getWfmScopeRule() {
		return wfmScopeRule;
	}
	public void setWfmScopeRule(WfmScopeRule wfmScopeRule) {
		this.wfmScopeRule = wfmScopeRule;
	}
	public VirtualNavHierarchy getVirtualNavHierarchy() {
		return virtualNavHierarchy;
	}
	public void setVirtualNavHierarchy(VirtualNavHierarchy virtualNavHierarchy) {
		this.virtualNavHierarchy = virtualNavHierarchy;
	}
	public WfmPartnerScopeRule getWfmPartnerScopeRule() {
		return wfmPartnerScopeRule;
	}
	public void setWfmPartnerScopeRule(WfmPartnerScopeRule wfmPartnerScopeRule) {
		this.wfmPartnerScopeRule = wfmPartnerScopeRule;
	}

	public WorkOrderRuleSkillLevel getWorkOrderRuleSkillLevel() {
		return workOrderRuleSkillLevel;
	}
	public void setWorkOrderRuleSkillLevel(WorkOrderRuleSkillLevel workOrderRuleSkillLevel) {
		this.workOrderRuleSkillLevel = workOrderRuleSkillLevel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<WorkOrderActionRule> getWorkOrderActionRuleList() {
		return workOrderActionRuleList;
	}
	public void setWorkOrderActionRuleList(List<WorkOrderActionRule> workOrderActionRuleList) {
		this.workOrderActionRuleList = workOrderActionRuleList;
	}
	public List<WorkOrderRuleSkill> getWorkOrderRuleSkillList() {
		return workOrderRuleSkillList;
	}
	public void setWorkOrderRuleSkillList(List<WorkOrderRuleSkill> workOrderRuleSkillList) {
		this.workOrderRuleSkillList = workOrderRuleSkillList;
	}
	public boolean isHasComponent() {
		return hasComponent;
	}

	public void setHasComponent(boolean hasComponent) {
		this.hasComponent = hasComponent;
	}

	public String getWorkOrderActionCd() {
		return workOrderActionCd;
	}
	public void setWorkOrderActionCd(String workOrderActionCd) {
		this.workOrderActionCd = workOrderActionCd;
	}
	public WorkSpecificationRole getRole() {
		return role;
	}
	public void setRole(WorkSpecificationRole role) {
		this.role = role;
	}
	public String getInstallTypeCd() {
		return installTypeCd;
	}
	public void setInstallTypeCd(String installTypeCd) {
		this.installTypeCd = installTypeCd;
	}
	public Double getEstimatedDuration() {
		return estimatedDuration;
	}
	public void setEstimatedDuration(Double estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}
	public List<WorkSpecification> getComponentSpecificationList() {
		return componentSpecificationList;
	}
	public void setComponentSpecificationList(List<WorkSpecification> componentSpecificationList) {
		this.componentSpecificationList = componentSpecificationList;
	}
	public List<WorkSpecification> getJobSpecificationList() {
		return jobSpecificationList;
	}
	public void setJobSpecificationList(List<WorkSpecification> jobSpecificationList) {
		this.jobSpecificationList = jobSpecificationList;
	}

	public String[] getLabel() {
		return label;
	}

	public void setLabel(String[] label) {
		this.label = label;
	}
	
	public void addLabel(String label) {
		if (this.getLabel() == null) {
			this.label = new String[1];
			this.label[0] = label;
		} else {
			this.label = ArrayUtils.add(this.label, label);
		}
	}

	public Long getSampleWorkOrderId() {
		return sampleWorkOrderId;
	}

	public void setSampleWorkOrderId(Long sampleWorkOrderId) {
		this.sampleWorkOrderId = sampleWorkOrderId;
	}

	public PriorityOverrideRule getPriorityOverrideRule() {
		return priorityOverrideRule;
	}

	public void setPriorityOverrideRule(PriorityOverrideRule priorityOverrideRule) {
		this.priorityOverrideRule = priorityOverrideRule;
	}

	public SeverityPriorityRule getSeverityPriorityRule() {
		return severityPriorityRule;
	}

	public void setSeverityPriorityRule(SeverityPriorityRule severityPriorityRule) {
		this.severityPriorityRule = severityPriorityRule;
	}

	public String getEngagmentLevel() {
		return engagmentLevel;
	}

	public void setEngagmentLevel(String engagmentLevel) {
		this.engagmentLevel = engagmentLevel;
	}

	public String getSeverityCd() {
		return severityCd;
	}

	public void setSeverityCd(String severityCd) {
		this.severityCd = severityCd;
	}

	public Integer getNumberOfTechnicianRequired() {
		return numberOfTechnicianRequired;
	}

	public void setNumberOfTechnicianRequired(Integer numberOfTechnicianRequired) {
		this.numberOfTechnicianRequired = numberOfTechnicianRequired;
	}
	
	

}
