import java.util.ArrayList;

class Tester {

  private int numberOfTests = 0;
  private int failedTests = 0;

  private void reportTestFailure(String msg, int expected, int found) {
    failedTests++;
    System.out.println("Failed test! " + msg + " Expected: " + expected + ", found: " + found);
  }

  interface Predicate<T> {
    boolean check(T t);
  }

  public <T> ArrayList<T> filter(ArrayList<T> l, Predicate<T> remove) {
    var res = new ArrayList<T>();
    for (var t : l) {
      if (!remove.check(t)) {
        res.add(t);
      }
    }
    return res;
  }

  public void runUnitTests(int lo, int hi) {
    System.out.println("   Running unit tests.");
    // Currently empty, problem + available resources
    // suggest only to use integration testesr
  }

  public void runCountingTests(int lo, int hi) {
    System.out.println("   Running integration tests for N-Queens.");
    int[] oracle = {
      1, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596, 2279184, 1477251
    };
    for (int i = Math.max(lo, 0); i <= Math.min(hi, oracle.length - 1); i++) {
      numberOfTests++;
      var results = Algorithm.queens(i);
      if (results.size() != oracle[i]) {
        reportTestFailure("runCountingTests i = " + i, oracle[i], results.size());
      }
    }
  }

  public void runFilterTests(int lo, int hi) {
    System.out.println("   Running integration tests for N-Queens With Lines.");
    for (int i = Math.max(lo, 0); i <= hi; i++) {
      numberOfTests++;
      var results = Algorithm.queens(i);
      var resultsFiltered =
          filter(results, (ArrayList<Queen> solution) -> Algorithm.has3Line(solution));
      var resultsNo3Lines = Algorithm.queensNo3Lines(i);
      if (resultsFiltered.size() != resultsNo3Lines.size()) {
        reportTestFailure("runFilterTests i" + i, resultsFiltered.size(), resultsNo3Lines.size());
      }
    }
  }

  public void runAllTests(int lo, int hi) {
    System.out.println("\nRunning tests:\n");
    runUnitTests(lo, hi);
    runCountingTests(lo, hi);
    runFilterTests(lo, hi);
    var successfulTests = numberOfTests - failedTests;
    System.out.println(
        "\nSuccessfully ran " + successfulTests + " of " + numberOfTests + " tests.\n");
  }
}

class Tests {

  public static void main(String[] args) {
    new Tester().runAllTests(0, 12);
  }
}
