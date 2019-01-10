package com.sbolo.syk.common.vo;

public class LinkAnalyzeResultVO {
	private String movieSize;
	private String movieFormat;
	private String downloadLink;
	private byte[] torrentBytes;
	
	public byte[] getTorrentBytes() {
		return torrentBytes;
	}
	public void setTorrentBytes(byte[] torrentBytes) {
		this.torrentBytes = torrentBytes;
	}
	public String getMovieSize() {
		return movieSize;
	}
	public void setMovieSize(String movieSize) {
		this.movieSize = movieSize;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public String getMovieFormat() {
		return movieFormat;
	}
	public void setMovieFormat(String movieFormat) {
		this.movieFormat = movieFormat;
	}
}
