package de.denis.mes.exception;

import de.denis.mes.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ValidationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(
            ConstraintViolationException exception) {

        List<String> errors = exception
                .getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .distinct()
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(
                "Validierung fehlgeschlagen.",
                errors
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorResponse)
                .build();
    }
}
