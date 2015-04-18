package com.prototipo01.util;

public class Verificador {
	
	public static boolean isValorado(final String arg)
	{
		boolean ret = true;
		if (arg == null || arg.trim().isEmpty())
		{
			ret = false;
		}
		return ret;
	}

}
