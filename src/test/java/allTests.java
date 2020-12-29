
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        dbTest.class,
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
