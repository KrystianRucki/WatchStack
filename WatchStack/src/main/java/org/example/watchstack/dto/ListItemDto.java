package org.example.watchstack.dto;

public class ListItemDto {
    private Long id;
    private Long customListId;
    private Long movieId;
    private Integer position;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomListId() { return customListId; }
    public void setCustomListId(Long customListId) { this.customListId = customListId; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
}
