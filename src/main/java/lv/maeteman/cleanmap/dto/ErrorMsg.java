package lv.maeteman.cleanmap.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorMsg {
    private HttpStatus code;
    private String message;
    private LocalDateTime dateTime;

    public ErrorMsg(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorMsg)) return false;
        ErrorMsg errorMsg = (ErrorMsg) o;
        return code == errorMsg.code &&
                Objects.equals(message, errorMsg.message) &&
                Objects.equals(dateTime, errorMsg.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, dateTime);
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
