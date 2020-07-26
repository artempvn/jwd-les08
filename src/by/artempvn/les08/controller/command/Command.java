package by.artempvn.les08.controller.command;

import java.util.Map;
import by.artempvn.les08.exception.ControllerException;

public interface Command {

	Map<String, Object> execute(Map<String, String> params)
			throws ControllerException;
}
