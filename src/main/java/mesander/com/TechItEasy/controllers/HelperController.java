package mesander.com.TechItEasy.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class HelperController {
    public static String handleBindingResultError(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();

        for (FieldError error: bindingResult.getFieldErrors()) {
            sb.append(error.getField());
            sb.append(" : ");
            sb.append(error.getDefaultMessage());
            sb.append("\n");
        }

        return sb.toString();
    }
}
