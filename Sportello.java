public class Sportello extends Thread{
    public int idSportello;
    public UfficioPostale ufficioPostale;
    public int durataOperazione; //Durata Operazioni
    
    //Costruttore
    public Sportello(int id, UfficioPostale ufficio){
        this.idSportello = id;
        this.ufficioPostale = ufficio;
    }

    //Metodo Run
    public void run(){
        int counterOperazioni = 0;
        System.out.println("Sportello #" +idSportello + " (CounterOperazioni: " +counterOperazioni +")" );
        System.out.println("Sportello #" +idSportello + " Può Servire!!" );

        if(idSportello == 0){ //Se idSportello == 0 Lo Sportello Esegue Una Veloce
            durataOperazione = 2000;
            ufficioPostale.serviVeloce(this);
        } else if(idSportello != 0){
            durataOperazione = 5000;
            ufficioPostale.serviConsulenza(this);  
        }
        counterOperazioni++;
        try {
            Thread.sleep(durataOperazione); //A Seconda Dell'Operazione Dura Di Piu O Di Meno
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
