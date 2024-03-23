package com.report.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class AddWidget{

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
}