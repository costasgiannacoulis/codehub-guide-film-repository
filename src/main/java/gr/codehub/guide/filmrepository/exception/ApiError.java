package gr.codehub.guide.filmrepository.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

/**
 * Our custom class to communicate back to the calling side exceptions in a unified way.
 */
@Value
@RequiredArgsConstructor
@ToString
public class ApiError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final Integer status;
	private final String message;
	private final String path;
}
