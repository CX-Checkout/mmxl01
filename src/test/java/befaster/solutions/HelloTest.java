package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class HelloTest {

  @Test
  public void hello_returns_message_with_friend_name() {
    assertThat(Hello.hello("John"), equalTo("Hello, John!"));
  }

  @Test
  public void hello_returns_message_with_null_input() {
    assertThat(Hello.hello(null), equalTo("Hello, null!"));
  }
}