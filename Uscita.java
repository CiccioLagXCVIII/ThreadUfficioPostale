public class Uscita extends Thread{
    public UfficioPostale ufficioPostale;

    public Uscita(UfficioPostale ufficio){
        this.ufficioPostale = ufficio;
    }

    public void run(){
        while(true){
            Cliente cliente = ufficioPostale.faiUscire();
            String tipoOperazione = ((cliente.tipoOperazione == 0)? "Veloce" : "Consulenza");
            System.out.println("Il Cliente " + cliente.idCliente + "Ha Svolto Una " + tipoOperazione + " Ed É Uscito!");
        }
    }
}