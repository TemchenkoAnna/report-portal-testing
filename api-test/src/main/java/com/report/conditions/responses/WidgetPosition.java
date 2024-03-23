package com.report.conditions.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetPosition{

	@JsonProperty("positionY")
	private int positionY;

	@JsonProperty("positionX")
	private int positionX;

	@Override
 	public String toString(){
		return 
			"WidgetPosition{" + 
			"positionY = '" + positionY + '\'' + 
			",positionX = '" + positionX + '\'' + 
			"}";
		}
}