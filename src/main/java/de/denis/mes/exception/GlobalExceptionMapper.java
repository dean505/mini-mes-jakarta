package de.denis.mes.exception;

import de.denis.mes.dto.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class GlobalExceptionMapper
        implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorResponse(
                        "Interner Serverfehler.",
                        List.of(exception.getMessage())
                ))
                .build();
    }
}
