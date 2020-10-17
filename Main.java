import java.util.ArrayList;

class Config {
    public boolean visualise = false;
    public int n = -1;    
}



class Main {

    static void checkConsistency ( Config config ) {
	if ( config.n < 0 ) {
	    throw new RuntimeException ( "Need a number n to run program." );
	}
    }
    
    static Config parse ( String[] args ) {
	var config = new Config();
	for ( int i = 0; i < args.length; ) {
	    if ( args [ i ] == "-visualise" ) { config.visualise = true; i++; continue; }
	    if ( i < args.length-1 && args [ i ] == "-n" ) {
		    i++;
		    try {
			config.n = Integer.parseInt( args [ i ] );
			i++;
		    }
		    catch ( Exception e ) {
			throw new RuntimeException ( "Could not convert numeric parameter" );
		    }
	    }
	}
	checkConsistency ( config );
	return config;
    }
    
    interface Predicate<T> {
	Boolean check (T t);
    }
    
    public static <T> ArrayList<T> filter ( ArrayList<T> l, Predicate<T> pred ) {
	var res = new ArrayList<T>();
	for ( var t : l ) {
	    if ( !pred.check ( t ) ) {
		res.add ( t );
	    }
	}
	return res;
    }
    
    public static void main(String[] args) {
	var low = Integer.parseInt( args [ 0 ] );
	var high = Integer.parseInt( args [ 1 ] );
	System.out.println("args[ 0 ] = " + low);
	System.out.println("args[ 1 ] = " + high);
	System.out.println("From: http://oeis.org/A000170");
	System.out.println("Expected: 0 -> 1, 1 -> 1, 2 -> 0, 3 -> 0, 4 -> 2, 5 -> 10, 6 -> 4" );
        System.out.println("          7 -> 40, 8 -> 92, 9 -> 352, 10 -> 724, 11 -> 2680, 12 -> 14200, 13 -> 73712,");

	for ( int n = low; n <= high; n++ ){
	    System.out.println ( "---------------------- n = " + n + " ----------------------");
	    var results = Algorithm.queens ( n );	
	    var resultsFiltered = filter ( results, ( ArrayList<Queen> solution ) -> Algorithm.has3Line ( solution ) );
	    var resultsNo3Lines = Algorithm.queensNo3Lines ( n );
	    //	    Helpers.present ( n, resultsNo3Lines );
	    //	    System.out.println ( "      ***********" );
	    //	    Helpers.present ( n, resultsFiltered );	    
	    System.out.println( "queensNo3Lines( " + n + " ) has " + resultsNo3Lines.size( ) + " solutions");
	    System.out.println( "FILTEREDqueens( " + n + " ) has " + resultsFiltered.size( ) + " solutions");	    
	}
    }

}
