class Rational {
  private int num;
  private int den;

  public Rational(int numerator, int denominator) {
    if (denominator == 0) {
      throw new RuntimeException("Denominator is zero");
    }
    int g = gcd(numerator, denominator);
    num = numerator / g;
    den = denominator / g;
  }

  private static int gcd(int m, int n) {
    if (0 == n) return m;
    else return gcd(n, m % n);
  }

  public boolean rationalEquals(Rational that) {
    return this.num == that.num && this.den == that.den;
  }
}
