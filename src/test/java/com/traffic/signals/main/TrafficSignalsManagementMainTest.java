/**
 * 
 */
package com.traffic.signals.main;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.traffic.signals.vo.TrafficDetails;

/**
 * @author Phani Mangisetti
 *
 */
public class TrafficSignalsManagementMainTest {
	TrafficDetails trafficDetails = new TrafficDetails();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trafficDetails.getStateData().put("currentCount", 0);
		Map<String, Integer> trafficInfo = new HashMap<String, Integer>();
		trafficInfo.put("N", 0);
		trafficInfo.put("S", 0);
		trafficInfo.put("E", 0);
		trafficInfo.put("W", 0);
		trafficDetails.getStateData().put("trafficInfo", trafficInfo);
	}

	/**
	 * Test method for {@link com.traffic.signals.main.TrafficSignalsManagementMain#main(java.lang.String[])}.
	 */
	@Test
	public final void testUpdateTrafficInfo() {
		TrafficSignalsManagementMain.updateTrafficInfo(trafficDetails);
		
		assertEquals("None", trafficDetails.getStateData().get("previousDirection"));
		assertNotNull(trafficDetails);
		assertNotNull(trafficDetails.getStateData());
		assertNotNull(trafficDetails.getStateData().get("trafficInfo"));
		assertEquals(Boolean.valueOf("false"), trafficDetails.getStateData().get("isEastWestBoundGreen"));

		int count = 19;
		for (int i = 0; i < count; i++) {
			TrafficSignalsManagementMain.updateTrafficInfo(trafficDetails);
		}
	}

	@Test
	public final void testSetAndIncrementTrafficInfo() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> trafficInfo = (Map<String, Integer>) trafficDetails.getStateData().get("trafficInfo");
		TrafficSignalsManagementMain.setAndIncrementTrafficInfo(trafficInfo);
		assertEquals(Integer.valueOf("1"), trafficInfo.get("N"));

	}
}
