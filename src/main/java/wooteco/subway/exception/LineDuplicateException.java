package wooteco.subway.exception;

public class LineDuplicateException extends RuntimeException {

    private static final String DUPLICATED_MESSAGE = "이미 존재하는 노선입니다.";

    public LineDuplicateException(final String inputtedData) {
        super(DUPLICATED_MESSAGE + " " + inputtedData);
    }
}
