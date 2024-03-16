package com.devsu.e2e.karate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.intuit.karate.Runner;
import com.intuit.karate.Suite;
import com.intuit.karate.core.FeatureCall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

public class Karate extends Runner.Builder<Karate> implements Iterable<DynamicNode> {

  // short cut for new Karate().path()
  public static Karate run(final String... paths) {
    return new Karate().path(paths);
  }

  @Override
  public Iterator<DynamicNode> iterator() {
    Suite suite = new Suite(this);
    List<DynamicNode> list = new ArrayList();
    List<CompletableFuture> futures = new ArrayList();
    for (FeatureCall featureCall : suite.features) {
      FeatureNode featureNode = new FeatureNode(suite, futures, featureCall, suite.tagSelector);
      if (!featureNode.hasNext()) // if no scenarios to execute, just skip the feature
      {
        continue;
      }
      String testName = featureCall.feature.getResource().getFileNameWithoutExtension();
      DynamicNode node = DynamicContainer.dynamicContainer(testName, featureNode);
      list.add(node);
    }
    if (list.isEmpty()) {
      Assertions.fail("no features or scenarios found: " + this);
    }
    return list.iterator();
  }

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @TestFactory
  public @interface Test {

  }

}