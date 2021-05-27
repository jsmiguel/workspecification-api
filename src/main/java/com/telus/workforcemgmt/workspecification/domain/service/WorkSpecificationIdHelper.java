package com.telus.workforcemgmt.workspecification.domain.service;

import java.util.Arrays;

import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecification;
import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecificationRequest;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.BeanUtils;



public class WorkSpecificationIdHelper {
	
	//public enum ID {WORK_ORDER_RULE_ID, WFM_SCOPE_RULE_ID, WORK_ORDER_ACTION_CD, WORK_ORDER_SKILL_LEVEL_ID, INSTALL_TYPE_CD, SWT};
	
	public enum ID_TYPE {WR_, SC_, WA_, SL_, EG_, SV_, IN_, DR_, CP_, MD_, MT_, NT_};//workorder rule, wfm scope rule, workorder action, skill level, engagment level, severityCd, install type, duration, component, multiday, multi tech, number of technician required 
	
	public enum ID_COMP {TJ_,PC_,WA_, IN_, MD_, DR_, MC_}; //Job Type, ProductCategory, WorkOrderAction, InstallType, MultiDay, Duration, MyComponent
	public static final String delims = "-";
	public static final String NA_VALUE = "";
	
	
	private Long workOrderRuleId;
	private Long wfmScopeRuleId;
	private Long workOrderSkillLevelId;
	private String engagmentLevel;
	private String severityCd;
	private String workOrderActionCd;
	private String installTypeCd;
	private Double swt;
	private Integer numberOfTechRequired;
	private WorkSpecificationIdHelper[] bundledWorkSpecId;
	
	private WorkSpecificationIdHelper() {
		
	}
	
	public WorkSpecificationIdHelper(WorkSpecification workSpecification) {
		workOrderRuleId = workSpecification.getWorkOrderRule().getWorkOrderRuleId();
		wfmScopeRuleId = workSpecification.getWfmScopeRule().getWfmScopeRuleId();
		workOrderSkillLevelId = (workSpecification.getWorkOrderRuleSkillLevel() == null) ? null : workSpecification.getWorkOrderRuleSkillLevel().getWorkOrderRuleSkillLevelId();
		workOrderActionCd = workSpecification.getWorkOrderActionCd();
		installTypeCd = workSpecification.getInstallTypeCd();
		swt = workSpecification.getEstimatedDuration();
		this.engagmentLevel = workSpecification.getEngagmentLevel();
		this.severityCd = workSpecification.getSeverityCd();
		this.numberOfTechRequired = workSpecification.getNumberOfTechnicianRequired();
		if (workSpecification.getComponentSpecificationList() != null && !workSpecification.getComponentSpecificationList().isEmpty()) {
			bundledWorkSpecId = new WorkSpecificationIdHelper[workSpecification.getComponentSpecificationList().size()];
			for (int i = 0; i<=bundledWorkSpecId.length-1; i++) {
				bundledWorkSpecId[i] = parseBundledWorkSpecificationIdHelper(workSpecification.getComponentSpecificationList().get(i));
			}
		}
	}
	
	
	public WorkSpecificationIdHelper[] getCompleteBundledWorkSpecificationIdHelper() {
		if (bundledWorkSpecId != null && bundledWorkSpecId.length > 0) {
			WorkSpecificationIdHelper[] results = new WorkSpecificationIdHelper[bundledWorkSpecId.length];
			int i = 0;
			for (WorkSpecificationIdHelper e : bundledWorkSpecId) {
				results[i] = new WorkSpecificationIdHelper();
				results[i].setWfmScopeRuleId(wfmScopeRuleId);
				results[i].setInstallTypeCd(e.getInstallTypeCd());
				results[i].setSwt(e.getSwt());
				results[i].setWorkOrderRuleId(e.getWorkOrderRuleId());
				results[i].setWorkOrderActionCd(e.getWorkOrderActionCd());
				i++;
			}
			return results;
		}
		return null;
	}
	
	private WorkSpecificationIdHelper parseBundledWorkSpecificationIdHelper(WorkSpecification workSpecification) {
		WorkSpecificationIdHelper bundled = new WorkSpecificationIdHelper();
		bundled.workOrderRuleId = workSpecification.getWorkOrderRule().getWorkOrderRuleId();
		bundled.workOrderActionCd = workSpecification.getWorkOrderActionCd();
		bundled.installTypeCd = workSpecification.getInstallTypeCd();
		bundled.swt = workSpecification.getEstimatedDuration();
		return bundled;
	}

	public Long getWorkOrderRuleId() {
		return workOrderRuleId;
	}

	public void setWorkOrderRuleId(Long workOrderRuleId) {
		this.workOrderRuleId = workOrderRuleId;
	}

	public Long getWfmScopeRuleId() {
		return wfmScopeRuleId;
	}

	public void setWfmScopeRuleId(Long wfmScopeRuleId) {
		this.wfmScopeRuleId = wfmScopeRuleId;
	}

	public Long getWorkOrderSkillLevelId() {
		return workOrderSkillLevelId;
	}

	public void setWorkOrderSkillLevelId(Long workOrderSkillLevelId) {
		this.workOrderSkillLevelId = workOrderSkillLevelId;
	}

	public String getWorkOrderActionCd() {
		return workOrderActionCd;
	}

	public void setWorkOrderActionCd(String workOrderActionCd) {
		this.workOrderActionCd = workOrderActionCd;
	}

	public String getInstallTypeCd() {
		return installTypeCd;
	}

	public void setInstallTypeCd(String installTypeCd) {
		this.installTypeCd = installTypeCd;
	}

	public WorkSpecificationIdHelper[] getBundledWorkSpecId() {
		return bundledWorkSpecId;
	}

	public void setBundledWorkSpecId(WorkSpecificationIdHelper[] bundledWorkSpecId) {
		this.bundledWorkSpecId = bundledWorkSpecId;
	}

	public Double getSwt() {
		return swt;
	}

	public void setSwt(Double swt) {
		this.swt = swt;
	}
	
    //JOB_TYPE_CD-PRODUCT_CATEGORY_CD-TECHNOLOGY_CD-ESTIMATED_DURATION_AMT-WORK_ORDER_ACTION_CD-INSTALL_TYPE_CD
    public static  WorkSpecificationRequest[] parseBundledWorkSpecificationRequest(WorkSpecificationRequest request) {
    	if (request.getComponents() == null || request.getComponents().length == 0) return null;
    	WorkSpecificationRequest[] results = new WorkSpecificationRequest[request.getComponents().length];
    	int i = 0;
    	for (String e : request.getComponents()) {
    		if (GenericValidator.isBlankOrNull(e)) throw new IllegalArgumentException("Input Id should not be empty : '" + e +"'");
    		String[] tokens = e.split(delims, -1);
    		
    		if (tokens == null || tokens.length != 6) {
    			throw new IllegalArgumentException("The request for bundled workSpecification is invalid :" + e);
    		} 
    		results[i] = new WorkSpecificationRequest();
    		BeanUtils.copyProperties(request, results[i]);

    		String jobTypeCd = tokens[0];
    		String productCategoryCd = tokens[1];
    		String technologyCd = tokens[2];
    		double estimatedDuration = Double.parseDouble(tokens[3]);
    		String workOrderActionCd = tokens[4];
    		String installTypeCd = tokens[5];

    		results[i].setJobTypeCd(jobTypeCd);
    		results[i].setProductCd(productCategoryCd);
    		results[i].setTechnologyCd(technologyCd);
    		results[i].setDuration(estimatedDuration);
    		results[i].setWorkOrderActionCd(workOrderActionCd);
    		results[i].setInstallTypeCd(installTypeCd);
    		results[i].setComponents(null);
    		results[i].setMultiDayInd(estimatedDuration > ConstantCodes.ONE_DAY_DURAION.getDoubleValue());
    		results[i].setMultiTechInd(false);
    		i++;
    	}
    	return results;
    }

	public static WorkSpecificationRequest[] parseComponentWorkSpecificationRequest(
			WorkSpecificationRequest request) {
		if (request.getComponents() == null || request.getComponents().length == 0) return null;
    	WorkSpecificationRequest[] results = new WorkSpecificationRequest[request.getComponents().length];
    	int i = 0;
    	for (String e : request.getComponents()) {
    		if (GenericValidator.isBlankOrNull(e)) throw new IllegalArgumentException("Input Id should not be empty : '" + e +"'");
    		String[] tokens = e.split(delims, -1);
    		
    		if (tokens == null || tokens.length != 6) {
    			throw new IllegalArgumentException("The request for bundled workSpecification is invalid :" + e);
    		} 
    		results[i] = new WorkSpecificationRequest();
    		BeanUtils.copyProperties(request, results[i]);

    		String jobTypeCd = tokens[0];
    		String productCategoryCd = tokens[1];
    		String technologyCd = tokens[2];
    		double estimatedDuration = Double.parseDouble(tokens[3]);
    		String workOrderActionCd = tokens[4];
    		String installTypeCd = tokens[5];

    		results[i].setJobTypeCd(jobTypeCd);
    		results[i].setProductCd(productCategoryCd);
    		results[i].setTechnologyCd(technologyCd);
    		results[i].setDuration(estimatedDuration);
    		results[i].setWorkOrderActionCd(workOrderActionCd);
    		results[i].setInstallTypeCd(installTypeCd);
    		results[i].setComponents(null);
    		results[i].setMultiDayInd(estimatedDuration > ConstantCodes.ONE_DAY_DURAION.getDoubleValue());
    		results[i].setMultiTechInd(false);
    		i++;
    	}
    	return results;
	}

	public String generateId() {
		StringBuilder sb = new StringBuilder();
		sb.append(ID_TYPE.WR_).append(this.workOrderRuleId).append(delims).append(ID_TYPE.SC_).append(this.wfmScopeRuleId);
		if (this.workOrderSkillLevelId != null) {
			sb.append(delims).append(ID_TYPE.SL_).append(workOrderSkillLevelId);
		}
		if (this.workOrderActionCd != null) {
			sb.append(delims).append(ID_TYPE.WA_).append(workOrderActionCd);
		}
		if (!isNullValue(installTypeCd)) {
			sb.append(delims).append(ID_TYPE.IN_).append(installTypeCd);
		}
		if (!isNullValue(this.severityCd)) {
			sb.append(delims).append(ID_TYPE.SV_).append(severityCd);
		}
		if (!isNullValue(this.engagmentLevel)) {
			sb.append(delims).append(ID_TYPE.EG_).append(engagmentLevel);
		}
		if (this.swt != null && this.swt.doubleValue() != 0.0d) {
			sb.append(delims).append(ID_TYPE.DR_).append(swt);
		}
		sb.append(delims).append(ID_TYPE.NT_).append(this.numberOfTechRequired);
		
		if (this.bundledWorkSpecId != null && bundledWorkSpecId.length > 0) {
			for (WorkSpecificationIdHelper bunlded :  bundledWorkSpecId) {
				sb.append(delims).append(ID_TYPE.CP_ ).append(ID_TYPE.WR_).append(bunlded.getWorkOrderRuleId());
				if (bunlded.getWorkOrderActionCd() != null) {
					sb.append(delims).append(ID_TYPE.WA_).append(bunlded.getWorkOrderActionCd());
				}
				if (bunlded.getInstallTypeCd() != null) {
					sb.append(delims).append(ID_TYPE.IN_).append(bunlded.getInstallTypeCd());
				}
				if (bunlded.swt != null && bunlded.swt.doubleValue() != 0.0d) {
					sb.append(delims).append(ID_TYPE.DR_).append(bunlded.swt);
				}
			}
		}
		return sb.toString();
	}
	
	private void init(String workSpecificationId) {
		String[] tokens = split(workSpecificationId);
		for (String e : tokens) {
			ID_TYPE type = ID_TYPE.valueOf(e.substring(0, 3));
			String value = e.substring(3);
			switch (type) {
				case  WR_: workOrderRuleId = Long.parseLong(value); break;
				case  SC_: wfmScopeRuleId = Long.parseLong(value); break;
				case  SL_: workOrderSkillLevelId = isNullValue(value)? null : Long.parseLong(value); break;
				case  EG_: engagmentLevel = isNullValue(value)? null : value; break;
				case  SV_: severityCd = isNullValue(value)? null : value; break;
				case  WA_: workOrderActionCd = isNullValue(value)? null : value; break;
				case  IN_: installTypeCd = isNullValue(value)? null : value; break;
				case  DR_: swt = isNullValue(value)? 0.0d : Double.parseDouble(value); break;
				case  NT_: numberOfTechRequired = isNullValue(value)? 1 : Integer.parseInt(value); break;
			};
		}
	}
	
	public WorkSpecificationIdHelper(String workSpecificationId) {
		if (!containComponents(workSpecificationId)) {
			init(workSpecificationId);
		} else {
			String[] comps = splitComps(workSpecificationId);
			init(comps[0]);
			for (int i = 1; i < comps.length; i++) {
				WorkSpecificationIdHelper comp = new WorkSpecificationIdHelper(comps[i]);
				if (this.bundledWorkSpecId == null) {
					this.bundledWorkSpecId  = new WorkSpecificationIdHelper[1];
					this.bundledWorkSpecId[0] = comp;
				} else {
					this.bundledWorkSpecId = Arrays.copyOf(bundledWorkSpecId, bundledWorkSpecId.length + 1);
					this.bundledWorkSpecId[bundledWorkSpecId.length - 1] = comp;	
				}
			}
		}
	}
	
	private boolean containComponents(String id) {
		return id.contains(delims+ID_TYPE.CP_);
	}
	
	private String[] splitComps(String id) {
		if (GenericValidator.isBlankOrNull(id)) throw new IllegalArgumentException("Input Id should not be empty : '" + id +"'");
		String[] tokens = id.split(delims+ID_TYPE.CP_);
		return tokens;
	}
	
	private String[] split(String id) {
		if (GenericValidator.isBlankOrNull(id)) throw new IllegalArgumentException("Input Id should not be empty : '" + id +"'");
		String[] tokens = id.split(delims);
		return tokens;
	}
	
	private boolean isNullValue(String value) {
		return (value == null || NA_VALUE.equals(value.trim()));
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

	public Integer getNumberOfTechRequired() {
		return numberOfTechRequired;
	}

	public void setNumberOfTechRequired(Integer numberOfTechRequired) {
		this.numberOfTechRequired = numberOfTechRequired;
	}


	
	
	
}
