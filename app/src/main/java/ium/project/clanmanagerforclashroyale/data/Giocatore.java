package ium.project.clanmanagerforclashroyale.data;

/**
 * Created by admin on 23/01/2018.
 */

public class Giocatore {
    //Attributi
    private String nome;
    private String tag;
    private String grado;
    private int corone;
    private int[] coppeBaule= new int [10];
    private int [] donazioni = new int [10];
    private boolean promozione;
    private boolean retrocessione;
    private boolean espulsione;
    public Giocatore (){
        nome="";
        tag= "";
        grado= "";
        corone=0;
        coppeBaule=null;
        donazioni=null;
        promozione=false;
        retrocessione=false;
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

    public int getCorone() {
        return corone;
    }

    public void setCorone(int corone) {
        this.corone = corone;
    }

    public int[] getCoppeBaule() {
        return coppeBaule;
    }

    public void setCoppeBaule(int[] coppeBaule) {
        this.coppeBaule = coppeBaule;
    }

    public int[] getDonazioni() {
        return donazioni;
    }

    public void setDonazioni(int[] donazioni) {
        this.donazioni = donazioni;
    }

    public boolean isPromozione() {
        return promozione;
    }

    public void setPromozione(boolean promozione) {
        this.promozione = promozione;
    }

    public boolean isRetrocessione() {
        return retrocessione;
    }

    public void setRetrocessione(boolean retrocessione) {
        this.retrocessione = retrocessione;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public boolean isEspulsione() {
        return espulsione;
    }

    public void setEspulsione(boolean espulsione) {
        this.espulsione = espulsione;
    }
    /***********************************************/
    //Metodi classe Giocatore

    public void Promozione(boolean flag){ //Flag viene settato true alla pressione del tasto prmuovi
        if(flag && !getGrado().equals("Capo") || !getGrado().equals("Co-capo")){
            if(getGrado().equals("Anziano")){
                setGrado("Co-capo");
            }else{
                setGrado("Anziano");
            }
        }
    }
    public void Retrocessione(boolean flag, int n){//Flag viene settato true alla pressione del tasto espelli
        if(flag && !getGrado().equals("Capo")){
            if(getGrado().equals("Co-capo")) setGrado("Anziano");
            else if(getGrado().equals("Anziano")) setGrado("Recluta");
            else {
                GiocatoriFactory.getInstance().RemovePlayer(this);
                Clan myClan = new Clan();
                myClan.setComponenti(GiocatoriFactory.getInstance().getAllPlayers());
            }
        }
    }

}
