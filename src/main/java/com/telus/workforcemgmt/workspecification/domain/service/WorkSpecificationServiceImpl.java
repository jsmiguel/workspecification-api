package com.telus.workforcemgmt.workspecification.domain.service;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import java.util.LinkedList;
import java.util.List;

import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecification;
import com.telus.workforcemgmt.workspecification.domain.model.WorkSpecificationRequest;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.telus.workforcemgmt.workspecification.domain.repository.PriorityOverrideRuleRepository;
import com.telus.workforcemgmt.workspecification.domain.repository.SeverityPriorityRuleRepository;
import com.telus.workforcemgmt.workspecification.domain.repository.VirtualNavHierarchyRepository;
import com.telus.workforcemgmt.workspecification.domain.entity.WfmPartnerScopeRule;
import com.telus.workforcemgmt.workspecification.domain.entity.WfmScopeRule;
import com.telus.workforcemgmt.workspecification.domain.repository.WfmScopeRuleRepository;
import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderActionRule;
import com.telus.workforcemgmt.workspecification.domain.repository.WorkOrderActionRuleRepository;
import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRule;
import com.telus.workforcemgmt.workspecification.domain.repository.WorkOrderRuleRepository;
import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRuleSkillLevel;
import com.telus.workforcemgmt.workspecification.domain.repository.WorkOrderRuleSkillLevelRepository;
import com.telus.workforcemgmt.workspecification.domain.repository.WorkOrderRuleSkillRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class WorkSpecificationServiceImpl {

	@Autowired
	private WorkOrderRuleRepository workOrderRuleRepository;
	@Autowired
	private WfmScopeRuleRepository wfmScopeRuleRepository;
	@Autowired
	private VirtualNavHierarchyRepository virtualNavHierarchyRepository;
	@Autowired
	private WorkOrderActionRuleRepository workOrderActionRuleRepository;
	@Autowired
	private WorkOrderRuleSkillRepository workOrderRuleSkillRepository;
	@Autowired
	private WorkOrderRuleSkillLevelRepository workOrderRuleSkillLevelRepository;

	private WorkOrderRuleSkillLevelQueryHelper workOrderRuleSkillLevelQueryHelper;
	@Autowired
	private PriorityOverrideRuleRepository priorityOverrideRuleRepository;
	@Autowired
	private SeverityPriorityRuleRepository severityPriorityRuleRepository;
	@Autowired
	private R2dbcEntityTemplate  entityTemplate;


	private Mono<WorkOrderRule> findWorkOrderRulesWithPredicate(WorkSpecificationRequest request, String workOrderActionCatgryCd) {
		Criteria criteria = where(ConstantCodes.WORK_ORDER_CLASSIFICATION_CD.value).is(request.getWorkOrderClassificationCd())
				.and(ConstantCodes.SERVICE_CLASS_CD.value).is(request.getServiceClassCd())
				.and(ConstantCodes.PRODUCT_CD.value).is(request.getProductCd())
				.and(ConstantCodes.JOB_TYPE_CD.value).is(request.getJobTypeCd())
				.and(ConstantCodes.OUT_OF_SERVICE_IND.value).is(request.isOutOfServiceInd() ? ConstantCodes.FWDS_BOOLEAN_TRUE.value : ConstantCodes.FWDS_BOOLEAN_FALSE.value)
				.and(ConstantCodes.SLA_IND.value).is(request.isSlaInd() ? ConstantCodes.FWDS_BOOLEAN_TRUE.value: ConstantCodes.FWDS_BOOLEAN_FALSE.value);

		if (!GenericValidator.isBlankOrNull(request.getTechnologyCd())) {
			criteria.and(ConstantCodes.TECHNOLOGY_CD.value).is(request.getTechnologyCd());
		}
		if (!GenericValidator.isBlankOrNull(request.getServiceSubclassCd())) {
			criteria.and(ConstantCodes.SERVICE_SUBCLASS_CD.value).is(request.getServiceSubclassCd());
		} else {
			criteria.and(ConstantCodes.SERVICE_SUBCLASS_CD.value).isNull();
		}
		if (!GenericValidator.isBlankOrNull(workOrderActionCatgryCd)) {
			criteria.and(ConstantCodes.WORK_ORDER_ACTION_CATGRY_CD.value).is(workOrderActionCatgryCd);
		}
		return entityTemplate.select(WorkOrderRule.class).matching(query(criteria)).all().next();      
	}

	private Mono<WfmScopeRule> findWfmScopeRulesWithPredicate(WorkSpecificationRequest request) {
		Criteria criteria = where(ConstantCodes.WORK_ORDER_CLASSIFICATION_CD.value).is(request.getWorkOrderClassificationCd())
				.and(ConstantCodes.SERVICE_CLASS_CD.value).is(request.getServiceClassCd())
				.and(ConstantCodes.PRODUCT_CD.value).is(request.getProductCd());

		if (!GenericValidator.isBlankOrNull(request.getJobTypeCd())) {
			criteria.and(ConstantCodes.JOB_TYPE_CD.value).is(request.getJobTypeCd());
		}
		if (!GenericValidator.isBlankOrNull(request.getServiceSubclassCd())) {
			criteria.and(ConstantCodes.SERVICE_SUBCLASS_CD.value).is(request.getServiceSubclassCd());
		} 
		if (!GenericValidator.isBlankOrNull(request.getWorkgroupCd())) {
			criteria.and(ConstantCodes.WORKGROUP_CD.value).is(request.getWorkgroupCd());
		}
		if (!GenericValidator.isBlankOrNull(request.getServiceAreaClliCd())) {
			criteria.and(ConstantCodes.SERVICE_AREA_CLLI_CD.value).is(request.getServiceAreaClliCd());
		}
		return entityTemplate.select(WfmScopeRule.class).matching(query(criteria)).one();     
	}


	private  Mono<WfmPartnerScopeRule> findWfmPartnerScopeRulesWithPredicate(WorkSpecificationRequest request, String workOrderCategoryCd, String workOrderActionCatgryCd, String serviceAreaClliCd) {
		Criteria criteria = where(ConstantCodes.WORK_ORDER_CLASSIFICATION_CD.value).is(request.getWorkOrderClassificationCd())
				.and(ConstantCodes.SERVICE_CLASS_CD.value).is(request.getServiceClassCd())
				.and(ConstantCodes.PRODUCT_CD.value).is(request.getProductCd())
				.and(ConstantCodes.JOB_TYPE_CD.value).is(request.getJobTypeCd())
				.and(ConstantCodes.TECHNOLOGY_CD.value).is(request.getTechnologyCd())
				.and(ConstantCodes.WORK_ORDER_CATEGORY_CD.value).is(workOrderCategoryCd)
				.and(ConstantCodes.WORK_ORDER_ACTION_CATGRY_CD.value).is(workOrderActionCatgryCd)
				.and(ConstantCodes.SERVICE_AREA_CLLI_CD.value).is(serviceAreaClliCd)
				.and(ConstantCodes.EFFECTIVE_START_TS.value).lessThan(request.getEffectDT())
				.and(ConstantCodes.EFFECTIVE_END_TS.value).greaterThan(request.getEffectDT());
		if (!GenericValidator.isBlankOrNull(request.getWorkgroupCd())) {
			criteria.and(ConstantCodes.WORKGROUP_CD.value).is(request.getWorkgroupCd());
		}
		return entityTemplate.select(WfmPartnerScopeRule.class).matching(query(criteria)).all().next();  
	}

	public Mono<WorkSpecification> getWorkSpecificationById(String id, WorkSpecificationRole role) {
		WorkSpecificationIdHelper helper = new WorkSpecificationIdHelper(id);
		Mono<WorkOrderRule> workOrderRule = this.workOrderRuleRepository.findById(helper.getWorkOrderRuleId());

		Mono<WfmScopeRule> wfmScopeRule = this.wfmScopeRuleRepository.findById(helper.getWfmScopeRuleId());
		
		Mono<WorkOrderRuleSkillLevel> workOrderRuleSkillLevel = Mono.empty();
		if (helper.getWorkOrderSkillLevelId() != null) {
			workOrderRuleSkillLevel = this.workOrderRuleSkillLevelRepository.findById(helper.getWorkOrderSkillLevelId());
		}

		Mono<WorkSpecification> spec =  
				Mono.zip(workOrderRule, wfmScopeRule, workOrderRuleSkillLevel)
			.flatMap(data -> { 
				return getWorkSpecificationByWorkSpecificationRequest(buildWorkSpecificationRequest(helper, data.getT1(), data.getT2(), data.getT3()), role); 
		});
		
		WorkSpecificationIdHelper[] bundledComponents = helper.getCompleteBundledWorkSpecificationIdHelper();
		if (bundledComponents != null) {
				Mono<List<WorkSpecification>> compSpeclist = Flux.fromArray(bundledComponents).flatMap(e -> {
					return getWorkSpecificationById(e.generateId(), WorkSpecificationRole.COMPONENT);
				}).collectList();
				spec =	Mono.zip(spec, compSpeclist)
					.flatMap(data -> { 
						data.getT1().setComponentSpecificationList(data.getT2());
						return Mono.just(data.getT1());
				});
		}
		return spec;

	}
	
	private WorkSpecificationRequest buildWorkSpecificationRequest(WorkSpecificationIdHelper helper, WorkOrderRule workOrderRule, WfmScopeRule wfmScopeRule, WorkOrderRuleSkillLevel workOrderRuleSkillLevel) {
		WorkSpecificationRequest request = new WorkSpecificationRequest();
		request.setWorkOrderClassificationCd(workOrderRule.getWorkOrderClassificationCd());
		request.setTechnologyCd(workOrderRule.getTechnologyCd());
		request.setSlaInd(ConstantCodes.FWDS_BOOLEAN_TRUE.value.equals(workOrderRule.getSlaInd()));
		request.setServiceSubclassCd(workOrderRule.getServiceSubclassCd());
		request.setServiceClassCd(workOrderRule.getServiceClassCd());
		request.setProductCd(workOrderRule.getProductCd());
		request.setOutOfServiceInd(ConstantCodes.FWDS_BOOLEAN_TRUE.value.equals(workOrderRule.getOutOfServiceInd()));
		request.setJobTypeCd(workOrderRule.getJobTypeCd());
		request.setWorkgroupCd(wfmScopeRule.getWorkgroupCd());
		request.setServiceAreaClliCd(wfmScopeRule.getServiceAreaClliCd());
		request.setWorkOrderActionCd(helper.getWorkOrderActionCd());
		request.setInstallTypeCd(helper.getInstallTypeCd());
		request.setDuration(helper.getSwt());
		request.setEngagementLevel(helper.getEngagmentLevel());
		request.setSeverityCd(helper.getSeverityCd());
		request.setNumberOfTechRequired(helper.getNumberOfTechRequired());
		if (workOrderRuleSkillLevel != null) {
			request.setMultiUnitInd(ConstantCodes.FWDS_BOOLEAN_TRUE.value.equals(workOrderRuleSkillLevel.getMultiUnitInd()));
			request.setTroubleTypeTxt(workOrderRuleSkillLevel.getTroubleTypeTxt());
			request.setCauseLevel1Txt(workOrderRuleSkillLevel.getCauseLevel1Txt());
			request.setCauseLevel2Txt(workOrderRuleSkillLevel.getCauseLevel2Txt());
			request.setCauseLevel3Txt(workOrderRuleSkillLevel.getCauseLevel3Txt());
		}
		return request;
	}

	public Mono<WorkSpecification> getWorkSpecificationByWorkSpecificationRequest(WorkSpecificationRequest request, WorkSpecificationRole role) {
		return findWfmScopeRulesWithPredicate(request).flatMap(wfmScopeRule -> {
			WorkSpecification result = new WorkSpecification(role);
			result.setWfmScopeRule(wfmScopeRule);
			result.setWorkOrderActionCd(request.getWorkOrderActionCd());
			result.setSeverityCd(request.getSeverityCd());
			result.setEngagmentLevel(request.getEngagementLevel());
			if (request.getDuration() != null) result.setEstimatedDuration(request.getDuration());
			result.setInstallTypeCd(request.getInstallTypeCd());
			result.setNumberOfTechnicianRequired(request.getNumberOfTechRequired());
						
			virtualNavHierarchyRepository.findByWorkOrderCategoryCdAndServiceAreaClliCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(
					wfmScopeRule.getWorkOrderCategoryCd(), wfmScopeRule.getServiceAreaClliCd(), request.getEffectDT(), request.getEffectDT())
					.subscribe(virtualNavHierarchy -> result.setVirtualNavHierarchy(virtualNavHierarchy));
			
			if (!GenericValidator.isBlankOrNull(request.getEngagementLevel())) {
				this.priorityOverrideRuleRepository.findByDomainNmAndDomainValueCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(ConstantCodes.ENGAGEMENT_LEVEL.value, request.getEngagementLevel(), request.getEffectDT(), request.getEffectDT())
				.subscribe(rule-> result.setPriorityOverrideRule(rule));
			}
					
			if (!GenericValidator.isBlankOrNull(request.getSeverityCd())) {
				this.severityPriorityRuleRepository.findByWorkOrderCategoryCdAndSeverityCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(wfmScopeRule.getWorkOrderCategoryCd(), request.getSeverityCd(), request.getEffectDT(), request.getEffectDT())
				.subscribe(rule -> result.setSeverityPriorityRule(rule));	
			} 
			
			Mono<WorkOrderRuleSkillLevelQueryHelper> skillLevelQueryHelper;
			if (workOrderRuleSkillLevelQueryHelper == null) {
				Mono<List<WorkOrderRuleSkillLevel>> slList1 = workOrderRuleSkillLevelRepository.findAllByMultiUnitIndAndEffectiveEndTsAfterAndEffectiveStartTsBefore(ConstantCodes.FWDS_BOOLEAN_TRUE.value, request.getEffectDT(), request.getEffectDT()).collectList(); 
				Mono<List<WorkOrderRuleSkillLevel>> slList2 = workOrderRuleSkillLevelRepository.findAllByMultiUnitIndAndEffectiveEndTsAfterAndEffectiveStartTsBefore(ConstantCodes.FWDS_BOOLEAN_FALSE.value, request.getEffectDT(), request.getEffectDT()).collectList();
				skillLevelQueryHelper = Mono.zip(slList1, slList2, WorkOrderRuleSkillLevelQueryHelper::new);
			} else {
				skillLevelQueryHelper = Mono.just(workOrderRuleSkillLevelQueryHelper);
			}
			skillLevelQueryHelper.subscribe(rule -> result.setWorkOrderRuleSkillLevel(rule.getSkillLevel(request)));

			Mono<WorkOrderRule> workOrderRule;
			if (!GenericValidator.isBlankOrNull(request.getWorkOrderActionCd())) {
				workOrderRule = workOrderActionRuleRepository.findAllByWorkOrderCategoryCdAndWorkOrderActionCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore
					(wfmScopeRule.getWorkOrderCategoryCd(), request.getWorkOrderActionCd(), request.getEffectDT(), request.getEffectDT()).collectList()
					.flatMap(rules -> {
						result.setWorkOrderActionRuleList(rules);
						String workOrderActionCatgryCd = WorkSpecificationUtils.getWorkOrderActionRuleByWorkOrderActionUsageCd(
								rules,  WorkOrderActionRule.WORK_ORDER_ACTION_USAGE_CODE.SUB_DEMAND.name()).getWorkOrderActionCtgryCd();
						return this.findWorkOrderRulesWithPredicate(request, workOrderActionCatgryCd);
					});
			} else {
				workOrderRule = this.findWorkOrderRulesWithPredicate(request, null);
			}
			workOrderRule.subscribe(rule -> {
				result.setWorkOrderRule(rule);
				result.setHasComponent(ConstantCodes.FWDS_BOOLEAN_TRUE.value.equals(rule.getComponentRequiredInd()));
				if (ConstantCodes.FWDS_BOOLEAN_FALSE.value.equals(rule.getComponentRequiredInd())) {
					workOrderRuleSkillRepository.findAllByWorkOrderClassificationCdAndTechnologyCdAndServiceSubclassCdAndServiceClassCdAndProductCdAndJobTypeCdAndEffectiveStartTsBeforeAndEffectiveEndTsAfter(
							request.getWorkOrderClassificationCd(), request.getTechnologyCd(), null, request.getServiceClassCd(), request.getProductCd(), request.getJobTypeCd(), request.getEffectDT(), request.getEffectDT())
					.collectList().subscribe(skills -> result.setWorkOrderRuleSkillList(skills));
					
					WorkOrderActionRule partner_workOrderActionRule = WorkSpecificationUtils.getWorkOrderActionRuleByWorkOrderActionUsageCd(result.getWorkOrderActionRuleList(), WorkOrderActionRule.WORK_ORDER_ACTION_USAGE_CODE.PARTNER.name());
					this.findWfmPartnerScopeRulesWithPredicate(request, wfmScopeRule.getWorkOrderCategoryCd(),
							partner_workOrderActionRule != null? partner_workOrderActionRule.getWorkOrderActionCtgryCd() : null, wfmScopeRule.getServiceAreaClliCd())
					.subscribe(wfmPartnerScopeRule -> result.setWfmPartnerScopeRule(wfmPartnerScopeRule));
				}
			});
			
			WorkSpecificationRequest[] bundled = WorkSpecificationIdHelper.parseBundledWorkSpecificationRequest(request);
			if (bundled != null) {
				result.setComponentSpecificationList(new LinkedList());;
				Flux.fromArray(bundled).flatMap(e -> {
					return getWorkSpecificationByWorkSpecificationRequest(e, WorkSpecificationRole.COMPONENT);
				}).collectList().subscribe(list -> {
					result.setComponentSpecificationList(list);
					result.setEstimatedDuration(list.stream().mapToDouble(f -> f.getEstimatedDuration().doubleValue()).sum());	
				});
			}
			result.setId(new WorkSpecificationIdHelper(result).generateId());
			return Mono.just(result);
		});
	}


}
