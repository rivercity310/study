package travelator.chap2;

// 1. Optional -> Nullable 리팩터링

import java.time.Duration;
import java.util.List;
import java.util.Optional;

/*
public class Leg_java {
    public static Optional<Leg_java> findLongestLegOver(List<Leg_java> legs, Duration duration) {
        Leg_java result = null;
        for (Leg_java leg : legs)
            if (isLongerThan(leg, duration))
                if (result == null || isLongerThan(leg, result.getPlannedDuration()))
                    result = leg;

        return Optional.ofNullable(result);
    }

    private static boolean isLongerThan(Leg_java leg, Duration duration) {
        return leg.getPlannedDuration().compareTo(duration) > 0;
    }
}*/
