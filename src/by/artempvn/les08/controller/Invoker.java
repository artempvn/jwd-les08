package by.artempvn.les08.controller;

import java.util.Map;
import java.util.Optional;
import by.artempvn.les08.controller.command.Command;
import by.artempvn.les08.exception.ControllerException;
import by.artempvn.les08.validator.InputDataValidator;

public class Invoker {

	private static Invoker invoker;

	private Invoker() {
	}

	public static Invoker getInstance() {
		if (invoker == null) {
			invoker = new Invoker();
		}
		return invoker;
	}

	public Optional<Map<String, Object>> processRequest(String command,
			Map<String, String> params) throws ControllerException {
		InputDataValidator dataValidator = new InputDataValidator();
		Optional<Map<String, Object>> output = Optional.empty();
		Command userCommand = InvokerProvider.defineCommand(command);
		if (params != null && dataValidator.isNotNullMapValue(params)) {
			output = Optional.of(userCommand.execute(params));
		}
		return output;
	}
}
