package ium.project.clanmanagerforclashroyale.data;

import java.util.List;

import ium.project.clanmanagerforclashroyale.R;

/**
 * Created by admin on 23/01/2018.
 */

public class Clan {
    // Attributi
   private String nome;
   private String tag;
   private String descrizione;
   private int[] donazioniTotali = new int [10]; /*Contiene il totale donazioni per settimana*/
   private int nMembri;
   private int coppeClan;
   private List<Giocatore> componenti ;
   private int logo;
   private int[] bauleClan= new int [10]; // Contiene il totale di coppe raggiunte ogni settimana per il baule clan
   private  String tipo;
   private int trofeiRichiesti;
    static private String posizione;
    //Costruttore
    public Clan (){
        nome="Royale Family";
        tag="#KAS894HX";
        descrizione="Clan serio, regole: 1) minimo donazioni settimanali: 200; 2) minimo corone baule clan: 30; buon game a tutti";
        setnMembri(GiocatoriFactory.getInstance().countPlayers());
        setCoppeClan(GiocatoriFactory.getInstance().countTrophies());
        setComponenti(GiocatoriFactory.getInstance().getAllPlayers());
        //anche in caso di eliminazione di un componente, lasciamo gli stessi punteggi per baule/donazioni/trofei del clan
        //vedere come viene gestito da clash royale
        setDonazioniTotali(getComponenti());
        setBauleClan(getComponenti());
        setLogo(R.drawable.progetto_ium);
        setTipo("Chiuso");
        setTrofeiRichiesti(2400);
        setPosizione("Italia");
    }

    /*                     getter & setter              */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int[] getDonazioniTotali() {
        return donazioniTotali;
    }

    public void setDonazioniTotali(List<Giocatore> giocatori) {
        for (Giocatore g : giocatori  ) {
            for(int i=0; i<10; i++){
                donazioniTotali[i]+= g.getDonazioni()[i];
            }
        }
    }

    public int getnMembri() {
        nMembri = getComponenti().size();
        return nMembri;
    }

    public void setnMembri(int nMembri) {
        this.nMembri = nMembri;
    }

    public int getCoppeClan() {
        return coppeClan;
    }

    public void setCoppeClan(int coppeClan) {
        this.coppeClan = coppeClan;
    }

    public List<Giocatore> getComponenti() {
        return componenti;
    }

    public void setComponenti(List<Giocatore> componenti) {
        this.componenti = componenti;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int[] getBauleClan() {
        return bauleClan;
    }

    public int getBauleIsettimana(int settimana){
        return bauleClan[settimana];
    }

    public void setBauleClan(List<Giocatore> giocatori) {
        for (Giocatore g : giocatori  ) {
            for(int i=0; i<10; i++){
                bauleClan[i]+= g.getCoppeBaule()[i];
            }
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTrofeiRichiesti() {
        return trofeiRichiesti;
    }

    public void setTrofeiRichiesti(int trofeiRichiesti) {
        this.trofeiRichiesti = trofeiRichiesti;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }
    /********************************************************************************/
    //Metodi della classe Clan
    public int NBauleClan (int corone){
        if(corone < 70)
            return 0;
        else if (corone < 160)
            return 1;
        else if(corone < 270)
            return 2;
        else if (corone < 400)
            return 3;
        else if(corone < 550)
            return 4;
        else if(corone < 720)
            return 5;
        else if(corone < 910)
            return 6;
        else if(corone <1120)
            return 7;
        else if(corone < 1350)
            return 8;
        else if(corone < 1600)
            return 9;
        else
            return 10;
    }
}
