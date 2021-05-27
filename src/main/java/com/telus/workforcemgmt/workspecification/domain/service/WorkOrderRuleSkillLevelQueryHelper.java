package com.telus.workforcemgmt.workspecification.domain.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecificationRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.validator.GenericValidator;

import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRuleSkillLevel;


public class WorkOrderRuleSkillLevelQueryHelper {

	private final static String ABSENT = "ABSENT_NULL";	

	private final static BigDecimal LEVEL_0 = new BigDecimal(0);
	private final static BigDecimal LEVEL_1 = new BigDecimal(1);
	private final static BigDecimal LEVEL_2 = new BigDecimal(2);
	private final static BigDecimal LEVEL_3 = new BigDecimal(3);
	private final static BigDecimal DEFAULT_LEVEL = LEVEL_3;
	
	private Map<String, Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>> multiUnitMap;
	private Map<String, Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>> notMultiUnitMap;

	public WorkOrderRuleSkillLevelQueryHelper(List<WorkOrderRuleSkillLevel> multiUnit, List<WorkOrderRuleSkillLevel> notMultiUnit) {		
		multiUnitMap = populateWorkOrderRuleSkillLevelMap(multiUnit);
		notMultiUnitMap = populateWorkOrderRuleSkillLevelMap(notMultiUnit);		
	}
	
	private Map<String, Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>> populateWorkOrderRuleSkillLevelMap(List<WorkOrderRuleSkillLevel> lst) {
		Map<String, Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>> retMap = new HashMap<String, Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>>();
		for (WorkOrderRuleSkillLevel e: lst) {
			if (retMap.get(e.getWorkOrderClassificationCd()) == null) {
				retMap.put(e.getWorkOrderClassificationCd(), new HashMap<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>>());
			}
			String tech = e.getTechnologyCd();
			String orderAction = e.getWorkOrderActionCd();
			String troubleType = e.getTroubleTypeTxt();
			
			if (GenericValidator.isBlankOrNull(tech)) {
				tech = ABSENT;
			}
			if (GenericValidator.isBlankOrNull(orderAction)) {
				orderAction = ABSENT;
			}
			if (GenericValidator.isBlankOrNull(troubleType)) {
				troubleType = ABSENT;
			}
			if (retMap.get(e.getWorkOrderClassificationCd()).get(tech) == null) {
				retMap.get(e.getWorkOrderClassificationCd()).put(tech, new HashMap<String, Map<String, List<WorkOrderRuleSkillLevel>>>());
			}
			if (retMap.get(e.getWorkOrderClassificationCd()).get(tech).get(orderAction) == null) {
				retMap.get(e.getWorkOrderClassificationCd()).get(tech).put(orderAction, new HashMap<String, List<WorkOrderRuleSkillLevel>>());
			}
			if (retMap.get(e.getWorkOrderClassificationCd()).get(tech).get(orderAction).get(troubleType) == null) {
				retMap.get(e.getWorkOrderClassificationCd()).get(tech).get(orderAction).put(troubleType, new LinkedList<WorkOrderRuleSkillLevel>());
			}
			
			retMap.get(e.getWorkOrderClassificationCd()).get(tech).get(orderAction).get(troubleType).add(e);
		}		
		return retMap;
	}
	public WorkOrderRuleSkillLevel getSkillLevel(WorkSpecificationRequest request) {
		WorkOrderRuleSkillLevel req = new WorkOrderRuleSkillLevel();
		req.setCauseLevel1Txt(request.getCauseLevel1Txt());
		req.setCauseLevel2Txt(request.getCauseLevel2Txt());
		req.setCauseLevel3Txt(request.getCauseLevel3Txt());
		req.setMultiUnitInd(request.isMultiUnitInd() ? ConstantCodes.FWDS_BOOLEAN_TRUE.value : ConstantCodes.FWDS_BOOLEAN_FALSE.value);
		req.setTechnologyCd(request.getTechnologyCd());
		req.setTroubleTypeTxt(request.getTroubleTypeTxt());
		req.setWorkOrderActionCd(request.getWorkOrderActionCd());
		req.setWorkOrderClassificationCd(request.getWorkOrderClassificationCd());
		return getSkillLevel(req);
	}

	
	public WorkOrderRuleSkillLevel getSkillLevel(WorkOrderRuleSkillLevel req) {
		boolean isMDU = false;
		if (ConstantCodes.FWDS_BOOLEAN_TRUE.value.equals(req.getMultiUnitInd())) {
			isMDU = true;
		}
		Map<String, Map<String, Map<String, List<WorkOrderRuleSkillLevel>>>> cls = null;		
		if (isMDU) {
			cls = multiUnitMap.get(req.getWorkOrderClassificationCd());
		} else {
			cls = notMultiUnitMap.get(req.getWorkOrderClassificationCd());
		}
		
		WorkOrderRuleSkillLevel result_default = new WorkOrderRuleSkillLevel();
		result_default.setWorkOrderRuleSkillLevelId(0L);
		result_default.setSkillLevelNum(DEFAULT_LEVEL);
		
		if (cls == null) return result_default;
		
		Map<String, Map<String, List<WorkOrderRuleSkillLevel>>> tech = cls.get(req.getTechnologyCd());
		if (tech == null) tech = cls.get(ABSENT);
		if (tech == null) return result_default;
		
		Map<String, List<WorkOrderRuleSkillLevel>> orderAction = tech.get(req.getWorkOrderActionCd());
		if (orderAction == null) orderAction = tech.get(ABSENT);
		if (orderAction == null) return result_default;
		
		//CSD-2114, handle MDU for troubleType
		if (isMDU) {
			List<WorkOrderRuleSkillLevel> troubleType = orderAction.get(req.getTroubleTypeTxt());
			if (troubleType == null) troubleType = orderAction.get(ABSENT);
			if (troubleType == null || troubleType.size() != 1) {
				return result_default;
			} else {
				return troubleType.iterator().next();
			}			
		}
		
		if (GenericValidator.isBlankOrNull(req.getTroubleTypeTxt())) {
			List<WorkOrderRuleSkillLevel> troubleType = orderAction.get(ABSENT);
			if (troubleType == null || troubleType.size() != 1) {
				return result_default;
			} else {
				return troubleType.iterator().next();
			}
		} else {
			List<WorkOrderRuleSkillLevel> troubleType = orderAction.get(req.getTroubleTypeTxt());
			if (troubleType == null) return result_default;
						
			WorkOrderRuleSkillLevel result = getUniqueWorkOrderRuleSkillLevel(troubleType, req);
			if (result == null) {
				result = getUniqueWorkOrderRuleSkillLevel(getSkill(troubleType, req.getCauseLevel1Txt()), req);
				if (result != null && !LEVEL_1.equals(result.getRequiredLevelNum())) {					
					result = null;
				}
				if (result == null) {
					result = getUniqueWorkOrderRuleSkillLevel(getSkill(troubleType, req.getCauseLevel1Txt(), req.getCauseLevel2Txt()), req);
					if (result != null && !LEVEL_2.equals(result.getRequiredLevelNum())) {
						result = null;
					}
				}
				if (result == null) {
					result = getUniqueWorkOrderRuleSkillLevel(getSkill(troubleType, req.getCauseLevel1Txt(), req.getCauseLevel2Txt(), req.getCauseLevel3Txt()), req);
					if (result != null && !LEVEL_3.equals(result.getRequiredLevelNum())) {
						return result_default;
					}
				}
			} else {
				if (LEVEL_0.equals(result.getRequiredLevelNum())) {
					return result;
				} else if (LEVEL_1.equals(result.getRequiredLevelNum()) && ObjectUtils.equals(result.getCauseLevel1Txt(), req.getCauseLevel1Txt())) {
					return result;
				} else if (LEVEL_2.equals(result.getRequiredLevelNum()) && ObjectUtils.equals(result.getCauseLevel1Txt(), req.getCauseLevel1Txt()) 
						&& ObjectUtils.equals(result.getCauseLevel2Txt(), req.getCauseLevel2Txt())) {
					return result;
				} else if (LEVEL_3.equals(result.getRequiredLevelNum()) && ObjectUtils.equals(result.getCauseLevel1Txt(), req.getCauseLevel1Txt()) 
						&& ObjectUtils.equals(result.getCauseLevel2Txt(), req.getCauseLevel2Txt()) 
						&& ObjectUtils.equals(result.getCauseLevel3Txt(), req.getCauseLevel3Txt())) {
					return result;
				} else {
					return result_default;
				}			
			}
			
			if (result == null) return result_default;
			
			return result;
		}
	}
	
	private WorkOrderRuleSkillLevel getUniqueWorkOrderRuleSkillLevel(List<WorkOrderRuleSkillLevel> results, WorkOrderRuleSkillLevel req) {
		if (results == null || results.size() == 0) {
			WorkOrderRuleSkillLevel level = new WorkOrderRuleSkillLevel();
			level.setSkillLevelNum(DEFAULT_LEVEL);
			return level;
		} else if (results.size() == 1){
			return results.iterator().next();
		} else {
			return null;
		}
		
	}
	
	private List<WorkOrderRuleSkillLevel> getSkill(List<WorkOrderRuleSkillLevel> lst, String causelevel1) {
		List<WorkOrderRuleSkillLevel> result = new LinkedList<WorkOrderRuleSkillLevel>();
		for (WorkOrderRuleSkillLevel e : lst) {
			if (!GenericValidator.isBlankOrNull(e.getCauseLevel1Txt())) {
				if (e.getCauseLevel1Txt().equals(causelevel1))	result.add(e);
			}
		}
		return result;
	}
	
	
	private List<WorkOrderRuleSkillLevel>  getSkill(List<WorkOrderRuleSkillLevel> lst, String causelevel1, String causelevel2) {
		List<WorkOrderRuleSkillLevel> result = new LinkedList<WorkOrderRuleSkillLevel>();
		for (WorkOrderRuleSkillLevel e : lst) {
			if (!GenericValidator.isBlankOrNull(e.getCauseLevel1Txt()) 
					&& !GenericValidator.isBlankOrNull(e.getCauseLevel2Txt())) {
				if (e.getCauseLevel1Txt().equals(causelevel1)
					&& e.getCauseLevel2Txt().equals(causelevel2)) 
				result.add(e);
			}
		}
		return result;
	}

	private List<WorkOrderRuleSkillLevel>  getSkill(List<WorkOrderRuleSkillLevel> lst, String causelevel1, String causelevel2, String causelevel3) {
		List<WorkOrderRuleSkillLevel> result = new LinkedList<WorkOrderRuleSkillLevel>();
		for (WorkOrderRuleSkillLevel e : lst) {
			if (!GenericValidator.isBlankOrNull(e.getCauseLevel1Txt()) 
					&& !GenericValidator.isBlankOrNull(e.getCauseLevel2Txt())
					&& !GenericValidator.isBlankOrNull(e.getCauseLevel3Txt())) {
				if (e.getCauseLevel1Txt().equals(causelevel1)
					&& e.getCauseLevel2Txt().equals(causelevel2)
					&& e.getCauseLevel3Txt().equals(causelevel3)) 
				result.add(e);
			}
		}
		return result;
	}


	/*
	private int getRequiredLevelNum (List<WorkOrderRuleSkillLevel> lst) {
		int maxLevel = 0;
		for (WorkOrderRuleSkillLevel e : lst) {
			int level = Integer.parseInt(e.getRequiredLevelNum());
			if (level > maxLevel) maxLevel = level;
		}
		return maxLevel;
	}
	*/
}
