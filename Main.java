class Main {

    public static void main(String[] args) {
	var low = Integer.parseInt( args [ 0 ] );
	var high = Integer.parseInt( args [ 1 ] );
	System.out.println("args[ 0 ] = " + low);
	System.out.println("args[ 1 ] = " + high);
	System.out.println("From: http://oeis.org/A000170");
	System.out.println("Expected: 0 -> 1, 1 -> 1, 2 -> 0, 3 -> 0, 4 -> 2, 5 -> 10, 6 -> 4" );
        System.out.println("          7 -> 40, 8 -> 92, 9 -> 352, 10 -> 724, 11 -> 2680, 12 -> 14200, 13 -> 73712,");

	for ( int n = low; n <= high; n++ ){
	    var results = Algorithm.queens ( n );
	    //	    NQueens.present ( n, results );
	    System.out.println( "queens( " + n + " ) has " + results.size( ) + " solutions");
	}
    }

}
