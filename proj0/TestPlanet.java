/**
 * Tests Planet
 */
public class TestPlanet {
  public static void main(String [] args) {
    printPairwiseForce();
  }

  public static void printPairwiseForce() {
    Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
    Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
    System.out.println("The pair wise forces between planets is: " + p1.calcForceExertedBy(p2));
  }
}
