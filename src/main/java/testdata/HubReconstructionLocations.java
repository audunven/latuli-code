package testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

/**
 * @author audunvennesland
 *
 */
public class HubReconstructionLocations
{

	public static void processHubReconstructionLocations (File hubReconstructionLocationsFolder, String baseURI, String dataDir, String indexes, Repository repo) {


		try (RepositoryConnection connection = repo.getConnection()) {
			
			connection.setNamespace("lat", baseURI);

			ValueFactory vf = connection.getValueFactory();

			IRI hubReconstructionInd;
			IRI partyInd;
			IRI hubReconstructionClass = vf.createIRI(baseURI, "HubReconstructionLocation");
			IRI terminalOperatorClass = vf.createIRI(baseURI, "TerminalOperator");

			File[] filesInDir = hubReconstructionLocationsFolder.listFiles();
			String[] params = null;

			BufferedReader br = null;

			for (int i = 0; i < filesInDir.length; i++) {


				try {

					String line;		

					br = new BufferedReader(new FileReader(filesInDir[i]));

					System.out.println("Reading file: " + filesInDir[i].getName());

					while ((line = br.readLine()) != null) {

						params = line.split(",");

						//adding type
						hubReconstructionInd = vf.createIRI(baseURI, params[0] + "_hubReconstructionLocation");			
						connection.add(hubReconstructionInd, RDF.TYPE, hubReconstructionClass);
						
						//adding predicates
						partyInd = vf.createIRI(baseURI, params[3] + "_party");
						connection.add(partyInd, RDF.TYPE, terminalOperatorClass);
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "hasHubParty"), partyInd);

						//adding literals
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "hubReconstructionLocationId"), vf.createLiteral(params[0]));
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "additionalPartyIdentification"), vf.createLiteral(params[1]));
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "reconstructionLane"), vf.createLiteral(params[4]));

					}//end while

				} catch (IOException e) {

					e.printStackTrace();

				} finally {

					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}


			}

		}
		
		repo.shutDown();

	}
	
	public static void processHubReconstructionLocationsHTTP (File hubReconstructionLocationsFolder, String baseURI, String rdf4jServer, String repositoryId, Repository repo) {

		try (RepositoryConnection connection = repo.getConnection()) {
			
			connection.setNamespace("lat", baseURI);

			ValueFactory vf = connection.getValueFactory();


			IRI hubReconstructionInd;
			IRI partyInd;
			IRI hubReconstructionClass = vf.createIRI(baseURI, "HubReconstructionLocation");
			IRI terminalOperatorClass = vf.createIRI(baseURI, "TerminalOperator");

			File[] filesInDir = hubReconstructionLocationsFolder.listFiles();
			String[] params = null;

			BufferedReader br = null;

			for (int i = 0; i < filesInDir.length; i++) {


				try {

					String line;		

					br = new BufferedReader(new FileReader(filesInDir[i]));

					System.out.println("Reading file: " + filesInDir[i].getName());

					while ((line = br.readLine()) != null) {

						params = line.split(",");

						//adding type
						hubReconstructionInd = vf.createIRI(baseURI, params[0] + "_hubReconstructionLocation");			
						connection.add(hubReconstructionInd, RDF.TYPE, hubReconstructionClass);
						
						//adding predicates
						partyInd = vf.createIRI(baseURI, params[3] + "_party");
						connection.add(partyInd, RDF.TYPE, terminalOperatorClass);
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "hasHubParty"), partyInd);

						//adding literals
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "hubReconstructionLocationId"), vf.createLiteral(params[0]));
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "additionalPartyIdentification"), vf.createLiteral(params[1]));
						connection.add(hubReconstructionInd, vf.createIRI(baseURI + "reconstructionLane"), vf.createLiteral(params[4]));

					}//end while

				} catch (IOException e) {

					e.printStackTrace();

				} finally {

					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}


			}

		}
		
		repo.shutDown();

	}

}//end class
