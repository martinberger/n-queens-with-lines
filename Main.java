import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
	try {
	    var config = Config.handleCommandLine ( args );
	    //	    run( config );
	}
	catch ( CustomException e ) {
	    System.out.println ( e.getMessage() );	    
	    System.out.println ( Strings.help );
	    System.out.println ( "" );	    
	}
	catch ( Exception e ) {
	    System.out.println ( "Aboring, caught unexpected exception:\n" );
	    System.out.println ( e.getMessage() );
	    System.out.println ( "" );
	}
	
    }

}
