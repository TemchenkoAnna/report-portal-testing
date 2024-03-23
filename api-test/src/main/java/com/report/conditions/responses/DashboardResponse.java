package com.report.conditions.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Getter
@Setter
@Accessors(fluent = true)
public class DashboardResponse{

	@JsonProperty("owner")
	private String owner;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	@JsonProperty("widgets")
	private List<WidgetsItem> widgets;

	@Override
 	public String toString(){
		return 
			"DashboardResponse{" + 
			"owner = '" + owner + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",widgets = '" + widgets + '\'' + 
			"}";
		}
}