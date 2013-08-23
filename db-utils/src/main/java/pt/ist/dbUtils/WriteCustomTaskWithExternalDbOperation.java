package pt.ist.dbUtils;

import java.sql.SQLException;

import pt.ist.bennu.scheduler.custom.CustomTask;

public abstract class WriteCustomTaskWithExternalDbOperation extends CustomTask {

    private class EmbededExternalDbOperation extends ExternalDbOperation {

        private final WriteCustomTaskWithExternalDbOperation instance;

        public EmbededExternalDbOperation(final WriteCustomTaskWithExternalDbOperation instance) {
            this.instance = instance;
        }

        @Override
        protected void doOperation() throws SQLException {
            instance.doOperation();
        }

        @Override
        protected String getDbPropertyPrefix() {
            return instance.getDbPropertyPrefix();
        }

    }

    @Override
    public void runTask() {
        final EmbededExternalDbOperation embededExternalDbOperation = new EmbededExternalDbOperation(this);
        embededExternalDbOperation.execute();
    }

    protected abstract String getDbPropertyPrefix();

    protected abstract void doOperation() throws SQLException;

}
