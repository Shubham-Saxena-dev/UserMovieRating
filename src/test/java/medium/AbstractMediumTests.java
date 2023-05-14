package medium;

import com.check24.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractMediumTests {

    public final Logger LOG = LoggerFactory.getLogger(AbstractMediumTests.class);
    public final String HEADER_NAME = "check24-Auth";
    public final String HEADER_VALUE = "check24_secret";

    @Before
    public void setupClass() {
        LOG.info("executing integration tests");
    }
}
