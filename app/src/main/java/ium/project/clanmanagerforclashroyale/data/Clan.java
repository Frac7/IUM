package ium.project.clanmanagerforclashroyale.data;

import android.media.Image;

import java.util.List;

/**
 * Created by admin on 23/01/2018.
 */

public class Clan {
    // Attributi
    private String nome;
    private String tag;
    private String descrizione;
    private int[] donazioniTotali = new int [10]; // Contiene il totale donazioni per settimana
    private int nMembri;
    private int coppeClan;
    private List<Giocatore> componenti ;
    private Image logo;
    private int[] bauleClan= new int [10]; // Contiene il totale di coppe raggiunte ogni settimana per il baule clan
    public Clan (){
        nome="Royale Family";
        tag="#KAS894HX";
        descrizione="Clan serio, regole: 1- minimo donazioni settimanali 200; 2 minimo coppe baule clan 30; buon game a tutti";
        setnMembri(GiocatoriFactory.getInstance().countPlayers());
        setCoppeClan(GiocatoriFactory.getInstance().countTrophies());
        setComponenti(GiocatoriFactory.getInstance().getAllPlayers());
        setDonazioniTotali(getComponenti());
        setBauleClan(getComponenti());
        logo=null;


    }
/*                     getter & setter           */
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public int[] getBauleClan() {
        return bauleClan;
    }

    public void setBauleClan(List<Giocatore> giocatori) {
        for (Giocatore g : giocatori  ) {
            for(int i=0; i<10; i++){
                bauleClan[i]+= g.getCoppeBaule()[i];
            }
        }
    }

    public List<Giocatore> getComponenti() {
        return componenti;
    }

    public void setComponenti(List<Giocatore> componenti) {
        this.componenti = componenti;
    }
    /***********************************************/

}
