package com.dtdev.aparat.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dtdev.aparat.models.Video;

import java.util.List;

@Dao
public interface IDAO {

    @Insert
    long insert(Video video);

    /*@Update
    long update(Video video);
*/
    @Delete
    void delete(Video video);

    @Query("select * from video")
    List<Video> getAllVideos();

    @Query("select * from video where title=:name")
    List<Video> searchVideos(String name);

    @Query("delete from video where id=:id")
    void deleteVideo(String id);



}
