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

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.Statement;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema;
import org.axonframework.extensions.reactor.eventstore.impl.R2dbcEventStoreEngine;
import org.axonframework.serialization.Serializer;

import java.util.List;

/**
 * Contract which defines how to build a PreparedStatement for use on {@link R2dbcEventStoreEngine#appendEvents(List,
 * Serializer)}
 *
 * @author Lucas Campos
 * @since 4.3
 */
@FunctionalInterface
public interface AppendEventsStatementBuilder {

    /**
     * Build a statement to be used at {@link R2dbcEventStoreEngine#appendEvents(List, Serializer)}
     *
     * @param connection      The connection to the database.
     * @param schema          The EventSchema to be used.
     * @param dataType        The serialized type of the payload and metadata.
     * @param events          The events to be added.
     * @param serializer      The serializer for the payload and metadata.
     * @param timestampWriter Writer responsible for writing timestamp in the correct format for the given database.
     * @return the newly created {@link Statement}.
     */
    Statement build(Connection connection,
                    EventSchema schema,
                    Class<?> dataType,
                    List<? extends EventMessage<?>> events,
                    Serializer serializer);
}
