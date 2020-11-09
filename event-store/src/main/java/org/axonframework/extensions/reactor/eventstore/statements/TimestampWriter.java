/*
 * Copyright (c) 2010-2020. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.extensions.reactor.eventstore.statements;


import io.r2dbc.spi.Statement;

import java.time.Instant;

/**
 * Writer interface for writing a formatted timestamp to a {@link Statement} during updates of the database.
 * Used in {@link AppendEventsStatementBuilder} and {@link AppendSnapshotStatementBuilder}.
 *
 * @author Trond Marius Øvstetun
 * @since 4.4
 */
@FunctionalInterface
public interface TimestampWriter {

    /**
     * Write a timestamp from a {@link Instant} to a data value suitable for the database scheme.
     *
     * @param preparedStatement the statement to update
     * @param position          the position of the timestamp parameter in the statement
     * @param timestamp         {@link Instant} to convert
     */
    void writeTimestamp(Statement preparedStatement, int position, Instant timestamp);
}