import java.util.ArrayList;

interface IsSafe {
    Boolean check (Queen q, ArrayList<Queen> solution);
}


class Algorithm {

    public static <T>  ArrayList<T> shallowClone( ArrayList<T> l ) {
	var clone = new ArrayList<T>();
	for ( var t : l ) { clone.add ( t ); }
	return clone;
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
		     l.add (newQueen); // always returns true
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

    public static Boolean is3Line (  Queen q1,  Queen q2,  Queen q3 ) {
	if ( q1.x == q2.x || q1.x == q3.x || q2.x == q3.x ) return false;
	var slope12 = new Rational ( q2.y - q1.y, Math.abs( q2.x - q1.x ) );
	var slope13 = new Rational ( q3.y - q1.y, Math.abs( q3.x - q1.x ) );
	var slope23 = new Rational ( q3.y - q2.y, Math.abs( q3.x - q2.x ) );
	return slope12.rationalEquals( slope13 ) && slope12.rationalEquals( slope23 );
    }

    public static Boolean has3Line ( ArrayList<Queen> solution ) {
	for ( Queen q1 : solution ) {
	    for ( Queen q2 : solution ) {
		for ( Queen q3 : solution ) {
		    if ( is3Line ( q1, q2, q3 ) ) return true;
		}
	    }
	}
	return false;
    }

    public static Boolean adds3Line ( Queen newQueen, ArrayList<Queen> solution ) {
	for ( Queen q1 : solution ) {
            for ( Queen q2 : solution ) {
		if ( is3Line ( newQueen, q1, q2 ) ) {
		    return true; } } }
	return false;
    }
    
    public static Boolean isSafeNo3Lines ( Queen newQueen, ArrayList<Queen> solution ) {    
	return isSafeChess ( newQueen, solution ) && !adds3Line ( newQueen, solution );
    }
    
    public static ArrayList<ArrayList<Queen>> queens ( int n ) {
	return genericQueens ( (Queen newQueen, ArrayList<Queen> solution) -> isSafeChess (newQueen, solution), n );
    }

    public static ArrayList<ArrayList<Queen>> queensNo3Lines ( int n ) {
	return genericQueens ( (Queen newQueen, ArrayList<Queen> solution) -> isSafeNo3Lines (newQueen, solution), n );
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
    
    public static void run ( Config config ) {
	var results = queensNo3Lines ( config.n );
	if ( config.visualise ) {
	    present( config.n, results );
	}
	System.out.println( "Found " + results.size () + " solutions." );
    }
    
}

