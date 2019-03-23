package com.d3.util;

import java.util.HashMap;
import java.util.Map;

public class Edge {

	// ***********************************
	// **** Instance Variables ***********
	// ***********************************
	private int edgeId;
	private Node sourceNode;
	private Node targetNode;
	private String edgeLabel;
	private double edgeWeight;
	private int edgeGroup;
	// Final Variables
	private final double defaultWeiughtIncrement = 0.50;

	/**
	 * Constructor of Edge
	 * 
	 * @param sorce  -parent Node
	 * @param target -child Node
	 * @param label  -name of edge
	 * @param id
	 * @param group
	 * @param weight
	 */
	public Edge(Node sorce, Node target, String label, int id, int group, double weight) {
		this.sourceNode=sorce;
		this.targetNode=target;
		this.edgeLabel=label;
		this.edgeId=id;
		this.edgeWeight=weight;
		this.edgeGroup=group;
	}

	/**
	 * Methods
	 */
	public void incrementWieght() {
		edgeWeight = getEdgeWeight() + defaultWeiughtIncrement;
	}

	public Map<String, Object> getDetails()
	{Map<String,Object>nodeDetailMap= new HashMap<String, Object>();
	nodeDetailMap.put("id", getEdgeId());
	nodeDetailMap.put("label", getEdgeLabel());
	nodeDetailMap.put("source",sourceNode.getNodeId());
	nodeDetailMap.put("target",targetNode.getNodeId());
	nodeDetailMap.put("weight", getEdgeWeight());
	
	return nodeDetailMap;
	}
	/**
	 * POJO
	 * 
	 */
	public int getEdgeId() {
		return edgeId;
	}

	public Node getSourceNode() {
		return sourceNode;
	}

	public Node getTargetNode() {
		return targetNode;
	}

	public String getEdgeLabel() {
		return edgeLabel;
	}

	public double getEdgeWeight() {
		return edgeWeight;
	}

}
