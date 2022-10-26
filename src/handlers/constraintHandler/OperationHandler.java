package handlers.constraintHandler;


import errors.InvalidOperationException;

import java.util.List;

public interface OperationHandler {
    int handle(List<Integer> valori, String operazione) throws InvalidOperationException;
    void setNext(OperationHandler handler);
}
