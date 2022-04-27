package ru.kosmos.restaurantvoting.error;

import lombok.Getter;

@Getter
public class ErrorInfo {

    private final String url;
    private final ErrorType type;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.details = details;
    }

}
