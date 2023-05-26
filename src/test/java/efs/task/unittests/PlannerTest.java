package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
public class PlannerTest {
    private Planner planner;

    @ParameterizedTest(name = "Activity level = {0}")
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectDailyCalories_whenActivitySpecified(ActivityLevel activityLevel) {
        //given TestConstants.TEST_USER
        planner = new Planner();
        User user = TestConstants.TEST_USER;

        //when
        int res = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel), res);
    }
    @Test
    void shouldReturnCorrectDailyIntake_whenGivenUser() {
        //given
        User user = TestConstants.TEST_USER;
        planner = new Planner();
        DailyIntake given = TestConstants.TEST_USER_DAILY_INTAKE;
        Function<DailyIntake, int[]> getterFunc = obj -> new int[]{
                obj.getCalories(),
                obj.getCarbohydrate(),
                obj.getFat(),
                obj.getProtein()
        };

        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        //then
        assertArrayEquals(getterFunc.apply(dailyIntake), getterFunc.apply(given));
    }
}
