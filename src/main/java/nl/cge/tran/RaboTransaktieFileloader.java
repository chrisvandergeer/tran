package nl.cge.tran;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RaboTransaktieFileloader {

	public RaboTransaktieFile load(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			return RaboTransaktieFile.newInstance(reader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
