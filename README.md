# ThreadUfficioPostale
Un ufficio postale offre ai propri clienti due diversi tipi di servizi: (1) operazione veloce e (2) consulenza. A tal
fine, all’interno dell’ufficio sono disponibili N sportelli, di cui uno solo destinato alle operazioni veloci.
Le operazioni veloci vengono gestite attraverso una coda A di lunghezza infinita: dopo essere stati serviti i
clienti si recano verso il varco di uscita.
I clienti interessati alla consulenza attendono che vi sia uno sportello libero posizionandosi in una coda
distinta B, di lunghezza BMAX. Se tutti gli sportelli sono occupati e la coda B è piena, i clienti rinunciano alla
consulenza ed optano per una operazione veloce accodandosi in A.
Al termine della consulenza, i clienti si recano allo stesso varco di uscita di chi ha svolto un’operazione
veloce, ma rispetto a questi ultimi avranno priorità maggiore nell’uscire dall’ufficio postale.
Si modelli lo scenario descritto mediante thread in linguaggio Java usando il costrutto monitor.
Si descriva la sincronizzazione tra thread, discutendo anche se la soluzione proposta può presentare rinvio
indefinito o deadlock. In tal caso, si propongano delle soluzioni implementative per evitare i due fenomeni.
