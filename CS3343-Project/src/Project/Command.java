package Project;

import Exceptions.ExInvalidInput;
import Exceptions.ExInvalidServiceType;

public interface Command {
	public void execute(String[] cmdParts) throws ExInvalidServiceType, ExInvalidInput;
}