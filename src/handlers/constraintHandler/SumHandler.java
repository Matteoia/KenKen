package handlers.constraintHandler;
import errors.InvalidOperationException;

import java.util.List;

public class SumHandler extends AbstractOperationHandler {

    public SumHandler(){
        OperationHandler next = new MultiplicationHandler();
        this.setNext(next);
    }
    @Override
    public int handle(List<Integer> valori, String operazione) throws InvalidOperationException {
        if(valori.size() > 0){
            if(operazione.equals("+")){
                int totale = 0;
                for(int valore : valori)
                    totale+=valore;
                return totale;
            }
            return nextHandler.handle(valori, operazione);
        }
        return -1;
    }
}
