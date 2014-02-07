package nl.cge.tran.persistence;

import java.util.List;

import nl.cge.tran.domein.Transaktie;

public interface TransaktieDao {

	public abstract void init();

	public abstract void close();

	public abstract void save(Transaktie transaktie);

	public abstract void commit();

	public abstract Transaktie read(Integer id);

	public abstract List<Transaktie> findAll();

	public abstract void save(List<Transaktie> transakties);

	public abstract void deleteAll();

}