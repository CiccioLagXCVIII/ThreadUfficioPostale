public class Main{
    public static void main(String[] args){
        int numeroSportelli = 4;
        int numeroClienti = 15;

        UfficioPostale ufficioPostale = new UfficioPostale();
        Sportello sportelli[] = new Sportello[numeroSportelli];
        Cliente clienti[] = new Cliente[numeroClienti];
        Uscita uscita = new Uscita(ufficioPostale);

        uscita.start();
        for(int i=0; i<numeroSportelli; i++){
            sportelli[i] = new Sportello(i, ufficioPostale);
            sportelli[i].start();
            //System.out.println("Sportello #" +sportelli[i].idSportello + " " +((sportelli[i].isVeloce == true)? "Veloce" : "Consulenza"));
        }

        for(int j=0; j<numeroClienti; j++){
            int tipoCliente = (int)((Math.random()*301)%2);
            //System.out.println("Cliente #" +j + " Tipo:" +tipoCliente);
            clienti[j] = new Cliente(j, tipoCliente, ufficioPostale);
            clienti[j].start();

            try{
                Thread.sleep( (int)(Math.random()*3001) );
            } catch (Exception e){
                //TODO: Handle Exception
            }
        }
    }
}