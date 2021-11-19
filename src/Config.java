class Config {
  public boolean visualise = false;
  public int n = -1;

  static void checkConsistency(Config config) throws CustomException {
    if (config.n < 0) {
      throw new CustomException("Problem: Need a number n >= 0 to run program.");
    }
  }

  private static Config parse(String[] args) throws CustomException {
    var config = new Config();
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-visualise")) {
        config.visualise = true;
      } else if ((i < args.length - 1) && (args[i].equals("-n"))) {
        i++;
        try {
          config.n = Integer.parseInt(args[i]);
        } catch (Exception e) {
          throw new CustomException("Problem: Could not convert numeric parameter");
        }
      } else {
        throw new CustomException("Problem: unknown command line format.");
      }
    }
    return config;
  }

  public static Config handleCommandLine(String[] args) throws CustomException {
    var config = parse(args);
    checkConsistency(config);
    return config;
  }
}
