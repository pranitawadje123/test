package com.example.test_service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.*;
import com.google.protobuf.Duration;
import org.springframework.web.client.RestTemplate;

import java.util.*;


public class RouteOptimization {

    static {
        Loader.loadNativeLibraries();
    }

    static class Location {
        String id;
        double lat;
        double lng;
        String address;

        public Location(String id, double lat, double lng ) {
            this.id = id;
            this.lat = lat;
            this.lng = lng;
        }
    }


    private static final String API_KEY = "AIzaSyDwBrxKtag1j_HjcIhUR1BhWUw6mIGeqEE";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        runMain();
    }

    public static void runMain() {
        List<Location> locations = new ArrayList<>();
        List<int[]> pickupDeliveryPairs = new ArrayList<>();
        Random rand = new Random();
        double centerLat = 37.7749;
        double centerLng = -122.4194;

        for (int i = 1; i <= 1; i++) {
            double pickupLat = centerLat + (rand.nextDouble() - 0.5) * 0.01;
            double pickupLng = centerLng + (rand.nextDouble() - 0.5) * 0.01;
            locations.add(new Location("P" + i, pickupLat, pickupLng));

            double dropoffLat = centerLat + (rand.nextDouble() - 0.5) * 0.1;
            double dropoffLng = centerLng + (rand.nextDouble() - 0.5) * 0.1;
            locations.add(new Location("D" + i, dropoffLat, dropoffLng));

            pickupDeliveryPairs.add(new int[]{2 * i - 2, 2 * i - 1});
        }
        Map<Integer, String> addressMap = new HashMap<>();
        long[][] distanceMatrix = getDistanceMatrix(locations,addressMap);
        if (distanceMatrix.length > 0) {
            solveTSP(pickupDeliveryPairs, distanceMatrix,addressMap);
        } else {
            System.out.println("Failed to fetch distance matrix.");
        }
    }

    private static long[][] getDistanceMatrix(List<Location> locations, Map<Integer, String> addressMap) {
        try {
            StringBuilder origins = new StringBuilder();
            StringBuilder destinations = new StringBuilder();


            for (int i = 0; i < locations.size(); i++) {
                origins.append(locations.get(i).lat).append(",").append(locations.get(i).lng).append("|");
                destinations.append(locations.get(i).lat).append(",").append(locations.get(i).lng).append("|");
                addressMap.put(i, locations.get(i).address); // Map index to address
            }

            String url = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                    "?origins=" + origins +
                    "&destinations=" + destinations +
                    "&key=" + API_KEY;

            url="https://maps.googleapis.com/maps/api/distancematrix/json?origins=37.77681534574045,-122.41730555856665&destinations=37.79947188251666,-122.39175472472445|&key=AIzaSyDwBrxKtag1j_HjcIhUR1BhWUw6mIGeqEE";

            System.out.println("url --->" + url);

            String response = restTemplate.getForObject(url, String.class);
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

            JsonArray rows = jsonResponse.getAsJsonArray("rows");
            long[][] distanceMatrix = new long[locations.size()][locations.size()];

            for (int i = 0; i < rows.size(); i++) {
                JsonArray elements = rows.get(i).getAsJsonObject().getAsJsonArray("elements");
                for (int j = 0; j < elements.size(); j++) {
                    JsonObject element = elements.get(j).getAsJsonObject();
                    long distance = element.getAsJsonObject("distance").get("value").getAsLong();
                    distanceMatrix[i][j] = distance/100;
                }
            }
            for (long[] row : distanceMatrix) {
                System.out.println(Arrays.toString(row));
            }
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix[i].length; j++) {
                    distanceMatrix[i][j] /= 100;  // Reduce scale
                }
            }

            return distanceMatrix;
        } catch (Exception e) {
            e.printStackTrace();
            return new long[0][0];
        }
    }

    public static void solveTSP(List<int[]> pickupDeliveryPairs, long[][] distanceMatrix,Map<Integer, String> addressMap) {

        distanceMatrix = new long[][]{
                {0, 10, 20, 30, 40},
                {10, 0, 15, 25, 35}
//                {20, 15, 0, 18, 28},
//                {30, 25, 18, 0, 22},
//                {40, 35, 28, 22, 0}
        };

       pickupDeliveryPairs = Arrays.asList(
                new int[]{1, 1}  // Pickup at node 1, Deliver at node 3
//                new int[]{2, 4}   // Pickup at node 2, Deliver at node 4
        );
        RoutingIndexManager manager = new RoutingIndexManager(
                distanceMatrix.length,
                1,
                0
        );
        RoutingModel routing = new RoutingModel(manager);

        long[][] finalDistanceMatrix = distanceMatrix;
        System.out.println("distanceMatrix" + distanceMatrix);
        final int transitCallbackIndex = routing.registerTransitCallback((long fromIndex, long toIndex) -> {
            int fromNode = manager.indexToNode((int) fromIndex);
            int toNode = manager.indexToNode((int) toIndex);
            System.out.println("fromNode" + fromNode);
            System.out.println("toNode" + toNode);
            return finalDistanceMatrix[fromNode][toNode];
        });

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);

        routing.addDimension(
                transitCallbackIndex, // Transit callback
                0,  // No waiting time
                1000000, // Max cumulative value (adjust as needed)
                true, // Start at zero
                "Time"
        );

        for (int[] pair : pickupDeliveryPairs) {
            int pickupIndex = pair[0];
            int deliveryIndex = pair[1];

            if (pickupIndex < 0 || pickupIndex >= manager.getNumberOfNodes() ||
                    deliveryIndex < 0 || deliveryIndex >= manager.getNumberOfNodes()) {
                throw new IllegalArgumentException("Invalid pickup or delivery index: " + pickupIndex + ", " + deliveryIndex);
            }

            routing.addPickupAndDelivery(pickupIndex, deliveryIndex);

            routing.solver().addConstraint(
                    routing.solver().makeLessOrEqual(
                            routing.vehicleVar(routing.start(pickupIndex)),
                            routing.vehicleVar(routing.start(deliveryIndex))
                    )
            );
        }

        RoutingSearchParameters searchParameters = main.defaultRoutingSearchParameters().toBuilder()
                .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                .setLocalSearchMetaheuristic(LocalSearchMetaheuristic.Value.GUIDED_LOCAL_SEARCH)
                .setTimeLimit(Duration.newBuilder().setSeconds(30).build())  // Give enough time
                .build();
        searchParameters.toBuilder().setLogSearch(true).build();

        Assignment solution = routing.solveWithParameters(searchParameters);


        if (solution != null) {
            printSolution(manager, routing, solution,addressMap);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static void printSolution(RoutingIndexManager manager, RoutingModel routing, Assignment solution, Map<Integer, String> addressMap) {
        long routeIndex = routing.start(0);
        System.out.println("\nOptimized Route:");
        while (!routing.isEnd(routeIndex)) {
            System.out.print(manager.indexToNode(routeIndex) + " -> ");
            routeIndex = (int) solution.value(routing.nextVar(routeIndex));
        }
        System.out.println(manager.indexToNode(routeIndex));

    }
}