package ium.project.clanmanagerforclashroyale.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 23/01/2018.
 */

public class ClanManager {
    //Attributi
    private Clan clan = new Clan();
    private Filtro filtro;
    private Suggeritore suggeritore;
    private int nSettimana;

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Filtro getFiltro() {
        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public Suggeritore getSuggeritore() {
        return suggeritore;
    }

    public void setSuggeritore(Suggeritore suggeritore) {
        this.suggeritore = suggeritore;
    }

    public int getnSettimana() {
        return nSettimana;
    }

    public void setnSettimana(int nSettimana) {
        this.nSettimana = nSettimana;
    }

    public List<Giocatore> ApplyFilters(){

        if(getFiltro() == null)
            return GiocatoriFactory.getInstance().getAllPlayers();

         ArrayList<Giocatore> giocatoriFiltrati= new ArrayList<Giocatore>();

         //List<Giocatore> giocatore = clan.getComponenti();
        List<Giocatore> giocatore = GiocatoriFactory.getInstance().getAllPlayers();

        for (Giocatore g: giocatore) {

            if(
                    g.getCorone() >= getFiltro().getMinTrofei() && g.getCorone() <= getFiltro().getMaxTrofei() &&
                    g.getDonazioni()[nSettimana] >= getFiltro().getMinDonazioni() && g.getDonazioni()[nSettimana] <= getFiltro().getMaxDonazioni() &&
                    g.getCoppeBaule()[nSettimana] >= getFiltro().getMinCoroneBaule() && g.getCoppeBaule()[nSettimana] <= getFiltro().getMaxCoroneBaule()

                    )
                giocatoriFiltrati.add(g);
        }
        return  giocatoriFiltrati;
    }

    public void applyHint(){
        for (Giocatore g : getClan().getComponenti()) {
            if(g.getDonazioni()[nSettimana]< getSuggeritore().getDonazioni() && g.getCoppeBaule()[nSettimana] < getSuggeritore().getCorone()) {
                if (g.getGrado().equals("Co-capo") || g.getGrado().equals("Anziano"))
                    g.setRetrocessione(true);
                else if (g.getGrado().equals("Recluta")) g.setEspulsione(true);
            }
            else if(g.getDonazioni()[nSettimana]>getSuggeritore().getDonazioni()+100 && g.getCoppeBaule()[nSettimana] > getSuggeritore().getCorone()+15){
                if(g.getGrado().equals("Recluta") || g.getGrado().equals("Anziano"))g.setPromozione(true);
            }

        }
    }


}
