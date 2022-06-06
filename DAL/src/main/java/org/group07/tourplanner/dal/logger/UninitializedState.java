package org.group07.tourplanner.dal.logger;

public class UninitializedState extends LoggerStateBase{

    @Override
    public void debug(String message) {
        this.printUninitializedWarning();
    }

    @Override
    public void fatal(String message) {
        this.printUninitializedWarning();
    }

    @Override
    public void error(String message) {
        this.printUninitializedWarning();
    }

    @Override
    public void warn(String message) {
        this.printUninitializedWarning();
    }

    private void printUninitializedWarning() {
        System.out.println("Operation was called in state uninitialized.");
    }
}
