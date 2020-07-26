package by.artempvn.les08.controller.command;

import by.artempvn.les08.controller.command.impl.AddBookCommand;
import by.artempvn.les08.controller.command.impl.FindByTagCommand;
import by.artempvn.les08.controller.command.impl.RemoveBookCommand;
import by.artempvn.les08.controller.command.impl.TakeAllBooksCommand;
import by.artempvn.les08.controller.command.impl.UpdateBookCommand;

public enum CommandType {

	ADD_BOOK {
		{
			this.command = new AddBookCommand();
		}
	},
	REMOVE_BOOK {
		{
			this.command = new RemoveBookCommand();
		}
	},
	UPDATE_BOOK {
		{
			this.command = new UpdateBookCommand();
		}
	},
	TAKE_ALL_BOOKS {
		{
			this.command = new TakeAllBooksCommand();
		}
	},
	FIND_BY_TAG {
		{
			this.command = new FindByTagCommand();
		}
	};

	protected Command command;

	public Command getCommand() {
		return command;
	}
}
