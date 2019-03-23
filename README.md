# D3-Force-Graph-Utility
---------------------------

This is utility project which aims to simplify the process of creating a D3 Force Directed Graph.
To view sample of a Force Graph View https://embed.plnkr.co/plunk/VZQjMV

The D3 json requires a complex mapping of ids and thier relationships.Using this Utility Class NetworkGraph the json required for D3 can be made easily.
	
NetworkGraph ng= new NetworkGraph("Initials");
	ng.addNode("RG", "Rahul Gandhi",1,5);
	ng.addNode("SG", "Sonia Gandhi",1,5);
	ng.addEdge("RG", "SG", "Mother");
	ng.createD3Json()
