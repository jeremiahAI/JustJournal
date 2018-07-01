package com.example.jeremiahvaris.justjournal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "entries_table")
public class JournalEntry implements Serializable {

    @ColumnInfo(name = "Title")
    private String mTitle;
    @ColumnInfo(name = "Content")
    private String mContent;
    @PrimaryKey
    @ColumnInfo(name = "Date Created")
    private long mDateCreated;
    @ColumnInfo(name = "Date Modified")
    private long mDateLastModified;


    // Todo: Delete dummy text
    public static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque iaculis erat nulla, eu lobortis enim euismod nec. Maecenas interdum efficitur ipsum ut pretium. Aliquam dictum nulla in nibh luctus, eu condimentum erat sollicitudin. Ut id augue volutpat, suscipit nisi id, bibendum nunc. Sed dignissim feugiat quam, vitae fringilla nisi rutrum quis. Ut porta ligula nec blandit luctus. Duis hendrerit blandit orci. Cras tristique ligula eu ligula molestie faucibus. Suspendisse scelerisque ultrices sem, vitae lacinia nibh suscipit at. Sed nibh massa, gravida non sollicitudin eget, condimentum a odio. Quisque lorem turpis, viverra at justo ac, congue rutrum tellus. Pellentesque purus turpis, iaculis eget nulla ut, convallis iaculis magna. Proin sit amet diam a turpis iaculis cursus.\n" +
            "\n" +
            "In sollicitudin justo nec nisi vehicula rutrum. Vestibulum dapibus sodales rhoncus. Suspendisse semper mauris vitae sem molestie vulputate. Proin ligula leo, volutpat eget nulla vitae, ullamcorper mattis mauris. Sed volutpat eu turpis a blandit. Quisque rutrum faucibus hendrerit. Nulla tempus ante at felis mattis eleifend. Quisque velit lacus, interdum a pharetra sed, vulputate venenatis tortor. Curabitur velit elit, mollis elementum dapibus et, accumsan vitae orci. In semper a quam sed volutpat. Phasellus sit amet lorem vitae erat aliquam ultrices et sit amet arcu. Curabitur sit amet ante id justo fermentum suscipit a vitae diam. Sed ipsum lectus, sollicitudin quis turpis vitae, venenatis pellentesque nulla. Aliquam sit amet augue sed sapien suscipit eleifend vel vitae nunc. Phasellus sed malesuada dolor, nec consequat libero.\n" +
            "\n" +
            "Proin tincidunt tortor vitae mi sollicitudin, ut tristique dolor pellentesque. Nunc tellus velit, congue ac hendrerit quis, pretium at orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc euismod convallis magna non dictum. Sed faucibus et arcu et semper. Sed orci sapien, suscipit id finibus quis, maximus vitae sem. Phasellus consectetur orci et est lobortis, at consequat ipsum faucibus. Vestibulum tempus, odio et luctus sollicitudin, arcu turpis consectetur ante, dictum blandit arcu est sed lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porttitor dolor neque, vitae gravida eros sagittis eget. Aliquam ante augue, ultrices vel varius non, sodales nec purus. Aliquam erat volutpat. Morbi molestie nibh lectus, ac vehicula odio interdum ut.";
    public static final String TITLE_TAG = "Entry title", CONTENT_TAG = "Entry content";
    public static final int SUMMARY_LENGTH = 160;
    private static final long NEW_ENTRY_DATE_TAG = 0;

    @Ignore
    public JournalEntry(String title, String content) {
        setContent(content);
        setTitle(title);
        setDateCreated(NEW_ENTRY_DATE_TAG);
        setDateLastModified(NEW_ENTRY_DATE_TAG);
    }

    public JournalEntry(String title, String content, long dateCreated, long dateLastModified) {
        setContent(content);
        setTitle(title);
        setDateCreated(dateCreated);
        setDateLastModified(dateLastModified);
    }

    public String getContent() {
        return mContent;
    }


    public String getContentSummary() {
        if (mContent.length() > SUMMARY_LENGTH) return mContent.substring(0, SUMMARY_LENGTH - 1);
        else return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getDateCreated() {
        return mDateCreated;

    }

    public long getDateLastModified() {
        return mDateLastModified;
    }

    // Todo: properly implement Date and time Formatting
    public String getDateCreatedString() {
        long timeDifference = System.currentTimeMillis() - mDateCreated;
        if (timeDifference < 1) return "Just now";
        else {
            DateFormat formatter = SimpleDateFormat.getDateTimeInstance();
            return formatter.format(mDateCreated);
        }
    }



    public String getDateLastModifiedString() {
        long timeDifference = System.currentTimeMillis() - mDateLastModified;
        if (timeDifference < 1) return "Just now";
        else {
            DateFormat formatter = SimpleDateFormat.getDateInstance();
            return formatter.format(mDateLastModified);
        }
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
        // Set the last modified date to current time
        setDateLastModified(NEW_ENTRY_DATE_TAG);
    }

    public void setDateCreated(long dateCreated) {
        if (dateCreated == NEW_ENTRY_DATE_TAG) {
            Date currentDate = new Date();
            this.mDateCreated = currentDate.getTime();
        } else this.mDateCreated = dateCreated;
    }


    public void setDateLastModified(long dateLastModified) {
        if (dateLastModified == NEW_ENTRY_DATE_TAG) {
            Date currentDate = new Date();
            this.mDateLastModified = currentDate.getTime();
        } else this.mDateCreated = dateLastModified;
    }

    public void setTitle(String title) {
        if (title == null | title.isEmpty()) this.mTitle = "Untitled";
        else this.mTitle = title;
        // Set the last modified date to current time
        setDateLastModified(NEW_ENTRY_DATE_TAG);
    }
}
