package org.hypothesis.evaluation;

import org.hypothesis.interfaces.action.Action;
import org.hypothesis.interfaces.command.Command;
import org.hypothesis.interfaces.variable.WithVariables;

import javax.annotation.Nonnull;

public abstract class AbstractBaseAction extends AbstractVariableContainer implements Action {

    private final String id;
    private Command executeCommand = null;

    protected AbstractBaseAction(WithVariables variables, String id) {
        super(variables);
        this.id = id;
    }

    public void setExecuteCommand(Command command) {
        this.executeCommand = command;
    }

    @Override
    public void execute() {
        Command.Executor.execute(executeCommand);
    }

    @Nonnull
    public String getId() {
        return id;
    }

}
