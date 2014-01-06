package com.example.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
public class Employee {
	private String id;
	private String name;
	private String project;

	public Employee(){
		
	}
	public Employee(String id, String name, String project){
		this.id=id;
		this.name=name;
		this.project=project;		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	public String toString(){
		
		return "ID :: "+getId()+"\nName :: "+getName()+"\nProject :: "+getProject();
		
	}
}
