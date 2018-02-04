package ium.project.clanmanagerforclashroyale.data;

/**
 * Created by admin on 23/01/2018.
 */

public class Filtro {
    private int maxCoroneBaule = Integer.MAX_VALUE;
    private int minCoroneBaule = 0;
    private int maxDonazioni = Integer.MAX_VALUE;;
    private int minDonazioni = 0;
    private int maxTrofei = Integer.MAX_VALUE;;
    private int minTrofei = 0;

    public int getMaxCoroneBaule() {
        return maxCoroneBaule;
    }

    public void setMaxCoroneBaule(int maxCoroneBaule) {
        this.maxCoroneBaule = maxCoroneBaule;
    }

    public int getMinCoroneBaule() {
        return minCoroneBaule;
    }

    public void setMinCoroneBaule(int minCoroneBaule) {
        this.minCoroneBaule = minCoroneBaule;
    }

    public int getMaxDonazioni() {
        return maxDonazioni;
    }

    public void setMaxDonazioni(int maxDonazioni) {
        this.maxDonazioni = maxDonazioni;
    }

    public int getMinDonazioni() {
        return minDonazioni;
    }

    public void setMinDonazioni(int minDonazioni) {
        this.minDonazioni = minDonazioni;
    }

    public int getMaxTrofei() {
        return maxTrofei;
    }

    public void setMaxTrofei(int maxTrofei) {
        this.maxTrofei = maxTrofei;
    }

    public int getMinTrofei() {
        return minTrofei;
    }

    public void setMinTrofei(int minTrofei) {
        this.minTrofei = minTrofei;
    }
}
