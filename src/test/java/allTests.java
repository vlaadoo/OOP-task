
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseTest.class,
        BishopTest.class,
        BoardTest.class,
        KingTest.class,
        KnightTest.class,
        PawnTest.class,
        QueenTest.class,
        RookTest.class,
        ModelTest.class,
})

public class allTests {

}
