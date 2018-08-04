package com.siyann.db;

public class AlarmUtil {
	public static String getAlarmPattern(Integer alarm_pattern) {
		String description = "";
		switch (alarm_pattern) {
		case 0:
			description = "入侵报警";
			break;
		case 1:
			description = "断线报警";
			break;
		case 2:
			description = "断线恢复报警";
			break;
		}
		return description;
	}

	public static String getDescription(String alarm_level) {
		String description = "";
		switch (Integer.parseInt(alarm_level)) {
		case -1:
			description = "断线";
			break;
		case -2:
			description = "恢复";
			break;
		case 1:
			description = "触动光芯";
			break;
		case 2:
			description = "触碰接头盒";
			break;
		case 3:
			description = "刮擦光缆或轻微触碰光缆或触碰光缆";
			break;
		// case 4:
		// description = "";
		// break;
		}
		return description;
	}
}
