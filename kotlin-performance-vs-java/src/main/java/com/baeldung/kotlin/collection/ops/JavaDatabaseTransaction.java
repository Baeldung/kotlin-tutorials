package com.baeldung.kotlin.collection.ops;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.function.Function;

public class JavaDatabaseTransaction {
    enum TransactionStatus {RUNNING, COMMITTED, ROLLEDBACK}

    static class Transaction {
        private final UUID id;
        private TransactionStatus status;

        public Transaction(UUID id) {
            this.id = id;
            this.status = TransactionStatus.RUNNING;
        }

        public UUID getId() {
            return id;
        }

        public TransactionStatus getStatus() {
            return status;
        }

        public void commit() {
            status = TransactionStatus.COMMITTED;
        }

        public void rollback() {
            status = TransactionStatus.ROLLEDBACK;
        }

    }

    static class Database {
        private final URL url;
        private final String credentials;

        Database(URL url, String credentials) {
            this.url = url;
            this.credentials = credentials;
        }

        public URL getUrl() {
            return url;
        }

        public String getCredentials() {
            return credentials;
        }
    }

public static <T> T inTransaction(JavaDatabaseTransaction.Database database, Function<JavaDatabaseTransaction.Database, T> action) {
    var transaction = new JavaDatabaseTransaction.Transaction(UUID.randomUUID());
    try {
        var result = action.apply(database);
        transaction.commit();
        return result;
    } catch (Exception ex) {
        transaction.rollback();
        throw ex;
    }
}

public static String transactedAction(Object obj) throws MalformedURLException {
    var database = new JavaDatabaseTransaction.Database(new URL("http://localhost"), "user:pass");
    return inTransaction(database, d -> UUID.randomUUID() + obj.toString());
}
}
