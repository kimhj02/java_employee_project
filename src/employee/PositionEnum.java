package employee;
//직위 enum

public enum PositionEnum 
{
    DIRECTOR(21, "이사"),
    GENERAL_MANAGER(22, "부장"),
    MANAGER(23, "과장"),
    ASSISTANT_MANAGER(24, "대리"),
    STAFF(25, "사원");

    private final int code;
    private final String message;

    PositionEnum(int code, String message) 
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
