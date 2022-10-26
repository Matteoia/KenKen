package handlers.constraintHandler;

import java.util.List;

public abstract class AbstractOperationHandler implements OperationHandler {
    protected OperationHandler nextHandler;
    @Override
    public abstract int handle(List<Integer> valori, String operazione);

    @Override
    public void setNext(OperationHandler handler) {
        this.nextHandler = handler;
    }
}
