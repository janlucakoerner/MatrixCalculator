import backend.ParserBigDecimalTest;
import backend.ParserFractionTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.*;

@Suite
@SelectClasses({
        ParserBigDecimalTest.class, ParserFractionTest.class
})
public class MainTest extends Main {

}