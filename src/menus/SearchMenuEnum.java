package menus;

public enum SearchMenuEnum 
{
    SEARCH_ALL(21, "일괄 검색"),
    SEARCH_FULLTIME(22,"정규직 일괄 검색"),
    SEARCH_PARTIME(23,"임시직 일괄 검색"),
    SEARCH_NAME(24,"이름 검색"),
    SEARCH_CANCEL(29, "돌아가기");

    int code;
    String message;

    SearchMenuEnum(int code, String message) 
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() 
    {
        return code;
    }

    public String getMessage() 
    {
        return message;
    }
}
