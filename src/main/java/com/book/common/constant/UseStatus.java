package com.book.common.constant;

public enum UseStatus
{
    Y,
    N;

    public static UseStatus getUseStatus(String status)
    {
        if (status.equalsIgnoreCase("Y"))
        {
            return Y;
        }
        else if (status.equalsIgnoreCase("N"))
        {
            return N;
        }
        return null;
    }
}
