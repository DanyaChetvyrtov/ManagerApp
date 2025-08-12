package ru.ex.managerms.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ex.managerms.exceptions.custom.ProductNotFound;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ProductNotFound.class)
    public String handleProductNotFound(
            ProductNotFound ex, Model model,
            HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(ex.getMessage(), new Object[0],
                        ex.getMessage(), locale));
        return "errors/404";

    }
}
