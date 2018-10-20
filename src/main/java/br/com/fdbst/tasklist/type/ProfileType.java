package br.com.fdbst.tasklist.type;

/**
 * Enum ProfileType
 *
 * Esse enum representa o status de uma Tarefa no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public enum ProfileType {

    ADMIN("ADMIN");

    private final String profileType;

    ProfileType(String profileType) {
        this.profileType = profileType;
    }

    @Override
    public String toString() {
        return this.profileType;
    }
}
