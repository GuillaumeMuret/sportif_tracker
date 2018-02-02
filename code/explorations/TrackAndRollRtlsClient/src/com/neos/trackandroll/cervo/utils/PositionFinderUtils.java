package com.neos.trackandroll.cervo.utils;

import com.neos.trackandroll.cervo.model.localisateur.Circle;
import com.neos.trackandroll.cervo.model.localisateur.Point;

import java.util.LinkedList;
import java.util.List;

public class PositionFinderUtils {

    public static Point findBestCoordinate(List<Circle> anchorsValues) {
        Point pointMedian = null;
        List<Point> nuageDePoints = new LinkedList<>();

        if (anchorsValues != null) {
            for (int i = 0; i < anchorsValues.size(); i++) {
                for (int j = i + 1; j < anchorsValues.size(); j++) {
                    Circle c1 = anchorsValues.get(i);
                    Circle c2 = anchorsValues.get(j);
                    List<Point> intersections = new LinkedList<>();
                    try {
                        intersections = intersectionsEntreDeuxCercles(c1, c2);
                    } catch (AssertionError ae) {
                        ae.printStackTrace();
                    }
                    nuageDePoints.addAll(intersections);
                }
            }

            if (nuageDePoints.size() != 0) {
                float x = 0, y = 0;
                for (Point nuageDePoint : nuageDePoints) {
                    x += nuageDePoint.x;
                    y += nuageDePoint.y;
                }
                x /= nuageDePoints.size();
                y /= nuageDePoints.size();
                pointMedian = new Point(x, y);
            }
        }

        return pointMedian;
    }

    private static List<Point> intersectionsEntreDeuxCercles(Circle c1, Circle c2) {
        List<Point> solutions = new LinkedList<>();

        Point O0 = c1.centre;
        float r0 = c1.rayon;
        Point O1 = c2.centre;
        float r1 = c2.rayon;

        if (O0.y != O1.y) {
            float div = (O0.x - O1.x) / (O0.y - O1.y);
            float N = (r1 * r1 - r0 * r0 - O1.x * O1.x + O0.x * O0.x - O1.y * O1.y + O0.y * O0.y) / (2 * (O0.y - O1.y));
            float A = div * div + 1;
            float B = 2 * O0.y * div - 2 * N * div - 2 * O0.x;
            float C = O0.x * O0.x + O0.y * O0.y + N * N - r0 * r0 - 2 * O0.y * N;
            float delta = (float) Math.sqrt(B * B - 4 * A * C);

            if (delta > 0) {
                float x1 = (-B + delta) / (2 * A);
                float y1 = N - x1 * div;
                float x2 = (-B - delta) / (2 * A);
                float y2 = N - x2 * div;
                solutions.add(new Point(x1, y1));
                solutions.add(new Point(x2, y2));
            } else if (delta == 0) {
                float x0 = (-B) / (2 * A);
                float y0 = N - x0 * div;
                solutions.add(new Point(x0, y0));
            } else {
                Point P0 = milieuEntreDeuxCerclesNonSecants(new Circle(O0, r0), new Circle(O1, r1));
                solutions.add(P0);
            }
        } else {
            float x = (r1 * r1 - r0 * r0 - O1.x * O1.x + O0.x * O0.x) / (2 * (O0.x - O1.x));
            float A = 1;
            float B = -2 * O1.y;
            float C = O1.x * O1.x + x * x - 2 * O1.x * x + O1.y * O1.y - r1 * r1;
            float delta = (float) Math.sqrt(B * B - 4 * A * C);

            if (delta > 0) {
                float y1 = (-B + delta) / (2 * A);
                float y2 = (-B - delta) / (2 * A);
                solutions.add(new Point(x, y1));
                solutions.add(new Point(x, y2));
            } else if (delta == 0) {
                float y0 = (-B) / (2 * A);
                solutions.add(new Point(x, y0));
            } else {
                Point P0 = milieuEntreDeuxCerclesNonSecants(new Circle(O0, r0), new Circle(O1, r1));
                solutions.add(P0);
            }
        }

        return solutions;
    }

    private static Point milieuEntreDeuxCerclesNonSecants(Circle c1, Circle c2) {
        Point extremiteC1 = pointDunCercleLePlusProcheDunAutrePoint(c1, c2.centre);
        Point extremiteC2 = pointDunCercleLePlusProcheDunAutrePoint(c2, c1.centre);
        float x = (extremiteC1.x + extremiteC2.x) / 2;
        float y = (extremiteC1.y + extremiteC2.y) / 2;
        return new Point(x, y);
    }

    private static Point pointDunCercleLePlusProcheDunAutrePoint(Circle cercle, Point point) {
        Point pointLePlusProche;

        float[] equationDroite = determineEquationDroite(cercle.centre, point);

        List<Point> solutions = intersectionsCercleEtDroitePassantParLeCentre(cercle, equationDroite);

        if (solutions.size() != 2)
            throw new AssertionError("\n\tERROR : Nombre de solutions incorrect");

        Point solution1 = solutions.get(0);
        float distance1 = (float) Math.sqrt(Math.pow((point.x - solution1.x), 2) + Math.pow((point.y - solution1.y), 2));

        Point solution2 = solutions.get(1);
        float distance2 = (float) Math.sqrt(Math.pow((point.x - solution2.x), 2) + Math.pow((point.y - solution2.y), 2));

        if (distance1 < distance2) {
            pointLePlusProche = solution1;
        } else {
            pointLePlusProche = solution2;
        }

        return pointLePlusProche;
    }

    private static float[] determineEquationDroite(Point P1, Point P2) {
        if (P1.x == P2.x && P1.y == P2.y)
            throw new AssertionError("\n\tERROR : Les deux points sont confondus");
        float[] solutions;
        if (P1.x == P2.x) {
            solutions = new float[1];
            solutions[0] = P1.x;
        } else {
            solutions = new float[2];
            solutions[0] = (P2.y - P1.y) / (P2.x - P1.x);
            solutions[1] = P1.y - solutions[0] * P1.x;
        }
        return solutions;
    }

    private static List<Point> intersectionsCercleEtDroitePassantParLeCentre(Circle cercle, float[] equationDroite) {
        List<Point> intersections = new LinkedList<>();
        if (equationDroite.length == 1) {
            float X = equationDroite[0];
            float A = 1;
            float B = -2 * cercle.centre.y;
            float C = (X - cercle.centre.x) * (X - cercle.centre.x) + cercle.centre.y * cercle.centre.y + cercle.rayon * cercle.rayon;
            float delta = (float) Math.sqrt(B * B - 4 * A * C);
            if (delta > 0) {
                float y1 = (-B + delta) / (2 * A);
                float y2 = (-B - delta) / (2 * A);
                intersections.add(new Point(X, y1));
                intersections.add(new Point(X, y2));
            } else {
                throw new AssertionError("\n\tERROR : La droite ne passe pas par le centre du cercle");
            }
        } else if (equationDroite.length == 2) {
            float M = equationDroite[0];
            float P = equationDroite[1];
            float A = 1 + M * M;
            float B = 2 * M * (P - cercle.centre.y) - 2 * cercle.centre.x;
            float C = cercle.centre.x * cercle.centre.x + (P - cercle.centre.y) * (P - cercle.centre.y) - cercle.rayon * cercle.rayon;
            float delta = (float) Math.sqrt(B * B - 4 * A * C);
            if (delta > 0) {
                float x1 = (-B + delta) / (2 * A);
                float y1 = M * x1 + P;
                float x2 = (-B - delta) / (2 * A);
                float y2 = M * x2 + P;
                intersections.add(new Point(x1, y1));
                intersections.add(new Point(x2, y2));
            } else {
                throw new AssertionError("\n\tERROR : La droite ne passe pas par le centre du cercle");
            }
        } else {
            throw new AssertionError("\n\tERROR : Equation de droite incorrecte");
        }
        return intersections;
    }
}
