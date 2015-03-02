// Copyright 2014 Pants project contributors (see CONTRIBUTORS.md).
// Licensed under the Apache License, Version 2.0 (see LICENSE).

package com.pants.examples.rb1761;

import java.io.IOException;

public class UndeclaredDepMain {
  public static void main(String[] args) throws IOException {
    UndeclaredDepLib.foo()
    DeclaredDepLib.foo()
    System.out.println("Hello World! from rb1761");
  }
}
