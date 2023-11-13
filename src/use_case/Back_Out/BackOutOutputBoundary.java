package use_case.Back_Out;

public interface BackOutOutputBoundary {
    void prepareSuccessView(BackOutOutputData user);

    void prepareFailView(String error);
}
