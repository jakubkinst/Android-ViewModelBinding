package cz.kinst.jakub.sample.viewmodelbinding;

public class ItemEntity
{
	private String title, subtitle;


	public ItemEntity(String title, String subtitle)
	{
		this.title = title;
		this.subtitle = subtitle;
	}


	public String getTitle()
	{
		return title;
	}


	public String getSubtitle()
	{
		return subtitle;
	}
}
