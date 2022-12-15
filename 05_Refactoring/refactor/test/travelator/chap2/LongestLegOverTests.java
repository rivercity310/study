package travelator.chap2;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
class LongestLegOverTests {
    private final Duration oneDay = Duration.ofDays(1);
    private final List<Leg_java> legs = List.of(
            leg("one hour", Duration.ofHours(1)),
            leg("one day", Duration.ofDays(1)),
            leg("two hours", Duration.ofHours(2))
    );

    @Test
    public void is_absent_when_no_legs() {
        assertEquals(
                Optional.empty(),
                Leg_java.findLongestLegOver(legs, oneDay)
        );
    }

    @Test
    public void is_longest_leg_when_one_match() {
        assertEquals(
                "one day",
                Leg_java.findLongestLegOver(legs, oneDay.minusMillis(1))
                        .orElseThrow().toString()
        );
    }
}
 */