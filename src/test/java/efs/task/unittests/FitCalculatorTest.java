package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowAnException_whenIncorrectValue() {
        //given
        double weight = 60.9;
        double height = .0;

        //when and then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator
                .isBMICorrect(weight, height));

    }

    @ParameterizedTest(name = "Test with weight = {0}")
    @ValueSource(doubles = {76.234, 79.43, 80.0})
    void shouldReturnTrue_forDifferentWeights(double weight) {
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Test for weight = {0}, height = {1}")
    @CsvSource({"62.5, 1.90", "25.0, 1.80", "81.3, 1.84"})
    void shouldReturnFalse_whenIncorrectWeightHeightPairs(double weight, double height) {
        //given values as arguments

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Test for weight = {0}, height = {1} from CSV file.")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_forDataFromCSVFile(double weight, double height) {
        //given arguments

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnUser_whereWorstBMI() {
        //given list of users

        //when
        User result = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);
        boolean isWorst = (result.getHeight() == 1.79) && (result.getWeight() == 97.3);

        //then
        assertTrue(isWorst);
    }

    @Test
    void shouldReturnNull_whenUserListEmpty() {
        //given
        List<User> list = new ArrayList<>();

        //when
        User result = FitCalculator.findUserWithTheWorstBMI(list);
        //then
        assertNull(result);
    }

    @Test
    void shouldReturnUsersBMIScore_whenGivenUserList() {
        //given efs.task.unittests.TestConstants.TEST_USERS_LIST

        //when
        double [] bmiScores = FitCalculator
                .calculateBMIScore(TestConstants.TEST_USERS_LIST);

        //then
        assertArrayEquals(bmiScores, TestConstants.TEST_USERS_BMI_SCORE);
    }

}