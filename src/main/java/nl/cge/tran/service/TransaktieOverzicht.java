package nl.cge.tran.service;

import nl.cge.tran.Transaktie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NOHi
 * Date: 4-6-13
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */
public class TransaktieOverzicht {

    private List<Transaktie> transakties;
    private double totalPositive = 0d;
    private double totalNegative = 0d;

    public double getTotalPositive() {
        return totalPositive;
    }

    public void setTotalPositive(double totalPositive) {
        this.totalPositive = totalPositive;
    }

    public double getTotalNegative() {
        return totalNegative;
    }

    public void setTotalNegative(double totalNegative) {
        this.totalNegative = totalNegative;
    }

    public List<Transaktie> getTransakties() {
        return transakties;
    }

    public void setTransakties(List<Transaktie> transakties) {
        this.transakties = transakties;
    }
}
