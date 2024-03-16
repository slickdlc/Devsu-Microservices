package com.devsu.e2e.karate;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.intuit.karate.Suite;
import com.intuit.karate.core.FeatureCall;
import com.intuit.karate.core.FeatureResult;
import com.intuit.karate.core.FeatureRuntime;
import com.intuit.karate.core.ScenarioIterator;
import com.intuit.karate.core.ScenarioRuntime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

/**
 * @author pthomas3
 */
public class FeatureNode implements Iterator<DynamicTest>, Iterable<DynamicTest> {

  public final List<CompletableFuture> futures;

  public final Suite suite;

  public final FeatureRuntime featureRuntime;

  private final Iterator<ScenarioRuntime> scenarios;

  public FeatureNode(Suite suite, List<CompletableFuture> futures, FeatureCall featureCall, String tagSelector) {
    this.suite = suite;
    this.futures = futures;
    featureRuntime = FeatureRuntime.of(suite, featureCall);
    CompletableFuture future = new CompletableFuture();
    futures.add(future);
    featureRuntime.setNext(() -> future.complete(Boolean.TRUE));
    scenarios = new ScenarioIterator(featureRuntime).filterSelected().iterator();
  }

  @Override
  public boolean hasNext() {
    return scenarios.hasNext();
  }

  @Override
  public DynamicTest next() {
    ScenarioRuntime runtime = scenarios.next();
    return DynamicTest.dynamicTest(runtime.scenario.getRefIdAndName(), runtime.scenario.getUriToLineNumber(), () -> {
      if (featureRuntime.beforeHook()) { // minimal code duplication from feature-runtime
        runtime.run();
        featureRuntime.result.addResult(runtime.result);
      } else {
        runtime.logger.info("before-feature hook returned [false], aborting: ", featureRuntime);
      }
      boolean failed = runtime.result.isFailed();
      if (!scenarios.hasNext()) {
        featureRuntime.afterFeature();
        FeatureResult result = featureRuntime.result;
        if (!result.isEmpty()) {
          suite.saveFeatureResults(result);
        }
        saveSummaryIfAllComplete();
      }
      if (failed) {
        Assertions.fail(runtime.result.getError().getMessage());
      }
    });
  }

  @Override
  public Iterator<DynamicTest> iterator() {
    return this;
  }

  private void saveSummaryIfAllComplete() {
    for (CompletableFuture cf : futures) {
      if (!cf.isDone()) {
        return;
      }
    }
    suite.buildResults();
  }

}