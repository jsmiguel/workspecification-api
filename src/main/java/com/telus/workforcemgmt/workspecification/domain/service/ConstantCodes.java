package com.telus.workforcemgmt.workspecification.domain.service;

import java.math.BigDecimal;

public enum ConstantCodes {

	WORK_ORDER_CLASSIFICATION_CD("workOrderClassificationCd"), SERVICE_CLASS_CD("serviceClassCd"),
	PRODUCT_CD("productCd"), JOB_TYPE_CD("jobTypeCd"), TECHNOLOGY_CD("technologyCd"),
	SERVICE_SUBCLASS_CD("serviceSubclassCd"), OUT_OF_SERVICE_IND("outOfServiceInd"), SLA_IND("slaInd"),
	WORKGROUP_CD("workgroupCd"), SERVICE_AREA_CLLI_CD("serviceAreaClliCd"), FMS_COID("fmsCoid"),
	PROVINCE_CD("provinceCd"), EFFECTIVE_START_TS("effectiveStartTs"), EFFECTIVE_END_TS("effectiveEndTs"),
	WORK_ORDER_CATEGORY_CD("workOrderCategoryCd"), WORK_ORDER_ACTION_CATGRY_CD("workOrderActionCatgryCd"),
	FWDS_BOOLEAN_TRUE("1"), FWDS_BOOLEAN_FALSE("0"), BOOLEAN_TRUE("1"), ONE_DAY_DURAION("6.5"),
	ENGAGEMENT_LEVEL("ENGAGEMENT_LEVEL"), MASS_MARKET("MM"), 
	SECURITY_JOB_SINGLEDAY_THRESHHOLD("6.5"), 
	SECURITY_JOB_DURATION_PERDAY("6"), 
	SECURITY_PRODUCT_CATEGORIES ("SECURITY"), 
	MULTI_TECH_JOB("MULTI_TECH"),
	SECURITY_MULTI_TECH_JOB("SEC_TECH"),
	NONSECURITY_MULTI_TECH_JOB("NONSEC_TECH")
	;

	public final String value;

	private ConstantCodes(String label) {
		this.value = label;
	}
	
	public BigDecimal getBigDecimalValue() {
		return BigDecimal.valueOf(Double.parseDouble(value));
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value);
	}
	
	public String[] getStringArrayValue() {
		if (value.contains(",")) {
		 return value.split(",");
		} else {
			String[] result = new String[1];
			result[0] = value;
			return result;
		}
	}
}
