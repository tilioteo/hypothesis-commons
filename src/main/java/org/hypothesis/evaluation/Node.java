package org.hypothesis.evaluation;

import org.hypothesis.interfaces.evaluable.Evaluable;
import org.hypothesis.interfaces.variable.Variable;
import org.hypothesis.interfaces.variable.WithVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node extends AbstractVariableContainer implements Serializable {

    private final long slideId;

    private final List<Evaluable> evaluables = new ArrayList<>();

    private int nextIndex = -1;
    private boolean breakExecution = false;

    public Node(WithVariables variables, long slideId) {
        super(variables);
        this.slideId = slideId;
    }

    public long getSlideId() {
        return slideId;
    }

    public void add(Evaluable evaluable) {
        evaluables.add(evaluable);
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;

        if (nextIndex > 0) {
            breakExecution = true;
        }
    }

    public void execute() {
        breakExecution = false;
        for (Evaluable evaluable : evaluables) {
            final Map<String, Variable<?>> variables = getVariables();

            evaluable.setVariables(variables);
            evaluable.evaluate();
            evaluable.updateVariables(variables);

            if (breakExecution) {
                break;
            }
        }
    }

}
