package com.report.conditions.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetsItem{

	@JsonProperty("widgetName")
	private String widgetName;

	@JsonProperty("widgetOptions")
	private WidgetOptions widgetOptions;

	@JsonProperty("widgetId")
	private int widgetId;

	@JsonProperty("widgetPosition")
	private WidgetPosition widgetPosition;

	@JsonProperty("widgetSize")
	private WidgetSize widgetSize;

	@JsonProperty("widgetType")
	private String widgetType;

	@Override
 	public String toString(){
		return 
			"WidgetsItem{" + 
			"widgetName = '" + widgetName + '\'' + 
			",widgetOptions = '" + widgetOptions + '\'' + 
			",widgetId = '" + widgetId + '\'' + 
			",widgetPosition = '" + widgetPosition + '\'' + 
			",widgetSize = '" + widgetSize + '\'' + 
			",widgetType = '" + widgetType + '\'' + 
			"}";
		}
}