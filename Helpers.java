//import java.lang.*;
//import java.util.List;
//import java.util.Arrays;
import java.util.ArrayList;
//import java.util.ArrayList.*;

class Helpers {

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

}
 
