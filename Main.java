class Main {

  public static void main(String[] args) {
    try {
      var config = Config.handleCommandLine(args);
      Algorithm.run(config);
    } catch (CustomException e) {
      System.out.println("");
      System.out.println(e.getMessage());
      System.out.println(Strings.help);
    } catch (Exception e) {
      System.out.println("Aborting, caught unexpected exception:\n");
      System.out.println(e.getMessage());
      System.out.println("");
    }
  }
}
