package com.pterapan.uts2072038.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "watchlist", schema = "utssa", catalog = "")
public class WatchlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idWatchList")
    private int idWatchList;
    @Basic
    @Column(name = "LastWatch")
    private Integer lastWatch;
    @Basic
    @Column(name = "Favorite")
    private Byte favorite;
    @ManyToOne
    @JoinColumn(name = "Movie_idMovie", updatable = false, insertable = false, referencedColumnName = "idMovie", nullable = false)
    private MovieEntity movieByMovieIdMovie;
    @ManyToOne
    @JoinColumn(name = "User_idUser", updatable = false, insertable = false, referencedColumnName = "idUser", nullable = false)
    private UserEntity userByUserIdUser;
    @Basic
    @Column(name = "Movie_idMovie")
    private int movieIdMovie;
    @Basic
    @Column(name = "User_idUser")
    private int userIdUser;

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public Integer getLastWatch() {
        return lastWatch;
    }

    public void setLastWatch(Integer lastWatch) {
        this.lastWatch = lastWatch;
    }

    public Byte getFavorite() {
        return favorite;
    }

    public void setFavorite(Byte favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistEntity that = (WatchlistEntity) o;
        return idWatchList == that.idWatchList && Objects.equals(lastWatch, that.lastWatch) && Objects.equals(favorite, that.favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWatchList, lastWatch, favorite);
    }

    public MovieEntity getMovieByMovieIdMovie() {
        return movieByMovieIdMovie;
    }

    public void setMovieByMovieIdMovie(MovieEntity movieByMovieIdMovie) {
        this.movieByMovieIdMovie = movieByMovieIdMovie;
    }

    public UserEntity getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(UserEntity userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    public int getMovieIdMovie() {
        return movieIdMovie;
    }

    public void setMovieIdMovie(int movieIdMovie) {
        this.movieIdMovie = movieIdMovie;
    }

    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }
}
