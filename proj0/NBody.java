import java.io.File;

public class NBody {
  public static String imageToDraw = "images/starfield.jpg";

  public static double readRadius(String dataFile) {
    In in = new In(dataFile);

    int noOfPlanets = in.readInt();
    double radiusOfUniverse = in.readDouble();

    return radiusOfUniverse;
  }

  public static Planet[] readPlanets(String dataFile) {
    In in = new In(dataFile);

    int noOfPlanets = in.readInt();

    Planet[] planets = new Planet[noOfPlanets];

    double radiusOfUniverse = in.readDouble();

    for (int i = 0; i < noOfPlanets; i++) {
      double xxPos  = in.readDouble();
      double yyPos = in.readDouble();

      double xxVel = in.readDouble();
      double yyVel = in.readDouble();

      double mass = in.readDouble();

      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }

    return planets;
  }

  private static void drawPlanets(Planet [] planets) {
    /* Clears the drawing window. */
    StdDraw.clear();

    StdDraw.picture(0, 0, imageToDraw);

    for (Planet p : planets) {
      p.draw();
    }
  }

  public static void main(String [] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);

    String fileName = args[2];
    double radius = readRadius(fileName);
    Planet[] planets = readPlanets(fileName);

    StdDraw.setScale(-radius, radius);

    drawPlanets(planets);

    StdDraw.enableDoubleBuffering();

    double time = 0;
    while (time != T) {
      double [] xForces = new double[planets.length];
      double [] yForces = new double[planets.length];

      for (int i = 0; i < planets.length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }

      for (int i = 0; i < planets.length; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      drawPlanets(planets);

      StdDraw.show();
      StdDraw.pause(10);

      time += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
