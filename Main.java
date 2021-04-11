class Main {

  public static void main(String[] args) {
    try {
      var config = Config.handleCommandLine(args);
      Algorithm.run(config);
    } catch (Exception e) {
      ErrorLib.handle(e);
    }
  }
}
