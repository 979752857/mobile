package com.tendy.common;

import java.util.stream.Stream;

/**
 * @author tiandi@guazi.com
 * @description
 * @date 2019/9/21
 */
public enum GeomanticType {
	LUCKY(1, "吉", ""),
	UNLUCKY(2, "凶", ""),
	WEAK_UNLUCKY(3, "凶带吉", ""),
	WEAK_LUCKY(4, "吉带凶", ""),
	UNKOWN(0, "未知", "");

	private Integer code;
	private String tag;
	private String desc;

	GeomanticType(Integer code, String tag, String desc) {
		this.code = code;
		this.tag = tag;
		this.desc = desc;
	}

	public static GeomanticType getBytag(String tag) {
		return Stream.of(GeomanticType.values()).filter(item -> item.getTag().equals(tag))
				.findFirst().orElse(UNKOWN);
	}

	public Integer getCode() {
		return code;
	}

	public GeomanticType setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getTag() {
		return tag;
	}

	public GeomanticType setTag(String tag) {
		this.tag = tag;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public GeomanticType setDesc(String desc) {
		this.desc = desc;
		return this;
	}
}
