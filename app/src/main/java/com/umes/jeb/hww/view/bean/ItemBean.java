package com.umes.jeb.hww.view.bean;

public class ItemBean {

	private int icon;
	private String title;
	private String detail;
	private boolean stateIcon;
    private String key;

	public ItemBean() {
		super();
	}

	public ItemBean(int icon, String title, String detail, boolean statusIcon, String key) {
		super();
		this.icon = icon;
		this.title = title;
		this.detail = detail;
		this.stateIcon = statusIcon;
        this.key = key;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean getStateIcon() {
		return stateIcon;
	}

	public void setStateIcon(boolean stateIcon) {
		this.stateIcon = stateIcon;
	}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
