/**
 * Copyright 2017-2020 LinkedIn Corporation. All rights reserved.
 * Licensed under the BSD-2 Clause license.
 * See LICENSE in the project root for license information.
 */
package com.linkedin.coral.hive.hive2rel.parsetree.parser;

import java.util.ArrayList;


//CHECKSTYLE:OFF
public class ParseException extends Exception {

  private static final long serialVersionUID = 1L;
  ArrayList<ParseError> errors;

  public ParseException(ArrayList<ParseError> errors) {
    super();
    this.errors = errors;
  }

  @Override
  public String getMessage() {

    StringBuilder sb = new StringBuilder();
    for (ParseError err : errors) {
      if (sb.length() > 0) {
        sb.append('\n');
      }
      sb.append(err.getMessage());
    }

    return sb.toString();
  }
}
