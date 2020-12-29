/**
 * Copyright 2019-2020 LinkedIn Corporation. All rights reserved.
 * Licensed under the BSD-2 Clause license.
 * See LICENSE in the project root for license information.
 */
package com.linkedin.coral.pig.rel2pig.rel.operators;

import com.linkedin.coral.hive.hive2rel.functions.UnknownSqlFunctionException;
import com.linkedin.coral.pig.rel2pig.rel.PigRexUtils;
import java.util.List;
import org.apache.calcite.rex.RexCall;


/**
 * PigPrefixOperator translates SqlPrefixOperators to Pig Latin.
 */
public class PigPrefixOperator extends PigOperator {

  public PigPrefixOperator(RexCall rexCall, List<String> inputFieldNames) {
    super(rexCall, inputFieldNames);
  }

  @Override
  public String unparse() {
    // TODO(ralam): Do not generalize operand calls; we are likely to have special cases
    final String operand = PigRexUtils.convertRexNodeToPigExpression(rexCall.getOperands().get(0), inputFieldNames);
    switch (rexCall.getOperator().getKind()) {
      case NOT:
        return String.format("%s %s", rexCall.getOperator().getName(), operand);
      default:
        throw new UnknownSqlFunctionException(rexCall.getOperator().getName() + "_pig");
    }
  }
}
