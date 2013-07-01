package nl.cge.tran;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaboTransaktieFile {
	
	private BufferedReader reader;	

	public List<Transaktie> parse() {
		List<Transaktie> result = new ArrayList<Transaktie>();
		String line = readLine();
		while (line != null && !"".equals(line.trim())) {
			RaboTransaktieRegel raboregel = RaboTransaktieRegel.newInstance();
			Transaktie transaktie = new Transaktie();
			raboregel.setRegel(line);
			transaktie.setRekeningnummer(raboregel.getRekeningnummer());
			transaktie.setBedrag(raboregel.getBedrag());
			transaktie.setDatum(raboregel.getDatum());
			transaktie.setOmschrijving1(raboregel.getOmschrijving1());
			transaktie.setOmschrijving2(raboregel.getOmschrijving2());
			transaktie.setOmschrijving3(raboregel.getOmschrijving3());
			transaktie.setOmschrijving4(raboregel.getOmschrijving4());
			transaktie.setTegenrekening(raboregel.getTegenrekening());
			transaktie.setTegenrekeningnaam(raboregel.getTegenrekeningnaam());
			result.add(transaktie);
			line = readLine();
		}
		return result;
	}

	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static RaboTransaktieFile newInstance(BufferedReader reader) {
		RaboTransaktieFile raboTransaktieFile = new RaboTransaktieFile();
		raboTransaktieFile.reader = reader;
		return raboTransaktieFile;
	}

}
