import java.util.HashMap;
import java.util.Map;

import com.d3.util.NetworkGraph;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class DbpediaGraph
{
	 String rdfs_label="http://www.w3.org/2000/01/rdf-schema#label";
	 String dct_subject="http://purl.org/dc/terms/subject";	 
	 

 public static void main(String[] args) throws JsonProcessingException {
	 ObjectMapper mapper= new ObjectMapper();
      System.out.println( mapper.writeValueAsString( new DbpediaGraph().createTree()));
      //View Output -/Images/DbpediaNG.png
    }
 
 public Map<String, String> exploreURI(String uri, String prefix)
 { 
	Map<String, String> outputMap= new HashMap<String, String>();

	ParameterizedSparqlString qs = new ParameterizedSparqlString( "select distinct ?s  ?l where {<"+uri+"> <"+prefix+"> ?s. ?s <"+rdfs_label+"> ?l.} LIMIT 100" );
 	System.out.println( qs );
 	  QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );	
   ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

 while ( results.hasNext() ) {
	QuerySolution solution = results.next();
	 System.out.println("s"+solution.get( "s" ).toString());
	 System.out.println("l"+solution.getLiteral( "l" ).toString());
     outputMap.put( solution.get( "s" ).toString(),solution.getLiteral( "l" ).toString());
 }
 // A simpler way of printing the results.
// ResultSetFormatter.out( results );
return outputMap;
 }
 public Map<String, String> getSimilarItems(String uri)
 {Map<String, String> outputMap= new HashMap<String, String>();

	ParameterizedSparqlString qs = new ParameterizedSparqlString( "select distinct ?s  ?l where {?s <"+dct_subject+"> <"+uri+"> . ?s <"+rdfs_label+"> ?l.FILTER (lang(?l) = 'en') } LIMIT 10" );
	System.out.println( qs );
	  QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );	
ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

while ( results.hasNext() ) {
	QuerySolution solution = results.next();
  outputMap.put( solution.get( "s" ).toString(),solution.getLiteral( "l" ).toString());
}
// A simpler way of printing the results.
//ResultSetFormatter.out( results );
return outputMap;
}

 public Map<String, Object> createTree()
 {
	 String uri="http://dbpedia.org/resource/Donald_Trump";
	 NetworkGraph ng=new NetworkGraph("label");
	 ng.addNode(uri, "Donald Trump");
	Map<String, String> subjectMap = exploreURI(uri, dct_subject);
	int count=0;
	for (String subjectUri : subjectMap.keySet()) {
		 ng.addNode(subjectUri, subjectMap.get(subjectUri),2,count);
		 ng.addEdge(uri, subjectUri, "subject",2,count);
		Map<String, String> simialarMap = getSimilarItems(subjectUri);
			for (String similarUri: simialarMap.keySet()) {
				 ng.addNode(similarUri, simialarMap.get(similarUri),1,count);
				 ng.addEdge( subjectUri,similarUri, "subject",1,count);
			}
		count++;
	}
return ng.createD3Json();
 }
}
 
