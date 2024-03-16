package karate;

import com.devsu.e2e.karate.Karate;

class AllTests {

  @Karate.Test
  Karate testAll() {
    return Karate.run().relativeTo(getClass());
  }
}
