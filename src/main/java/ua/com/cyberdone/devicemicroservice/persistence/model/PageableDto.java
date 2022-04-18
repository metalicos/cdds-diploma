package ua.com.cyberdone.devicemicroservice.persistence.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
public class PageableDto<T> {
    public Integer page;
    public Integer elementsOnThePage;
    public Integer totallyPages;
    public Long totallyElements;
    public List<T> content;
}
