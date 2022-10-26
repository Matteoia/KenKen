package handlers.constraintHandler;

import errors.InvalidOperationException;

import java.util.Collections;
import java.util.List;

public class SubtractionHandler extends AbstractOperationHandler {

    public SubtractionHandler(){
        OperationHandler next = new DivisionHandler();
        this.setNext(next);
    }
    @Override
    public int handle(List<Integer> valori, String operazione) throws InvalidOperationException {
        if(valori.size() > 0){
            if(operazione.equals("-")){
                valori.sort(Collections.reverseOrder());
                int totale = valori.get(0);
                for(int i=1; i<valori.size(); i++)
                    totale-=valori.get(i);
                return Math.abs(totale);
            }
            return nextHandler.handle(valori, operazione);
        }
        return -1;
    }
}
