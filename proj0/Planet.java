public class Planet {
  public static final double G = 6.67e-11;
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet otherPlanet) {
    double xDiff = (otherPlanet.xxPos - xxPos);
    double yDiff = (otherPlanet.yyPos - yyPos);

    return Math.sqrt((xDiff * xDiff)  + (yDiff * yDiff));
  }

  public double calcForceExertedBy(Planet otherPlanet) {
    return (G * mass * otherPlanet.mass) / (calcDistance(otherPlanet) * calcDistance(otherPlanet));
  }

  public double calcForceExertedByX(Planet otherPlanet) {
    double xDiff = otherPlanet.xxPos - xxPos;
    return (calcForceExertedBy(otherPlanet) * xDiff) / calcDistance(otherPlanet);
  }

  public double calcForceExertedByY(Planet otherPlanet) {
    double yDiff = otherPlanet.yyPos - yyPos;
    return (calcForceExertedBy(otherPlanet) * yDiff) / calcDistance(otherPlanet);
  }

  public double calcNetForceExertedByX(Planet [] allPlanets) {
    double netForceExertedByX = 0.0;
    for (Planet planet : allPlanets) {
      if (!this.equals(planet)) {
        netForceExertedByX += calcForceExertedByX(planet);
      }
    }
    return netForceExertedByX;
  }

  public double calcNetForceExertedByY(Planet [] allPlanets) {
    double netForceExertedByY = 0.0;
    for (Planet planet : allPlanets) {
      if (!this.equals(planet)) {
        netForceExertedByY += calcForceExertedByY(planet);
      }
    }
    return netForceExertedByY;
  }

  public void update(double dt, double fX, double fY) {
    double accNetX = fX / mass;
    double accNetY = fY / mass;

    xxVel = xxVel + (dt * accNetX);
    yyVel = yyVel + (dt * accNetY);

    xxPos = xxPos + (dt * xxVel);
    yyPos = yyPos + (dt * yyVel);
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
