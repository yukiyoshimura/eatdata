package resource;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor.Builder;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class HelloResourceTest extends JerseyTest {

    @Test
    public void test_say() throws Exception {
        String response = resource()
                .path("hello")
                .queryParam("name", "world")
                .get(String.class);
        assertThat(response, is("Hello, world!"));
    }

    @Override
    protected AppDescriptor configure() {
        return new Builder(HelloResource.class).build();
    }
}