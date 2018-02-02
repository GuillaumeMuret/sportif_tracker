package com.neos.trackandroll.cervo.utils;

import com.neos.trackandroll.cervo.model.localisateur.Circle;
import com.neos.trackandroll.cervo.model.localisateur.Point;

import java.util.LinkedList;
import java.util.List;

public class PositionFinderUtils {

    /**
     * Number four
     */
    private static final int FOUR = 4;

    /**
     * Number two
     */
    private static final int TWO = 2;

    /**
     * The little number
     */
    private static final double LITTLE_NUMBER = 0.00001;

    /**
     * Process called to get the best coordinate intersection of the different circle in parameters
     *
     * @param anchorsValues : the different circles
     * @return : the best coordinate intersection of the different circle in parameters
     */
    public static Point findBestCoordinate(List<Circle> anchorsValues) {
        List<Point> sortedNuageDePoints = getAndSortDotsCloud(anchorsValues);
        return findPointMedian(sortedNuageDePoints);
    }

    /**
     * Process called to get the sorted intersections of the different circle in parameters
     *
     * @param anchorsValues : the different circles
     * @return : the sorted intersections of the different circle in parameters
     */
    private static List<Point> getAndSortDotsCloud(List<Circle> anchorsValues) {
        List<Point> nuageDePoints = findDotsCloud(anchorsValues);
        List<Point> sortedNuageDePoints = new LinkedList<>();
        List<Point> lonesomeDots = new LinkedList<>();
        for (Point point : nuageDePoints) {
            if (point.family == -1) {
                lonesomeDots.add(point);
            }
        }
        if (lonesomeDots.size() != 0) {
            sortedNuageDePoints.addAll(lonesomeDots);
            Point medianLonesomeDots = findPointMedian(lonesomeDots);
            for (int i = 0; i < nuageDePoints.size(); i++) {
                Point point1 = nuageDePoints.get(i);
                if (point1.family != -1) {
                    if (i + 1 < nuageDePoints.size() && nuageDePoints.get(i + 1).family == point1.family) {
                        i++;
                        Point point2 = nuageDePoints.get(i);
                        if (findDistance(medianLonesomeDots, point1) < findDistance(medianLonesomeDots, point2)) {
                            sortedNuageDePoints.add(point1);
                        } else {
                            sortedNuageDePoints.add(point2);
                        }
                    } else {
                        LogUtils.point("Localisateur : Erreur, membres de la famille d'un point introuvables.");
                        sortedNuageDePoints.add(point1);
                    }
                }
            }
        } else if (nuageDePoints.size() != 0) {
            for (Point point : nuageDePoints) {
                Boolean inAllCircle = true;
                int j = 0;
                while (inAllCircle && j < anchorsValues.size()) {
                    Circle anchor = anchorsValues.get(j);
                    double distance = findDistance(point, anchor.centre) - LITTLE_NUMBER;
                    if (distance > anchor.rayon) {
                        inAllCircle = false;
                    } else {
                        j++;
                    }
                }
                if (inAllCircle) {
                    sortedNuageDePoints.add(point);
                }
            }
        }
        return sortedNuageDePoints;
    }

    /**
     * Process called to get the median point of the different points in parameters
     *
     * @param nuageDePoints : the differents points
     * @return : the median point of the different points in parameters
     */
    private static Point findPointMedian(List<Point> nuageDePoints) {
        Point pointMedian = null;
        if (nuageDePoints != null && nuageDePoints.size() != 0) {
            float x = 0;
            float y = 0;
            for (Point nuageDePoint : nuageDePoints) {
                x += nuageDePoint.x;
                y += nuageDePoint.y;
            }
            x /= nuageDePoints.size();
            y /= nuageDePoints.size();
            pointMedian = new Point(x, y);
        }
        return pointMedian;
    }

    /**
     * Process called to get the intersections of the different circle in parameters
     *
     * @param anchorsValues : the differents circles
     * @return : the intersections of the different circle in parameters
     */
    private static List<Point> findDotsCloud(List<Circle> anchorsValues) {
        List<Point> nuageDePoints = new LinkedList<>();
        int familyValue = 0;
        if (anchorsValues != null) {
            for (int i = 0; i < anchorsValues.size(); i++) {
                for (int j = i + 1; j < anchorsValues.size(); j++) {
                    Circle c1 = anchorsValues.get(i);
                    Circle c2 = anchorsValues.get(j);
                    List<Point> intersections = new LinkedList<>();
                    try {
                        intersections = intersectionsEntreDeuxCercles(c1, c2);
                    } catch (AssertionError ae) {
                        LogUtils.point("Error point " + ae.getMessage());
                    }

                    if (intersections.size() == 1) {
                        nuageDePoints.addAll(intersections);
                    } else if (intersections.size() == 2) {
                        Point pointA = intersections.get(0);
                        Point pointB = intersections.get(1);
                        nuageDePoints.add(new Point(pointA.x, pointA.y, familyValue));
                        nuageDePoints.add(new Point(pointB.x, pointB.y, familyValue));
                        familyValue++;
                    }
                }
            }
        }
        return nuageDePoints;
    }

    /**
     * Process called to get the intersection between 2 circles
     *
     * @param c1 : the circle 1
     * @param c2 : the circle 2
     * @return : the intersection between 2 circles
     */
    private static List<Point> intersectionsEntreDeuxCercles(Circle c1, Circle c2) {
        List<Point> solutions = new LinkedList<>();

        Point o0 = c1.centre;
        float r0 = c1.rayon;
        Point o1 = c2.centre;
        float r1 = c2.rayon;

        if (o0.y != o1.y) {
            float div = (o0.x - o1.x) / (o0.y - o1.y);
            float n = (r1 * r1 - r0 * r0 - o1.x * o1.x + o0.x * o0.x - o1.y * o1.y + o0.y * o0.y) / (2 * (o0.y - o1.y));
            float a = div * div + 1;
            float b = 2 * o0.y * div - 2 * n * div - 2 * o0.x;
            float c = o0.x * o0.x + o0.y * o0.y + n * n - r0 * r0 - 2 * o0.y * n;
            float delta = (float) Math.sqrt(b * b - FOUR * a * c);

            if (delta > 0) {
                float x1 = (-b + delta) / (2 * a);
                float y1 = n - x1 * div;
                float x2 = (-b - delta) / (2 * a);
                float y2 = n - x2 * div;
                solutions.add(new Point(x1, y1));
                solutions.add(new Point(x2, y2));
            } else if (delta == 0) {
                float x0 = (-b) / (2 * a);
                float y0 = n - x0 * div;
                solutions.add(new Point(x0, y0));
            } else {
                Point p0 = milieuEntreDeuxCerclesNonSecants(new Circle(o0, r0), new Circle(o1, r1));
                solutions.add(p0);
            }
        } else {
            float x = (r1 * r1 - r0 * r0 - o1.x * o1.x + o0.x * o0.x) / (2 * (o0.x - o1.x));
            float a = 1;
            float b = -TWO * o1.y;
            float c = o1.x * o1.x + x * x - 2 * o1.x * x + o1.y * o1.y - r1 * r1;
            float delta = (float) Math.sqrt(b * b - FOUR * a * c);

            if (delta > 0) {
                float y1 = (-b + delta) / (2 * a);
                float y2 = (-b - delta) / (2 * a);
                solutions.add(new Point(x, y1));
                solutions.add(new Point(x, y2));
            } else if (delta == 0) {
                float y0 = (-b) / (2 * a);
                solutions.add(new Point(x, y0));
            } else {
                Point p0 = milieuEntreDeuxCerclesNonSecants(new Circle(o0, r0), new Circle(o1, r1));
                solutions.add(p0);
            }
        }

        return solutions;
    }

    /**
     * Process called to get the middle between two non secant circles
     *
     * @param c1 : the circle 1
     * @param c2 : the circle 2
     * @return : the middle between two non secant circles
     */
    private static Point milieuEntreDeuxCerclesNonSecants(Circle c1, Circle c2) {
        Point extremiteC1 = pointDunCercleLePlusProcheDunAutrePoint(c1, c2.centre);
        Point extremiteC2 = pointDunCercleLePlusProcheDunAutrePoint(c2, c1.centre);
        float x = (extremiteC1.x + extremiteC2.x) / TWO;
        float y = (extremiteC1.y + extremiteC2.y) / TWO;
        return new Point(x, y);
    }

    /**
     * Process called to get the point in one circle closest to another
     *
     * @param cercle : the circle
     * @param point  : the point
     * @return : the point in one circle closest to another
     */
    private static Point pointDunCercleLePlusProcheDunAutrePoint(Circle cercle, Point point) {
        Point pointLePlusProche;

        float[] equationDroite = determineEquationDroite(cercle.centre, point);

        List<Point> solutions = intersectionsCercleEtDroitePassantParLeCentre(cercle, equationDroite);

        if (solutions.size() != 2) {
            throw new AssertionError("\n\tERROR : Nombre de solutions incorrect");
        }

        Point solution1 = solutions.get(0);
        float distance1 = (float) Math.sqrt(Math.pow(point.x - solution1.x, TWO) + Math.pow(point.y - solution1.y, TWO));

        Point solution2 = solutions.get(1);
        float distance2 = (float) Math.sqrt(Math.pow(point.x - solution2.x, TWO) + Math.pow(point.y - solution2.y, TWO));

        if (distance1 < distance2) {
            pointLePlusProche = solution1;
        } else {
            pointLePlusProche = solution2;
        }

        return pointLePlusProche;
    }

    /**
     * Process called to get the right equation passing through 2 points
     *
     * @param p1 : point 1
     * @param p2 : point 2
     * @return : the right equation passing through 2 points
     */
    private static float[] determineEquationDroite(Point p1, Point p2) {
        if (p1.x == p2.x && p1.y == p2.y) {
            throw new AssertionError("\n\tERROR : Les deux points sont confondus");
        }
        float[] solutions;
        if (p1.x == p2.x) {
            solutions = new float[1];
            solutions[0] = p1.x;
        } else {
            solutions = new float[2];
            solutions[0] = (p2.y - p1.y) / (p2.x - p1.x);
            solutions[1] = p1.y - solutions[0] * p1.x;
        }
        return solutions;
    }

    /**
     * Process called to get the intersection between a circle and a straight line passing through the centre.
     *
     * @param cercle         : the circle
     * @param equationDroite : the right equation
     * @return : the intersection between a circle and a straight line passing through the centre
     */
    private static List<Point> intersectionsCercleEtDroitePassantParLeCentre(Circle cercle, float[] equationDroite) {
        List<Point> intersections = new LinkedList<>();
        if (equationDroite.length == 1) {
            float x = equationDroite[0];
            float a = 1;
            float b = -TWO * cercle.centre.y;
            float c = (x - cercle.centre.x) * (x - cercle.centre.x)
                    + cercle.centre.y * cercle.centre.y + cercle.rayon * cercle.rayon;
            float delta = (float) Math.sqrt(b * b - FOUR * a * c);
            if (delta > 0) {
                float y1 = (-b + delta) / (2 * a);
                float y2 = (-b - delta) / (2 * a);
                intersections.add(new Point(x, y1));
                intersections.add(new Point(x, y2));
            } else {
                throw new AssertionError("\n\tERROR - La droite ne passe pas par le centre du cercle");
            }
        } else if (equationDroite.length == 2) {
            float m = equationDroite[0];
            float p = equationDroite[1];
            float a = 1 + m * m;
            float b = 2 * m * (p - cercle.centre.y) - 2 * cercle.centre.x;
            float c = cercle.centre.x * cercle.centre.x
                    + (p - cercle.centre.y) * (p - cercle.centre.y) - cercle.rayon * cercle.rayon;
            float delta = (float) Math.sqrt(b * b - FOUR * a * c);
            if (delta > 0) {
                float x1 = (-b + delta) / (2 * a);
                float y1 = m * x1 + p;
                float x2 = (-b - delta) / (2 * a);
                float y2 = m * x2 + p;
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

    /**
     * Process called to get the distance between 2 points
     *
     * @param point1 : point 1
     * @param point2 : point 2
     * @return : the distance between 2 points
     */
    private static double findDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.x - point1.x, TWO) + Math.pow(point2.y - point1.y, TWO));
    }

}
