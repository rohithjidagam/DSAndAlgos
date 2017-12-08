package com.microsoft;

public class ValidIpv4Ipv6 {

	public static void main(String[] args) {

		System.out.println(validate("172.16.254.1"));
		System.out.println(validate("172.01.254.1"));
		System.out.println(validate("172.0.254.1"));
	}

	private static String validate(String s) {

		if (s == null || s.length() == 0)
			return "Neither";

		String[] split = s.split("\\.");
		
		if (split.length != 4) {
			return "Neither";
		}

		for (String str : split) {

			if (str.charAt(0) - '0' == 0 && str.length() > 1)
				return "Neither";
			
			if (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 255)
				return "Neither";
		}

		return "IPV4";
	}

}
