package nl.test;

import java.util.Comparator;
import java.util.List;

public class Node {

    private List<Route> routes;

    public boolean canReach(Node target) {
        if (this == target) {
            return true;
        }
        if (routes == null || routes.isEmpty()) {
            return false;
        }

        for (Route route : routes) {
            if (route.end().equals(target)) return true;
        }

        return routes.stream()
                .anyMatch(route -> route.end().canReach(target));
    }

    public int smallestHopsTo(Node target) {
        if (this == target) {
            return 0;
        }
        if (routes == null || routes.isEmpty()) {
            return 0;
        }

        for (Route route : routes) {
            if (route.end().equals(target)) return 1;
        }

        return 1 + routes.stream()
                .map(n -> n.end().smallestHopsTo(target))
                .min(Comparator.naturalOrder())
                .orElse(0);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
