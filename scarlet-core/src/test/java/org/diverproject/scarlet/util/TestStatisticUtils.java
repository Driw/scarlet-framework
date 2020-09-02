package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Statistic Utils")
public class TestStatisticUtils {

	@Test
	@DisplayName("Percentage")
	public void testPercentage() {
		assertEquals(StatisticUtils.percentage(100, 100), 100);
		assertEquals(StatisticUtils.percentage(200, 100), 200);
		assertEquals(StatisticUtils.percentage(50, 100), 50);
		assertEquals(StatisticUtils.percentage(100, 1000), 10);
		assertEquals(StatisticUtils.percentage(200, 1000), 20);
		assertEquals(StatisticUtils.percentage(50, 1000), 5);

		assertEquals(StatisticUtils.percentage(100L, 100L), 100L);
		assertEquals(StatisticUtils.percentage(200L, 100L), 200L);
		assertEquals(StatisticUtils.percentage(50L, 100L), 50L);
		assertEquals(StatisticUtils.percentage(100L, 1000L), 10L);
		assertEquals(StatisticUtils.percentage(200L, 1000L), 20L);
		assertEquals(StatisticUtils.percentage(50L, 1000L), 5L);

		assertEquals(StatisticUtils.percentage(100D, 100D), 100D);
		assertEquals(StatisticUtils.percentage(200D, 100D), 200D);
		assertEquals(StatisticUtils.percentage(50D, 100D), 50D);
		assertEquals(StatisticUtils.percentage(100D, 1000D), 10D);
		assertEquals(StatisticUtils.percentage(200D, 1000D), 20D);
		assertEquals(StatisticUtils.percentage(50D, 1000D), 5D);

		assertEquals(StatisticUtils.percentage(100F, 100F), 100F);
		assertEquals(StatisticUtils.percentage(200F, 100F), 200F);
		assertEquals(StatisticUtils.percentage(50F, 100F), 50F);
		assertEquals(StatisticUtils.percentage(100F, 1000F), 10F);
		assertEquals(StatisticUtils.percentage(200F, 1000F), 20F);
		assertEquals(StatisticUtils.percentage(50F, 1000F), 5F);
	}
}
