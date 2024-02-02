package org.leftbrained.roombinding.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("delete from user")
    void deleteAll();
}
