package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.LikeComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCommentsRepository extends JpaRepository<LikeComments,Integer> {
    @Query(value="select count(*) from like_comments l where l.com_id= :com_id and l.user_id= :user_id and react_comments= 'like' and is_liked=1",nativeQuery=true)
    int LikesComments(@Param("com_id") Long com_id, @Param("user_id") Long user_id);
    @Query(value="select count(*) from like_comments l where l.com_id= :com_id and l.user_id= :user_id and react_comments = 'love' and is_liked=1",nativeQuery=true)
    int LoveComments(@Param("com_id") Long com_id,@Param("user_id") Long user_id);
    @Query(value="select count(*) from like_comments l where l.com_id= :com_id and l.user_id= :user_id and react_comments = 'haha' and is_liked=1",nativeQuery=true)
    int HahaComments(@Param("com_id") Long com_id,@Param("user_id") Long user_id);
    @Query(value="select count(*) from like_comments l where l.com_id= :com_id and l.user_id= :user_id and react_comments = 'sad' and is_liked=1",nativeQuery=true)
    int SadComments(@Param("com_id") Long com_id,@Param("user_id") Long user_id);
    @Query(value="select count(*) from like_comments l where l.com_id= :com_id and l.user_id= :user_id and react_comments = 'angry' and is_liked=1",nativeQuery=true)
    int AngryComments(@Param("com_id") Long com_id,@Param("user_id") Long user_id);
}
