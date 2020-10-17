class CustomException extends Exception { 
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}

class Config {
    public boolean visualise = false;
    public int n = -1;    


    static void checkConsistency ( Config config ) throws CustomException {
	if ( config.n < 0 ) {
	    throw new CustomException ( "Need a number n to run program." );
	}
    }
    
    static Config parse ( String[] args ) throws CustomException {
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
			throw new CustomException ( "Could not convert numeric parameter" );
		    }
	    }
	}
	return config;
    }

    static Config handleCommandLine ( String[] args ) throws CustomException {
	var config = parse ( args );
	checkConsistency ( config );
	return config;
    }
    

}


