package com.siyann.db;

//{"command_code":1001,"description":"alarm","device_id":0,"alarm_pattern":0,"alarm_zone":0,"alarm_time":"2018-08-02 17:08:11"}
// {"command_code":1001,"description":"alarm","seq_num":"","device_id":0,"alarm_num":0,"alarm_level":0,"alarm_pattern":0,"alarm_zone":0,"alarm_time":"2018-08-02 17:12:50"}
public class ShortDistance {
	private Integer command_code;
	private String description;
	private String seq_num;
	private Integer device_id;
	private Integer alarm_num;
	private Integer alarm_level;
	private Integer alarm_pattern;
	private Integer alarm_zone;
	private String alarm_time;

	public ShortDistance() {
		super();
	}

	public ShortDistance(Integer command_code, String description,
			String seq_num, Integer device_id, Integer alarm_num,
			Integer alarm_level, Integer alarm_pattern, Integer alarm_zone,
			String alarm_time) {
		super();
		this.command_code = command_code;
		this.description = description;
		this.seq_num = seq_num;
		this.device_id = device_id;
		this.alarm_num = alarm_num;
		this.alarm_level = alarm_level;
		this.alarm_pattern = alarm_pattern;
		this.alarm_zone = alarm_zone;
		this.alarm_time = alarm_time;
	}

	public Integer getCommand_code() {
		return command_code;
	}

	public void setCommand_code(Integer command_code) {
		this.command_code = command_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeq_num() {
		return seq_num;
	}

	public void setSeq_num(String seq_num) {
		this.seq_num = seq_num;
	}

	public Integer getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}

	public Integer getAlarm_num() {
		return alarm_num;
	}

	public void setAlarm_num(Integer alarm_num) {
		this.alarm_num = alarm_num;
	}

	public Integer getAlarm_level() {
		return alarm_level;
	}

	public void setAlarm_level(Integer alarm_level) {
		this.alarm_level = alarm_level;
	}

	public Integer getAlarm_pattern() {
		return alarm_pattern;
	}

	public void setAlarm_pattern(Integer alarm_pattern) {
		this.alarm_pattern = alarm_pattern;
	}

	public Integer getAlarm_zone() {
		return alarm_zone;
	}

	public void setAlarm_zone(Integer alarm_zone) {
		this.alarm_zone = alarm_zone;
	}

	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

}
