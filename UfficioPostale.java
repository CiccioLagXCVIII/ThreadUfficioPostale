import java.util.LinkedList;

public class UfficioPostale {
    private LinkedList<Cliente> codaVeloce = new LinkedList<Cliente>();
    private LinkedList<Cliente> codaConsulenze = new LinkedList<Cliente>();
    private LinkedList<Cliente> uscitaVeloce = new LinkedList<Cliente>(); 
    private LinkedList<Cliente> uscitaConsulenze = new LinkedList<Cliente>();
    private int maxConsulenze = 10;
    private int counterUsciti;
    private Cliente clienteC; //Oggetto Temporaneo Per Cliente Consulenze Che Esce 
    private Cliente clienteV; //Oggetto Temporaneo Per Cliente Veloce Che Esce

    //Metodo Che Aggiunge Un Cliente In Coda Per Un' Operazione Veloce
    public synchronized void accodaVeloce(Cliente cliente){
        codaVeloce.add(cliente);
        System.out.println("Il Cliente " + cliente.idCliente + " É In Coda Per Un'Operazione Veloce");
        notifyAll();
    }
    
    //Metodo Che Aggiunge Un Cliente In Coda Per Una Consulenza
    public synchronized boolean accodaConsulenza(Cliente cliente){
        if(codaConsulenze.size() == maxConsulenze){
            System.out.println("Il Cliente " + cliente.idCliente + " Non Può Svolgere Una Consulenza. Causa: COSA PIENA");
            return false;
        } else {
            codaConsulenze.add(cliente);
            System.out.println("Il Cliente " + cliente.idCliente + " É In Coda Per Una Consulenza");
            notifyAll();
            return true;
        }
    }
    
    //Metodo Che Permette Allo Sportello Di Effettuare Una Veloce
    public synchronized void serviVeloce(Sportello sportello){
        while(codaVeloce.size() == 0){
            System.out.println("Sportello #" +sportello.idSportello + " Attende Clienti Per Operazione Veloce...");
            try{
                wait();
            } catch (Exception e){
                //TODO: Handle Exception
            }
        }
        Cliente cliente = codaVeloce.removeFirst();
        System.out.println("Il Cliente " + cliente.idCliente + " Ha Terminato E Si Dirige All'Uscita!");
        uscitaVeloce.add(cliente);
        notifyAll();

    }

    //Metodo Che Permette Allo Sportello Di Effettuare Una Consulenzw
    public synchronized void serviConsulenza(Sportello sportello){
        while(codaVeloce.size() == 0){
            System.out.println("Sportello #" +sportello.idSportello + " Attende Clienti Per Consulenza...");
            try{
                wait();
            } catch (Exception e){
                //TODO: Handle Exception
            }
        }
        Cliente cliente = codaConsulenze.removeFirst();
        System.out.println("Il Cliente " + cliente.idCliente + " Ha Terminato E Si Dirige All'Uscita!");
        uscitaConsulenze.add(cliente);
        notifyAll();
    }

    //Metodo Che Gestisce La Priorità In Uscita
    public synchronized Cliente faiUscire(){
        while(uscitaVeloce.size() == 0 && uscitaConsulenze.size() == 0){
            System.out.println("Nessun Cliente Vuole Uscire Attendo...");
            try{
                wait();
            } catch (Exception e){
                //TODO: Handle Exception
            }
        }

        while(!uscitaVeloce.isEmpty() || !uscitaConsulenze.isEmpty()){
            //Se uscitaVeloce Non É Vuota Per Far Uscire Un Cliente Veloce 
            //La uscitaConsulenze Deve Essere Vuota Oppure 
            //Sono Usciti X Clienti (In Questo Caso 5)
            if(uscitaVeloce.size() > 0){
                if(uscitaConsulenze.size() == 0 || (counterUsciti % 5) == 0){
                    counterUsciti++;
                    return uscitaVeloce.removeFirst();
                    //System.out.println("Il Cliente " + clienteV.idCliente + "Ha Svolto Una Veloce Ed É Uscito!");
                }
            }

            //Uscita Consulenze >> Uscita Veloci
            //Se uscitaConsulenze Non É Vuota Si Fa Uscire Un Cliente Consulenza
            if(uscitaConsulenze.size() > 0){
                counterUsciti++;
                return uscitaConsulenze.removeFirst();
                //System.out.println("Il Cliente " + clienteC.idCliente + "Ha Svolto Una Consulenza É Uscito!");
            }
        }

        return null;
    }
}