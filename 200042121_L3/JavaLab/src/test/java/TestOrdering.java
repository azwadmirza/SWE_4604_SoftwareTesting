

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)

public class TestOrdering {
    @Test
    @Order(1)
    public void Testcase_3() {
        System.out.println("Testcase_3 executes");
    }
    @Test
    @Order(2)
    public void Testcase_1() {
        System.out.println("Testcase_1 executes");
    }
    @Test
    @Order(3)
    public void Testcase_2() {
        System.out.println("Testcase_2 executes ");
    }
}