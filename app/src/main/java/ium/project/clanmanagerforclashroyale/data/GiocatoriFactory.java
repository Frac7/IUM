package ium.project.clanmanagerforclashroyale.data;

/**
 * Created by Administrator on 26/01/2018.
 */
import  java.util.ArrayList;
import java.util.List;
public class GiocatoriFactory {

    private static GiocatoriFactory singleton;

    public static GiocatoriFactory getInstance() {
        if(singleton==null)
            singleton= new GiocatoriFactory();
        return singleton;
    }
    private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
    //costruttore
    private GiocatoriFactory(){
        Giocatore giocatore1 = new Giocatore();
        giocatore1.setNome("✌PeaceMan✌");
        giocatore1.setTag("#JV8H923K");
        giocatore1.setGrado("Capo");
        giocatore1.setCorone(4600);
        giocatore1.setCoppeBaule(new int[]{20, 30, 31, 0, 15, 50, 170, 10, 15, 12});
        giocatore1.setDonazioni(new int[]{1200,200,300,70,40,0,30,50,78,90});
        giocatore1.setPromozione(false);
        giocatore1.setRetrocessione(false);
        giocatore1.setEspulsione(false);

        Giocatore giocatore2 = new Giocatore();
        giocatore2.setNome("Frac");
        giocatore2.setTag("#KV86923L");
        giocatore2.setGrado("Co-capo");
        giocatore2.setCorone(4200);
        giocatore2.setCoppeBaule(new int[]{200, 35, 37, 40, 0, 51, 70, 15, 10, 11});
        giocatore2.setDonazioni(new int[]{200,0,30,70,50,55,1300,10,20,70});
        giocatore2.setPromozione(false);
        giocatore2.setRetrocessione(false);
        giocatore2.setEspulsione(false);

        Giocatore giocatore3 = new Giocatore();
        giocatore3.setNome("Avanguards");
        giocatore3.setTag("#SV87453L");
        giocatore3.setGrado("Co-capo");
        giocatore3.setCorone(3500);
        giocatore3.setCoppeBaule(new int[]{0, 43, 27, 50, 0, 90, 170, 5, 100, 200});
        giocatore3.setDonazioni(new int[]{0,0,500,700,55,90,120,12,200,750});
        giocatore3.setPromozione(false);
        giocatore3.setRetrocessione(false);
        giocatore3.setEspulsione(false);

        Giocatore giocatore4 = new Giocatore();
        giocatore4.setNome("JonSnow");
        giocatore4.setTag("#LV27454C");
        giocatore4.setGrado("Recluta");
        giocatore4.setCorone(3203);
        giocatore4.setCoppeBaule(new int[]{20, 15, 7, 0, 0, 90, 0, 12, 11, 2});
        giocatore4.setDonazioni(new int[]{380,134,88,0,0,800,130,15,190,50});
        giocatore4.setPromozione(false);
        giocatore4.setRetrocessione(false);
        giocatore4.setEspulsione(false);

        Giocatore giocatore5 = new Giocatore();
        giocatore5.setNome("Ste");
        giocatore5.setTag("#GV25404S");
        giocatore5.setGrado("Recluta");
        giocatore5.setCorone(3033);
        giocatore5.setCoppeBaule(new int[]{200, 150, 77, 10, 15, 20, 200, 52, 110, 20});
        giocatore5.setDonazioni(new int[]{200,140,770,120,450,800,750,155,130,51});
        giocatore5.setPromozione(false);
        giocatore5.setRetrocessione(false);
        giocatore5.setEspulsione(false);

        Giocatore giocatore6 = new Giocatore();
        giocatore6.setNome("Smigol");
        giocatore6.setTag("#XV2k405T");
        giocatore6.setGrado("Anziano");
        giocatore6.setCorone(2940);
        giocatore6.setCoppeBaule(new int[]{0, 111, 112, 0, 70, 45, 21, 55, 19, 200});
        giocatore6.setDonazioni(new int[]{0,1400,70,20,0,800,55,185,1300,510});
        giocatore6.setPromozione(false);
        giocatore6.setRetrocessione(false);
        giocatore6.setEspulsione(false);

        Giocatore giocatore7 = new Giocatore();
        giocatore7.setNome("Sfinz");
        giocatore7.setTag("#BG2k405L");
        giocatore7.setGrado("Co-capo");
        giocatore7.setCorone(2870);
        giocatore7.setCoppeBaule(new int[]{100, 17, 12, 50, 7, 5, 71, 95, 190, 80});
        giocatore7.setDonazioni(new int[]{1400,1600,77,68,1255,871,78,540,10,800});
        giocatore7.setPromozione(false);
        giocatore7.setRetrocessione(false);
        giocatore7.setEspulsione(false);

        Giocatore giocatore8 = new Giocatore();
        giocatore8.setNome("RolyPolyHoly");
        giocatore8.setTag("#QG3GY05K");
        giocatore8.setGrado("Anziano");
        giocatore8.setCorone(2680);
        giocatore8.setCoppeBaule(new int[]{0, 170, 120, 0, 64, 57, 120, 5, 9, 10});
        giocatore8.setDonazioni(new int[]{0,1560,430,0,65,751,368,1234,400,70});
        giocatore8.setPromozione(false);
        giocatore8.setRetrocessione(false);
        giocatore8.setEspulsione(false);

        Giocatore giocatore9 = new Giocatore();
        giocatore9.setNome("MStery");
        giocatore9.setTag("#WGFG705O");
        giocatore9.setGrado("Recluta");
        giocatore9.setCorone(2560);
        giocatore9.setCoppeBaule(new int[]{200, 150, 77, 10, 15, 20, 200, 52, 110, 20});
        giocatore9.setDonazioni(new int[]{200,140,770,120,450,800,750,155,130,51});
        giocatore9.setPromozione(false);
        giocatore9.setRetrocessione(false);
        giocatore9.setEspulsione(false);

        Giocatore giocatore10 = new Giocatore();
        giocatore10.setNome("TrissMerigold");
        giocatore10.setTag("#HV97B54F");
        giocatore10.setGrado("Co-capo");
        giocatore10.setCorone(2203);
        giocatore10.setCoppeBaule(new int[]{20, 15, 7, 0, 0, 90, 0, 12, 11, 2});
        giocatore10.setDonazioni(new int[]{380,134,88,0,0,800,130,15,190,50});
        giocatore10.setPromozione(false);
        giocatore10.setRetrocessione(false);
        giocatore10.setEspulsione(false);

        giocatori.add(giocatore1);
        giocatori.add(giocatore2);
        giocatori.add(giocatore3);
        giocatori.add(giocatore4);
        giocatori.add(giocatore5);
        giocatori.add(giocatore6);
        giocatori.add(giocatore7);
        giocatori.add(giocatore8);
        giocatori.add(giocatore9);
        giocatori.add(giocatore10);

    }

    public List<Giocatore> getAllPlayers(){
        //return giocatori;
        List<Giocatore> l = new ArrayList<>();
        for(Giocatore g : giocatori)
            l.add(g);
        return l;
    }

    public int countPlayers(){
        int counter=0;
        for (Giocatore g : this.giocatori) {
            counter++;
        }
        return counter;
    }

    public int countTrophies(){
        int counter=0;
        for (Giocatore g : this.giocatori) {
            //for(int i=0; i<10; i++){
            counter += g.getCorone();
            //}
        }
        return counter/2;
    }

    public void RemovePlayer(Giocatore player){
        giocatori.remove(player);
    }
}
