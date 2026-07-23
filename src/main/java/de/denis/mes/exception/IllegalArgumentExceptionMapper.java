package de.denis.mes.exception;

import de.denis.mes.dto.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class IllegalArgumentExceptionMapper
        implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(
            IllegalArgumentException exception
    ) {

        ErrorResponse errorResponse = new ErrorResponse(
                "Ungueltige Anfrage.",
                List.of(exception.getMessage())
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorResponse)
                .build();
    }
}
