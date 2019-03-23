import com.d3.util.NetworkGraph;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphTester {

	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper= new ObjectMapper();
	
		NetworkGraph ng= new NetworkGraph("Initials");
		ng.addNode("INDIA", "India",1,0);
		ng.addNode("PP", "Political Parties",1,1);
		ng.addNode("RG", "Rahul Gandhi",1,5);
		ng.addNode("SG", "Sonia Gandhi",1,5);
		ng.addNode("NM", "Narendra Modi",2,9);
		ng.addNode("BJP", "Bhartiya Janta Party",2,9);
		ng.addNode("INC", "Indian National Congress",2,5);
		ng.addNode("AS", "Amit Shah",2,9);
		ng.addEdge("RG", "INC", "HEAD");
		ng.addEdge("SG", "INC", "Leader");
		ng.addEdge("NM", "BJP", "HEAD");
		ng.addEdge("AS", "BJP", "Leader");
		ng.addEdge("INDIA", "PP", "Leader");
		ng.addEdge("PP", "INC", "Leader");
		ng.addEdge("PP", "BJP", "Leader");
		System.out.println(mapper.writeValueAsString(ng.createD3Json()));
		 //View Output -/Images/PloiticalPartyNG.png
		
		
	}
}
