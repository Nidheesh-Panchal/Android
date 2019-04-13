package com.example.android;

public class instantmessage
{
	private String message;
	private String author;

	instantmessage(String message, String author) {
		this.message = message;
		this.author = author;
	}

	public instantmessage() {
	}

	String getMessage()
	{
		return message;
	}

	String getAuthor()
	{
		return author;
	}
}
