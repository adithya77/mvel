/**
 * MVEL (The MVFLEX Expression Language)
 *
 * Copyright (C) 2007 Christopher Brock, MVFLEX/Valhalla Project and the Codehaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.mvel.optimizers.impl.refl;

import org.mvel.ast.Function;
import org.mvel.compiler.Accessor;
import org.mvel.integration.VariableResolverFactory;


public class FunctionAccessor extends BaseAccessor {
    private Function function;
    private Accessor[] parameters;

    public FunctionAccessor(Function function, Accessor[] parms) {
        this.function = function;
        this.parameters = parms;
    }

    public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
        Object[] parms = null;

        if (parameters != null && parameters.length != 0) {
            parms = new Object[parameters.length];
            for (int i = parms.length - 1; i != -1; i--) {
                parms[i] = parameters[i].getValue(ctx, elCtx, variableFactory);
            }
        }

        if (nextNode != null) {
            return nextNode.getValue(function.call(ctx, elCtx, variableFactory, parms), elCtx, variableFactory);
        }
        else {
            return function.call(ctx, elCtx, variableFactory, parms);
        }
    }

    public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
        throw new RuntimeException("can't write to function");
    }
}
