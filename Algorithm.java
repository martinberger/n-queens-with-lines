import java.lang.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayList.*;

class Algorithm {

    public static <T>  ArrayList<T> shallowClone( ArrayList<T> l ) {
	var clone = new ArrayList<T>();
	for ( var t : l ) { clone.add ( t ); }
	return clone;
    }
    

    public static void present ( int n, ArrayList<ArrayList<Queen>>  solutions ) {
	for ( var solution : solutions ) {
	    output ( n, solution );
	}
    }

    public static void output ( int n, ArrayList<Queen>  solution ) {
	System.out.println();
	for ( var queen : solution ) {
	    var row = "_|".repeat( queen.y ) + "Q" + "|_".repeat(n - queen.y-1);
	    System.out.println( row );
	}
    
    }
    
    interface IsSafe {
	Boolean check (Queen q, ArrayList<Queen> solution);
    }

    public static ArrayList<ArrayList<Queen>> place ( IsSafe  isSafe, int n, int row ) {
	if ( row < 0 ) {
	    var results = new ArrayList<ArrayList<Queen>>();		    
	    var empty = new ArrayList<Queen>();
	    results.add(empty);			    
	    return results;
	}
	var results = new ArrayList<ArrayList<Queen>>();		
	for ( ArrayList<Queen> queens : place ( isSafe, n, row-1 ) ) {
	    for ( int col = 0; col < n; col++ ) {
		var l = shallowClone ( queens );
		var newQueen = new Queen ( row, col );
		if ( isSafe.check ( newQueen, l ) )  {
		     l.add (0, newQueen); // always returns true
		     results.add (l ); // always returns true
		}
	    }
	}
	return results;
    }
    
    public static ArrayList<ArrayList<Queen>> genericQueens ( IsSafe isSafe, int n ) {
	return place ( isSafe, n, n-1 );
    }

    public static Boolean isSafeChess ( Queen newQueen, ArrayList<Queen> solution ) {
	for ( Queen queen : solution ) {
	    if ( newQueen.x == queen.x ||
		 newQueen.y == queen.y ||
		 Math.abs( newQueen.x - queen.x ) == Math.abs ( newQueen.y - queen.y ) ) {
		return false; } }
	return true;
    }

    

    public static ArrayList<ArrayList<Queen>> queens ( int n ) {
	return genericQueens ( (Queen newQueen, ArrayList<Queen> solution) -> isSafeChess (newQueen, solution), n );
    }

}

