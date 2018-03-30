package pl.sternik.dp.entities;


public enum Status {
    
    NEW("NEW"),
    OLD("OLD"),
    FOR_SALE("FOR_SALE"),
    DUBLET("Dublet");
    
    
    public static final Status[] ALL = { NEW, FOR_SALE, DUBLET };
    
    
    private final String name;

    private Status(final String name) {
    	this.name = name;
    }

    
    public String getName() {
        return this.name;
    }
}
