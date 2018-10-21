package br.com.fdbst.tasklist.type;

/**
 * Enum StatusType
 *
 * Esse enum representa o status de uma Tarefa no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public enum StatusType {

    OPEN("OPEN"),
    ON_GOING("ON_GOING"),
    CONCLUDED("CONCLUDED"),
    REMOVED("REMOVED");

    private final String statusType;

    StatusType(String statusType) {
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        return this.statusType;
    }
}
