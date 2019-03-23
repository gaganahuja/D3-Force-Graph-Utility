package com.d3.util;

import java.util.HashMap;
import java.util.Map;

public class Node {

	// ***********************************
	// **** Instance Variables ***********
	// ***********************************
	private String uniqueFieldVal;
	private int nodeId;
	private String nodeLabel;
	private int group;
	private double weight;
	private Map<String,Object>metadata;
	
	// Final Variables
	private final double defaultWieghtIncrementor = 1.0;
	
	/**
	 * Constructor for Node
	 * @param uniqueFieldValue- Value of field which can not be duplicate in the Graph 
	 * @param label-name of the Node
	 * @param nodeId
	 * @param group
	 * @param weight
	 */
	public Node(String uniqueFieldValue, String label, int nodeId, int group, double weight,Map<String,Object>meta) {
		this.nodeId=nodeId;
		this.nodeLabel=label;
		this.uniqueFieldVal=uniqueFieldValue;
		this.group=group;
		this.weight=weight;
		this.metadata=meta;
		
}

	/**
	 * Methods
	 */
	
	public void incrementWeight() {
		weight = getWeight() + defaultWieghtIncrementor;
	}

	public Map<String, Object> getDetails()
	{Map<String,Object>nodeDetailMap= new HashMap<String, Object>();
	nodeDetailMap.put("id", getNodeId());
	nodeDetailMap.put("label", getNodeLabel());
	nodeDetailMap.put("group", getGroup());
	nodeDetailMap.put("weight", getWeight());
	nodeDetailMap.put("uniqueKeyValue", getUniqueFieldVal());
	if(metadata!=null&&!metadata.isEmpty())
		nodeDetailMap.putAll(metadata);
	return nodeDetailMap;
	}
	//POJO
	public String getUniqueFieldVal() {
		return uniqueFieldVal;
	}

	public int getNodeId() {
		return nodeId;
	}

	public String getNodeLabel() {
		return nodeLabel;
	}

	public int getGroup() {
		return group;
	}

	public double getWeight() {
		return weight;
	}

}
