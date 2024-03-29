package ru.wheretoeat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.wheretoeat.AuthorizedUser;
import ru.wheretoeat.exceptions.ApplicationException;
import ru.wheretoeat.exceptions.validation.ErrorType;
import ru.wheretoeat.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSourceAccessor messageSourceAccessor;

    public GlobalExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest req, NoHandlerFoundException e) throws Exception {

        return logAndGetExceptionView(req, e, false, ErrorType.WRONG_REQUEST, null);
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) throws Exception {
        return logAndGetExceptionView(req, appEx, true, appEx.getType(),
                messageSourceAccessor.getMessage(appEx.getMsgCode(), appEx.getArgs()));
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("Exception at request " + req.getRequestURL(), e);
        return logAndGetExceptionView(req, e, true, ErrorType.APP_ERROR, null);
    }

    private ModelAndView logAndGetExceptionView(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String msg) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, req, e, logException, errorType);

        HttpStatus httpStatus = errorType.getStatus();

        ModelAndView mav = new ModelAndView("exception",
                Map.of("exception", rootCause, "message", msg != null ? msg : ValidationUtil.getMessage(rootCause),
                        "typeMessage", messageSourceAccessor.getMessage(errorType.getErrorCode()),
                        "status", httpStatus));
        mav.setStatus(httpStatus);

        return mav;
    }
}
