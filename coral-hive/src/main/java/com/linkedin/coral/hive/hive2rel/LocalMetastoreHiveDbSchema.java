/**
 * Copyright 2017-2020 LinkedIn Corporation. All rights reserved.
 * Licensed under the BSD-2 Clause license.
 * See LICENSE in the project root for license information.
 */
package com.linkedin.coral.hive.hive2rel;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.Function;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.SchemaVersion;
import org.apache.calcite.schema.Table;

import static com.google.common.base.Preconditions.*;


/**
 * This class is a replacement for {@link HiveDbSchema} to work with localMetastore in coral-spark-plan module
 *
 * Adaptor from Hive catalog providing database and table names
 * to Calcite {@link Schema}
 */
public class LocalMetastoreHiveDbSchema implements Schema {

  private final Map<String, Map<String, List<String>>> localMetastore;
  private final String dbName;

  LocalMetastoreHiveDbSchema(Map<String, Map<String, List<String>>> localMetastore, String dbName) {
    checkNotNull(localMetastore);
    checkNotNull(dbName);
    this.localMetastore = localMetastore;
    this.dbName = dbName;
  }

  @Override
  public Table getTable(String tableName) {
    if (localMetastore.containsKey(dbName) && localMetastore.get(dbName).containsKey(tableName)) {
      return new LocalMetastoreHiveTable(tableName, localMetastore.get(dbName).get(tableName));
    }
    return null;
  }

  @Override
  public Set<String> getTableNames() {
    return ImmutableSet.copyOf(localMetastore.get(dbName).keySet());
  }

  @Override
  public RelProtoDataType getType(String s) {
    return null;
  }

  @Override
  public Set<String> getTypeNames() {
    return null;
  }

  @Override
  public Collection<Function> getFunctions(String name) {
    return ImmutableList.of();
  }

  @Override
  public Set<String> getFunctionNames() {
    return ImmutableSet.of();
  }

  /**
   * A Hive DB does not have subschema
   * @param name name of the schema
   * @return Calcite schema
   */
  @Override
  public Schema getSubSchema(String name) {
    return null;
  }

  @Override
  public Set<String> getSubSchemaNames() {
    return ImmutableSet.of();
  }

  @Override
  public Expression getExpression(SchemaPlus parentSchema, String name) {
    return null;
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  // TODO: This needs to be snapshot of current state of catalog
  @Override
  public Schema snapshot(SchemaVersion schemaVersion) {
    return this;
  }
}
