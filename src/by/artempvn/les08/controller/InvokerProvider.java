package by.artempvn.les08.controller;

import by.artempvn.les08.controller.command.Command;
import by.artempvn.les08.controller.command.CommandType;
import by.artempvn.les08.exception.ControllerException;

public class InvokerProvider {

	public static Command defineCommand(String command)
			throws ControllerException {
		Command current = null;
		if (command == null) {
			throw new ControllerException("Null command input");
		}
		boolean isCorrect = false;
		for (CommandType type : CommandType.values()) {
			if (type.name().equalsIgnoreCase(command)) {
				isCorrect = true;
			}
		}
		CommandType currentType = null;
		if (isCorrect) {
			currentType = CommandType.valueOf(command.toUpperCase());
		} else {
			throw new ControllerException("Invalid command input");
		}
		current = currentType.getCommand();
		return current;
	}
}
