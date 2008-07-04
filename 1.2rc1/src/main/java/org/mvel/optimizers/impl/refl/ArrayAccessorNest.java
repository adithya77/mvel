package org.mvel.optimizers.impl.refl;

import org.mvel.AccessorNode;
import org.mvel.ExecutableStatement;
import org.mvel.integration.VariableResolverFactory;

public class ArrayAccessorNest implements AccessorNode {
    private AccessorNode nextNode;
    private ExecutableStatement index;

    public Object getValue(Object ctx, Object elCtx, VariableResolverFactory vars) {
        if (nextNode != null) {
            return nextNode.getValue(((Object[]) ctx)[(Integer) index.getValue(ctx, elCtx, vars)], elCtx, vars);
        }
        else {
            return ((Object[]) ctx)[(Integer) index.getValue(ctx, elCtx, vars)];
        }
    }


    public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
       return ((Object[]) ctx)[(Integer) index.getValue(ctx, elCtx, variableFactory)] = value; 
    }

    public ExecutableStatement getIndex() {
        return index;
    }

    public void setIndex(ExecutableStatement index) {
        this.index = index;
    }

    public AccessorNode getNextNode() {
        return nextNode;
    }

    public AccessorNode setNextNode(AccessorNode nextNode) {
        return this.nextNode = nextNode;
    }


    public String toString() {
        return "Array Accessor -> [" + index + "]";
    }
}