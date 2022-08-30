public class Cliente extends Thread{

    public int idCliente;
    public int tipoOperazione;
    //tipoOperazione Rules:
    //  0   Operazione Veloce
    //  1   Consulenza
    private UfficioPostale ufficioPostale;

    //Costruttore
    public Cliente(int id, int tipo, UfficioPostale ufficio){
        this.idCliente = id;
        this.tipoOperazione = tipo;
        this.ufficioPostale = ufficio;
        
    }

    public String operazioneToString(){
        String operazione = "";
        if(tipoOperazione == 0){
            operazione = "Veloce";
        } else if (tipoOperazione == 1){
            operazione = "Consulenza";
        }
        return operazione;
    }

    //Metodo Run
    public void run(){
        try {
            //System.out.println("Il Cliente " +idCliente + " Deve Svolgere Operazione: " + operazioneToString());

            if(tipoOperazione == 0){
                ufficioPostale.accodaVeloce(this);
            } else if (tipoOperazione == 1){
                if(!ufficioPostale.accodaConsulenza(this)){ 
                    //Se accodaConsulenza == false, Vuol Dire Che codaConsulenze É Piena Quindi
                    //Non Si Possono Aggiungere Più Clienti E Quindi Entra Nell'If Ed Effetta Il
                    //Cambio Operazione.
                    //Se La codaConsulenze NON É Piena Questa Parte Viene Saltata E Il Monitor
                    //Aggiunge Il Cliente In Coda Per Le Consulenze (UfficioPostale Riga: 23)
                    tipoOperazione = 0;
                    System.out.println("Coda Consulenze Piena. Il Cliente " +idCliente + " Si Mette In Coda Per Le Veloci");
                    ufficioPostale.accodaVeloce(this);
                }

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}