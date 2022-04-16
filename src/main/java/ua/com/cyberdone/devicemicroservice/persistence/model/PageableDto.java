package ua.com.cyberdone.devicemicroservice.persistence.model;

import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

import java.util.List;

@SuperBuilder
public class PageableDto<T> {
    public Integer page;
    public Integer elementsOnThePage;
    public Integer totallyPages;
    public Long totallyElements;
    public Sort sort;
    public List<T> content;
}
