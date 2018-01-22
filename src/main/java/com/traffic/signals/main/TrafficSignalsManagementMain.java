package com.traffic.signals.main;

import java.util.HashMap;
import java.util.Map;

import com.traffic.signals.vo.TrafficDetails;


public class TrafficSignalsManagementMain {

	/**
	 * Call the updateTrafficInfo method with 0 as currentCount to intialize the HashMap.
	 * Call the method again in a for loop, say 20 occurances to display the current state 
	 * of the vehicles that gets accumulated at the signal.
	 * @param args
	 */
	public static void main(String[] args) {
		TrafficDetails trafficDetails = new TrafficDetails();

		// Set currentCount to 0, to start with
		trafficDetails.getStateData().put("currentCount", 0);
		updateTrafficInfo(trafficDetails);

		// Iterate and print 20 occurances in a loop
		for (int i = 0; i < 19; i++) {
			updateTrafficInfo(trafficDetails);
		}
	}

	/**
	 * Initialize the trafficDetails HashMap and track the signal changes as key/value pairs. 
	 * @param TrafficDetails
	 */
	public static void updateTrafficInfo(TrafficDetails trafficDetails) {

		if ((Integer) trafficDetails.getStateData().get("currentCount") == 0) {
			// Initialize the variables at the start of iteration

			// trafficInfo -> Map to keep track of counter for each direction N, S, E & W
			Map<String, Integer> trafficInfo = new HashMap<String, Integer>();
			trafficInfo.put("N", 0);
			trafficInfo.put("S", 0);
			trafficInfo.put("E", 0);
			trafficInfo.put("W", 0);
			trafficDetails.getStateData().put("trafficInfo", trafficInfo);

			// previousDirection -> Key to keep track of the traffic flow in the previous
			// iteration (NS or EW). None is the default value at the beginning. */
			trafficDetails.getStateData().put("previousDirection", "None");

			// isNorthSouthBoundGreen -> To keep track of signal in NS direction
			// isEastWestBoundGreen -> To keep track of signal in EW direction
			// isNorthSouthBoundGreenForNSeconds -> Counter to keep track of number of
			// vehicles stopped in NS direction (Snell Rd)
			// isEastWestBoundGreenForNSeconds -> Counter to keep track of number of
			// vehicles stopped in EW direction (Weaver Rd)

			// Start by turning on the traffic on Snell Rd to "GREEN"
			trafficDetails.getStateData().put("isNorthSouthBoundGreen", true);
			trafficDetails.getStateData().put("isEastWestBoundGreen", false);
			trafficDetails.getStateData().put("isNorthSouthBoundGreenForNSeconds", 0);
			trafficDetails.getStateData().put("isEastWestBoundGreenForNSeconds", 0);

			// Print the map here, initialized map values to output
			printTrafficInfo(trafficDetails);

			// Incrementing the counter after initialization is done
			trafficDetails.getStateData().put("currentCount",
					(Integer) trafficDetails.getStateData().get("currentCount") + 1);
		}

		@SuppressWarnings("unchecked")
		Map<String, Integer> trafficInfo = (Map<String, Integer>) trafficDetails.getStateData().get("trafficInfo");

		// Switch the direction of traffic from NS->EW or EW->NS, when both signals are
		// RED.
		if (!(Boolean) trafficDetails.getStateData().get("isNorthSouthBoundGreen")
				&& !(Boolean) trafficDetails.getStateData().get("isEastWestBoundGreen")) {

			if (null != trafficDetails.getStateData().get("previousDirection")) {
				if (trafficDetails.getStateData().get("previousDirection").equals("NS")) {
					trafficDetails.getStateData().put("isEastWestBoundGreen", true);
				}
				if (trafficDetails.getStateData().get("previousDirection").equals("EW")) {
					trafficDetails.getStateData().put("isNorthSouthBoundGreen", true);
				}
			}
		}

		// North-Sound Bound traffic management
		if ((Boolean) trafficDetails.getStateData().get("isNorthSouthBoundGreen")) {
			trafficDetails.getStateData().put("isNorthSouthBoundGreenForNSeconds",
					(Integer) trafficDetails.getStateData().get("isNorthSouthBoundGreenForNSeconds") + 1);
		}

		// Red -> Green #First car takes 2 seconds
		if ((Integer) trafficDetails.getStateData().get("isNorthSouthBoundGreenForNSeconds") == 1) {
			if (!trafficDetails.getStateData().get("previousDirection").equals("None")) {
				trafficInfo.put("N", trafficInfo.get("N") + 1);
				trafficInfo.put("S", trafficInfo.get("S") + 1);
			}
			trafficInfo.put("E", trafficInfo.get("E") + 1);
			trafficInfo.put("W", trafficInfo.get("W") + 1);
		}

		// Subsequent cars take 1 second each
		if ((Integer) trafficDetails.getStateData().get("isNorthSouthBoundGreenForNSeconds") > 1
				&& (Integer) trafficDetails.getStateData().get("isNorthSouthBoundGreenForNSeconds") < 4) {
			trafficInfo.put("E", trafficInfo.get("E") + 1);
			trafficInfo.put("W", trafficInfo.get("W") + 1);
		}

		// Green -> Red in NS
		if ((Integer) trafficDetails.getStateData().get("isNorthSouthBoundGreenForNSeconds") == 4) {
			trafficDetails.getStateData().put("isNorthSouthBoundGreenForNSeconds", 0);
			trafficDetails.getStateData().put("isNorthSouthBoundGreen", false);
			setAndIncrementTrafficInfo(trafficInfo);
			trafficDetails.getStateData().put("previousDirection", "NS");
		}

		// East-West Bound traffic management
		if ((Boolean) trafficDetails.getStateData().get("isEastWestBoundGreen")) {
			trafficDetails.getStateData().put("isEastWestBoundGreenForNSeconds",
					(Integer) trafficDetails.getStateData().get("isEastWestBoundGreenForNSeconds") + 1);
		}

		// Red -> Green #First car takes 2 seconds
		if ((Integer) trafficDetails.getStateData().get("isEastWestBoundGreenForNSeconds") == 1) {
			setAndIncrementTrafficInfo(trafficInfo);
		}

		// Subsequent cars take 1 second each
		if ((Integer) trafficDetails.getStateData().get("isEastWestBoundGreenForNSeconds") > 1
				&& (Integer) trafficDetails.getStateData().get("isEastWestBoundGreenForNSeconds") < 4) {
			trafficInfo.put("N", trafficInfo.get("N") + 1);
			trafficInfo.put("S", trafficInfo.get("S") + 1);
		}

		// Green -> Red in EW
		if ((Integer) trafficDetails.getStateData().get("isEastWestBoundGreenForNSeconds") == 4) {
			trafficDetails.getStateData().put("isEastWestBoundGreenForNSeconds", 0);
			trafficDetails.getStateData().put("isEastWestBoundGreen", false);
			setAndIncrementTrafficInfo(trafficInfo);
			trafficDetails.getStateData().put("previousDirection", "EW");
		}

		printTrafficInfo(trafficDetails);
		// Increment the counter
		trafficDetails.getStateData().put("currentCount",
				(Integer) trafficDetails.getStateData().get("currentCount") + 1);
	}

	// Utility method to set and increment the counter in all 4 directions (N, S, E & W)
	/**
	 * Utility method to increment the counter in all 4 directions (N, S, E & W).
	 * @param trafficInfo
	 */
	public static void setAndIncrementTrafficInfo(Map<String, Integer> trafficInfo) {
		trafficInfo.put("N", trafficInfo.get("N") + 1);
		trafficInfo.put("S", trafficInfo.get("S") + 1);
		trafficInfo.put("E", trafficInfo.get("E") + 1);
		trafficInfo.put("W", trafficInfo.get("W") + 1);
	}

	/**
	 * Print method to log the result for each loop ineration.
	 * @param trafficDetails
	 */
	@SuppressWarnings("unchecked")
	private static void printTrafficInfo(TrafficDetails trafficDetails) {
		Map<String, Integer> trafficInfo = (Map<String, Integer>) trafficDetails.getStateData().get("trafficInfo");

		// Uncomment the below sysout to see the map for each iteration, as needed for debug
		//System.out.println(" trafficDetails: " + trafficDetails.toString());
		System.out.println(trafficDetails.getStateData().get("currentCount") + ":" + " N = " + trafficInfo.get("N")
				+ ";" + " S = " + trafficInfo.get("S") + ";" + " E = " + trafficInfo.get("E") + ";" + " W = "
				+ trafficInfo.get("W"));
	}
}
