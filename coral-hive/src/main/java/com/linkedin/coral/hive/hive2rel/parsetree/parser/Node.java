/**
 * Copyright 2017-2020 LinkedIn Corporation. All rights reserved.
 * Licensed under the BSD-2 Clause license.
 * See LICENSE in the project root for license information.
 */
package com.linkedin.coral.hive.hive2rel.parsetree.parser;

import java.util.List;


/**
 * This interface defines the functions needed by the walkers and dispatchers.
 * These are implemented by the node of the graph that needs to be walked.
 */
//CHECKSTYLE:OFF
public interface Node {

  /**
   * Gets the vector of children nodes. This is used in the graph walker
   * algorithms.
   *
   * @return List&lt;? extends Node&gt;
   */
  List<? extends Node> getChildren();

  /**
   * Gets the name of the node. This is used in the rule dispatchers.
   *
   * @return String
   */
  String getName();
}
