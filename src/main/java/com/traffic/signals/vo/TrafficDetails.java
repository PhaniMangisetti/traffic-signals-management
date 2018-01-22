package com.traffic.signals.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class TrafficDetails {
	// HashMap to track the current signal state at a given moment
	Map<String, Object> stateData = new HashMap<String, Object>();
	
	// getters/setters methods
	public Map<String, Object> getStateData() {
		return stateData;
	}
	public void setStateData(Map<String, Object> stateData) {
		this.stateData = stateData;
	}
	
	// To print the object
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	 
}
