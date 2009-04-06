/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

/**
 *
 * @author owner
 */
public class CategoriesBean {
    private double energyTotal=0;
    private double healthTotal=0;
    private double transitTotal=0;
    private double investTotal=0;

    public CategoriesBean(double energy, double health, double transit, double invest)
    {
        energyTotal=energy;
        healthTotal=health;
        transitTotal=transit;
        investTotal=invest;
    }

    public double getEnergyTotal() {
        return energyTotal;
    }

    public double getHealthTotal() {
        return healthTotal;
    }

    public double getInvestTotal() {
        return investTotal;
    }

    public double getTransitTotal() {
        return transitTotal;
    }

}
