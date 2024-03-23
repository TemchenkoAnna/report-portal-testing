package com.report.conditions.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetSize{

	@JsonProperty("width")
	private int width;

	@JsonProperty("height")
	private int height;

	@Override
 	public String toString(){
		return 
			"WidgetSize{" + 
			"width = '" + width + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}