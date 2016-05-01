package org.pantsbuild.tools.junit.impl.experimental;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.pantsbuild.junit.annotations.TestParallel;
import org.pantsbuild.junit.annotations.TestParallelBoth;
import org.pantsbuild.junit.annotations.TestSerial;
import org.pantsbuild.tools.junit.impl.Concurrency;
import org.pantsbuild.junit.annotations.TestParallelMethods;

public class TestSpec {
  private Class<?> clazz;
  private Set<String> methods;

  public TestSpec(Class<?> clazz) {
    Preconditions.checkNotNull(clazz);
    this.clazz = clazz;
    this.methods = new HashSet<String>();
  }

  public String getSpecName() {
    return this.clazz.getName();
  }

  public Class<?> getSpecClass() {
    return this.clazz;
  }

  public void addMethod(String method)  throws TestSpecException {
    Preconditions.checkNotNull(method);
    methods.add(method);
  }

  /**
   * @return either the Concurrency value specified by the class annotation or the default concurrency
   * passed in the parameter.
   */
  public Concurrency getConcurrency(Concurrency defaultConcurrency) {
    if (clazz.isAnnotationPresent(TestSerial.class)) {
      return Concurrency.SERIAL;
    } else if (clazz.isAnnotationPresent(TestParallel.class)) {
      return Concurrency.PARALLEL_CLASSES;
    } else if (clazz.isAnnotationPresent(TestParallelMethods.class)) {
      return Concurrency.PARALLEL_METHODS;
    } else if (clazz.isAnnotationPresent(TestParallelBoth.class)) {
      return Concurrency.PARALLEL_BOTH;
    }
    return defaultConcurrency;
  }

  public Collection<String> getMethods() {
    return ImmutableList.copyOf(methods);
  }
}
