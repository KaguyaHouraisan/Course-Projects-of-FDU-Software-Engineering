class Column {
    private String label;
    private String type;

    Column(String label, String type) {
        this.label = label;
        this.type = type;
    }

    String getLabel() {
        return label;
    }

    String getType() {
        return type;
    }
}
