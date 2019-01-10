package oth.common.constants;

import oth.common.tools.IStringUtil;

public enum TimeDirectEnum {
	UP("23:59:59", "Last time"),
	DOWN("00:00:00", "minimum time"),
	NORMAL("", "normal");
	
	
	private String code;
	private String desc;

	TimeDirectEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCodeByName(String name){
		name = IStringUtil.replaceBlank2(name).toUpperCase();
		TimeDirectEnum[] values = TimeDirectEnum.values();
		for(TimeDirectEnum value : values){
			if(value.name().equals(name)){
				return value.getCode();
			}
		}
		return null;
	}
	
	public static String getCodeByDesc(String desc){
		TimeDirectEnum[] values = TimeDirectEnum.values();
		for(TimeDirectEnum value : values){
			if(value.getDesc().equals(desc)){
				return value.getCode();
			}
		}
		return null;
	}
	
	public static String getDescByCode(String code){
		TimeDirectEnum[] values = TimeDirectEnum.values();
		for(TimeDirectEnum value : values){
			if(value.getCode().equals(code)){
				return value.getDesc();
			}
		}
		return null;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
