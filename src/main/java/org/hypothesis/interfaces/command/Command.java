package org.hypothesis.interfaces.command;

@FunctionalInterface
public interface Command {

	final class Executor {

		public static void execute(Command command) {
			if (command != null) {
				command.execute();
			}
		}
	}

	/**
	 * Causes the Command to perform its encapsulated behavior.
	 */
	void execute();

	/**
	 * Executes commands in chain.
	 * @param after command to execute after
	 * @return composed command
	 */
	default Command andThen(Command after) {
		return () -> {
			execute();
			Executor.execute(after);
		};
	}
}
