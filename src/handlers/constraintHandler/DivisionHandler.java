package handlers.constraintHandler;

import errors.InvalidOperationException;

import java.util.Collections;
import java.util.List;

public class DivisionHandler extends AbstractOperationHandler {

    @Override
    public int handle(List<Integer> valori, String operazione) {
        if(valori.size() > 0){
            if(operazione.equals("/")){
                valori.sort(Collections.reverseOrder());
                int totale = valori.get(0);
                for(int i=1; i<valori.size(); i++)
                    totale/=valori.get(i);
                return Math.abs(totale);
            }
            throw new InvalidOperationException();
        }
        return -1;
    }
}
