package com.example.customcontentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ArticleMetaData {
	private ArticleMetaData() { }
	
	public static final String AUTHORITY = "customcontentprovider.Articles";
	public static final Uri CONTENT_URI = Uri.parse(
		"content://" + AUTHORITY + "/articles"
	);
	
	public static final String DATABASE_NAME = "articles.db";
	public static final int DATABSE_VERSION = 1;
	
	public static final String CONTENT_TYPE_ARTICLES_LIST = "vnd.android.cursor.dir/vnd.nimai.articles";
	public static final String CONTENT_TYPE_ARTICLE_ONE = "vnd.android.cursor.item/vnd.nimai.articles";
	
	public class ArticleTable implements BaseColumns {
		private ArticleTable() { }
		
		public static final String TABLE_NAME = "tbl_articles";
		public static final String ID = "_id";
		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		
	}
	
}
