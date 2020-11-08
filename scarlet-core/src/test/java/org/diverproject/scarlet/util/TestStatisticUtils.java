package org.diverproject.scarlet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Statistic Utils")
public class TestStatisticUtils {

	@Test
	@DisplayName("Percentage")
	public void testPercentage() {
		assertEquals(100, StatisticUtils.percentage(100, 100));
		assertEquals(200, StatisticUtils.percentage(200, 100));
		assertEquals(50, StatisticUtils.percentage(50, 100));
		assertEquals(10, StatisticUtils.percentage(100, 1000));
		assertEquals(20, StatisticUtils.percentage(200, 1000));
		assertEquals(5, StatisticUtils.percentage(50, 1000));
		assertEquals(100L, StatisticUtils.percentage(100L, 100L));
		assertEquals(200L, StatisticUtils.percentage(200L, 100L));
		assertEquals(50L, StatisticUtils.percentage(50L, 100L));
		assertEquals(10L, StatisticUtils.percentage(100L, 1000L));
		assertEquals(20L, StatisticUtils.percentage(200L, 1000L));
		assertEquals(5L, StatisticUtils.percentage(50L, 1000L));
		assertEquals(100D, StatisticUtils.percentage(100D, 100D));
		assertEquals(200D, StatisticUtils.percentage(200D, 100D));
		assertEquals(50D, StatisticUtils.percentage(50D, 100D));
		assertEquals(10D, StatisticUtils.percentage(100D, 1000D));
		assertEquals(20D, StatisticUtils.percentage(200D, 1000D));
		assertEquals(5D, StatisticUtils.percentage(50D, 1000D));
		assertEquals(100F, StatisticUtils.percentage(100F, 100F));
		assertEquals(200F, StatisticUtils.percentage(200F, 100F));
		assertEquals(50F, StatisticUtils.percentage(50F, 100F));
		assertEquals(10F, StatisticUtils.percentage(100F, 1000F));
		assertEquals(20F, StatisticUtils.percentage(200F, 1000F));
		assertEquals(5F, StatisticUtils.percentage(50F, 1000F));
	}
}
