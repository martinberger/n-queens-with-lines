class CustomException extends Exception {
  public CustomException(String errorMessage) {
    super(errorMessage);
  }
}

class ErrorLib {

  public static void handle(Exception caught) {
    try {
      throw caught;
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
