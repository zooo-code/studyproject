package study.project.domain.item.category;



public enum CategoryName {

    APPLIANCES("생활가전"),
    ETC("기타");

    private final String KrName;

    CategoryName(String krName) {
        KrName = krName;
    }
    public String getKrName() {
        return KrName;
    }


}
