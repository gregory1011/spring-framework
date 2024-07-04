import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class CalculatorParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
    void testCase1(String args) {
        Assertions.assertTrue(!args.isEmpty());
    }

    // mvn -Dtest=TestCircle#mytest test
    // maven-surefire-plugin in pom.xml help you run mvn comand like mvn Dtest ....
    // mvn clean test
    // to use mvn commands we need to Use @Tag("Test2") : mvn Dtest.tags.test2 ...

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 10})
    void testCase2(int number) {
        Assertions.assertEquals(0, number % 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
//    @EmptySource
//    @NullSource
    @NullAndEmptySource
    void testCase3(String args) {
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringProvider") // provide method name, execute each data parrticulary
    void testCase4(String arg) {
        Assertions.assertNotNull(arg);
    }

    static String[] stringProvider() {
        return new String[]{"Java", "JS", "TS"};
    }

    @ParameterizedTest
    @CsvSource({"10,20,30", "20,20,40", "30,20,50"})
    void testCase5(int num1, int num2, int result) {  // 30, 10+20 or 40, 20+20, or 30+20, 50
        Assertions.assertEquals(result, Calculator.add(num1, num2));
    }

    @ParameterizedTest //  numLinesToSkip = 1 - means we skip first row of the file table : num1, num2, result
    @CsvFileSource(resources = "/sample-data.csv", numLinesToSkip = 1) // use path file to read data
    void testCase6(int num1, int num2, int result) { // the file has 3 colomns and each int represents one colomn
        Assertions.assertEquals(result, Calculator.add(num1, num2)); // assert data with the Calculator Java class method.add
    }

}
