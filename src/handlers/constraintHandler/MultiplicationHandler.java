package handlers.constraintHandler;

import errors.InvalidOperationException;

import java.util.List;

public class MultiplicationHandler extends AbstractOperationHandler {

    public MultiplicationHandler(){
        OperationHandler next = new SubtractionHandler();
        this.setNext(next);
    }

    @Override
    public int handle(List<Integer> valori, String operazione) throws InvalidOperationException {
        if(valori.size() > 0){
            if(operazione.equals("x")){
                int totale = 1;
                for(int valore : valori)
                    totale*=valore;
                return totale;
            }
            return nextHandler.handle(valori, operazione);
        }
        return -1;
    }
}
