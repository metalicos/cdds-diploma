package ua.com.cyberdone.devicemicroservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonPropertyOrder({"timestamp", "error", "title", "detail"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestError implements Serializable {
    @Serial
    private static final long serialVersionUID = 8586637223L;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @JsonProperty("error")
    private final String error;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("detail")
    private final String detail;

    public RestError(String error, String title, String detail) {
        this.error = error;
        this.title = title;
        this.detail = detail;
    }
}
