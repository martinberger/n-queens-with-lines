**Introduction.** This software implements a variation of the famous
N-Queens problem [A] which asks to compute all NxN chess boards with N
queens, placed so that no two queens threaten each other; thus, a
solution requires that no two queens share the same row, column, or
diagonal. The variation under consideration here is that *in addition*
it is required of valid boards that:

     no 3 or more queens be in a straight line.

Here is an example1 8x8 board with a line of length 4. The slope of the
line 2.

       _|_|_|_|_|_|_|_|
       _|_|_|Q|_|_|_|_|
       _|_|_|_|_|_|_|_|
       _|_|Q|_|_|_|_|_|
       _|_|_|_|_|_|_|_|
       _|Q|_|_|_|_|_|_|
       _|_|_|_|_|_|_|_|
       Q|_|_|_|_|_|_|_|

From now on we use the term **N-Queens-With-Lines** to refer to this
generalisation of the N-Queens problem.

**Assumptions and insights.** The N-Queens problem is NP-complete
[B]. Although we have no proof, it is likely that the N-Queens-With-Lines
problem is *also* NP-complete. It is therefore extremely unlikely that
an efficient algorithm exist. For this reason, and given the limited
time budget, we implement a simple backtracking algorithm, with an
emphasis on clarity of the algorithm. In particular, we use a somewhat
brute-force approach to the identification of prohibited lines:

- We simply consider all triples of queens, compute the slop(es)
  they form and compare if they form a line with three or more
  points. There is redundancy, that could probably be eliminated by
  preprocessing (ordering) queens by row / column (see many algorithm
  in computational geometry, e.g. the convex hull problem [C]. We
  avoid because it makes the code more complicated and we have not
  enough time. This also allows us to keep the core algorithm (the
  method `genericQueens` in Algorithm.java) virtually identical with
  that for the original N-Queens problem. This makes verification
  easier.

- The core insight making this problem digestible is that slopes of
  lines on discrete grids are always *rational numbers*, this allows
  us to avoid the many intricacies and pitfalls of Java's `float`
  (32-bit IEEE-754) and `double` (64-bit IEEE-754) data types
  [D]. Instead, we use a conventional Rational data type (in
  Rational.java). The main problem of rational numbers, exploding
  numerator and denominator, are avoided here, since the exponential
  nature of (our approach to) the N-Queens-With-Lines problem ensures
  small numerator and denominator for practically feasible N.


**Verification Plan.** The problem does *not* come with a customer
approved test suite, or other suitable testing oracle. For N > 8
visual inspection of solutions is becomes unreliable quickly. Moreover
it is not easy to work out if the algorithm might have 'forgotten' a
solution. In addition, the notion of line is itself somewhat tricky,
and a specification that can be tested against, might be
wrong. Finally, we have limited time.

1. Implement an additional solver for the original N-Queens problem.

2. Verify the solver from (1) simply by *counting* solutions and
   comparing them with the official number of solutions from the
   relevant entry in the *On-Line Encyclopedia of Integer Sequences*
   [E].

3. Use the solver from (1) to get a *second* solver (very inefficient)
   for the N-Queens-With-Lines problem:

    - First generate all N-Queens boards
    - Then *filter* all boards that contains lines with 3 or more points

4. Compare the two solvers of the N-Queens-With-Lines problem against each
other. More precisely, compare if the find identical numbers of
solutions.

This is not a perfect verification strategy and with more time &
resources, better testing could be done.

**Libraries and tools.** Since I have not written (non-trivial) Java
before, I am not familiar with the Java library and tooling eco
system. I therefore write everything I need by hand.  In
particular, since I have not used Gradle before, and it's similar to
tools like SBT or Maven, I will not use Gradle.

**Compiling and running the code.** All relevant code is in the root
directory and can be compiled by invoking

    javac *.java

on the command line.

**Running the code.** The program is run on the command line by invoking

    java Main <options>

The program has the following command line options.

    -n <int>    This parameter is required, must be
                non-negative and determines board size.

    -visualise  This optional parameter prints out all
                found valid boards. 

Regardless of the optional parameter, the program will always print
out the number of valid boards found.

Here is an example use case:

    java Main -n 8 -visualise    

**Feasible parameters.** In our experience, using a 2020 MacBook Pro,
solving the N-Queens-With-Lines for N=15 takes about 2 minutes. For N=16
it takes about 13 minutes.

# Bibliography

[A] https://en.wikipedia.org/wiki/Eight_queens_puzzle

[B] http://www.claymath.org/events/news/8-queens-puzzle

[C] https://en.wikipedia.org/wiki/Convex_hull

[D] https://en.wikipedia.org/wiki/IEEE_754

[E] http://oeis.org/A000170
