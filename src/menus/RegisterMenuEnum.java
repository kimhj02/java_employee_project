package menus;

public enum RegisterMenuEnum 
{
    REGISTER_FULL_TIME(11, "정규직 등록"),
    REGISTER_PART_TIME(12, "임시직 등록"),
    REGISTER_CANCEL(19, "돌아가기");

    int code;
    String message;

    RegisterMenuEnum(int code, String message) 
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
