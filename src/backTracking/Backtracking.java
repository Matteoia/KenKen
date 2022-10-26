package backTracking;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Backtracking<P,S> {
	protected abstract boolean assignable( P p, S s );
	protected abstract void assign( P ps, S s );
	protected abstract void deassign( P ps );
	protected abstract void addSolution();
	protected abstract boolean existSolution( P p );//
	protected abstract boolean lastSolution( );
	protected abstract List<P> choicePoints();
	protected abstract Collection<S> choices( P p );
	protected abstract void resolve();

	protected P nextChoicePoint( List<P> ps, P p ) {
		if( existSolution(p) ) throw new NoSuchElementException();
		int i=ps.indexOf(p);
		return ps.get(i+1);
	}

	protected void test( List<P> ps, P p ) {
		Collection<S> sa = choices(p); //scelte ammissibili per p
		for (S s : sa) {
			if (lastSolution()) break;
			if (assignable(p, s)) {
				assign(p, s);
				if (existSolution(p))
					addSolution();
				else if(ps.indexOf(p) < ps.size()-1)
					test(ps, nextChoicePoint(ps, p));
				deassign(p);
			}
		}
	}
}
