package org.axonframework.extensions.reactor.eventstore.utils;

/**
 * @author vtiwar27
 * @date 2020-11-05
 */
/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * A tuple that holds two non-null values.
 *
 * @param <T1> The type of the first non-null value held by this tuple
 * @param <T2> The type of the second non-null value held by this tuple
 * @author Jon Brisbin
 * @author Stephane Maldini
 */
@SuppressWarnings("rawtypes")
public class Tuple2<T1, T2> implements Iterable<Object>, Serializable {

    private static final long serialVersionUID = -3518082018884860684L;

    @NonNull
    final T1 t1;
    @NonNull
    final T2 t2;

    public Tuple2(T1 t1, T2 t2) {
        this.t1 = Objects.requireNonNull(t1, "t1");
        this.t2 = Objects.requireNonNull(t2, "t2");
    }


    public T1 getT1() {
        return t1;
    }


    public T2 getT2() {
        return t2;
    }

    /**
     * Map the left-hand part (T1) of this {@link Tuple2} into a different value and type,
     * keeping the right-hand part (T2).
     *
     * @param mapper the mapping {@link Function} for the left-hand part
     * @param <R>    the new type for the left-hand part
     * @return a new {@link Tuple2} with a different left (T1) value
     */
    public <R> Tuple2<R, T2> mapT1(Function<T1, R> mapper) {
        return new Tuple2<>(mapper.apply(t1), t2);
    }

    /**
     * Map the right-hand part (T2) of this {@link Tuple2} into a different value and type,
     * keeping the left-hand part (T1).
     *
     * @param mapper the mapping {@link Function} for the right-hand part
     * @param <R>    the new type for the right-hand part
     * @return a new {@link Tuple2} with a different right (T2) value
     */
    public <R> Tuple2<T1, R> mapT2(Function<T2, R> mapper) {
        return new Tuple2<>(t1, mapper.apply(t2));
    }

    /**
     * Get the object at the given index.
     *
     * @param index The index of the object to retrieve. Starts at 0.
     * @return The object or {@literal null} if out of bounds.
     */
    @Nullable
    public Object get(int index) {
        switch (index) {
            case 0:
                return t1;
            case 1:
                return t2;
            default:
                return null;
        }
    }

    /**
     * Turn this {@code Tuple} into a {@link List List&lt;Object&gt;}.
     * The list isn't tied to this Tuple but is a <strong>copy</strong> with limited
     * mutability ({@code add} and {@code remove} are not supported, but {@code set} is).
     *
     * @return A copy of the tuple as a new {@link List List&lt;Object&gt;}.
     */
    public List<Object> toList() {
        return Arrays.asList(toArray());
    }

    /**
     * Turn this {@code Tuple} into a plain {@code Object[]}.
     * The array isn't tied to this Tuple but is a <strong>copy</strong>.
     *
     * @return A copy of the tuple as a new {@link Object Object[]}.
     */
    public Object[] toArray() {
        return new Object[]{t1, t2};
    }


    @Override
    public Iterator<Object> iterator() {
        return Collections.unmodifiableList(toList()).iterator();
    }


    @Override
    public int hashCode() {
        int result = size();
        result = 31 * result + t1.hashCode();
        result = 31 * result + t2.hashCode();
        return result;
    }

    /**
     * Return the number of elements in this {@literal Tuples}.
     *
     * @return The size of this {@literal Tuples}.
     */
    public int size() {
        return 2;
    }

}

