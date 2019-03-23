package com.d3.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkGraph {

	//***********************************
	//**** Instance Variables ***********
	//***********************************
	private List<Node>nodes= new ArrayList<Node>();
	private List<Edge>edges= new ArrayList<Edge>();
	private String uniqueFiled;
	
	// final Variables
	private final int defaultNodeGroup=0;
	private final double defaultNodeWeight=0.00;
	private final int defaultEdgeGroup=0;
	private final double defaultEdgeWeight=0.00;
	
	/**
	 *  Constructor for Class Network Graph 
	 * @param fieldName
	 */
	public NetworkGraph(String fieldName) {
	this.uniqueFiled=fieldName;
		}
	
	/**
	 * Methods
	 */
	//NODES
	public boolean addNode(String uniqueField,String label,long weight,int group)
	{return insertNode(uniqueField, label,  group,weight);}

	public boolean addNode(String uniqueField,String label)
	{return insertNode(uniqueField,label,defaultNodeGroup,defaultNodeWeight);}
	
	private boolean insertNode(String uniqueFieldValue,String label,int group,double weight)
	{ 
		if(uniqueFieldValue==null||label==null||uniqueFieldValue.isEmpty()||label.isEmpty())
			return false;
		Node newNode=new Node(uniqueFieldValue,label,nodes.size(),group,weight,null);
	    if(nodes.isEmpty())
		{nodes.add(newNode);
			return true;
		}
		else {
			for (Node node : nodes) {
				if(node.getUniqueFieldVal().equals(uniqueFieldValue))
				{ 
					node.incrementWeight();
					return true;
				}
			}
		
		nodes.add(newNode);
		return true;
		}
	}
	// EDGES
	public boolean addEdge(String sorceNodeUniqueValue,String targetNodeUniqueValue,String edgeLabel)
	{return insertEdge(sorceNodeUniqueValue, targetNodeUniqueValue, edgeLabel, defaultEdgeGroup, defaultEdgeWeight);}
	public boolean addEdge(String sorceNodeUniqueValue,String targetNodeUniqueValue,String edgeLabel,int group,double weight)
	{return insertEdge(sorceNodeUniqueValue, targetNodeUniqueValue, edgeLabel, group, weight);}
	private boolean insertEdge(String sorceNodeUniqueValue,String targetNodeUniqueValue,String edgeLabel,int group,double weight)
{
		Node sourceNode=null;
		Node targetNode=null;
		
		if(sorceNodeUniqueValue==null||targetNodeUniqueValue==null||edgeLabel==null||edgeLabel.isEmpty()||sorceNodeUniqueValue.isEmpty()||targetNodeUniqueValue.isEmpty())
		return false;
		
		
		for (Edge edge : edges) {
			if(edge.getTargetNode().getUniqueFieldVal().equals(targetNodeUniqueValue) && edge.getTargetNode().getUniqueFieldVal().equals(sorceNodeUniqueValue))
			{edge.incrementWieght();
			return true;}
		}
		for (Node node : nodes) {
			if(node.getUniqueFieldVal().equals(sorceNodeUniqueValue))
			{ sourceNode=node;
				node.incrementWeight();
			}
			if(node.getUniqueFieldVal().equals(targetNodeUniqueValue))
			{ targetNode=node;
				node.incrementWeight();
			}
		}
		if(sourceNode!=null&&targetNode!=null)
		{
			Edge newEdge=new Edge(sourceNode,targetNode,edgeLabel,edges.size(),group,weight);
			edges.add(newEdge);
			return true;
		}
		return false;
		}
	/**
	 * @return 
	 * 
	 */
	public Map<String, Object> createD3Json()
	{
		Map<String,Object>outputMap= new HashMap<String, Object>();
		List<Map<String,Object>>nodeList= new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>edgeList= new ArrayList<Map<String,Object>>();
		for (Node node : nodes) {
			nodeList.add(node.getDetails());
		}
		for (Edge edge : edges) {
			edgeList.add(edge.getDetails());
		}
		outputMap.put("nodes", nodeList);
		outputMap.put("links", edgeList);
		return outputMap;
	}
	
}
